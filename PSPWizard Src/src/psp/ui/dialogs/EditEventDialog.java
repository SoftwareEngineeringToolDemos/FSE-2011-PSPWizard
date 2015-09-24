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

public class EditEventDialog extends javax.swing.JDialog 
{    
    private PSPController fPSPController;

    public EditEventDialog( PSPController aPSPController )
    {
        super( aPSPController.getHostFrame(), true );
        initComponents();
        
        fPSPController = aPSPController;
        
        getRootPane().setDefaultButton(fOk );
    }

    private Event fDialogResult;
    private EditEvent fEditEvent;
    
    private static class EditEvent
    {
        public Event fEvent;
        
        public EditEvent( Event aEvent )
        {
            fEvent = aEvent;
        }
        
        public String toString()
        {
            return fEvent.getName();
        }
    }
    
    public Event showDialog() 
    {
        fEventList.removeAllItems();
        
        for ( Event e : fPSPController )
        {
            if ( !e.isDefault() )
                fEventList.addItem( new EditEvent( e ) );
        }

        fDialogResult = null;

        if ( fEventList.getItemCount() > 0 )
        {
            fSpecification.requestFocus();
            fEditEvent = (EditEvent)fEventList.getSelectedItem();
        }
        else
        {
            fCancel.requestFocus();
            fEditEvent = null;
        }

        setLocationRelativeTo( fPSPController.getHostFrame() );
        setVisible( true );

        return fDialogResult;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        fCancel = new javax.swing.JButton();
        fOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fSpecification = new javax.swing.JTextArea();
        fClear = new javax.swing.JButton();
        fEventList = new javax.swing.JComboBox();

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

        fEventList.setMaximumSize(new java.awt.Dimension(300, 27));
        fEventList.setMinimumSize(new java.awt.Dimension(300, 27));
        fEventList.setPreferredSize(new java.awt.Dimension(300, 27));
        fEventList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fEventListActionPerformed(evt);
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
                        .addComponent(fCancel)
                        .addGap(64, 64, 64)
                        .addComponent(fClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fOk))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fEventList, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fCancel, fClear, fOk});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fEventList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fCancel)
                    .addComponent(fClear)
                    .addComponent(fOk))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void fOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fOkActionPerformed
        // insert event if possible
        String lNewSpec = fSpecification.getText();
        

        if ( lNewSpec.isEmpty() )
            fSpecification.requestFocus();
        else
        {
            fDialogResult = ((EditEvent)fEventList.getSelectedItem()).fEvent;
            if ( fDialogResult != null )
                fDialogResult.setSpecification( lNewSpec );

            setVisible( false );
        }
    }//GEN-LAST:event_fOkActionPerformed

    private void fCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fCancelActionPerformed
        // abort

        setVisible( false );
    }//GEN-LAST:event_fCancelActionPerformed

    private void fClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fClearActionPerformed
        // clear selections
        fSpecification.setText( "true" );
    }//GEN-LAST:event_fClearActionPerformed

    private void fEventListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fEventListActionPerformed
        // change text
        EditEvent lEvent = (EditEvent)fEventList.getSelectedItem();
        
        if ( lEvent != null )
        {
            if ( fEditEvent != null )
            {
                String lSpec = fSpecification.getText();

                if ( !fEditEvent.fEvent.getSpecification().equals( lSpec ) )
                {
                    if ( JOptionPane.showConfirmDialog( this, 
                                                       "Save changes", 
                                                       "Update Event Specification",
                                                       JOptionPane.YES_NO_OPTION,
                                                       JOptionPane.WARNING_MESSAGE ) == JOptionPane.YES_OPTION )
                        fEditEvent.fEvent.setSpecification( lSpec );
                }
            }
            
            fEditEvent = lEvent;
            fSpecification.setText( lEvent.fEvent.getSpecification() );
        }
    }//GEN-LAST:event_fEventListActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fCancel;
    private javax.swing.JButton fClear;
    private javax.swing.JComboBox fEventList;
    private javax.swing.JButton fOk;
    private javax.swing.JTextArea fSpecification;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
