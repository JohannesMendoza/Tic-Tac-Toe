package com.example.a4x4tic_tac_toe;

import java.util.Random;

class AI_Player extends Player {

    AI_Player(){
        name = "AI Opponent";
        wins = 0;
        win_streak = 0;
    }
    public Move make_basic_move(GameBoard game_board){
        Move move = new Move();
        Random rand = new Random();
        move.row = rand.nextInt(4);
        move.column = rand.nextInt(4);
        boolean move_made = false;
        while (!move_made) {
            if (game_board.board[move.row][move.column].equals("")) {
                game_board.setBoard(move.row, move.column, "O");
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
