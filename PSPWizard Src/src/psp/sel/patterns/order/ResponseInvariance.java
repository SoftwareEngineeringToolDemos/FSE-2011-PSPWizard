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

import psp.constraints.ProbabilityBound;
import psp.constraints.TimeBound;
import psp.engine.PSPConstants;
import psp.sel.Event;
import psp.sel.patterns.Order;

public class ResponseInvariance extends Order
{
    private TimeBound fSTimeBound;

    public TimeBound getSTimeBound()
    {
        return fSTimeBound;
    }

    public void setSTimeBound( TimeBound aSTimeBound )
    {
        fSTimeBound = aSTimeBound;
    }
    
    public ResponseInvariance()
    {
        this( Event.getDefault(), Event.getDefault(), null, null );
    }

    public ResponseInvariance( Event aEventP, Event aEventS, TimeBound aSTimeBound, ProbabilityBound aProbBound )
    {
        super( aEventP, aEventS, null, aProbBound );

        fSTimeBound = aSTimeBound;
    }

    public int getType() 
    {
        return PSPConstants.P_ResponseInvariance;
    }

    public String getSpecificationAsSEL() 
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append( "if " );
        sb.append( getP().getAsSELEvent() );
        sb.append( " [has occurred]" );

        sb.append( " then in response " );
        sb.append( getS().getAsSELEvent() );
        sb.append( " [holds]" );
        sb.append( " continually" );

        if ( fSTimeBound != null )
        {
            sb.append( " " );
            sb.append( fSTimeBound.getSpecificationAsSEL() );
        }

        if ( getProbabilityBound() != null )
        {
            sb.append( " " );
            sb.append( getProbabilityBound().getSpecificationAsSEL() );
        }
        
        return sb.toString();
    }    
}
