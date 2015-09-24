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

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import psp.constraints.ProbabilityBound;
import psp.engine.PSPController;
import psp.sel.Event;
import psp.sel.patterns.Pattern;
import psp.sel.patterns.occurrence.MinimumDuration;
import psp.ui.dialogs.ProbabilityBoundDialog;
import psp.ui.panels.pattern.PatternPanelFeatures;

public class MinimumDurationPanel extends javax.swing.JPanel implements PatternPanelFeatures
{
    private PSPController fPSPController;
    private MinimumDuration fSelectedPattern;

    public Pattern getSelectedPattern()
    {
        return fSelectedPattern;
    }

    public void setSelectedPattern( Pattern aSelectedPattern ) 
    {
        if ( aSelectedPattern instanceof MinimumDuration )
        {
            fSelectedPattern = (MinimumDuration)aSelectedPattern;
            
            // update gui elements
            fP.setSelectedItem( fSelectedPattern.getP() );
            fTUP.setText( String.format( "%d", fSelectedPattern.getUpperLimit() ) );
            fTimeUnits.setText( fSelectedPattern.getTimeUnit() );

            if ( fSelectedPattern.getProbabilityBound() == null )
                fProbabilityBound.setText( "Probability Bound" );
            else
                fProbabilityBound.setText( fSelectedPattern.getProbabilityBound().toString() );
        }
    }

    public MinimumDurationPanel() 
    {
        initComponents();
        
        fSelectedPattern = new MinimumDuration();
        
        fTUP.setText( String.format( "%d", fSelectedPattern.getUpperLimit() ) );
        fTimeUnits.setText( fSelectedPattern.getTimeUnit() );
        
        fTUP.getDocument().addDocumentListener( new DocumentListener() 
        {
            private void update()
            {
                String lSpec = fTUP.getText();
                
                if ( lSpec.isEmpty() )
                    fSelectedPattern.setUpperLimit( 0 );
                else
                    fSelectedPattern.setUpperLimit( Long.parseLong( fTUP.getText() ) );
    
                updatePattern();   
            }
            
            public void removeUpdate(DocumentEvent e) 
            {
                update();
            }

            public void insertUpdate(DocumentEvent e) 
            {
                update();
            }

            public void changedUpdate(DocumentEvent e) 
            {
                update();
            }
         });

        fTimeUnits.getDocument().addDocumentListener( new DocumentListener() 
        {
            private void update()
            {
                String lSpec = fTimeUnits.getText();
                
                fSelectedPattern.setTimeUnit( lSpec );
    
                updatePattern();   
            }
            
            public void removeUpdate(DocumentEvent e) 
            {
                update();
            }

            public void insertUpdate(DocumentEvent e) 
            {
                update();
            }

            public void changedUpdate(DocumentEvent e) 
            {
                update();
            }
         });
    }

    public void clearSelection()
    {
        setSelectedPattern( new MinimumDuration() );
    }
    
    public void setController( PSPController aPSPController )
    {
        fPSPController = aPSPController;
        
        fP.setController( fPSPController );
    }

    public void updateEvents()
    {
        fP.updateEvents();
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

    private void updateP()
    {
        fP.acceptSelection();

        fSelectedPattern.setP( (Event)fP.getSelectedItem() );

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

        fP = new psp.ui.util.EventComboBox();
        fProbabilityBound = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        fTUP = new psp.ui.util.TimeTextField();
        fTimeUnits = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        fLabel5 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(536, 350));

        fP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fPActionPerformed(evt);
            }
        });

        fProbabilityBound.setText("Probability Bound");
        fProbabilityBound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fProbabilityBoundActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel16.setText("it remains so for at least");

        fTUP.setText("timeTextField1");
        fTUP.setMaximumSize(new java.awt.Dimension(80, 28));
        fTUP.setMinimumSize(new java.awt.Dimension(80, 28));
        fTUP.setPreferredSize(new java.awt.Dimension(80, 28));

        fTimeUnits.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fTimeUnits.setText("time units");
        fTimeUnits.setMaximumSize(new java.awt.Dimension(120, 28));
        fTimeUnits.setMinimumSize(new java.awt.Dimension(120, 28));
        fTimeUnits.setPreferredSize(new java.awt.Dimension(120, 28));

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel17.setText("once");

        fLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        fLabel5.setText("[becomes satisfied]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(fP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(fTUP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fTimeUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(fProbabilityBound, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(fLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(fTUP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fTimeUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(fProbabilityBound)
                .addGap(109, 109, 109))
        );
    }// </editor-fold>//GEN-END:initComponents

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fLabel5;
    private psp.ui.util.EventComboBox fP;
    private javax.swing.JButton fProbabilityBound;
    private psp.ui.util.TimeTextField fTUP;
    private javax.swing.JTextField fTimeUnits;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    // End of variables declaration//GEN-END:variables

}
