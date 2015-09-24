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
import java.util.Iterator;
import psp.sel.Event;

public class ChainEvents implements Iterable<ChainEvent>
{
    private ArrayList<ChainEvent> fTis;

    public ArrayList<ChainEvent> getTis()
    {
        return fTis;
    }

    public int size()
    {
        return fTis.size();
    }

    public ChainEvent getTi( int aIndex )
    {
        return fTis.get( aIndex );
    }
    
    public Iterator<ChainEvent> iterator()
    {
        return fTis.iterator();
    }

    public ChainEvents()
    {
        fTis = new ArrayList<ChainEvent>();
        fTis.add( new ChainEvent() );
    }

    public ChainEvents( ArrayList<ChainEvent> aTis )
    {
        fTis = new ArrayList<ChainEvent>( aTis );
    }
    
    public ArrayList<Event> getEvents()
    {
        ArrayList<Event> Result = new ArrayList<Event>();
    
        for ( ChainEvent ce : fTis )
        {
            for ( Event e : ce.getEvents() )
            {
                if ( !Result.contains( e ) )
                    Result.add( e );
            }
        }

        return Result;   
    }
    
    public String getSpecificationAsSEL( String aConnector ) 
    {
        StringBuilder sb = new StringBuilder();
    
        for ( ChainEvent ce : fTis )
        {
            sb.append( aConnector );
            sb.append( ce.getSpecificationAsSEL() );
        }
        
        return sb.toString();
    }    
}
