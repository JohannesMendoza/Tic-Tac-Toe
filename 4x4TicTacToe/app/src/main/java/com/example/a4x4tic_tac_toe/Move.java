package com.example.a4x4tic_tac_toe;
//class that describes the coordinates of the game board chosen when the AI makes its move, with fields row and column
public class Move {
    int row;
    int column;
    String value;

    int getRow(){
        return row;
    }
    int getColumn(){
        return column;
    }
    String getValue(){
        return value;
    }
    void setRow(int r){
        row = r;
    }
    void setColumn(int c){
        column = c;
    }
    void setValue(String v){
        value = v;
    }
}

