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

package psp.ui;

import java.awt.Color;
import java.util.Iterator;
import javax.swing.JFrame;
import psp.engine.EventSelectionValidator;
import psp.engine.EventStorage;
import psp.engine.PSPController;
import psp.mappings.LTLMapper;
import psp.mappings.MTLMapper;
import psp.mappings.PatternMapper;
import psp.mappings.PrismMapper;
import psp.mappings.QuantitativePrismMapper;
import psp.mappings.SELMapper;
import psp.sel.Event;
import psp.sel.patterns.Pattern;
import psp.sel.scopes.Scope;
import psp.ui.dialogs.EditEventDialog;
import psp.ui.dialogs.NewEventDialog;

public class PSPWizard extends javax.swing.JFrame implements PSPController
{
    private EventStorage fEvents;
    
    private void initMappings()
    {
        fMappings.addItem( new SELMapper() );
        fMappings.addItem( new LTLMapper() );
        fMappings.addItem( new MTLMapper() );
        fMappings.addItem( new PrismMapper() );
        fMappings.addItem( new QuantitativePrismMapper() );
    }
    
    public PSPWizard()
    {
        initComponents();
        
        reset();
        
        initMappings();
        
        // connect scopes with controller
        fScopes.setController( this );
        fSelectedScope = fScopes.getSelectedScope();

        // connect patterns with controller
        fPatterns.setController( this );
        fSelectedPattern = fPatterns.getSelectedPattern();

        // update initial SEL and mapping
        updateSELandMapping();
    }

       // Entry point
    
