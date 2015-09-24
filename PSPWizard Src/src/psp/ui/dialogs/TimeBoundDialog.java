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
import javax.swing.JTextField;
import psp.constraints.Interval;
import psp.constraints.LowerTimeBound;
import psp.constraints.TimeBound;
import psp.constraints.UpperTimeBound;
import psp.engine.PSPConstants;
import psp.engine.PSPController;
import psp.sel.Event;

public class TimeBoundDialog extends javax.swing.JDialog 
{   
    private PSPController fPSPController;
    
    public TimeBoundDialog( PSPController aController )
    {
        this( true, aController );
    }
    
    public TimeBoundDialog( boolean aModal, PSPController aPSPController ) 
    {
        super( aPSPController.getHostFrame(), aModal );

        initComponents();
        
        fPSPController = aPSPController;

        fResetActionPerformed( null );
        fIsUpperTimeOnly = false;
        fIsIntervalOnly = false;

        getRootPane().setDefaultButton( fAccept );
    }

    public String getTimeUnitsAsString()
    {
        return fTimeUnits.getText();
    }
    
    private boolean fIsUpperTimeOnly;
    
    protected void restrictToUpperTimeBound()
    {
        fLowerLimit.setEnabled( false );
        fLowerTime.setEnabled( false );
        fInterval.setEnabled( false );
        fUpperInterval.setEnabled( false );
        fLowerInterval.setEnabled( false );
        jLabel1.setForeground( new java.awt.Color(153, 153, 153) );
        
        fIsUpperTimeOnly = true;
    }

    private boolean fIsIntervalOnly;

    protected void restrictToInterval()
    {
        fUpperLimit.setEnabled( false );
        fUpperTime.setEditable( false );
        fLowerLimit.setEnabled( false );
        fLowerTime.setEnabled( false );
        fInterval.setEnabled( true );
        fUpperInterval.setEnabled( true );
        fLowerInterval.setEnabled( true );
        
        fIsIntervalOnly = true;
    }

    private TimeBound fDialogResult;
    private TimeBound fOldResult;
    private Event fAssociatedEvent;
    
    public TimeBound showDialog( Event aAssociatedEvent, TimeBound aExistingTimeBound ) 
    {
        fResetActionPerformed( null );

        fDialogResult = aExistingTimeBound;
        fOldResult = aExistingTimeBound;
        fAssociatedEvent = aAssociatedEvent;
        
        if ( fDialogResult == null )
        {
            fNoTimeBound.setSelected( true );
            fNoTimeBoundActionPerformed( null );
        }
        else
        {
            long lUValue;
            long lLValue;
            
            fTimeUnits.setText( aExistingTimeBound.getTimeUnit() );
            
            if ( fIsUpperTimeOnly )
            {
                switch ( aExistingTimeBound.getType() )
                {
                    case PSPConstants.CT_Upper:
                        lUValue = ((UpperTimeBound)aExistingTimeBound).getUpperLimit();
                    
                        if ( lUValue == Long.MAX_VALUE )
                            fUpperTime.setText( "" );
                        else
                            fUpperTime.setText( Long.toString( lUValue ) );
                        fUpperLimit.setSelected( true );
                        fUpperLimitActionPerformed( null );
                        break;
                    default:
                        fNoTimeBound.setSelected( true );
                        fNoTimeBoundActionPerformed( null );
                        break;
                }
            }
            else
            {
                if ( fIsIntervalOnly )
                {
                    switch ( aExistingTimeBound.getType() )
                    {
                    case PSPConstants.CT_Interval:
                        lLValue = ((Interval)aExistingTimeBound).getLowerLimit();
                        lUValue = ((Interval)aExistingTimeBound).getUpperLimit();
                    
                        fLowerInterval.setText( Long.toString( lLValue ) );
                        if ( lUValue == Long.MAX_VALUE )
                            fUpperInterval.setText( "" );
                        else
                            fUpperInterval.setText( Long.toString( lUValue ) );
                        fInterval.setSelected( true );
                        fIntervalActionPerformed( null );
                        break;
                    default:
                        fNoTimeBound.setSelected( true );
                        fNoTimeBoundActionPerformed( null );
                        break;
                    }
                }
                else
                {
                    switch ( aExistingTimeBound.getType() )
                    {
                        case PSPConstants.CT_Upper:
                            lUValue = ((UpperTimeBound)aExistingTimeBound).getUpperLimit();
                    
                            if ( lUValue == Long.MAX_VALUE )
                                fUpperTime.setText( "" );
                            else
                                fUpperTime.setText( Long.toString( lUValue ) );
                            fUpperLimit.setSelected( true );
                            fUpperLimitActionPerformed( null );
                            break;
                        case PSPConstants.CT_Lower:
                            lLValue = ((LowerTimeBound)aExistingTimeBound).getLowerLimit();

                            if ( lLValue == 0 )
                                fLowerTime.setText( "" );
                            else
                                fLowerTime.setText( Long.toString( lLValue ) );
                            fLowerLimit.setSelected( true );
                            fLowerLimitActionPerformed( null );
                            break;
                        case PSPConstants.CT_Interval:
                            lLValue = ((Interval)aExistingTimeBound).getLowerLimit();
                            lUValue = ((Interval)aExistingTimeBound).getUpperLimit();

                            fLowerInterval.setText( Long.toString( lLValue ) );
                            if ( lUValue == Long.MAX_VALUE )
                                fUpperInterval.setText( "" );
                            else
                                fUpperInterval.setText( Long.toString( lUValue ) );
                            fInterval.setSelected( true );
                            fIntervalActionPerformed( null );
                            break;
                    }
                }
            }
        }
        
        setLocationRelativeTo( fPSPController.getHostFrame() );
        setVisible( true );

        return fDialogResult;
    }
    
