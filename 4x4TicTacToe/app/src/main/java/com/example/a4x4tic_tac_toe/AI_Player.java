package com.example.a4x4tic_tac_toe;

import android.util.Log;

import java.util.Random;

class AI_Player extends Player {

    AI_Player(){
        name = "AI Opponent";
        wins = 0;
        winStreak = 0;
    }

    public Move playRandomMove(GameBoard game_board){                 //method for the AI agent to make a random move on a game board
        Move move = new Move();
        move.setValue("O");
        Random rand = new Random();
        move.row = rand.nextInt(game_board.boardSize);
        move.column = rand.nextInt(game_board.boardSize);
        boolean move_made = false;
        while (!move_made) {
            if (game_board.board[move.row][move.column].equals("")) {           //Choose a random index on the board that does not have a mark yet
                game_board.setBoard(move);
                move_made = true;
            }
            else {
                move.row = rand.nextInt(game_board.boardSize);
                move.column = rand.nextInt(game_board.boardSize);
            }
        }
        return move;
    }

    /*public int minimaxAlgorithm(GameBoard gameBoard, int depth, Boolean turn){                      //Minimax algorithm
        int score = gameBoard.terminalStateStatus();
        if (score == 17){                                                                           //If
            return score - depth;
        }
        if (score == -17){
            return score + depth;
        }
        if (gameBoard.isBoardFull()){
            return 0;
        }
        if (turn){
            int best = -1000;
            for(int row = 0; row < gameBoard.boardSize; row++){
                for(int column = 0; column < gameBoard.boardSize; column++) {
                    if (gameBoard.board[row][column].equals("")){
                        gameBoard.board[row][column] = "O";
                        best = Math.max(best, minimaxAlgorithm(gameBoard, depth + 1, !turn));
                        gameBoard.board[row][column] = "";
                    }
                }
            }
            return best;
        }
        else{
            int best = 1000;
            for(int row = 0; row < gameBoard.boardSize; row++){
                for(int column = 0; column < gameBoard.boardSize; column++) {
                    if (gameBoard.board[row][column].equals("")){
                        gameBoard.board[row][column] = "X";
                        best = Math.min(best, minimaxAlgorithm(gameBoard, depth + 1, !turn));
                        gameBoard.board[row][column] = "";
                    }
                }
            }
            return best;
        }
    }*/

    public int minimaxAlphaBeta(GameBoard gameBoard, int depth, boolean turn, int alpha, int beta){
        int score = gameBoard.terminalStateStatus();
        if (gameBoard.boardSize == 3) {
            if (score == 10) {
                return score - depth;
            }
            if (score == -10) {
                return score + depth;
            }
            if (gameBoard.isBoardFull()) {
                return 0;
            }
        }
        if (gameBoard.boardSize == 4) {
            if (score == 17) {
                return score - depth;
            }
            if (score == -17) {
                return score + depth;
            }
            if (gameBoard.isBoardFull()) {
                Log.d("bestVal", "empty");
                return 0;
            }
        }
        if (turn){
            int best = -1000;
            for(int row = 0; row < gameBoard.boardSize; row++){
                for(int column = 0; column < gameBoard.boardSize; column++) {
                    if (gameBoard.board[row][column].equals("")){
                        gameBoard.board[row][column] = "O";
                        gameBoard.turnCount++;
                        best = Math.max(best, minimaxAlphaBeta(gameBoard, depth + 1, !turn, alpha, beta));
                        gameBoard.turnCount--;
                        Log.d("bestVal after AI's move", Integer.toString(best));
                        gameBoard.board[row][column] = "";
                        alpha = Math.max(alpha, best);
                        if(beta <= alpha){
                            break;
                        }
                    }
                }
            }
            return best;
        }
        else{
            int best = 1000;
            for(int row = 0; row < gameBoard.boardSize; row++){
                for(int column = 0; column < gameBoard.boardSize; column++) {
                    if (gameBoard.board[row][column].equals("")){
                        gameBoard.board[row][column] = "X";
                        //gameBoard.printGameBoard();
                        gameBoard.turnCount++;
                        best = Math.min(best, minimaxAlphaBeta(gameBoard, depth + 1, !turn, alpha, beta));
                        gameBoard.turnCount--;
                        Log.d("bestVal after my move", Integer.toString(best));
                        gameBoard.board[row][column] = "";
                        beta = Math.min(beta, best);
                        if(beta <= alpha){
                            break;
                        }
                    }
                }
            }
            return best;
        }
    }

    public Move playBestMove(GameBoard gameBoard){                                                  //helper function
        int bestValue = -1000;
        Move bestMove = new Move();
        bestMove.setRow(-1);
        bestMove.setColumn(-1);
        Log.d("bestVal", "Beginning best move search");
        for (int row = 0; row < gameBoard.boardSize; row++){
            for (int column = 0; column <  gameBoard.boardSize; column++){
                if(gameBoard.board[row][column].equals("")){
                    gameBoard.board[row][column] = "O";
                    gameBoard.turnCount++;
                    int moveValue = minimaxAlphaBeta(gameBoard, 0, false, -1000, 1000);
                    gameBoard.turnCount--;
                    gameBoard.board[row][column] = "";
                    if (moveValue > bestValue){
                        bestMove.setRow(row);
                        bestMove.setColumn(column);
                        bestMove.setValue("O");
                        bestValue = moveValue;
                    }
                }
            }
        }
        Log.d("The best move is", Integer.toString(bestMove.getRow()) + Integer.toString(bestMove.getColumn()));
        Log.d("bestVal", Integer.toString(bestValue));
        return bestMove;
    }
    public Move playMove(GameBoard gameBoard){
        if (gameBoard.turnCount > -1 && gameBoard.boardSize == 4){
            return playBestMove(gameBoard);
        }
        else
            return playRandomMove(gameBoard);
    }
}
