package com.example.a4x4tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static Context context;
    GameBoard game_board = new GameBoard(context);
    private Button[][] buttons = new Button[4][4];
    private boolean playerTurn = true;
    private int turnCount;
    private int playerPoints;
    private int AIPoints;
    Player player = new Player();
    AI_Player AI = new AI_Player();

    private TextView textViewPlayer;
    private TextView textViewAI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {                                            //method to set up a player, AI player, the internal game board, and the
                                                                                                    //GUI for the game board
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer = findViewById(R.id.text_view_player);
        textViewAI = findViewById(R.id.text_view_AI);
        for (int x = 0; x < 4; x++){                                                                //for all spaces in the grid
            for (int y = 0; y < 4; y++){
                String buttonID = "button_" + x + y;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName()); //fetches IDs of the views dynamically  within the loop
                buttons[x][y] = findViewById(resID);
                buttons[x][y].setOnClickListener(this);
            }
        }

        Button resetButton = findViewById(R.id.button_reset);
    }
    @Override
    public void onClick(View v){                            //function for action when a button on the grid is clicked
        if (!((Button) v).getText().toString().equals("")) {//if button that was clicked contains an empty string
            return;
        }
        setBoard(get_move_from_button_click(v, "X"));

    }
    //function that resets the whole board to an empty state and the number of turns to 0
    public void reset_board(View v){
        for(int x = 0; x < 4; x++){
            for (int y = 0; y < 4; y++){
                buttons[x][y].setText("");
            }
        }
        turnCount = 0;
    }
    //function that determines if the board is full
    /*private boolean board_full(){

    }*/

    public Move get_move_from_button_click(View v, String symbol){
        Move move = new Move();
        String id_of_button_clicked = v.getResources().getResourceName(v.getId());                                                      //set ID of button clicked to variable
        String button_row_token = id_of_button_clicked.substring(id_of_button_clicked.length()-2, id_of_button_clicked.length()-1);     //take the row denoted in string and set it to a variable
        String button_column_token = id_of_button_clicked.substring(id_of_button_clicked.length()-1);                                   //take the column denoted in string and set it to a variable

        move.row = Integer.parseInt(button_row_token);                                     //Convert row substring into an integer
        move.column = Integer.parseInt(button_column_token);                               //Convert column substring into an integer
        move.value = "X";

        game_board.setBoard(move);                               //use row/column indices to place mark on internal game_board
        game_board.print_game_board();
        return move;

    }

    public void setBoard(Move move){
        /*if (!((Button) v).getText().toString().equals("")) {//if button that was clicked contains an empty string
            return;
        }
        if(playerTurn) {
            String id_of_button_clicked = v.getResources().getResourceName(v.getId());                                                      //set ID of button clicked to variable
            String button_row_token = id_of_button_clicked.substring(id_of_button_clicked.length()-2, id_of_button_clicked.length()-1);     //take the row denoted in string and set it to a variable
            String button_column_token = id_of_button_clicked.substring(id_of_button_clicked.length()-1);                                   //take the column denoted in string and set it to a variable

            int row_value = Integer.parseInt(button_row_token);                                     //Convert row substring into an integer
            int column_value = Integer.parseInt(button_column_token);                               //Convert column substring into an integer
            game_board.setBoard(row_value, column_value, "X");                               //use row/column indices to place mark on internal game_board
            game_board.print_game_board();

            ((Button) v).setTextColor(getResources().getColor(R.color.blue));
            ((Button) v).setText("X");
            turnCount++;
            playerTurn = !playerTurn;
            Log.d("myTag3000", Integer.toString(turnCount));
            is_terminal_state();
        }
        AI.make_random_move(game_board);
        playerTurn = !playerTurn;
        is_terminal_state();*/
        if (move.value.equals('X')) {
            buttons[move.row][move.column].setTextColor(getResources().getColor(R.color.blue));
        }
        else{
            buttons[move.row][move.column].setTextColor(getResources().getColor(R.color.red));
        }
        buttons[move.row][move.column].setText(move.value);
    }


    public int determine_winner(){
        if(is_terminal_state()){

        }

        return 0;
    }

    /*public Move get_ai_move(){

    }

    public int minimax (String[][] board, int depth, Boolean player){

    }*/
}
