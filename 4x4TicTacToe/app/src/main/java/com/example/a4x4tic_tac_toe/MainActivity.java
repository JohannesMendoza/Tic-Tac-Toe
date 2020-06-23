package com.example.a4x4tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

    public void get_ai_placement(){
        Random rand = new Random();
        int x_value = rand.nextInt(4);
        int y_value = rand.nextInt(4);
        boolean move_made = false;
        while (!move_made) {
            if (buttons[x_value][y_value].getText().toString().equals("")) {
                buttons[x_value][y_value].setTextColor(getResources().getColor(R.color.red));
                buttons[x_value][y_value].setText("O");
                move_made = true;
                turnCount++;
            }
            else {
                x_value = rand.nextInt(4);
                y_value = rand.nextInt(4);
            }
        }
    }
    public int determine_winner(){
        if(is_terminal_state()){

        }

        return 0;
    }
}
