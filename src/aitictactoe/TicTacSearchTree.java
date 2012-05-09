/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package aitictactoe;

/**
 *
 * @author The_Master
 */
public class TicTacSearchTree {
    private class Node{
        public int utility;
        public TicTacBoard currentPosition;
        public java.util.ArrayList<Node> children;
 
        Node(int value, TicTacBoard board)
        { 
            utility = value; 
            children = new java.util.ArrayList();
            currentPosition = (TicTacBoard) board.clone();
        }
        void addChild(Node e) { children.add(e);};
        void setUtility(int value){ utility = value; }
        public String toString(){return "Value:" + this.utility + "\n" + currentPosition;}
    }
    
    private Node root;
    public  Node getRoot(){return root;}
    boolean isX; 
    int i= 0;
    
    TicTacSearchTree(int size)
    {
        isX = true;
        root = new Node(0, new TicTacBoard(size));
        buildTree(root);
    }
    TicTacSearchTree(TicTacBoard game)
    {
        isX = true;
        root = new Node(0, game);
    }
    TicTacSearchTree(int value, int size, TicTacBoard game )
    {
        isX = true;
        root = new Node(value, game);
    }
    
    
    void buildTree(Node daNode)
    {
        if(daNode.currentPosition.isGameOver())
            return;
        
       Node boo = new Node(0,daNode.currentPosition);
       boo = SetNodePos(boo);
       
       daNode.children.add(boo);
       System.out.println("Boo! " + boo);
       isX = !isX;
                
        for (Node m : daNode.children)
                        buildTree(m);
    }
    
    Node SetNodePos(Node boo)
    {
        StringBuilder buffy = new StringBuilder();
        buffy.append(boo.currentPosition.getGameBoard()[0]);
        buffy.append(boo.currentPosition.getGameBoard()[1]);
        buffy.append(boo.currentPosition.getGameBoard()[2]);
        
        String positions = new String(buffy);
        if(isX)
            positions = positions.replaceFirst("-", "X");
        else
            positions = positions.replaceFirst("-", "O");
        System.out.println( positions );
        for(int rows =0; rows < boo.currentPosition.getBoardSize(); rows++)
            for(int columns =0; columns < boo.currentPosition.getBoardSize(); columns++)
                boo.currentPosition.setGameBoardPosition(rows, columns, positions.toCharArray()[rows+columns]);
        
        return boo;
    }
    
    public int[] minimaxDecision(Node n)
    {
        int value = maxValue(n);
        Node action = new Node(0, n.currentPosition);
        for(Node c : n.children)
            if(c.utility == value)
            {
                action = c;
                break;
            }
        int[] decision =findDifference(action, n);
        return decision;
    }
    
    private int[] findDifference(Node a, Node b)
    {
        for(int rows=0; rows<a.currentPosition.getBoardSize(); rows++)
            for(int columns=0; columns<a.currentPosition.getBoardSize(); columns++)
                if(a.currentPosition.getGameBoard()[rows][columns] != b.currentPosition.getGameBoard()[rows][columns])
                    return new int[]{rows, columns};
    
        return new int[] {0,0};
    }
    private int maxValue(Node n)
    {
        if(n.currentPosition.isGameOver())
            return n.utility;
        
        int value =0;
        for(Node c : n.children )
            value = Math.max(value, minValue(c));
        return value;
    }
    
    private int maxValue(Node n, int alpha, int beta)
    {
        if(n.currentPosition.isGameOver())
            return n.utility;
        
        int value = Integer.MIN_VALUE;
        for (Node child : n.children)
        {
            value = Math.max(value, minValue(child, alpha, beta));
            if(value >= beta)
                return value;
            alpha = Math.max(alpha, value);
        }
     return value;    
    }
    private int minValue(Node n)
    {
        if(n.currentPosition.isGameOver())
            return n.utility;
        
        int value =0;
        for(Node c : n.children )
            value = Math.min(value, maxValue(c));
        return value;
    }
    private int minValue(Node n, int alpha, int beta)
    {
        if(n.currentPosition.isGameOver())
            return n.utility;
        int value = Integer.MAX_VALUE;
        
        for(Node child : n.children)
        {
            value = Math.min(value, maxValue(child, alpha, beta));
            if (value < alpha)
                    return value;
            beta = Math.min(beta, value);
        }
        return value;
    }
    
    public int[] alphaBetaSearch(Node n)
    {
        int value = maxValue(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
         Node action = new Node(0, n.currentPosition);
        for(Node c : n.children)
            if(c.utility == value)
            {
                action = c;
                break;
            }
        int[] decision =findDifference(action, n);
        return decision;
        
    }
}