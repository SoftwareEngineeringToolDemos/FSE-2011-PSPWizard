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

import javax.swing.JOptionPane;
import psp.engine.PSPController;
import psp.sel.Event;

public class NewEventDialog extends javax.swing.JDialog 
{    
    private PSPController fPSPController;

    public NewEventDialog( PSPController aPSPController )
    {
        super( aPSPController.getHostFrame(), true );
        initComponents();
        
        fPSPController = aPSPController;
        
        getRootPane().setDefaultButton(fOk );
    }

    private Event fDialogResult;

    public Event showDialog() 
    {
        fClearActionPerformed( null );
        
        fDialogResult = null;
        fSpecification.requestFocus();

        setLocationRelativeTo( fPSPController.getHostFrame() );
        setVisible( true );

        return fDialogResult;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        fName = new javax.swing.JTextField();
        fCancel = new javax.swing.JButton();
        fOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fSpecification = new javax.swing.JTextArea();
        fClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Event");
        setResizable(false);

        jLabel1.setText("Name:");

        fCancel.setText("Cancel");
        fCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fCancelActionPerformed(evt);
            }
        });

        fOk.setText("Accept");
        fOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fOkActionPerformed(evt);
            }
        });

        jLabel2.setText("Specification:");

        fSpecification.setColumns(20);
        fSpecification.setRows(5);
        fSpecification.setWrapStyleWord(true);
        jScrollPane1.setViewportView(fSpecification);

        fClear.setText("Clear");
        fClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fCancel)
                        .addGap(64, 64, 64)
                        .addComponent(fClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fOk))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fCancel, fClear, fOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fCancel)
                    .addComponent(fClear)
                    .addComponent(fOk))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void fOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fOkActionPerformed
        // insert event if possible
        String lNewName = fName.getText();
        String lNewSpec = fSpecification.getText();
        
        if ( lNewName.isEmpty() )
            fName.requestFocus();
        else
        {
            if ( lNewSpec.isEmpty() )
                fSpecification.requestFocus();
            else
            {
                fDialogResult = fPSPController.newEvent( lNewName, lNewSpec );
                if ( fDialogResult != null  )
                {
                    setVisible( false );
                }
                else
                    JOptionPane.showMessageDialog( this, 
                                                   String.format( "Duplicate event name: \"%s\"", lNewName ), 
                                                   "Error",
                                                   JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_fOkActionPerformed

    private void fCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fCancelActionPerformed
        // abort
        setVisible( false );
    }//GEN-LAST:event_fCancelActionPerformed

    private void fClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fClearActionPerformed
        // clear selections
        
        fName.setText( Event.getFreshEventName() );
        fSpecification.setText( "true" );
    }//GEN-LAST:event_fClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fCancel;
    private javax.swing.JButton fClear;
    private javax.swing.JTextField fName;
    private javax.swing.JButton fOk;
    private javax.swing.JTextArea fSpecification;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
