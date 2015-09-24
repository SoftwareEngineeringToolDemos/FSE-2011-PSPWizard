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

package psp.ui.panels.pattern;

import psp.sel.patterns.Pattern;
import psp.ui.panels.PanelFeatures;

public interface PatternPanelFeatures extends PanelFeatures
{
    // Occurrence
    public static final int PP_Occurrence         = 0x0001;

    public static final int PP_Universality       = 0x0001;
    public static final int PP_Absence            = 0x0002;
    public static final int PP_Existence          = 0x0004;
    public static final int PP_BoundedExistence   = 0x0008;
    public static final int PP_TransientState     = 0x0010;
    public static final int PP_SteadyState        = 0x0020;
    public static final int PP_MinimumDuration    = 0x0040;
    public static final int PP_MaximumDuration    = 0x0080;
    public static final int PP_Recurrence         = 0x0100;

    // Order
    public static final int PP_Order              = 0x0002;

    public static final int PP_Precedence         = 0x0001;
    public static final int PP_PrecedenceChain1N  = 0x0002;
    public static final int PP_PrecedenceChainN1  = 0x0004;
    public static final int PP_Until              = 0x0008;
    public static final int PP_Response           = 0x0010;
    public static final int PP_ResponseChain1N    = 0x0020;
    public static final int PP_ResponseChainN1    = 0x0040;
    public static final int PP_ResponseInvariance = 0x0080;

    public Pattern getSelectedPattern();

    public void setSelectedPattern( Pattern aSelectedPattern );
}
