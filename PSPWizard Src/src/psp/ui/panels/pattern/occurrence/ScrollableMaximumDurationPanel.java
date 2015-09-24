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

package psp.ui.panels.pattern.occurrence;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import psp.engine.PSPController;
import psp.sel.patterns.Pattern;
import psp.sel.patterns.order.ChainEvents;
import psp.ui.panels.pattern.PatternPanelFeatures;
import psp.ui.panels.pattern.order.ChainChanged;

public class ScrollableMaximumDurationPanel extends javax.swing.JPanel implements PatternPanelFeatures, ChainChanged
{
    private MaximumDurationPanel fPatternPanel;

    public ScrollableMaximumDurationPanel()
    {
        fPatternPanel = new MaximumDurationPanel();

        // For scrollpane to work there must be some extra space at the vertical end.
        // Otherwise, the panel is just resizing vvertically.
        // The PrecedenceChain1NPanel is 555x266. Setting the host panel to 556x360
        // has the desired effect. Also, no horizontal scrollbar is required.
        Dimension lD = fPatternPanel.getPreferredSize();
        lD.height = 360;
        lD.width = 556;
        setPreferredSize( lD );
        
        setLayout( new BorderLayout( 0, 0 ) );
        
        JScrollPane lPane = new JScrollPane( fPatternPanel );
        lPane.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );

        add( lPane, BorderLayout.CENTER );
    }
            
    public Pattern getSelectedPattern() 
    {
        return fPatternPanel.getSelectedPattern();
    }

    public void clearSelection()
    {
        fPatternPanel.clearSelection();
    }

    public void setSelectedPattern( Pattern aSelectedPattern ) 
    {
        fPatternPanel.setSelectedPattern( aSelectedPattern );
    }

    public void setController( PSPController aPSPController ) 
    {
        fPatternPanel.setController( aPSPController );
    }

    public void updateEvents() 
    {
        fPatternPanel.updateEvents();
    }

    public void updateChain( ChainEvents aEventChain )
    {
        // empty
    }    
}
