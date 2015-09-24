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

public class PSPConstants 
{
    // Scopes
    public static final int S_Globally = 1;
    public static final int S_BeforeR = 2;
    public static final int S_AfterQ = 3;
    public static final int S_BetweenQandR = 4;
    public static final int S_AfterQuntilR = 5;
    
    // Patterns
    
    // Pattern Class
    public static final int PC_Occurrence = 1;
    public static final int PC_Order = 2;

    // Occurrence
    public static final int P_Universality = 1;
    public static final int P_Absence = 2;
    public static final int P_Existence = 3;
    public static final int P_BoundedExistence = 4;
    public static final int P_TransientState = 5;
    public static final int P_SteadyState = 6;
    public static final int P_MinimumDuration = 7;
    public static final int P_MaximumDuration = 8;
    public static final int P_Recurrence = 9;

    public static final int P_LastOccurrence = P_Recurrence;
    
    // Order
    public static final int P_Precedence = P_LastOccurrence + 1;
    public static final int P_PrecedenceChain1N = 11;
    public static final int P_PrecedenceChainN1 = 12;
    public static final int P_Until = 13;
    public static final int P_Response = 14;
    public static final int P_ResponseChain1N = 15;
    public static final int P_ResponseChainN1 = 16;
    public static final int P_ResponseInvariance = 17;

    public final int P_LastOrder = P_ResponseInvariance;

    // Constraints
    public static final int C_Event = 1;
    public static final int C_Time = 2;
    public static final int C_Probability = 3;

    // Time
    public static final int CT_Upper = 1;
    public static final int CT_Lower = 2;
    public static final int CT_Interval = 3;

    // Probability
    public static final int CP_Lower = 1;
    public static final int CP_LowerEqual = 2;
    public static final int CP_Greater = 3;
    public static final int CP_GreaterEqual = 4;
}
