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

package psp.mappings;

import psp.constraints.Interval;
import psp.constraints.LowerTimeBound;
import psp.constraints.ProbabilityBound;
import psp.constraints.TimeBound;
import psp.constraints.UpperTimeBound;
import psp.engine.PSPConstants;
import psp.sel.Event;
import psp.sel.patterns.order.ChainEvent;
import psp.sel.patterns.order.ChainEvents;

public abstract class PrismSupport extends GenericMapper
{
    private static final String lNot = "!";

    public String cnt( Event aZP )
    {
        if ( aZP != null )
            return lNot + aZP.getAsEvent();
        else
            return "true";
    }
    
    public String time( TimeBound aPTimeBound )
    { 
        StringBuilder sb = new StringBuilder();
      
        if ( aPTimeBound != null )
        {
            String lTLP = tL( aPTimeBound );
            String lTUP = tU( aPTimeBound );

            if ( lTLP == null && lTUP == null )
            {
                // no time bound
            }
            else
            {
                if ( lTLP == null )
                {
                    // no lower time bound
                    sb.append( "<=" );
                    sb.append( lTUP );
                }
                else
                {
                    if ( lTUP == null )
                    {
                        // no upper time bound (infinity
                        sb.append( ">=" );
                        sb.append( lTLP );
                    }
                    else
                    {
                        // interval
                        sb.append( "[" );
                        sb.append( lTLP );
                        sb.append( "," );
                        sb.append( lTUP );
                        sb.append( "]" );
                    }
                }
            }
        }
        
        return sb.toString(); 
    }
    
    public String lmintime( TimeBound aPTimeBound )
    { 
        StringBuilder sb = new StringBuilder();
        
        if ( aPTimeBound != null )
        {
            String lTLP = tL( aPTimeBound );

            if ( lTLP != null )
            {
                sb.append( ">=" );
                sb.append( lTLP );
            }
        }

        return sb.toString(); 
    }
    
    public String umintime( TimeBound aPTimeBound )
    { 
        StringBuilder sb = new StringBuilder();
        
        if ( aPTimeBound != null )
        {
            String lTLP = tL( aPTimeBound );

            if ( lTLP != null )
            {
                sb.append( "<=" );
                sb.append( lTLP );
            }
        }
        
        return sb.toString(); 
    }
    
    public String utb( TimeBound aPTimeBound )
    { 
        StringBuilder sb = new StringBuilder();
        
        if ( aPTimeBound != null )
        {
            String lTUP = tU( aPTimeBound );

            if ( lTUP == null )
                markError();     // inf makes no sense
            
            sb.append( "<=" );
            sb.append( lTUP );
        }
        
        return sb.toString(); 
    }
    
    public String trigger( Interval aPTimeBound )
    {
        String Result = "";

        if ( aPTimeBound != null )
        {
            long lUP = aPTimeBound.getUpperLimit();
                
            if ( lUP == Long.MAX_VALUE )
            {
                markError();     // inf? makes no sense
            }
            Result = String.format( "=%d", lUP );
        }
       
        return Result; 
    }
    
    public String gap( Interval aPTimeBound )
    { 
        String Result = "";

        if ( aPTimeBound != null )
        {
            long lUP = aPTimeBound.getUpperLimit();
            long lLP = aPTimeBound.getLowerLimit();
        
            if ( lUP == Long.MAX_VALUE )
                Result = String.format( "<=%d", lUP ); // inf - x = inf
            else
            {
                long lDelta = lUP - lLP;
                
                if ( lDelta < 0 )
                    markError();       // negative gap

                Result = String.format( "<=%d", lDelta );
            }
        }
       
        return Result; 
    }
    
    public String elapsed( Interval aPTimeBound )
    { 
        String Result = "";

        if ( aPTimeBound != null )
        {
            long lUP = ((Interval)aPTimeBound).getUpperLimit();
                
            if ( lUP == Long.MAX_VALUE )
                Result = String.format( "<=%d", lUP ); // inf
            else                
                Result = String.format( "<=%d", lUP );
        }
       
        return Result; 
    }
    
    public String maxgap( Interval aPTimeBound )
    { 
        String Result = "";

        if ( aPTimeBound != null )
        {
            long lUP = ((Interval)aPTimeBound).getUpperLimit();
            long lLP = ((Interval)aPTimeBound).getLowerLimit();
        
            if ( lUP == Long.MAX_VALUE )
            {
                markError();
                Result = String.format( "<=%d", lUP ); // inf - x = inf
            }
            else
            {
                long lDelta = lUP - lLP;
                    
                if ( lDelta < 0 )
                    markError();            // negative gap?

                Result = String.format( "<=%d", lDelta );
            }
        }
       
        return Result; 
    }

    // Tis can only have an upper time bound (precedence chains)
    private long sumTis( ChainEvents Tis, int n )
    {
        // aggregate Tis
        long lTUTis = 0;
        int count = 0;

        for ( int i = 0; i < n; i++ )
        {
            ChainEvent Ti = Tis.getTi( i );
            TimeBound tbTi = Ti.getTimeBound();

            if ( tbTi != null )
            {
                count++;    // count components, must reach n

                if ( lTUTis != Long.MAX_VALUE )
                {
                    long lTUTi = ((UpperTimeBound)tbTi).getUpperLimit();

                    if ( lTUTi != Long.MAX_VALUE )
                    {
                        long lNewTUTis = lTUTis + lTUTi;    // could lead to overflow
                        
                       if ( lNewTUTis > lTUTis )
                            lTUTis = lNewTUTis;
                        else
                            lTUTis = Long.MAX_VALUE;        // max value reached
                    }
                    else
                        lTUTis = Long.MAX_VALUE;            // max value reached
                }
            }
        }

        if ( count == n )
            return lTUTis;
        else
            return -1;      // signal incomplete time annotations
    }
    
