
package aitictactoe;
/*
 * TicTacToe.java
 *
 * Created on March 7, 2008, 3:04 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
import java.io.*;
import java.util.Random;
/**
 *
 * @author nvaidyan
 */
public class TicTacToe {
    
    private int boardSize;
    private int lookAhead;
    private boolean ABpruning;
    private boolean humanPlaying;
    private boolean gameOver;
    private InputStreamReader isr;
    private BufferedReader stdin;
    private TicTacBoard playWith;
        
    
    /** Creates a new instance of TicTacToe */
    public TicTacToe(InputStreamReader isr) {
        this.lookAhead =0;
        this.ABpruning =false;
        this.humanPlaying = false;
        this.gameOver = false;
        stdin= new BufferedReader(isr);
        initialize(stdin);
    }
    
    public void playTicTacToe()
    {
       /* while the game is not over, we need to continue playing.
        * first, we see if it is the human's turn. if so, they are prompted
        to make a move. If not, the machine makes a move. We then print the
        * current game position, and we check the game status to determine if
        * we should continue playing */
       while(!gameOver)
       {
            if(humanPlaying)
                promptForInput();
            else
                machineMove();
            
            playWith.printBoard();
            gameOver = playWith.isGameOver();
            humanPlaying = !humanPlaying;
       }
    }
    
    /* Since machine has to move, we have to do a minmax search to determine best move
     * then place an X there. Once done, set the turn to human by toggling humanPlaying
     */
    private void machineMove()
    {
      int[] moveToMake = new int[2];
      //determine the appropriate position
      if(this.ABpruning)
          moveToMake =alphaBetaSearch();
      else
          moveToMake = minMaxSearch();
      //make the move in the appropriate position 
      if((moveToMake[0] == -1 ) && (moveToMake[0] == -1 ))
          randomize(moveToMake);
    }
    
    private void randomize(int[] moveToMake)
    {
      moveToMake[0] = new Random().nextInt(this.boardSize);
      moveToMake[1] = new Random().nextInt(this.boardSize);
      while(!(playWith.getGameBoard()[moveToMake[0]][moveToMake[1]] == '-'))
      {
         moveToMake[0] = new Random().nextInt(this.boardSize);
         moveToMake[1] = new Random().nextInt(this.boardSize);
      } 
      
      playWith.setGameBoardPosition(moveToMake[0], moveToMake[1], 'X');  
    }
            
    /* Skeleton for alphaBetaSearch */
    private int[]alphaBetaSearch()
    {
        int[] i = {-1,-1}; 
        return i;
    }
    
    /* Skeleton for minMaxSearch */
    private int[] minMaxSearch()
    {
        int[] action = {-1,-1};
        TicTacSearchTree toSearch = new TicTacSearchTree(boardSize);
        action = toSearch.minimaxDecision(toSearch.getRoot());
        return action;
    }
    
    
    private void initialize(BufferedReader stdin)
    {
        String temp = new String();
        boardSize =0;
        
        try
        {    
            System.out.println("Enter in the size of the desired tic-tac-toe board");
            boardSize = Integer.parseInt(stdin.readLine());
             if(((boardSize != 3) && (boardSize != 5) && (boardSize != 7)) || ((boardSize == 0)))
                throw new java.lang.IllegalArgumentException("Inproper board size choice");

            System.out.println("Enter in the look ahead depth for the search");
            temp = stdin.readLine();
            this.lookAhead = Integer.parseInt(temp);
            if(this.lookAhead < 0)
                throw new java.lang.IllegalArgumentException("Look ahead value must be positive!");

            System.out.println("I should do alpha pruning, true or false?");
            temp = stdin.readLine();
            if((temp.equalsIgnoreCase("true")) || (temp.equalsIgnoreCase("false")))
                this.ABpruning = Boolean.parseBoolean(temp);
            else
                throw new java.lang.IllegalArgumentException("Enter in true or false!");

            System.out.println("Who should play first, Human or Computer?");
            temp = stdin.readLine();
            if(!((temp.equalsIgnoreCase("Human")) || (temp.equalsIgnoreCase("Computer"))))
                throw new java.lang.IllegalArgumentException("Specify who plays!");
            else
            {
                if(temp.equalsIgnoreCase("Human"))
                    this.humanPlaying =true;
                else
                    this.humanPlaying =false;
            }
       }
       catch(IOException e)
       { System.out.println("Looks like something happened");}
       playWith = new TicTacBoard(boardSize);
        
        
        
    }
    private void promptForInput()
    {
        String temp = new String();
        int row =0;
        int column=0;
        try
        {
            System.out.println("Select a location for your O to be placed");
            System.out.println("First enter the row number, ranging from 1 to " + playWith.getBoardSize());
            temp = stdin.readLine();
            row = Integer.parseInt(temp);
            if((row > playWith.getBoardSize()) || (row <= 0))
                throw new java.lang.IllegalArgumentException("Row must be valid!");
            
            System.out.println("Now enter the column number, ranging from 1 to " + playWith.getBoardSize());
            temp = stdin.readLine();
            column = Integer.parseInt(temp);
            if((column > playWith.getBoardSize()) || (column <= 0))
                throw new java.lang.IllegalArgumentException("Column must be valid!");
            
            if(playWith.getGameBoard()[row-1][column-1] == '-')
                    playWith.setGameBoardPosition(row-1, column-1, 'O');
            else
                System.out.println("Sorry, that space is full!");
            
        }
        catch(IOException e)
        {
            throw new java.lang.IllegalArgumentException("Enter in a correct placement!");
        }
        
    }
    
   
}