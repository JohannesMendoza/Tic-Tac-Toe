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

    private TextView textViewPlayer;
    private TextView textViewAI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    public void onClick(View v){
        if (!((Button) v).getText().toString().equals("")) {//if button that was clicked contains an empty string
            return;
        }
        if(playerTurn) {
            String id_of_button_clicked = v.getResources().getResourceName(v.getId());
            String button_row_token = id_of_button_clicked.substring(id_of_button_clicked.length()-2, id_of_button_clicked.length()-1);
            String button_column_token = id_of_button_clicked.substring(id_of_button_clicked.length()-1);
            /*Context context = getApplicationContext();
            CharSequence text = button_index_tokens;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();*/
            int row_value = Integer.parseInt(button_row_token);
            Log.d("row", button_row_token);
            int column_value = Integer.parseInt(button_column_token);
            Log.d("column", button_column_token);
            game_board.setBoard(row_value, column_value, "X");
            game_board.print_game_board();


            ((Button) v).setTextColor(getResources().getColor(R.color.blue));
            ((Button) v).setText("X");
            turnCount++;
            playerTurn = !playerTurn;
            Log.d("myTag3000", Integer.toString(turnCount));
            is_terminal_state();
        }
        get_ai_placement();
        playerTurn = !playerTurn;
        is_terminal_state();
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

    private boolean is_terminal_state(){
        //diagonal check
        //down right
        for (int x = 0; x < 2; x++){
            for (int y = 0; y < 2; y++){
                if ((!buttons[x][y].getText().toString().equals(""))
                        && (buttons[x][y].getText().toString().equals(buttons[x+1][y+1].getText().toString()))
                        && (buttons[x][y].getText().toString().equals(buttons[x+2][y+2].getText().toString()))){
                    Log.d("myTag", "WINNER!!!");
                    Log.d("myTag1", buttons[x][y].getText().toString());
                    Log.d("myTag2", buttons[x+1][y+1].getText().toString());
                    Log.d("myTag3", buttons[x+2][y+2].getText().toString());

                    return true;
                }
            }
        }
        //down left
        for (int x = 0; x < 2; x++){
            for (int y = 3; y > 1; y--){
                if ((!buttons[x][y].getText().toString().equals(""))
                        && (buttons[x][y].getText().toString() == buttons[x+1][y-1].getText().toString())
                        && (buttons[x][y].getText().toString() == buttons[x+2][y-2].getText().toString())){
                    Log.d("myTag", "WINNER!!!");
                    Log.d("myTag1", buttons[x][y].getText().toString());
                    Log.d("myTag2", buttons[x+1][y-1].getText().toString());
                    Log.d("myTag3", buttons[x+2][y-2].getText().toString());
                    return true;
                }
            }
        }
        //horizontal
        for (int x = 0; x < 4; x++){
            for(int y = 0; y < 2; y++){
                if ((!buttons[x][y].getText().toString().equals(""))
                        && (buttons[x][y].getText().toString() == buttons[x][y+1].getText().toString())
                        && (buttons[x][y].getText().toString() == buttons[x][y+2].getText().toString())) {
                    Log.d("myTag", "WINNER WINNER CHICKEN DINNER!!!");
                    Log.d("myTag1", buttons[x][y].getText().toString());
                    Log.d("myTag2", buttons[x][y+1].getText().toString());
                    Log.d("myTag3", buttons[x][y+2].getText().toString());
                    return true;
                }
            }
        }
        //vertical
        for (int x = 0; x < 2; x++){
            for(int y = 0; y < 4; y++){
                if ((!buttons[x][y].getText().toString().equals(""))
                        && (buttons[x][y].getText().toString() == buttons[x+1][y].getText().toString())
                        && (buttons[x][y].getText().toString() == buttons[x+2][y].getText().toString())) {
                    Log.d("myTag", "WINNER!!!");
                    Log.d("myTag1", buttons[x][y].getText().toString());
                    Log.d("myTag2", buttons[x+1][y].getText().toString());
                    Log.d("myTag3", buttons[x+2][y].getText().toString());
                    return true;
                }
            }
        }
        if (turnCount == 16){
            Log.d("myTag", "Draw");
            return true;
        }
        return false;
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
