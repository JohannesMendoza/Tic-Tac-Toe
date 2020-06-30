//GameBoard.java
//Johannes Mendoza
//06/25/20
//A class to represent the internal structure of a 4x4 Tic-Tac-Toe game board
package com.example.a4x4tic_tac_toe;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

public class GameBoard {
    Context context;
    String[][] board;                   //2D array representation of the board
    boolean boardFull;                 //denotes if board is full
    int turnCount = 0;                 //variable that keeps count of the number of turns played so far
    //Move moveLog[];                    //log of all the moves played within the game
    Player players[] = new Player[2];                   //list of players in the game
    boolean player1sTurn;



    GameBoard (Context context, Player player1, Player player2){
        this.context = context;
        board = new String[4][4];
        resetBoard();
        players[0] = player1;
        players[1] = player2;
        player1sTurn = true;
    }

    public boolean isBoardFull(){     //function to determine whether  a game board is full or not
        if (turnCount == 16){
            return true;
        }
        else
            return false;
    }
    public boolean isPlayer1sTurn(){
        return player1sTurn;
    }

    public void resetBoard(){          //function to reset the game board
        for(int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){
                board[row][col] = "";
            }
        }
        turnCount = 0;
    }

    public int terminalStateStatus(){        //function to check whether the board is full, or a player has accumulated 3 in a row
        //diagonal check
        //down right
        for (int x = 0; x < 2; x++){
            for (int y = 0; y < 2; y++){
                if ((!board[x][y].equals(""))
                        && (board[x][y].equals(board[x+1][y+1]))
                        && (board[x][y].equals(board[x+2][y+2]))){
                    if (board[x][y].equals("X"))
                        return 2;
                    else
                        return 3;
                }
            }
        }
        //down left
        for (int x = 0; x < 2; x++){
            for (int y = 3; y > 1; y--){
                if ((!board[x][y].equals(""))
                        && (board[x][y].equals(board[x+1][y-1]))
                        && (board[x][y].equals(board[x+2][y-2]))){
                    if (board[x][y].equals("X"))
                        return 2;
                    else
                        return 3;
                }
            }
        }
        //horizontal
        for (int x = 0; x < 4; x++){
            for(int y = 0; y < 2; y++){
                if ((!board[x][y].equals(""))
                        && (board[x][y].equals(board[x][y+1]))
                        && (board[x][y].equals(board[x][y+2]))){
                    if (board[x][y].equals("X"))
                        return 2;
                    else
                        return 3;
                }
            }
        }
        //vertical
        for (int x = 0; x < 2; x++){
            for(int y = 0; y < 4; y++){
                if ((!board[x][y].equals(""))
                        && (board[x][y].equals(board[x+1][y]))
                        && (board[x][y].equals(board[x+2][y]))){
                    if (board[x][y].equals("X"))
                        return 2;
                    else
                        return 3;
                }
            }
        }
        if (isBoardFull()){
            return 1;
        }
        return 0;
    }
    void setBoard(Move m){
        board[m.row][m.column] = m.value;
        turnCount++;
    }
    /*void undoMove(){                               //function to undo the previous 2 moves
        for (int x = 0; x < 2; x++) {
            board[moveLog[turnCount].row][moveLog[turnCount].column] = "";
            turnCount--;
        }
    }*/

    public boolean isDraw(){
        if(terminalStateStatus() == 0){
            return true;
        }
            return false;
    }

    /*public Player determineWinner(){
        if(terminalStateStatus() == 0){
            if(isPlayer1sTurn()){

            }
        }

        return 0;
    }*/

    public void printGameBoard(){
        Log.d("row_turns", Integer.toString(turnCount));
        Log.d("game_board_row 0", board[0][0] + board[0][1] + board[0][2] + board[0][3]);
        Log.d("game_board_row 1", board[1][0] + board[1][1] + board[1][2] + board[1][3]);
        Log.d("game_board_row 2", board[2][0] + board[2][1] + board[2][2] + board[2][3]);
        Log.d("game_board_row 3", board[3][0] + board[3][1] + board[3][2] + board[3][3]);
    }

}
