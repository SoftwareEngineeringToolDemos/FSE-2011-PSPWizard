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

import psp.engine.PSPConstants;
import psp.engine.PSPController;
import psp.sel.patterns.Pattern;

public class PatternPanel extends javax.swing.JPanel implements PatternPanelFeatures
{
    private PSPController fPSPController;
    private Pattern fSelectedPattern;

    public PatternPanel() 
    {
        initComponents();
        
        setSelectedPattern( null );
    }

    public Pattern getSelectedPattern() 
    {
        return fSelectedPattern;
    }

    public void setSelectedPattern( Pattern aSelectedPattern ) 
    {
        fSelectedPattern = aSelectedPattern;
        
        if ( fSelectedPattern == null )
        {
            fCategories.setSelectedIndex( 0 );
            fOccurrencePattern.setSelectedIndex( 0 );
            fSelectedPattern = ((PatternPanelFeatures)fOccurrencePattern.getSelectedComponent()).getSelectedPattern();
        }
        else
        {
            PatternPanelFeatures lTargetPanel = null;

            switch ( fSelectedPattern.getPatternCategory() )
            {
                case PSPConstants.PC_Occurrence:
                    switch ( fSelectedPattern.getType() )
                    {
                        case PSPConstants.P_Universality:
                            fOccurrencePattern.setSelectedIndex( 0 );
                            break;
                        case PSPConstants.P_Absence:
                            fOccurrencePattern.setSelectedIndex( 1 );
                            break;
                        case PSPConstants.P_Existence:
                            fOccurrencePattern.setSelectedIndex( 2 );
                            break;
                        case PSPConstants.P_BoundedExistence:
                            fOccurrencePattern.setSelectedIndex( 3 );
                            break;
                        case PSPConstants.P_TransientState:
                            fOccurrencePattern.setSelectedIndex( 4 );
                            break;
                        case PSPConstants.P_SteadyState:
                            fOccurrencePattern.setSelectedIndex( 5 );
                            break;
                        case PSPConstants.P_MinimumDuration:
                            fOccurrencePattern.setSelectedIndex( 6 );
                            break;
                        case PSPConstants.P_MaximumDuration:
                            fOccurrencePattern.setSelectedIndex( 7 );
                            break;
                        case PSPConstants.P_Recurrence:
                            fOccurrencePattern.setSelectedIndex( 8 );
                            break;
                    }
                    lTargetPanel = (PatternPanelFeatures)fOccurrencePattern.getSelectedComponent();
                    break;
                case PSPConstants.PC_Order:
                    switch ( fSelectedPattern.getType() )
                    {
                        case PSPConstants.P_Precedence:
                            fOrderPattern.setSelectedIndex( 0 );
                            break;
                        case PSPConstants.P_PrecedenceChain1N:
                            fOrderPattern.setSelectedIndex( 1 );
                            break;
                        case PSPConstants.P_PrecedenceChainN1:
                            fOrderPattern.setSelectedIndex( 2 );
                            break;
                        case PSPConstants.P_Until:
                            fOrderPattern.setSelectedIndex( 3 );
                            break;
                        case PSPConstants.P_Response:
                            fOrderPattern.setSelectedIndex( 4 );
                            break;
                        case PSPConstants.P_ResponseChain1N:
                            fOrderPattern.setSelectedIndex( 5 );
                            break;
                        case PSPConstants.P_ResponseChainN1:
                            fOrderPattern.setSelectedIndex( 6 );
                            break;
                        case PSPConstants.P_ResponseInvariance:
                            fOrderPattern.setSelectedIndex( 7 );
                            break;
                    }
                    lTargetPanel = (PatternPanelFeatures)fOrderPattern.getSelectedComponent();
                    break;
            }
        
            if ( lTargetPanel != null )
                lTargetPanel.setSelectedPattern( fSelectedPattern );
        }
    }

    public void clearSelection()
    {
        // Occurrence
        fUniversality.clearSelection();
        fAbsence.clearSelection();
        fExistence.clearSelection();
        fBoundedExistence.clearSelection();
        fTransientState.clearSelection();
        fSteadyState.clearSelection();
        fMinimumDuration.clearSelection();
        fMaximumDuration.clearSelection();
        fRecurrence.clearSelection();

        // Order
        fPrecedence.clearSelection();
        fPrecedenceChain1N.clearSelection();
        fPrecedenceChainN1.clearSelection();
        fUntil.clearSelection();
        fResponse.clearSelection();
        fResponseChain1N.clearSelection();
        fResponseChainN1.clearSelection();
        fResponseInveriance.clearSelection();
        
        // set selected pattern
        setSelectedPattern( null );
    }

