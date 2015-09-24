/**
 *
 * Copyright (C) 2011-2014 Swinburne University of Technology
 *
 * This file is part of PSPWizard, a tool for machine-assisted 
 * definition of temporal formulae capturing pattern-based system
 * properties, developed at the Faculty of Science, Engineering and
 * Technology at Swinburne University of Technology, Australia.
 * The patterns, structured English grammar and mappings are due to
 *
 *   Marco Autili, Universita` dell'Aquila
 *   Lars Grunske, University of Stuttgart
 *   Markus Lumpe, Swinburne University of Technology
 *   Patrizio Pelliccione, University of Gothenburg
 *   Antony Tang, Swinburne University of Technology
 *
 * Details about the PSP framework can found in
 *   "Aligning Qualitative, Real-Time, and Probabilistic
 *    Property Specification Patterns Using a Structured
 *    English Grammar"
 *
 *
 * PSPWizard is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * PSPWizard is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PSPWizard; see the file COPYING.  If not, write to
 * the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */

package psp.engine;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import psp.sel.Event;
import psp.sel.patterns.Pattern;
import psp.sel.scopes.Scope;

public class EventSelectionValidator 
{
    private static Event fScopeEvents[] = new Event[2];
    
    public static void updateScopeEvents( Scope aScope ) 
    {
        fScopeEvents[0] = aScope.getQ();
        fScopeEvents[1] = aScope.getR();
    }
    
    private static boolean isEventUsedInScope( Event aEvent )
    {
        if ( aEvent != null )
        {
            for ( int i = 0; i < 2; i++ )
            {
                Event lEvent = fScopeEvents[i];

                if ( lEvent == null || lEvent.isDefault() )
                    continue;
        
                // true if already used
                if ( lEvent.equals( aEvent ) )
                    return true;
            }
        }
        
        return false;
    }

    private static boolean fIsEditUpdate = false;
    
    public static void startEditUpdate()
    {
        fIsEditUpdate = true;
    }
    
    public static void stopEditUpdate()
    {
        fIsEditUpdate = false;
    }
    
    public static boolean isScopeEventSelectionPossible( JFrame aMessageFrame, Event aEvent ) 
    {
        if ( fIsEditUpdate )
            return true;
        
        if ( isEventUsedInScope( aEvent ) || isEventUsedInPattern( aEvent ) )
        {
            JOptionPane.showMessageDialog( aMessageFrame, 
                                           String.format( "Illegal scope event \"%s\"", aEvent.toString() ), 
                                           "Error",
                                           JOptionPane.ERROR_MESSAGE );
            
            return false;
        }
        else
            return true;
    }
    
    private static ArrayList<Event> fPatternEvents = new ArrayList<Event>();

    public static void clearSelection()
    {
        fScopeEvents[0] = null;
        fScopeEvents[1] = null;
        fPatternEvents.clear();        
    }
    
    public static void updatePatternEvents( Pattern aPattern )
    {
        fPatternEvents.clear();
        
        if ( aPattern != null )
            fPatternEvents.addAll( aPattern.getEvents() );
    }
    
    private static boolean isEventUsedInPattern( Event aEvent )
    {
        if ( aEvent != null )
        {
            for ( Event pe : fPatternEvents )
            {
                if ( pe.isDefault() )
                    continue;
        
                // true if already used
                if ( pe.equals( aEvent ) )
                    return true;
            }
        }
        
        return false;
    }

    public static boolean isPatternEventSelectionPossible( JFrame aMessageFrame, Event aEvent ) 
    {
        if ( fIsEditUpdate )
            return true;

        if ( isEventUsedInScope( aEvent ) || isEventUsedInPattern( aEvent ) )
        {
            JOptionPane.showMessageDialog( aMessageFrame, 
                                           String.format( "Illegal pattern event \"%s\"", aEvent.toString() ), 
                                           "Error",
                                           JOptionPane.ERROR_MESSAGE );
            
            return false;
            
        }

        return true;
    }

    public static boolean isPatternEventSelectionPossible( JFrame aMessageFrame, Event aEvent, Event aAltEvent )
    {
        if ( aEvent != null  )
        {
            if ( aEvent.isDefault() )
                return true;
        }          
        
        return isPatternEventSelectionPossible( aMessageFrame, aEvent );
    }
}