    private long getUpperTime( JTextField aTextField )
    {
        String lNumber = aTextField.getText();
           
        return lNumber.isEmpty() ? Long.MAX_VALUE : Long.parseLong( lNumber );        
    }

    private long getLowerTime( JTextField aTextField )
    {
        String lNumber = aTextField.getText();
           
        return lNumber.isEmpty() ? 0 : Long.parseLong( lNumber );        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fTimeBoundOptions = new javax.swing.ButtonGroup();
        fCancel = new javax.swing.JButton();
        fAccept = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        fNoTimeBound = new javax.swing.JRadioButton();
        fUpperLimit = new javax.swing.JRadioButton();
        fLowerLimit = new javax.swing.JRadioButton();
        fInterval = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        fTimeUnits = new javax.swing.JTextField();
        fUpperInterval = new psp.ui.util.TimeTextField();
        fUpperTime = new psp.ui.util.TimeTextField();
        fLowerTime = new psp.ui.util.TimeTextField();
        fLowerInterval = new psp.ui.util.TimeTextField();
        fReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Time Bound Specification");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Time bound options"));

        fTimeBoundOptions.add(fNoTimeBound);
        fNoTimeBound.setText("unconstrained");
        fNoTimeBound.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fNoTimeBoundActionPerformed(evt);
            }
        });

        fTimeBoundOptions.add(fUpperLimit);
        fUpperLimit.setText("within");
        fUpperLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fUpperLimitActionPerformed(evt);
            }
        });

        fTimeBoundOptions.add(fLowerLimit);
        fLowerLimit.setText("after");
        fLowerLimit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fLowerLimitActionPerformed(evt);
            }
        });

        fTimeBoundOptions.add(fInterval);
        fInterval.setText("between");
        fInterval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fIntervalActionPerformed(evt);
            }
        });

        jLabel1.setText("and");

        fTimeUnits.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fTimeUnits.setText("time units");
        fTimeUnits.setMaximumSize(new java.awt.Dimension(120, 28));
        fTimeUnits.setMinimumSize(new java.awt.Dimension(120, 28));
        fTimeUnits.setPreferredSize(new java.awt.Dimension(120, 28));

        fUpperInterval.setText("timeTextField1");
        fUpperInterval.setMaximumSize(new java.awt.Dimension(80, 28));
        fUpperInterval.setMinimumSize(new java.awt.Dimension(80, 28));
        fUpperInterval.setPreferredSize(new java.awt.Dimension(80, 28));

        fUpperTime.setText("timeTextField1");
        fUpperTime.setMaximumSize(new java.awt.Dimension(80, 28));
        fUpperTime.setMinimumSize(new java.awt.Dimension(80, 28));
        fUpperTime.setPreferredSize(new java.awt.Dimension(80, 28));

        fLowerTime.setText("timeTextField1");
        fLowerTime.setMaximumSize(new java.awt.Dimension(80, 28));
        fLowerTime.setMinimumSize(new java.awt.Dimension(80, 28));
        fLowerTime.setPreferredSize(new java.awt.Dimension(80, 28));

        fLowerInterval.setText("timeTextField1");
        fLowerInterval.setMaximumSize(new java.awt.Dimension(80, 28));
        fLowerInterval.setMinimumSize(new java.awt.Dimension(80, 28));
        fLowerInterval.setPreferredSize(new java.awt.Dimension(80, 28));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fNoTimeBound)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fInterval, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fLowerLimit, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fUpperLimit, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fUpperTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fLowerTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fLowerInterval, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(fUpperInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fTimeUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fNoTimeBound)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fUpperLimit)
                    .addComponent(fUpperTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fLowerLimit)
                    .addComponent(fLowerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fInterval)
                    .addComponent(jLabel1)
                    .addComponent(fTimeUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fUpperInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fLowerInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(fCancel)
                .addGap(79, 79, 79)
                .addComponent(fReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fAccept)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(fAccept)
                    .addComponent(fCancel)
                    .addComponent(fReset))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {fAccept, fCancel, fReset});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fNoTimeBoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fNoTimeBoundActionPerformed
        fUpperTime.setEnabled( false );
        fLowerTime.setEnabled( false );
        fLowerInterval.setEnabled( false );
        fUpperInterval.setEnabled( false );
        fTimeUnits.setEnabled( false );
    }//GEN-LAST:event_fNoTimeBoundActionPerformed

    private void fUpperLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fUpperLimitActionPerformed
        fUpperTime.setEnabled( true );
        fUpperTime.requestFocus();
        fLowerTime.setEnabled( false );
        fLowerInterval.setEnabled( false );
        fUpperInterval.setEnabled( false );
        fTimeUnits.setEnabled( true );
    }//GEN-LAST:event_fUpperLimitActionPerformed

    private void fLowerLimitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fLowerLimitActionPerformed
        fUpperTime.setEnabled( false );
        fLowerTime.setEnabled( true );
        fLowerTime.requestFocus();
        fLowerInterval.setEnabled( false );
        fUpperInterval.setEnabled( false );
        fTimeUnits.setEnabled( true );
    }//GEN-LAST:event_fLowerLimitActionPerformed

    private void fIntervalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fIntervalActionPerformed
        fUpperTime.setEnabled( false );
        fLowerTime.setEnabled( false );
        fLowerInterval.setEnabled( true );
        fLowerInterval.requestFocus();
        fUpperInterval.setEnabled( true );
        fTimeUnits.setEnabled( true );
    }//GEN-LAST:event_fIntervalActionPerformed

    private void fCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fCancelActionPerformed
        fDialogResult = fOldResult;
        setVisible( false );
    }//GEN-LAST:event_fCancelActionPerformed

    private void fAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fAcceptActionPerformed
 
        if ( fNoTimeBound.isSelected() )
        {
            fDialogResult = null;
            setVisible( false );
        }
        else
        {
            if ( fUpperLimit.isSelected() )
            {
                fDialogResult = new UpperTimeBound( fAssociatedEvent, getUpperTime( fUpperTime ), fTimeUnits.getText() );
                setVisible( false );
            }
            else
            {
                if ( fLowerLimit.isSelected() )
                {
                    fDialogResult = new LowerTimeBound( fAssociatedEvent,  getLowerTime( fLowerTime ), fTimeUnits.getText() );
                    setVisible( false );
                }
                else
                {        
                    long lLowerNumber = getLowerTime( fLowerInterval );
                    long lUpperNumber = getUpperTime( fUpperInterval );
           
                    if ( lLowerNumber > lUpperNumber )
                    {
                        JOptionPane.showMessageDialog( this, 
                                                       "Lower time bound exceeds upper time bound.", 
                                                       "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        fDialogResult = new Interval( fAssociatedEvent, lLowerNumber, lUpperNumber, fTimeUnits.getText() );
                        setVisible( false );
                    }
                }
            }
            
            fDialogResult.setTimeUnit( fTimeUnits.getText() );
        }
    }//GEN-LAST:event_fAcceptActionPerformed

    private void fResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fResetActionPerformed
        fUpperTime.setText( "" );
        fLowerTime.setText( "" );
        fLowerInterval.setText( "" );
        fUpperInterval.setText( "" );
        fTimeUnits.setText( "time units" );
        fDialogResult = null;
        fNoTimeBound.setSelected( true );
    }//GEN-LAST:event_fResetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fAccept;
    private javax.swing.JButton fCancel;
    private javax.swing.JRadioButton fInterval;
    private psp.ui.util.TimeTextField fLowerInterval;
    private javax.swing.JRadioButton fLowerLimit;
    private psp.ui.util.TimeTextField fLowerTime;
    private javax.swing.JRadioButton fNoTimeBound;
    private javax.swing.JButton fReset;
    private javax.swing.ButtonGroup fTimeBoundOptions;
    private javax.swing.JTextField fTimeUnits;
    private psp.ui.util.TimeTextField fUpperInterval;
    private javax.swing.JRadioButton fUpperLimit;
    private psp.ui.util.TimeTextField fUpperTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
