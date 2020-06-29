package com.example.a4x4tic_tac_toe;

import java.util.Random;

class AI_Player extends Player {

    AI_Player(){
        name = "AI Opponent";
        wins = 0;
        winStreak = 0;
    }
    public Move makeRandomMove(GameBoard game_board){                 //method for the AI agent to make a random move on a game board
        Move move = new Move();
        move.setValue("O");
        Random rand = new Random();
        move.row = rand.nextInt(4);
        move.column = rand.nextInt(4);
        boolean move_made = false;
        while (!move_made) {
            if (game_board.board[move.row][move.column].equals("")) {
                game_board.setBoard(move);
                move_made = true;
            }
            else {
                move.row = rand.nextInt(4);
                move.column = rand.nextInt(4);
            }
        }
        return move;
    }
}
