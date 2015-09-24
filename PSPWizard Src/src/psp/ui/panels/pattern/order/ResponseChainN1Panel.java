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
import psp.sel.patterns.order.ResponseChainN1;
import psp.ui.dialogs.ProbabilityBoundDialog;
import psp.ui.dialogs.TimeBoundDialog;
import psp.ui.panels.pattern.PatternPanelFeatures;

public class ResponseChainN1Panel extends javax.swing.JPanel implements PatternPanelFeatures, ChainChanged
{
    private PSPController fPSPController;
    private ResponseChainN1 fSelectedPattern;

    public Pattern getSelectedPattern()
    {
        return fSelectedPattern;
    }

    public void setSelectedPattern( Pattern aSelectedPattern ) 
    {
        if ( aSelectedPattern instanceof ResponseChainN1 )
        {
            fSelectedPattern = (ResponseChainN1)aSelectedPattern;
            
            // update gui elements
            fP.setSelectedItem( fSelectedPattern.getP() );
            fS.setSelectedItem( fSelectedPattern.getS() );
            if ( fSelectedPattern.getPConstraint() != null )
                fZP.setSelectedItem( fSelectedPattern.getPConstraint().getEvent() );
            else
                fZP.setSelectedItem( Event.getConstraintDefault() );

            fTi.setChainSequence( fSelectedPattern.getTis().getTis() );
            
            if ( fSelectedPattern.getPTimeBound() == null )
                fTimeBound.setText( "Time Bound" );
            else
                fTimeBound.setText( fSelectedPattern.getPTimeBound().toString() );

            if ( fSelectedPattern.getProbabilityBound() == null )
                fProbabilityBound.setText( "Probability Bound" );
            else
                fProbabilityBound.setText( fSelectedPattern.getProbabilityBound().toString() );
        }
    }

    public ResponseChainN1Panel() 
    {
        initComponents();
        
        fSelectedPattern = new ResponseChainN1();
        fTi.setChainUpdateListener( this );
    }

    public void clearSelection()
    {
        setSelectedPattern( new ResponseChainN1() );
    }
    
    public void setController( PSPController aPSPController )
    {
        fPSPController = aPSPController;
        
        fS.setController( fPSPController );
        fP.setController( fPSPController );
        fZP.setController( fPSPController );
        fTi.setController( fPSPController );
    }

    public void updateEvents()
    {
        fS.updateEvents();
        fP.updateEvents();
        fZP.updateEvents();
        fTi.updateEvents();
    }

    public void updateChain( ChainEvents aEventChain )
    {
        fSelectedPattern.setTis( aEventChain );
        updatePattern();
    }

    private void updatePattern()
    {            
        if ( fPSPController != null )
            fPSPController.updatePattern();
    }

    private boolean isEventSelectionPossible( Event aEvent )
    {
        if ( fPSPController != null )
            return fPSPController.isPatternEventSelectionPossible( aEvent );

        return true;
    }

    private void updateS()
    {
        fS.acceptSelection();

        fSelectedPattern.setS( (Event)fS.getSelectedItem() );

        updatePattern();
    }

    private void updateP()
    {
        fP.acceptSelection();

        fSelectedPattern.setP( (Event)fP.getSelectedItem() );

        updatePattern();
    }

    private void updateZP()
    {
        fZP.acceptSelection();

        fSelectedPattern.setPConstraint( EventConstraint.newEventConstraint( (Event)fZP.getSelectedItem() ) );

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
        fS = new psp.ui.util.EventComboBox();
        fLabel4 = new javax.swing.JLabel();
        fP = new psp.ui.util.EventComboBox();
        fLabel5 = new javax.swing.JLabel();
        fTimeBound = new javax.swing.JButton();
        fProbabilityBound = new javax.swing.JButton();
        fZP = new psp.ui.util.ContraintComboBox();
        fTiLabel = new javax.swing.JLabel();
        fTi = new psp.ui.panels.pattern.order.chains.ResponseChainEventSequencePanel();

        setMaximumSize(new java.awt.Dimension(536, 350));
        setMinimumSize(new java.awt.Dimension(536, 276));

        fLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel1.setText("if");

        fS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSActionPerformed(evt);
            }
        });

        fLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel4.setText("then in response");

        fP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fPActionPerformed(evt);
            }
        });

        fLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel5.setText("[eventually holds]");

        fTimeBound.setText("Time Bound");
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

        fZP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fZPActionPerformed(evt);
            }
        });

        fTiLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fTiLabel.setText("[have occurred]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fTiLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(fS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(235, 235, 235)
                                .addComponent(fProbabilityBound, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fTimeBound, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fZP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(fLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(fP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(fLabel5)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(fTi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fLabel1)
                    .addComponent(fS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fTi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fTiLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fLabel4)
                    .addComponent(fP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fTimeBound)
                    .addComponent(fZP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(fProbabilityBound)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fTimeBoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fTimeBoundActionPerformed
        TimeBoundDialog lDialog = new TimeBoundDialog( fPSPController );

        TimeBound lNewTimeBound = lDialog.showDialog( fSelectedPattern.getS(), fSelectedPattern.getPTimeBound() );

        if ( lNewTimeBound == null )
            fTimeBound.setText( "Time Bound" );
        else
            fTimeBound.setText( lNewTimeBound.toString() );

        fSelectedPattern.setPTimeBound( lNewTimeBound );
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

    private void fSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSActionPerformed
        // check that selected Event is not used in pattern
        Event lEvent = (Event)fS.getSelectedItem();
        
        if ( lEvent != null )
        {
            if ( fS.isStable( lEvent ) || isEventSelectionPossible( lEvent ) )
            {
                updateS();
            }
            else
                fS.revertSelection();
        }
    }//GEN-LAST:event_fSActionPerformed

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

    private void fZPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fZPActionPerformed
        // check that selected Event is not used in pattern
        Event lEvent = (Event)fZP.getSelectedItem();
        
        if ( lEvent != null )
        {
            if ( fZP.isStable( lEvent ) || isEventSelectionPossible( lEvent ) )
            {
                updateZP();
            }
            else
                fZP.revertSelection();
        }
    }//GEN-LAST:event_fZPActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fLabel1;
    private javax.swing.JLabel fLabel4;
    private javax.swing.JLabel fLabel5;
    private psp.ui.util.EventComboBox fP;
    private javax.swing.JButton fProbabilityBound;
    private psp.ui.util.EventComboBox fS;
    private psp.ui.panels.pattern.order.chains.ResponseChainEventSequencePanel fTi;
    private javax.swing.JLabel fTiLabel;
    private javax.swing.JButton fTimeBound;
    private psp.ui.util.ContraintComboBox fZP;
    // End of variables declaration//GEN-END:variables

}
