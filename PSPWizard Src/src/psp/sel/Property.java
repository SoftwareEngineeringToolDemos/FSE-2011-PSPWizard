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

package psp.sel;

import java.util.ArrayList;
import psp.sel.patterns.Pattern;
import psp.sel.scopes.Globally;
import psp.sel.scopes.Scope;

public class Property implements Comparable<Property>
{
    private String fDescriptor;
    private Scope fScope;
    private Pattern fPattern;
    private ArrayList<Event> fEvents;
    
    public String getDescriptor()
    {
        return fDescriptor;
    }

    public Scope getScope()
    {
        return fScope;
    }

    public void setScope( Scope aScope )
    {
        fScope = aScope;
    }

    public Pattern getPattern()
    {
        return fPattern;
    }

    public void setPattern( Pattern aPattern )
    {
        fPattern = aPattern;
        
        // TBA: update event usages
        
    }
    
    public ArrayList<Event> getEvents()
    {
        return fEvents;
    }
    
    public Property( String aDescriptor )
    {
        fDescriptor = aDescriptor;
        fScope = new Globally();
        fPattern = null;
        fEvents = new ArrayList<Event>();
    }
    
    public String getSpecification()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append( fScope.getSpecificationAsSEL() );
        sb.append( ", " );
        if ( fPattern != null )
            sb.append( fPattern.getSpecificationAsSEL() );
        sb.append( "." );
        
        return sb.toString();
    }

    public int compareTo( Property aOtherProperty ) 
    {
        return fDescriptor.compareTo( aOtherProperty.fDescriptor );
    }

    public int hashCode() 
    {
        return fDescriptor.hashCode();
    }
    
    public boolean equals( Object aOtherProperty )
    {
        if ( aOtherProperty instanceof Property )
        {
            return ((Property)aOtherProperty).fDescriptor.equals( fDescriptor );
        }
        
        return false;
    }

    public String toString()
    {
        // used in ui
        return fDescriptor;
    }
}