    public String gapNP( int n, ChainEvents Tis, Interval aPTimeBound )
    { 
        String Result = "";
            
        long lTUTis = sumTis( Tis, n );

        if ( aPTimeBound != null && lTUTis >= 0 )
        {
            long lTUP = aPTimeBound.getUpperLimit();
        
            if ( lTUP != Long.MAX_VALUE )
            {
                if ( lTUTis == Long.MAX_VALUE )
                    lTUP = Long.MAX_VALUE;
                else
                {
                    long lNewTUP = lTUP + lTUTis;    // could lead to overflow
                        
                    if ( lNewTUP > lTUP )
                        lTUP = lNewTUP;
                    else
                        lTUP = Long.MAX_VALUE;        // max value reached                
                }
            }

            if ( lTUP == Long.MAX_VALUE )
                Result = String.format( "<=%d", lTUP );
            else
                Result = String.format( "<=%d", lTUP ); 
        }
        
        return Result;      // "" if some time component is missing
    }
    
    public String gapPN( int n, ChainEvents Tis, Interval aPTimeBound )
    { 
        String Result = "";
            
        long lTUTis = sumTis( Tis, n );

        if ( aPTimeBound != null && lTUTis >= 0 )
        {
            long lTUP = aPTimeBound.getUpperLimit() - aPTimeBound.getLowerLimit();
            
            if ( lTUP < 0 )
                markError();
        
            if ( lTUP != 0 )
            {
                if ( lTUTis == Long.MAX_VALUE )
                    markError();
                else
                {
                    lTUP -= lTUTis;    // could lead to overflow
                        
                    if ( lTUP < 0 )
                        markError();
                }
            }

            Result = String.format( "[0,%d]", lTUP ); 
        }
        
        return Result;      // "" if some time component is missing
    }
    
    public String tL( TimeBound aPTimeBound )
    {
        long lLValue = 0;
        
        if ( aPTimeBound != null )
        {
            switch ( aPTimeBound.getType() )
            {
                case PSPConstants.CT_Lower:
                    lLValue = ((LowerTimeBound)aPTimeBound).getLowerLimit();
                    break;
                case PSPConstants.CT_Interval:
                    lLValue = ((Interval)aPTimeBound).getLowerLimit();
                    break;
            }

            return String.format( "%d", lLValue );
        }

        return null;    // no lower time bound
    }
    
    public String tU( TimeBound aPTimeBound )
    { 
        if ( aPTimeBound != null )
        {
            long lUValue = Long.MAX_VALUE;
            
            switch ( aPTimeBound.getType() )
            {
                case PSPConstants.CT_Upper:
                    lUValue = ((UpperTimeBound)aPTimeBound).getUpperLimit();
                    break;
                case PSPConstants.CT_Interval:
                    lUValue = ((Interval)aPTimeBound).getUpperLimit();
                    break;
            }
            
            if ( lUValue != Long.MAX_VALUE )
                return String.format( "%d", lUValue );
        }

        return null;    // no upper time bound 
    }

    private boolean fSupportQuantitative;
    
    public PrismSupport( boolean aSupportQuantitative )
    {
        fSupportQuantitative = aSupportQuantitative;
    }
    
    public String prop( ProbabilityBound aPropBound )
    { 
        StringBuilder sb = new StringBuilder();
 
        if ( aPropBound != null )
        {
            sb.append( "P" );
            
            switch ( aPropBound.getType() )
            {
                case PSPConstants.CP_Lower:
                    sb.append( "<" );
                    break;
                case PSPConstants.CP_LowerEqual:
                    sb.append( "<=" );
                    break;
                case PSPConstants.CP_Greater:
                    sb.append( ">" );
                    break;
                case PSPConstants.CP_GreaterEqual:
                    sb.append( ">=" );
                    break;
            }
        
            sb.append( String.format( "%.4f ", aPropBound.getProbability() ) );
        }
        else
        {
            if ( fSupportQuantitative )
            {
                sb.append( "P=? " );
            }
        }
        
        return sb.toString();
    }

    public String propS( ProbabilityBound aPropBound )
    {
        StringBuilder sb = new StringBuilder();
 
        if ( aPropBound != null )
        {
            sb.append( "S" );
            
            switch ( aPropBound.getType() )
            {
                case PSPConstants.CP_Lower:
                    sb.append( "<" );
                    break;
                case PSPConstants.CP_LowerEqual:
                    sb.append( "<=" );
                    break;
                case PSPConstants.CP_Greater:
                    sb.append( ">" );
                    break;
                case PSPConstants.CP_GreaterEqual:
                    sb.append( ">=" );
                    break;
            }
        
            sb.append( String.format( "%.4f ", aPropBound.getProbability() ) );
        }
        else
        {
            if ( fSupportQuantitative )
            {
                sb.append( "S=? " );
            }
        }
        
        return sb.toString();
    }
    
    public String getNotSupportedMessage()
    {
        return "Mapping not supported for Prism Property Specification.";
    }

    public String toString()
    {
        return "Prism";
    }
}
