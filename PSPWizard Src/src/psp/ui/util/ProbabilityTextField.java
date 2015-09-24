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

package psp.ui.util;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class ProbabilityTextField extends JTextField 
{
    public ProbabilityTextField()
    {
        super();
    }
    
    public ProbabilityTextField( Document doc, String text, int columns )
    {
        super( doc, text, columns );
    }

    public ProbabilityTextField( int cols ) 
    {
        super( cols );
    }

    public ProbabilityTextField( String aText ) 
    {
        super( aText );
    }
    
    public ProbabilityTextField( String text, int columns )
    {
        super( text, columns );
    }

    protected Document createDefaultModel() 
    {
        return new ProbabilityDocument();
    }
 
    private static class ProbabilityDocument extends PlainDocument 
    {
        private char lFirst;
        
        public void insertString( int offs, String str, AttributeSet a ) throws BadLocationException
        {
            if (str == null) 
            {
                return;
            }
            
            int lOffset = offs; // start
            boolean lInsert = true;
            char[] chars = str.toCharArray();
            
            // for all added characters
            
            for ( int i = 0; i < chars.length; i++ )
            {
                switch ( lOffset )
                {
                    case 0: // first character: 0 or 1
                        if ( chars[i] == '0' || chars[i] == '1' )
                        {
                            lFirst = chars[i];
                            lOffset++;
                        }
                        else
                            lInsert = false;
                        break;
                    case 1: // second character: .
                        if ( chars[i] == '.' )
                            lOffset++;
                        else
                            lInsert = false;
                        break;
                    default: // third and above: #
                        if ( Character.isDigit( chars[i] ) )
                        {
                            if ( lFirst == '1' && chars[i] != '0' )
                                lInsert = false;
                            else
                                lOffset++;
                        }
                        else
                            lInsert = false;
                        break;
                }

                if ( !lInsert )
                    break;
            }
            
            if ( lInsert )
                super.insertString( offs, str, a );
         }
     }
 }
