/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aitictactoe;

/**
 *
 * @author The_Master
 */
public class TicTacBoard implements Cloneable {
    private int boardSize;
    private char[][] gameBoard;
    private String XWinningString;
    private String OWinningString;
    
    public char[][] getGameBoard(){ return gameBoard;}
    public void setGameBoard(char[][] board){this.gameBoard = board;}
    public void setGameBoardPosition(int row, int column, char symbol)
    { 
        if((row < boardSize) || (column < boardSize)
                || (row > 0) || (column > 0))
            gameBoard[row][column] = symbol;
    }
    public int getBoardSize(){return boardSize;}
            
    TicTacBoard(int size)
    {
        boardSize = size;
        XWinningString = new String();
        OWinningString = new String();
        this.gameBoard = new char[size][size];
        for(int i=0; i<this.boardSize; i++ )
            for(int j=0; j<this.boardSize; j++)
                this.gameBoard[i][j] = '-';
        for(int index =0; index < this.boardSize; index++)
        {
            XWinningString+='X';
            OWinningString+='O';
        }
    }
    /* Get the string for each row, and compare it against the winning X string 
     and winning O string. If a row matches, game over!*/  
    public boolean checkRowWin()
    {
        String currentRowString;
        boolean isWon = false;
                
        for(int rowIndex = 0; rowIndex < gameBoard.length-1; rowIndex++)
        {
            currentRowString= new String(gameBoard[rowIndex]);
           
            if(currentRowString.equals(XWinningString) || currentRowString.equals(OWinningString))
                isWon = true;
            
            if(isWon)
                break;
        }
            
        return isWon;
    }
    /* Get the string for each column, and compare it against the winning X string 
     and winning O string. If a column matches, game over!*/
    public boolean checkColumnWin()
    {
        String aColumn = new String();
        boolean isWon = false;
        for(int columnIndex = 0; columnIndex < gameBoard[0].length; columnIndex++)
        {
            for(int rowIndex=0; rowIndex < gameBoard.length; rowIndex++)
                aColumn+=gameBoard[rowIndex][columnIndex];
            
            if(aColumn.equals(XWinningString) || aColumn.equals(OWinningString))
                isWon = true;

            if(isWon)
                break;
        }
        return isWon;
    }
    /* Get the string for each diagnoal, and compare it against the winning X string 
     and winning O string. If a diagonal matches, game over!*/
    public boolean checkDiagonalWin()
    {
        String aDiagonal = new String();
        boolean isWon = false;
        for(int columnIndex = 0; columnIndex < boardSize; columnIndex++)
        {
            for(int rowIndex=0; rowIndex < boardSize; rowIndex++)
                if(rowIndex == columnIndex)
                    aDiagonal+=gameBoard[rowIndex][columnIndex];
            
            if(aDiagonal.equals(XWinningString) || aDiagonal.equals(OWinningString))
                isWon = true;

            if(isWon)
                break;
        }
        return isWon;
    }
    
    /*  Check if there are any more blank spaces. If not, game over!*/
    public boolean checkCatGame()
    {
        boolean isOver = true;
        for(int columnIndex = 0; columnIndex < gameBoard[0].length; columnIndex++)
            for(int rowIndex=0; rowIndex < gameBoard.length; rowIndex++)
                if(this.gameBoard[rowIndex][columnIndex] == '-')
                    isOver = false; 
        return isOver;
    }
     public void printBoard()
    {
        System.out.println("Current Game Board:");
        for(int i=0; i< this.boardSize; i++ )
        {
          for(int j=0; j< this.boardSize; j++)
          {
                System.out.format("%-5c", this.gameBoard[i][j]);
                System.out.print("\t");
          }
          System.out.println();
        }
    }
    public String toString()
    {
        StringBuilder representation = new StringBuilder();
        representation.append("Current Game Board:\n");
        for(int i=0; i< this.boardSize; i++ )
        {
          for(int j=0; j< this.boardSize; j++)
          {
                representation.append(String.format("%-5c", this.gameBoard[i][j]));
                representation.append("\t");
          }
          representation.append("\n");
        }
        return new String(representation);
    }
     public boolean isGameOver()
     {
        boolean rows = this.checkRowWin();
        boolean columns = this.checkColumnWin();
        boolean diagonal = this.checkDiagonalWin();
        boolean catGame = this.checkCatGame();
        return (rows || columns || diagonal || catGame); 
    }
    
     public Object clone()
     {
         TicTacBoard theClone = new TicTacBoard(this.boardSize);
         for(int i =0; i< theClone.getBoardSize(); i++)
             for(int j =0; j< theClone.getBoardSize(); j++)
                 theClone.setGameBoardPosition(i, j, this.gameBoard[i][j]);
         
         return theClone;
     }
}
