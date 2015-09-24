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

package psp.sel.patterns.order;

import java.util.ArrayList;
import psp.constraints.EventConstraint;
import psp.constraints.TimeBound;
import psp.sel.Event;

public class ChainEvent 
{
    public static final int PrecedenceChain = 1;
    public static final int ResponseChain = 2;

    private Event fEvent;
    private EventConstraint fConstraint;
    private TimeBound fTimeBound;

    public Event getEvent()
    {
        return fEvent;
    }
    
    public void setEvent( Event aEvent )
    {
        fEvent = aEvent;
    }
    
    public EventConstraint getConstraint()
    {
        return fConstraint;
    }
    
    public void setConstraint( Event aConstraintEvent )
    {
        fConstraint = null;

        if ( aConstraintEvent != null )
        {
            if ( !aConstraintEvent.isDefault() )
                fConstraint = new EventConstraint( aConstraintEvent );
        }
    }

    public ArrayList<Event> getEvents()
    {
        ArrayList<Event> Result = new ArrayList<Event>();
        
        if ( fEvent != null )
            Result.add( fEvent );

        if ( fConstraint != null )
        {
            if ( !Result.contains( fConstraint.getEvent() ) )
               Result.add( fConstraint.getEvent() );
        }

        return Result;   
    }
    
    public TimeBound getTimeBound()
    {
        return fTimeBound;
    }

    public void setTimeBound( TimeBound aTimeBound )
    {
        fTimeBound = aTimeBound;
    }

    public ChainEvent()
    {
        this( Event.getDefault(), null, null );
    }
    
    public ChainEvent( Event aEvent, Event aConstraintEvent, TimeBound aTimeBound )
    {
        setEvent( aEvent );        
        setConstraint( aConstraintEvent );
        setTimeBound( aTimeBound );
    }

    public String getSpecificationAsSEL() 
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append( fEvent.getAsSELEvent() );

        if ( fTimeBound != null )
        {
            sb.append( " " );
            sb.append( fTimeBound.getSpecificationAsSEL() );
        }

        if ( fConstraint != null )
        {
            sb.append( " " );
            sb.append( fConstraint.getSpecificationAsSEL() );
        }
        
        return sb.toString();
    }    
}
