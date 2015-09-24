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

package psp.ui.panels.pattern.order;

import psp.constraints.EventConstraint;
import psp.constraints.ProbabilityBound;
import psp.constraints.TimeBound;
import psp.engine.PSPController;
import psp.sel.Event;
import psp.sel.patterns.Pattern;
import psp.sel.patterns.order.ChainEvents;
import psp.sel.patterns.order.PrecedenceChain1N;
import psp.ui.dialogs.IntervalTimeBoundDialog;
import psp.ui.dialogs.ProbabilityBoundDialog;
import psp.ui.dialogs.TimeBoundDialog;
import psp.ui.panels.pattern.PatternPanelFeatures;

public class PrecedenceChain1NPanel extends javax.swing.JPanel implements PatternPanelFeatures, ChainChanged
{
    private PSPController fPSPController;
    private PrecedenceChain1N fSelectedPattern;

    public Pattern getSelectedPattern()
    {
        return fSelectedPattern;
    }

    public void setSelectedPattern( Pattern aSelectedPattern ) 
    {
        if ( aSelectedPattern instanceof PrecedenceChain1N )
        {
            fSelectedPattern = (PrecedenceChain1N)aSelectedPattern;
            
            // update gui elements
            fS1.setSelectedItem( fSelectedPattern.getS() );
            fS2.setSelectedItem( fSelectedPattern.getS() );
            fP.setSelectedItem( fSelectedPattern.getP() );
            if ( fSelectedPattern.getSConstraint() != null )
                fZS.setSelectedItem( fSelectedPattern.getSConstraint().getEvent() );
            else
                fZS.setSelectedItem( Event.getConstraintDefault() );

            fTi.setChainSequence( fSelectedPattern.getTis().getTis() );
            
            if ( fSelectedPattern.getSTimeBound() == null )
                fTimeBound.setText( "Time Bound" );
            else
                fTimeBound.setText( fSelectedPattern.getSTimeBound().toString() );

            if ( fSelectedPattern.getProbabilityBound() == null )
                fProbabilityBound.setText( "Probability Bound" );
            else
                fProbabilityBound.setText( fSelectedPattern.getProbabilityBound().toString() );
        }
    }

    public PrecedenceChain1NPanel() 
    {
        initComponents();
        
        fSelectedPattern = new PrecedenceChain1N();
        fTi.setChainUpdateListener( this );
    }

    public void clearSelection()
    {
        setSelectedPattern( new PrecedenceChain1N() );
    }
    
    public void setController( PSPController aPSPController )
    {
        fPSPController = aPSPController;
        
        fS1.setController( fPSPController );
        fS2.setController( fPSPController );
        fP.setController( fPSPController );
        fZS.setController( fPSPController );
        fTi.setController( fPSPController );
    }

    public void updateEvents()
    {
        fS1.updateEvents();
        fS2.updateEvents();
        fP.updateEvents();
        fZS.updateEvents();
        fTi.updateEvents();
    }

    public void updateChain( ChainEvents aEventChain )
    {
        fSelectedPattern.setTis( aEventChain );
        updatePattern();
    }

    private void updatePattern()
    {
        if ( fTi.getChainSequence().size() > 1 )
            fTiLabel.setText( "[hold]" );
        else
            fTiLabel.setText( "[holds]" );
            
        if ( fPSPController != null )
            fPSPController.updatePattern();
    }

    private boolean isEventSelectionPossible( Event aEvent )
    {
        if ( fPSPController != null )
            return fPSPController.isPatternEventSelectionPossible( aEvent );

        return true;
    }

    private boolean isSSelectionPossible( Event aS, Event aSAlt )
    {
        if ( fPSPController != null )
            return fPSPController.isPatternEventSelectionPossible( aS, aSAlt );

        return true;
    }

    private void updateS1()
    {
        fS1.acceptSelection();
        fS2.setSelectedItem( fS1.getSelectedItem() );
        fS2.acceptSelection();

        fSelectedPattern.setS( (Event)fS1.getSelectedItem() );

        updatePattern();
    }

    private void updateS2()
    {
        fS2.acceptSelection();
        fS1.setSelectedItem( fS2.getSelectedItem() );
        fS1.acceptSelection();

        fSelectedPattern.setS( (Event)fS2.getSelectedItem() );

        updatePattern();
    }

    private void updateP()
    {
        fP.acceptSelection();

        fSelectedPattern.setP( (Event)fP.getSelectedItem() );

        updatePattern();
    }

    private void updateZS()
    {
        fZS.acceptSelection();

        fSelectedPattern.setSConstraint( EventConstraint.newEventConstraint( (Event)fZS.getSelectedItem() ) );

        updatePattern();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fLabel1 = new javax.swing.JLabel();
        fS1 = new psp.ui.util.EventComboBox();
        fLabel2 = new javax.swing.JLabel();
        fTi = new psp.ui.panels.pattern.order.chains.PrecedenceChainEventSequencePanel();
        fLabel4 = new javax.swing.JLabel();
        fP = new psp.ui.util.EventComboBox();
        fLabel5 = new javax.swing.JLabel();
        fTimeBound = new javax.swing.JButton();
        fProbabilityBound = new javax.swing.JButton();
        fLabel6 = new javax.swing.JLabel();
        fS2 = new psp.ui.util.EventComboBox();
        fLabel7 = new javax.swing.JLabel();
        fZS = new psp.ui.util.ContraintComboBox();
        fTiLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(536, 350));
        setMinimumSize(new java.awt.Dimension(536, 276));

        fLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel1.setText("if");

        fS1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fS1ActionPerformed(evt);
            }
        });

        fLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel2.setText("[has occurred]");

        fTi.setMaximumSize(new java.awt.Dimension(504, 200));
        fTi.setMinimumSize(new java.awt.Dimension(504, 92));

        fLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel4.setText("then it must be the case that");

        fP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fPActionPerformed(evt);
            }
        });

        fLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel5.setText("[has occurred]");

        fTimeBound.setText("Interval Time Bound");
        fTimeBound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fTimeBoundActionPerformed(evt);
            }
        });

        fProbabilityBound.setText("Probability Bound");
        fProbabilityBound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fProbabilityBoundActionPerformed(evt);
            }
        });

        fLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel6.setText("before");

        fS2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fS2ActionPerformed(evt);
            }
        });

        fLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel7.setText("[holds]");

        fZS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fZSActionPerformed(evt);
            }
        });

        fTiLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fTiLabel.setText("[holds]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fTimeBound, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(fS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(fP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fLabel5))
                    .addComponent(fTiLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(fS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(fTi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(fZS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fProbabilityBound, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fLabel1)
                    .addComponent(fS1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fTi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fTiLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fLabel4)
                    .addComponent(fP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fTimeBound)
                    .addComponent(fLabel6)
                    .addComponent(fS2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fLabel7)
                    .addComponent(fZS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fProbabilityBound))
                .addContainerGap(58, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fTimeBoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fTimeBoundActionPerformed
        TimeBoundDialog lDialog = new IntervalTimeBoundDialog( fPSPController );

        TimeBound lNewTimeBound = lDialog.showDialog( fSelectedPattern.getS(), fSelectedPattern.getSTimeBound() );

        if ( lNewTimeBound == null )
            fTimeBound.setText( "Interval Time Bound" );
        else
            fTimeBound.setText( lNewTimeBound.toString() );

        fSelectedPattern.setSTimeBound( lNewTimeBound );
        updatePattern();
    }//GEN-LAST:event_fTimeBoundActionPerformed

    private void fProbabilityBoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fProbabilityBoundActionPerformed
        ProbabilityBoundDialog lDialog = new ProbabilityBoundDialog( fPSPController );

        ProbabilityBound lNewProbabilityBound = lDialog.showDialog( fSelectedPattern.getProbabilityBound() );

        if ( lNewProbabilityBound == null )
            fProbabilityBound.setText( "Probability Bound" );
        else
            fProbabilityBound.setText( lNewProbabilityBound.toString() );

        fSelectedPattern.setProbabilityBound( lNewProbabilityBound );
        updatePattern();
    }//GEN-LAST:event_fProbabilityBoundActionPerformed

    private void fS1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fS1ActionPerformed
        // check that selected Event is not used in pattern
        Event lEvent = (Event)fS1.getSelectedItem();
        
        if ( lEvent != null )
        {
            if ( fS1.isStable( lEvent ) || isSSelectionPossible( lEvent, (Event)fS2.getSelectedItem() ) )
            {
                fS1.acceptSelection();
                updateS1();
            }
            else
                fS1.revertSelection();
        }
    }//GEN-LAST:event_fS1ActionPerformed

    private void fS2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fS2ActionPerformed
        // check that selected Event is not used in pattern
        Event lEvent = (Event)fS2.getSelectedItem();
        
        if ( lEvent != null )
        {
            if ( fS2.isStable( lEvent ) || isSSelectionPossible( lEvent, (Event)fS1.getSelectedItem() ) )
            {
                updateS2();
            }
            else
                fS2.revertSelection();
        }
    }//GEN-LAST:event_fS2ActionPerformed

    private void fPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fPActionPerformed
        // check that selected Event is not used in pattern
        Event lEvent = (Event)fP.getSelectedItem();
        
        if ( lEvent != null )
        {
            if ( fP.isStable( lEvent ) || isEventSelectionPossible( lEvent ) )
            {
                updateP();
            }
            else
                fP.revertSelection();
        }
    }//GEN-LAST:event_fPActionPerformed

    private void fZSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fZSActionPerformed
        // check that selected Event is not used in pattern
        Event lEvent = (Event)fZS.getSelectedItem();
        
        if ( lEvent != null )
        {
            if ( fZS.isStable( lEvent ) || isEventSelectionPossible( lEvent ) )
            {
                updateZS();
            }
            else
                fZS.revertSelection();
        }
    }//GEN-LAST:event_fZSActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fLabel1;
    private javax.swing.JLabel fLabel2;
    private javax.swing.JLabel fLabel4;
    private javax.swing.JLabel fLabel5;
    private javax.swing.JLabel fLabel6;
    private javax.swing.JLabel fLabel7;
    private psp.ui.util.EventComboBox fP;
    private javax.swing.JButton fProbabilityBound;
    private psp.ui.util.EventComboBox fS1;
    private psp.ui.util.EventComboBox fS2;
    private psp.ui.panels.pattern.order.chains.PrecedenceChainEventSequencePanel fTi;
    private javax.swing.JLabel fTiLabel;
    private javax.swing.JButton fTimeBound;
    private psp.ui.util.ContraintComboBox fZS;
    // End of variables declaration//GEN-END:variables

}
