package com.example.a4x4tic_tac_toe;

//class to represent a player within the Tic-Tac-Toe game;
class Player {
    String name;
    int wins;
    int winStreak;

    Player(){
        name = "Player 1";
        wins = 0;
        winStreak = 0;
    }
    String getName(){
        return name;
    }
    int getWins(){
        return wins;
    }
    int getWinStreak(){
        return winStreak;
    }


}
