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

package psp.ui.dialogs;

import psp.constraints.GreaterThanEqualProbability;
import psp.constraints.GreaterThanProbability;
import psp.constraints.LowerThanEqualProbability;
import psp.constraints.LowerThanProbability;
import psp.constraints.ProbabilityBound;
import psp.engine.PSPConstants;
import psp.engine.PSPController;

public class ProbabilityBoundDialog extends javax.swing.JDialog 
{   
    private PSPController fPSPController;
    
    public ProbabilityBoundDialog( PSPController fPSPController )
    {
        this( true, fPSPController );
    }
    
    public ProbabilityBoundDialog( boolean aModal, PSPController aPSPController ) 
    {
        super( aPSPController.getHostFrame(), aModal );

        initComponents();
        
        fPSPController = aPSPController;
        fResetActionPerformed( null );

        getRootPane().setDefaultButton( fAccept );
    }
    
    private ProbabilityBound fDialogResult;
    private ProbabilityBound fOldResult;
    
    public ProbabilityBound showDialog( ProbabilityBound aExistingProbBound ) 
    {
        fResetActionPerformed( null );

        fDialogResult = aExistingProbBound;
        fOldResult = aExistingProbBound;
        
        if ( fDialogResult == null )
        {
            fNoProbability.setSelected( true );
            fNoProbabilityActionPerformed( null );
        }
        else
        {
            fWithProbability.setSelected( true );
            fGreaterEqualThan.setEnabled( true );
            fGreaterThan.setEnabled( true );
            fLowerEqualThan.setEnabled( true );
            fLowerThan.setEnabled( true );
           fProbability.setEnabled( true );
            fProbability.setText( Double.toString( fDialogResult.getProbability() ) );
            
            switch ( aExistingProbBound.getType() )
            {
                case PSPConstants.CP_Lower:
                    fLowerThan.setSelected( true );
                    break;
                case PSPConstants.CP_LowerEqual:
                    fLowerEqualThan.setSelected( true );
                    break;
                case PSPConstants.CP_Greater:
                    fGreaterThan.setSelected( true );
                    break;
                case PSPConstants.CP_GreaterEqual:
                    fGreaterEqualThan.setSelected( true );
                    break;
            }
        }
        
        setLocationRelativeTo( fPSPController.getHostFrame() );
        setVisible( true );

        return fDialogResult;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fPropOptions = new javax.swing.ButtonGroup();
        fPropVariants = new javax.swing.ButtonGroup();
        fCancel = new javax.swing.JButton();
        fAccept = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        fNoProbability = new javax.swing.JRadioButton();
        fLowerThan = new javax.swing.JRadioButton();
        fLowerEqualThan = new javax.swing.JRadioButton();
        fGreaterThan = new javax.swing.JRadioButton();
        fGreaterEqualThan = new javax.swing.JRadioButton();
        fWithProbability = new javax.swing.JRadioButton();
        fProbability = new psp.ui.util.ProbabilityTextField();
        fReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Probability Bound Specification");
        setResizable(false);

        fCancel.setText("Cancel");
        fCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fCancelActionPerformed(evt);
            }
        });

        fAccept.setText("Accept");
        fAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fAcceptActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Probability bound options"));

        fPropOptions.add(fNoProbability);
        fNoProbability.setText("unconstrained");
        fNoProbability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fNoProbabilityActionPerformed(evt);
            }
        });

        fPropVariants.add(fLowerThan);
        fLowerThan.setText("lower than");
        fLowerThan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fLowerThanActionPerformed(evt);
            }
        });

        fPropVariants.add(fLowerEqualThan);
        fLowerEqualThan.setText("lower or equal to");
        fLowerEqualThan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fLowerEqualThanActionPerformed(evt);
            }
        });

        fPropVariants.add(fGreaterThan);
        fGreaterThan.setText("greater than");
        fGreaterThan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fGreaterThanActionPerformed(evt);
            }
        });

        fPropVariants.add(fGreaterEqualThan);
        fGreaterEqualThan.setText("greater or equal to");
        fGreaterEqualThan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fGreaterEqualThanActionPerformed(evt);
            }
        });

        fPropOptions.add(fWithProbability);
        fWithProbability.setText("with a probability");
        fWithProbability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fWithProbabilityActionPerformed(evt);
            }
        });

        fProbability.setText("probabilityTextField1");
        fProbability.setMaximumSize(new java.awt.Dimension(80, 28));
        fProbability.setMinimumSize(new java.awt.Dimension(80, 28));
        fProbability.setPreferredSize(new java.awt.Dimension(80, 28));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fNoProbability))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fWithProbability))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fLowerEqualThan)
                            .addComponent(fGreaterThan)
                            .addComponent(fLowerThan)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fGreaterEqualThan)
                                .addGap(18, 18, 18)
                                .addComponent(fProbability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(fNoProbability)
                .addGap(18, 18, 18)
                .addComponent(fWithProbability)
                .addGap(18, 18, 18)
                .addComponent(fLowerThan)
                .addGap(18, 18, 18)
                .addComponent(fLowerEqualThan)
                .addGap(18, 18, 18)
                .addComponent(fGreaterThan)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fGreaterEqualThan)
                    .addComponent(fProbability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fReset.setText("Clear");
        fReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(fCancel)
                        .addGap(18, 18, 18)
                        .addComponent(fReset)
                        .addGap(18, 18, 18)
                        .addComponent(fAccept)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fAccept, fCancel, fReset});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fCancel)
                    .addComponent(fReset)
                    .addComponent(fAccept))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {fAccept, fCancel, fReset});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fNoProbabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fNoProbabilityActionPerformed
        fProbability.setEnabled( false );
        fGreaterEqualThan.setEnabled( false );
        fGreaterThan.setEnabled( false );
        fLowerEqualThan.setEnabled( false );
        fLowerThan.setEnabled( false );
    }//GEN-LAST:event_fNoProbabilityActionPerformed

    private void fCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fCancelActionPerformed
        fDialogResult = fOldResult;
        setVisible( false );
    }//GEN-LAST:event_fCancelActionPerformed

    private void fAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fAcceptActionPerformed
 
        if ( fNoProbability.isSelected() )
        {
            fDialogResult = null;
            setVisible( false );
        }
        else
        {
            double lValue = Double.parseDouble( fProbability.getText() );

            if ( fLowerThan.isSelected() )
            {
                fDialogResult = new LowerThanProbability( lValue );
                setVisible( false );
            }
            else
            {
                if ( fLowerEqualThan.isSelected() )
                {
                    fDialogResult = new LowerThanEqualProbability( lValue );
                    setVisible( false );
                }
                else
                {
                    if ( fGreaterThan.isSelected() )
                    {
                        fDialogResult = new GreaterThanProbability( lValue );
                        setVisible( false );
                    }
                    else
                    {
                        fDialogResult = new GreaterThanEqualProbability( lValue );
                        setVisible( false );                        
                    }
                }
            }
        }
    }//GEN-LAST:event_fAcceptActionPerformed

    private void fResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fResetActionPerformed
        fNoProbabilityActionPerformed( null );
        fProbability.setText( "" );
        fNoProbability.setSelected( true );
        fDialogResult = null;
    }//GEN-LAST:event_fResetActionPerformed

    private void fWithProbabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fWithProbabilityActionPerformed
        fProbability.setEnabled( true );
        fGreaterEqualThan.setEnabled( true );
        fGreaterThan.setEnabled( true );
        fLowerEqualThan.setEnabled( true );
        fLowerThan.setEnabled( true );

        if ( fLowerThan.isSelected() || fLowerEqualThan.isSelected() ||
             fGreaterThan.isSelected() || fGreaterEqualThan.isSelected() )
            {}
        else
            fLowerThan.setSelected( true );
        fProbability.requestFocus();
    }//GEN-LAST:event_fWithProbabilityActionPerformed

    private void fLowerThanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fLowerThanActionPerformed
        fProbability.requestFocus();
    }//GEN-LAST:event_fLowerThanActionPerformed

    private void fLowerEqualThanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fLowerEqualThanActionPerformed
        fProbability.requestFocus();
    }//GEN-LAST:event_fLowerEqualThanActionPerformed

    private void fGreaterThanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fGreaterThanActionPerformed
        fProbability.requestFocus();
    }//GEN-LAST:event_fGreaterThanActionPerformed

    private void fGreaterEqualThanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fGreaterEqualThanActionPerformed
        fProbability.requestFocus();
    }//GEN-LAST:event_fGreaterEqualThanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fAccept;
    private javax.swing.JButton fCancel;
    private javax.swing.JRadioButton fGreaterEqualThan;
    private javax.swing.JRadioButton fGreaterThan;
    private javax.swing.JRadioButton fLowerEqualThan;
    private javax.swing.JRadioButton fLowerThan;
    private javax.swing.JRadioButton fNoProbability;
    private psp.ui.util.ProbabilityTextField fProbability;
    private javax.swing.ButtonGroup fPropOptions;
    private javax.swing.ButtonGroup fPropVariants;
    private javax.swing.JButton fReset;
    private javax.swing.JRadioButton fWithProbability;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