    public static void main(String args[]) 
    {
       /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable() 
        {
            public void run() 
            {
                (new PSPWizard()).showEditor();
            }
        });
    }

    public void showEditor()
    {
        setLocationRelativeTo( null );  // center?
        setVisible( true );
    }

    public JFrame getHostFrame() 
    {
        return this;
    }

    public void reset()
    {
        Event.reset();
        fEvents = new EventStorage();

        fSelectedScope = null;
        fSelectedPattern = null;
        EventSelectionValidator.clearSelection();
        fScopes.updateEvents();
        fPatterns.updateEvents();
        fScopes.clearSelection();
        fPatterns.clearSelection();
        fSEs.setEnabled( true );
        fESpec.setSelected( false );
        fEName.setSelected( true );
        Event.EventStringMethod = Event.E_Name;
}

    // event controller facet
    
    public Event newEvent( String aName )
    {
        Event Result = fEvents.newEvent( aName );
                        
        return Result;
    }

    public Event newEvent( String aName, String aSpecification )
    {
        Event Result = fEvents.newEvent( aName, aSpecification );
                        
        return Result;
    }
 
    public Iterator<Event> iterator()
    {
        return fEvents.iterator();
    }

    // event selection validation facet

    // Scope events
    
    private Scope fSelectedScope;

    public boolean isScopeEventSelectionPossible( Event aEvent ) 
    {
        return EventSelectionValidator.isScopeEventSelectionPossible( this, aEvent );
    }

    public void updateScope() 
    {
        fSelectedScope = fScopes.getSelectedScope();

        EventSelectionValidator.updateScopeEvents( fSelectedScope );

        // update SEL and mapping
        updateSELandMapping();
    }

    // Pattern events
    private Pattern fSelectedPattern;
    public boolean isPatternEventSelectionPossible( Event aEvent ) 
    {
        return EventSelectionValidator.isPatternEventSelectionPossible( this, aEvent );
    }

    public boolean isPatternEventSelectionPossible( Event aEvent, Event aAltEvent )
    {
        return EventSelectionValidator.isPatternEventSelectionPossible( this, aEvent, aAltEvent );
    }

    public void updatePattern() 
    {
        fSelectedPattern = fPatterns.getSelectedPattern();
    
        EventSelectionValidator.updatePatternEvents( fSelectedPattern );
        
        // update SEL and mapping
        updateSELandMapping();
    }

    // SEL expansion
    
    private void updateSELandMapping()
    {
        StringBuilder sb = new StringBuilder();
        
        if ( fSelectedScope != null && fSelectedPattern != null )
        {
            sb.append( fSelectedScope.getSpecificationAsSEL() );
            sb.append( ", " );
            sb.append( fSelectedPattern.getSpecificationAsSEL() );
            sb.append( '.' );
        
            fSELP.setText( sb.toString() );

            PatternMapper lMapper = (PatternMapper)fMappings.getSelectedItem();

            if ( lMapper != null )
            {
                String lMapping = lMapper.getMapping( fSelectedScope, fSelectedPattern );

                if ( !lMapping.isEmpty() )
                {
                    if ( lMapper.hasMappingErrorOccurred() )
                        fMapping.setForeground( Color.red );
                    else
                        fMapping.setForeground( Color.black );
                    fMapping.setText( lMapping );
                }
                else
                {
                    fMapping.setForeground( Color.red );
                    fMapping.setText( lMapper.getNotSupportedMessage() );
                }
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fScopes = new psp.ui.panels.scopes.ScopePanel();
        jPanel2 = new javax.swing.JPanel();
        fPatterns = new psp.ui.panels.pattern.PatternPanel();
        jPanel3 = new javax.swing.JPanel();
        fEName = new javax.swing.JCheckBox();
        fESpec = new javax.swing.JCheckBox();
        fClear = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fSELP = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        fMappings = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        fMapping = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        fNE = new javax.swing.JButton();
        fSEs = new javax.swing.JButton();
        fEE = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PSP Wizard");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Scope"));
        jPanel1.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fScopes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fScopes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pattern"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fPatterns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fPatterns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        fEName.setSelected(true);
        fEName.setText("Show event names");
        fEName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fENameActionPerformed(evt);
            }
        });

        fESpec.setText("Show event specification");
        fESpec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fESpecActionPerformed(evt);
            }
        });

        fClear.setText("Reset Editor");
        fClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fESpec))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(fClear))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fEName)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fEName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fESpec)
                .addGap(18, 18, 18)
                .addComponent(fClear)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Structured English Grammar"));

        fSELP.setEditable(false);
        fSELP.setColumns(20);
        fSELP.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        fSELP.setLineWrap(true);
        fSELP.setRows(5);
        fSELP.setWrapStyleWord(true);
        jScrollPane1.setViewportView(fSELP);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Mapping"));

        fMappings.setMaximumSize(new java.awt.Dimension(180, 27));
        fMappings.setMinimumSize(new java.awt.Dimension(180, 27));
        fMappings.setPreferredSize(new java.awt.Dimension(180, 27));
        fMappings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMappingsActionPerformed(evt);
            }
        });

        fMapping.setEditable(false);
        fMapping.setColumns(20);
        fMapping.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        fMapping.setLineWrap(true);
        fMapping.setRows(5);
        jScrollPane2.setViewportView(fMapping);

        jLabel1.setText("Target Logic:");

        jLabel2.setText("Formula:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(fMappings, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fMappings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Events"));

        fNE.setText("New Event");
        fNE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fNEActionPerformed(evt);
            }
        });

        fSEs.setText("Add Sample Events");
        fSEs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSEsActionPerformed(evt);
            }
        });

        fEE.setText("Edit Events");
        fEE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fEEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fSEs)
                    .addComponent(fNE)
                    .addComponent(fEE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fEE, fNE, fSEs});

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fNE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fEE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fSEs)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fNEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fNEActionPerformed
        // add new event

        if ( (new NewEventDialog( this )).showDialog() != null )
        {
            EventSelectionValidator.startEditUpdate();
            fScopes.updateEvents();
            fPatterns.updateEvents();
            EventSelectionValidator.stopEditUpdate();
        }
        
        this.requestFocus();
    }//GEN-LAST:event_fNEActionPerformed

    private void fMappingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMappingsActionPerformed
        // TODO add your handling code here:
        updateSELandMapping();
    }//GEN-LAST:event_fMappingsActionPerformed

    private void fENameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fENameActionPerformed
        // TODO add your handling code here:

        if ( !fEName.isSelected() )
        {
            fESpec.setSelected( true );
            Event.EventStringMethod = Event.E_Spec;
        }
        else
        {
            if ( fESpec.isSelected() )
                Event.EventStringMethod = Event.E_NameAndSpec;
            else
                Event.EventStringMethod = Event.E_Name;                
        }
        updateSELandMapping();
        repaint();
    }//GEN-LAST:event_fENameActionPerformed

    private void fESpecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fESpecActionPerformed
        // TODO add your handling code here:

        if ( !fESpec.isSelected() )
        {
            fEName.setSelected( true );
            Event.EventStringMethod = Event.E_Name;
        }
        else
        {
            if ( fEName.isSelected() )
                Event.EventStringMethod = Event.E_NameAndSpec;
            else
                Event.EventStringMethod = Event.E_Spec;                
        }
        updateSELandMapping();
        repaint();
    }//GEN-LAST:event_fESpecActionPerformed

    private void fSEsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSEsActionPerformed
        // add sample events
        
        newEvent( "P" );
        newEvent( "S" );
        newEvent( "T" );
        newEvent( "T1" );
        newEvent( "T2" );
        newEvent( "T3" );
        newEvent( "Q" );
        newEvent( "R" );
        newEvent( "Z" );
        newEvent( "ZS" );
        newEvent( "Z1" );
        newEvent( "Z2" );
        newEvent( "Z3" );

        fScopes.updateEvents();
        fPatterns.updateEvents();
        fSEs.setEnabled( false );
        
        this.requestFocus();
    }//GEN-LAST:event_fSEsActionPerformed

    private void fClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fClearActionPerformed
        // clear everything

        reset();
        
        updateScope();
        updatePattern();
        
        repaint();
        this.requestFocus();
    }//GEN-LAST:event_fClearActionPerformed

    private void fEEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fEEActionPerformed
        // edit events

        if ( (new EditEventDialog( this )).showDialog() != null )
        {
            EventSelectionValidator.startEditUpdate();
            fScopes.updateEvents();
            fPatterns.updateEvents();
            EventSelectionValidator.stopEditUpdate();
        }
        
        this.requestFocus();
    }//GEN-LAST:event_fEEActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton fClear;
    private javax.swing.JButton fEE;
    private javax.swing.JCheckBox fEName;
    private javax.swing.JCheckBox fESpec;
    private javax.swing.JTextArea fMapping;
    private javax.swing.JComboBox fMappings;
    private javax.swing.JButton fNE;
    private psp.ui.panels.pattern.PatternPanel fPatterns;
    private javax.swing.JTextArea fSELP;
    private javax.swing.JButton fSEs;
    private psp.ui.panels.scopes.ScopePanel fScopes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

}