    public void setController( PSPController aPSPController ) 
    {
        fPSPController = aPSPController;

        // Occurrence
        fUniversality.setController( fPSPController );
        fAbsence.setController( fPSPController );
        fExistence.setController( fPSPController );
        fBoundedExistence.setController( fPSPController );
        fTransientState.setController( fPSPController );
        fSteadyState.setController( fPSPController );
        fMinimumDuration.setController( fPSPController );
        fMaximumDuration.setController( fPSPController );
        fRecurrence.setController( fPSPController );

        // Order
        fPrecedence.setController( fPSPController );
        fPrecedenceChain1N.setController( fPSPController );
        fPrecedenceChainN1.setController( fPSPController );
        fUntil.setController( fPSPController );
        fResponse.setController( fPSPController );
        fResponseChain1N.setController( fPSPController );
        fResponseChainN1.setController( fPSPController );
        fResponseInveriance.setController( fPSPController );
    }

    public void updateEvents() 
    {
        // Occurrence
        fUniversality.updateEvents();
        fAbsence.updateEvents();
        fExistence.updateEvents();
        fBoundedExistence.updateEvents();
        fTransientState.updateEvents();
        fSteadyState.updateEvents();
        fMinimumDuration.updateEvents();
        fMaximumDuration.updateEvents();
        fRecurrence.updateEvents();

        // Order
        fPrecedence.updateEvents();
        fPrecedenceChain1N.updateEvents();
        fPrecedenceChainN1.updateEvents();
        fUntil.updateEvents();
        fResponse.updateEvents();
        fResponseChain1N.updateEvents();
        fResponseChainN1.updateEvents();
        fResponseInveriance.updateEvents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fCategories = new javax.swing.JTabbedPane();
        fOccurrence = new javax.swing.JPanel();
        fOccurrencePattern = new javax.swing.JTabbedPane();
        fUniversality = new psp.ui.panels.pattern.occurrence.ScrollableUniversalityPanel();
        fAbsence = new psp.ui.panels.pattern.occurrence.ScrollableAbsencePanel();
        fExistence = new psp.ui.panels.pattern.occurrence.ScrollableExistencePanel();
        fBoundedExistence = new psp.ui.panels.pattern.occurrence.ScrollableBoundedExistencePanel();
        fTransientState = new psp.ui.panels.pattern.occurrence.ScrollableTransientStatePanel();
        fSteadyState = new psp.ui.panels.pattern.occurrence.ScrollableSteadyStatePanel();
        fMinimumDuration = new psp.ui.panels.pattern.occurrence.ScrollableMinimumDurationPanel();
        fMaximumDuration = new psp.ui.panels.pattern.occurrence.ScrollableMaximumDurationPanel();
        fRecurrence = new psp.ui.panels.pattern.occurrence.ScrollableRecurrencePanel();
        fOrder = new javax.swing.JPanel();
        fOrderPattern = new javax.swing.JTabbedPane();
        fPrecedence = new psp.ui.panels.pattern.order.ScrollablePrecedencePanel();
        fPrecedenceChain1N = new psp.ui.panels.pattern.order.ScrollablePrecedenceChain1NPanel();
        fPrecedenceChainN1 = new psp.ui.panels.pattern.order.ScrollablePrecedenceChainN1Panel();
        fUntil = new psp.ui.panels.pattern.order.ScrollableUntilPanel();
        fResponse = new psp.ui.panels.pattern.order.ScrollableResponsePanel();
        fResponseChain1N = new psp.ui.panels.pattern.order.ScrollableResponseChain1NPanel();
        fResponseChainN1 = new psp.ui.panels.pattern.order.ScrollableResponseChainN1Panel();
        fResponseInveriance = new psp.ui.panels.pattern.order.ScrollableResponseInveriancePanel();

        fCategories.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fCategoriesStateChanged(evt);
            }
        });

        fOccurrencePattern.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fOccurrencePatternStateChanged(evt);
            }
        });
        fOccurrencePattern.addTab("Universality", fUniversality);
        fOccurrencePattern.addTab("Absence", fAbsence);
        fOccurrencePattern.addTab("Existence", fExistence);
        fOccurrencePattern.addTab("Bounded Existence", fBoundedExistence);
        fOccurrencePattern.addTab("Transient State", fTransientState);
        fOccurrencePattern.addTab("Steady State", fSteadyState);
        fOccurrencePattern.addTab("Minimum Duration", fMinimumDuration);
        fOccurrencePattern.addTab("Maximum Duration", fMaximumDuration);
        fOccurrencePattern.addTab("Recurrence", fRecurrence);

        javax.swing.GroupLayout fOccurrenceLayout = new javax.swing.GroupLayout(fOccurrence);
        fOccurrence.setLayout(fOccurrenceLayout);
        fOccurrenceLayout.setHorizontalGroup(
            fOccurrenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fOccurrencePattern, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );
        fOccurrenceLayout.setVerticalGroup(
            fOccurrenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fOccurrenceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fOccurrencePattern, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addContainerGap())
        );

        fCategories.addTab("Occurrence", fOccurrence);

        fOrderPattern.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fOrderPatternStateChanged(evt);
            }
        });
        fOrderPattern.addTab("Precedence", fPrecedence);
        fOrderPattern.addTab("Precedence Chain 1N", fPrecedenceChain1N);
        fOrderPattern.addTab("Precedence Chain N1", fPrecedenceChainN1);
        fOrderPattern.addTab("Until", fUntil);
        fOrderPattern.addTab("Response", fResponse);
        fOrderPattern.addTab("Response Chain 1N", fResponseChain1N);
        fOrderPattern.addTab("Response Chain N1", fResponseChainN1);
        fOrderPattern.addTab("Response Invariance", fResponseInveriance);

        javax.swing.GroupLayout fOrderLayout = new javax.swing.GroupLayout(fOrder);
        fOrder.setLayout(fOrderLayout);
        fOrderLayout.setHorizontalGroup(
            fOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fOrderPattern, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addContainerGap())
        );
        fOrderLayout.setVerticalGroup(
            fOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fOrderPattern, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );

        fCategories.addTab("Order", fOrder);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fCategories, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fCategoriesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fCategoriesStateChanged
        // switch category

        PatternPanelFeatures lPatternPanel = null;
        
        switch ( fCategories.getSelectedIndex() )
        {
            case 0: // Occurrence
                lPatternPanel = (PatternPanelFeatures)fOccurrencePattern.getSelectedComponent();
                break;
            case 1: // Order
                lPatternPanel = (PatternPanelFeatures)fOrderPattern.getSelectedComponent();
                break;                
        }
        
        if ( lPatternPanel != null )
        {
            fSelectedPattern = lPatternPanel.getSelectedPattern();
    
            if ( fPSPController != null )
                fPSPController.updatePattern();
        }
    }//GEN-LAST:event_fCategoriesStateChanged

    private void fOccurrencePatternStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fOccurrencePatternStateChanged
        // switch occurrence pattern

        PatternPanelFeatures lPatternPanel = (PatternPanelFeatures)fOccurrencePattern.getSelectedComponent();
        
        if ( lPatternPanel != null )
        {
            fSelectedPattern = lPatternPanel.getSelectedPattern();
    
            if ( fPSPController != null )
                fPSPController.updatePattern();
        }
    }//GEN-LAST:event_fOccurrencePatternStateChanged

    private void fOrderPatternStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fOrderPatternStateChanged
        // switch order pattern

        PatternPanelFeatures lPatternPanel = (PatternPanelFeatures)fOrderPattern.getSelectedComponent();
        
        if ( lPatternPanel != null )
        {
            fSelectedPattern = lPatternPanel.getSelectedPattern();
    
            if ( fPSPController != null )
                fPSPController.updatePattern();
        }
    }//GEN-LAST:event_fOrderPatternStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private psp.ui.panels.pattern.occurrence.ScrollableAbsencePanel fAbsence;
    private psp.ui.panels.pattern.occurrence.ScrollableBoundedExistencePanel fBoundedExistence;
    private javax.swing.JTabbedPane fCategories;
    private psp.ui.panels.pattern.occurrence.ScrollableExistencePanel fExistence;
    private psp.ui.panels.pattern.occurrence.ScrollableMaximumDurationPanel fMaximumDuration;
    private psp.ui.panels.pattern.occurrence.ScrollableMinimumDurationPanel fMinimumDuration;
    private javax.swing.JPanel fOccurrence;
    private javax.swing.JTabbedPane fOccurrencePattern;
    private javax.swing.JPanel fOrder;
    private javax.swing.JTabbedPane fOrderPattern;
    private psp.ui.panels.pattern.order.ScrollablePrecedencePanel fPrecedence;
    private psp.ui.panels.pattern.order.ScrollablePrecedenceChain1NPanel fPrecedenceChain1N;
    private psp.ui.panels.pattern.order.ScrollablePrecedenceChainN1Panel fPrecedenceChainN1;
    private psp.ui.panels.pattern.occurrence.ScrollableRecurrencePanel fRecurrence;
    private psp.ui.panels.pattern.order.ScrollableResponsePanel fResponse;
    private psp.ui.panels.pattern.order.ScrollableResponseChain1NPanel fResponseChain1N;
    private psp.ui.panels.pattern.order.ScrollableResponseChainN1Panel fResponseChainN1;
    private psp.ui.panels.pattern.order.ScrollableResponseInveriancePanel fResponseInveriance;
    private psp.ui.panels.pattern.occurrence.ScrollableSteadyStatePanel fSteadyState;
    private psp.ui.panels.pattern.occurrence.ScrollableTransientStatePanel fTransientState;
    private psp.ui.panels.pattern.occurrence.ScrollableUniversalityPanel fUniversality;
    private psp.ui.panels.pattern.order.ScrollableUntilPanel fUntil;
    // End of variables declaration//GEN-END:variables
}
