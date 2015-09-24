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

package psp.ui.panels.pattern.order.chains;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import psp.engine.PSPController;
import psp.sel.patterns.order.ChainEvent;
import psp.sel.patterns.order.ChainEvents;
import psp.ui.panels.pattern.order.ChainChanged;

public class ResponseChainEventSequencePanel extends javax.swing.JPanel implements ChainEventSequencePanelFeatures
{
    private PSPController fPSPController;
    private ArrayList<ChainEvent> fEventSequence;
    private ChainChanged fListener;

    private GridLayout fEventPanels;
    private JPanel fEventPanel;
    
    public ResponseChainEventSequencePanel() 
    {
        fEventSequence = new ArrayList<ChainEvent>();

        init();
    }

    private void init()
    {
        // Border layout (stacked)
        setLayout( new BorderLayout( 0, 0 ) );

        fEventPanel = new JPanel( new GridLayout( 0, 1, 0, 0 ) );

        // add event panel 
        add( fEventPanel );
        
        // add default T1
        addPanel( new ResponseChainEventPanel() );
    }

    public void addPanel( ResponseChainEventPanel aEventPanel )
    {
        aEventPanel.setController( fPSPController );
        aEventPanel.setHostPanel( this );
        fEventPanel.add( aEventPanel );
        fEventSequence.add( aEventPanel.getChainEvent() );
        
        if ( fPSPController != null )
            fPSPController.getHostFrame().validate();
        
        updateSelection();
    }
    
    public void deletePanel( ResponseChainEventPanel aEventPanel )
    {
        if ( fEventPanel.getComponentCount() > 1 )
        {
            fEventPanel.remove( aEventPanel );
            fEventSequence.remove( aEventPanel.getChainEvent() );
        
            if ( fPSPController != null )
                fPSPController.getHostFrame().validate();
            
            updateSelection();
        }
    }

    public void updateSelection()
    {
        if ( fListener != null )
            fListener.updateChain( new ChainEvents( fEventSequence ) );
    }
    
    public ArrayList<ChainEvent> getChainSequence() 
    {
        return fEventSequence;
    }

    public void setChainSequence(ArrayList<ChainEvent> aChainSequence) 
    {
        fEventSequence = aChainSequence;
        
        // update panels
                
        fEventPanel.removeAll();
        
        for ( ChainEvent ce : fEventSequence )
        {
            ResponseChainEventPanel lEventPanel = new ResponseChainEventPanel();            
            lEventPanel.setChainEvent( ce );
            lEventPanel.setController( fPSPController );
            lEventPanel.setHostPanel( this );
            fEventPanel.add( lEventPanel );
        }
        
        if ( fPSPController != null )
            fPSPController.getHostFrame().validate();
        
        updateSelection();
    }

    public void setController( PSPController aPSPController ) 
    {
        fPSPController = aPSPController;
        
        for ( Component p : fEventPanel.getComponents() )
        {
            ((ResponseChainEventPanel)p).setController( fPSPController );
        }
    }

    public void updateEvents() 
    {
        for ( Component p : fEventPanel.getComponents() )
        {
            ((ResponseChainEventPanel)p).updateEvents();
        }
    }

    public void setChainUpdateListener(ChainChanged aListener) 
    {
        fListener = aListener;
    }
}
    