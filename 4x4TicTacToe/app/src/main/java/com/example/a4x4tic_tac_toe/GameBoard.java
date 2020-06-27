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
    boolean board_full;                 //denotes if board is full
    int turn_count = 0;                 //variable that keeps count of the number of turns played so far
    //Move move_log[];                    //log of all the moves played within the game
    Player players[];                   //list of players in the game



    GameBoard (Context context){
        this.context = context;
        board = new String[4][4];
        turn_count = 0;
    }

    public boolean is_board_full(){     //function to determine whether  a game board is full or not
        if (turn_count == 16){
            return true;
        }
        else
            return false;
    }

    public void reset_board(){          //function to reset the game board
        for(int row = 0; row < 4; row++){
            for (int col = 0; col < 4; col++){
                board[row][col] = "";
            }
        }
        turn_count = 0;
    }

    private boolean is_terminal_state(){        //function to check whether the board is full, or a player has accumulated 3 in a row
        //diagonal check
        //down right
        if (is_board_full()){
            return true;
        }
        for (int x = 0; x < 2; x++){
            for (int y = 0; y < 2; y++){
                if ((!board[x][y].equals(""))
                        && (board[x][y].equals(board[x+1][y+1]))
                        && (board[x][y].equals(board[x+2][y+2]))){
                    return true;
                }
            }
        }
        //down left
        for (int x = 0; x < 2; x++){
            for (int y = 3; y > 1; y--){
                if ((!board[x][y].equals(""))
                        && (board[x][y].equals(board[x+1][y-1]))
                        && (board[x][y].equals(board[x+2][y-2]))){
                    return true;
                }
            }
        }
        //horizontal
        for (int x = 0; x < 4; x++){
            for(int y = 0; y < 2; y++){
                if ((!board[x][y].equals(""))
                        && (board[x][y].equals(board[x][y+1]))
                        && (board[x][y].equals(board[x][y+2]))){
                    return true;
                }
            }
        }
        //vertical
        for (int x = 0; x < 2; x++){
            for(int y = 0; y < 4; y++){
                if ((!board[x][y].equals(""))
                        && (board[x][y].equals(board[x+1][y]))
                        && (board[x][y].equals(board[x+2][y]))){
                    return true;
                }
            }
        }
        return false;
    }
    void setBoard(int row, int col, String value){
        board[row][col] = value;
        turn_count++;
    }
    /*void undo_move(){                               //function to undo the previous 2 moves
        for (int x = 0; x < 2; x++) {
            board[move_log[turn_count].row][move_log[turn_count].column] = "";
            turn_count--;
        }
    }*/
    public int determine_winner(){
        if(is_terminal_state()){

        }

        return 0;
    }

    public void print_game_board(){
        Log.d("game_board_row 0", board[0][0] + board[0][1] + board[0][2] + board[0][3]);
        Log.d("game_board_row 0", board[1][0] + board[1][1] + board[1][2] + board[1][3]);
        Log.d("game_board_row 0", board[2][0] + board[2][1] + board[2][2] + board[2][3]);
        Log.d("game_board_row 0", board[3][0] + board[3][1] + board[3][2] + board[3][3]);
    }

}
