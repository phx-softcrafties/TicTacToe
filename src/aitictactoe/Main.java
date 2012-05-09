/*
 * Main.java
 *
 * Created on March 7, 2008, 3:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package aitictactoe;

import java.io.InputStreamReader;

/**
 *
 * @author nvaidyan
 */
public class Main {
    /** Creates a new instance of Main */
    public Main() {}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        InputStreamReader isr= new InputStreamReader(System.in);
        TicTacToe game = new TicTacToe(isr);
        game.playTicTacToe();
    }
}
