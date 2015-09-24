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

package psp.ui.panels.scopes;

import psp.engine.EventSelectionValidator;
import psp.engine.PSPConstants;
import psp.engine.PSPController;
import psp.sel.scopes.Scope;

public class ScopePanel extends javax.swing.JPanel implements ScopePanelFeatures
{
    private PSPController fPSPController;
    private Scope fSelectedScope;
    
    public ScopePanel() 
    {
        initComponents();

        fScopes.setSelectedIndex( 0 );
        fSelectedScope = ((ScopePanelFeatures)fScopes.getSelectedComponent()).getSelectedScope();
    }
    
    public Scope getSelectedScope()
    {
        return fSelectedScope;
    }

    public void setSelectedScope( Scope aSelectedScope )
    {
        fSelectedScope = aSelectedScope;

        if ( fSelectedScope == null )
        {
            fScopes.setSelectedIndex( 0 );
            fGlobally.setSelectedScope( aSelectedScope );
        }
        else
        {
            switch (fSelectedScope.getType() )
            {
                case PSPConstants.S_BeforeR:
                    fScopes.setSelectedIndex( 1 );
                    fBeforeR.setSelectedScope( aSelectedScope );
                    break;
                case PSPConstants.S_AfterQ:
                    fScopes.setSelectedIndex( 2 );
                    fAfterQ.setSelectedScope( aSelectedScope );
                    break;
                case PSPConstants.S_BetweenQandR:
                    fScopes.setSelectedIndex( 3 );
                    fBetweenQandR.setSelectedScope( aSelectedScope );
                    break;
                case PSPConstants.S_AfterQuntilR:
                    fScopes.setSelectedIndex( 4 );
                    fAfterQuntilR.setSelectedScope( aSelectedScope );
                    break;
                default:
                    fScopes.setSelectedIndex( 0 );
                    fGlobally.setSelectedScope( aSelectedScope );
                break;
            }
        }
    }

    public void clearSelection()
    {
        fGlobally.clearSelection();
        fBeforeR.clearSelection();
        fAfterQ.clearSelection();
        fBetweenQandR.clearSelection();
        fAfterQuntilR.clearSelection();
        
        setSelectedScope( fGlobally.getSelectedScope() );
    }
    
    public void setController( PSPController aPSPController )
    {
        fPSPController = aPSPController;

        fGlobally.setController( aPSPController );
        fBeforeR.setController( aPSPController );
        fAfterQ.setController( aPSPController );
        fBetweenQandR.setController( aPSPController );
        fAfterQuntilR.setController( aPSPController );
    }

    public void updateEvents()
    {
        fGlobally.updateEvents();
        fBeforeR.updateEvents();
        fAfterQ.updateEvents();
        fBetweenQandR.updateEvents();
        fAfterQuntilR.updateEvents();
    }
    
    private void updateSelectedScope( int aPanelIndex )
    {
        ScopePanelFeatures lScopePanel;
        
        switch ( aPanelIndex )
        {
            case 1:
                lScopePanel = fBeforeR;
                break;
            case 2:
                lScopePanel = fAfterQ;
                break;
            case 3:
                lScopePanel = fBetweenQandR;
                break;
            case 4:
                lScopePanel = fAfterQuntilR;
                break;
            default:
                lScopePanel = fGlobally;
                break;
        }
        
        if ( lScopePanel != null )
            fSelectedScope = lScopePanel.getSelectedScope();
    
        if ( fPSPController != null )
            fPSPController.updateScope();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fScopes = new javax.swing.JTabbedPane();
        fGlobally = new psp.ui.panels.scopes.GloballyPanel();
        fBeforeR = new psp.ui.panels.scopes.BeforeRPanel();
        fAfterQ = new psp.ui.panels.scopes.AfterQPanel();
        fBetweenQandR = new psp.ui.panels.scopes.BetweenQandRPanel();
        fAfterQuntilR = new psp.ui.panels.scopes.AfterQuntilRPanel();

        setMaximumSize(new java.awt.Dimension(533, 115));
        setMinimumSize(new java.awt.Dimension(533, 115));

        fScopes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fScopesStateChanged(evt);
            }
        });
        fScopes.addTab("Globally", fGlobally);
        fScopes.addTab("Before R", fBeforeR);
        fScopes.addTab("After Q", fAfterQ);
        fScopes.addTab("Between Q and R", fBetweenQandR);
        fScopes.addTab("After Q until R", fAfterQuntilR);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fScopes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fScopes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fScopesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fScopesStateChanged
        // tab changed
        
        updateSelectedScope( fScopes.getSelectedIndex() );        
    }//GEN-LAST:event_fScopesStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private psp.ui.panels.scopes.AfterQPanel fAfterQ;
    private psp.ui.panels.scopes.AfterQuntilRPanel fAfterQuntilR;
    private psp.ui.panels.scopes.BeforeRPanel fBeforeR;
    private psp.ui.panels.scopes.BetweenQandRPanel fBetweenQandR;
    private psp.ui.panels.scopes.GloballyPanel fGlobally;
    private javax.swing.JTabbedPane fScopes;
    // End of variables declaration//GEN-END:variables
}
