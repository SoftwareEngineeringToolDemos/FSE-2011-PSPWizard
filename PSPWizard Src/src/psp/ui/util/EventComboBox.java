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

package psp.ui.util;

import javax.swing.JComboBox;
import psp.engine.PSPController;
import psp.sel.Event;

public class EventComboBox extends JComboBox
{
    private Event fStableEvent;

    public boolean isStable( Event aEvent )
    {
        return aEvent.equals( fStableEvent );
    }
    
    public EventComboBox()
    {
        super();

        // add unspecified event
        fIsReverting = false;        
        addItem( Event.getDefault() );
        setSelectedIndex( 0 );
        
        // 12 X characters wide
        setPrototypeDisplayValue("XXXXXXXXXXXX");
    }

    private boolean fIsReverting;
    
    public boolean isReverting()
    {
        return fIsReverting;
    }
    
    public void clearReverting()
    {
        fIsReverting = false;
    }
    
    public void acceptSelection()
    {
        fStableEvent = (Event)getSelectedItem();
    }

    public void revertSelection()
    {
        fIsReverting = true;
        setSelectedItem( fStableEvent );
    }
    
    private PSPController fPSPController;

    public void setController( PSPController aPSPController )
    {
        fPSPController = aPSPController;
        
        updateEvents();
    }
    
    // add scope or pattern events (to distinguisj between available events)
    public void updateEvents()
    {
        if ( fPSPController != null )
        {
            // save selected events
            Event lCurrent = (Event)getSelectedItem();

            removeAllItems();

            // add unspecified event
            addItem( Event.getDefault() );

            for ( Event e : fPSPController )
                addItem( e );
        
            setSelectedItem( lCurrent );
        }
    }
}
