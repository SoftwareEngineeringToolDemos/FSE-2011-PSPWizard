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

public class Event implements Comparable<Event>
{
    private String fName;
    private String fSpecification;
 
    private static Event Default = new Event( "N/A" );
    private static Event Constraint = new Event( "Constraint" );

    public static final int E_Name = 1;
    public static final int E_Spec = 2;
    public static final int E_NameAndSpec = E_Name | E_Spec;

    // can be set globally
    public static int EventStringMethod = E_Name;
    
    private static long fEventCounter = 1;
    
    public static void reset()
    {
        fEventCounter = 1;
    }
    
    public static String getFreshEventName()
    {
        return String.format( "SF%04d", fEventCounter++ );
    }
    
    public static Event getDefault()
    {
        return Default;
    }

    public static Event getConstraintDefault()
    {
        return Constraint;
    }

    public boolean isDefault()
    {
        return fName.equals( "N/A" ) || fName.equals( "Constraint" );
    }

    public String getName()
    {
        return fName;
    }
    
    public String getSpecification()
    {
        return fSpecification;
    }

    public void setSpecification( String aSpecification )
    {
        fSpecification = aSpecification;
    }

    public Event( String aName )
    {
        this( aName, aName );
    }

    public Event( String aName, String aSpecification )
    {
        fName = aName;
        fSpecification = aSpecification;
    }

    public int compareTo( Event aOtherEvent ) 
    {
        return fName.compareTo( aOtherEvent.fName );
    }

    public int hashCode() 
    {
        return fName.hashCode();
    }
    
    public boolean equals( Object aOtherEvent )
    {
        if ( aOtherEvent instanceof Event )
        {
            return ((Event)aOtherEvent).fName.equals( fName );
        }
        
        return false;
    }

    private String getEventString()
    {
        StringBuilder sb = new StringBuilder();
        
        if ( isDefault() )
        {
            sb.append( fName );
        }
        else
        {        
            switch ( EventStringMethod )
            {
                case E_Name:
                    sb.append( fName );
                    break;
                case E_Spec:
                    sb.append( fSpecification );
                    break;
                default:
                    sb.append( fName );
                    sb.append( "::" );
                    sb.append( fSpecification );
                    break;
            }
        }
        
        return sb.toString();
    }
    
    public String getAsEvent()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append( "(" );
        sb.append( getEventString() );
        sb.append( ")" );
        
        return sb.toString();
    }

    public String getAsSELEvent()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append( "{" );
        sb.append( getEventString() );
        sb.append( "}" );
        
        return sb.toString();
    }

    public String toString()
    {
        return getEventString();
    }
}
