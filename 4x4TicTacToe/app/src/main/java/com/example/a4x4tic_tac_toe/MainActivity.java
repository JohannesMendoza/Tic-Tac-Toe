package com.example.a4x4tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        for (int x = 0; x < 4; x++){
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
            ((Button) v).setText("X");
            is_terminal_state();
        }

        turnCount++;
    }

    private boolean is_terminal_state(){
        //diagonal check
        //down right
        for (int x = 0; x < 2; x++){
            for (int y = 0; y < 2; y++){
                if ((!buttons[x][y].getText().toString().equals(""))&& (buttons[x][y].getText().toString().equals(buttons[x+1][y+1].getText().toString())) && (buttons[x][y].getText().toString().equals(buttons[x+2][y+2].getText().toString()))){
                    Log.d("myTag", "WINNER!!!");
                    Log.d("myTag1", buttons[x][y].getText().toString());
                    Log.d("myTag2", buttons[x+1][y+1].getText().toString());
                    Log.d("myTag3", buttons[x+2][y+2].getText().toString());
                    //return true;
                }
            }
        }
        //down left
        for (int x = 0; x < 2; x++){
            for (int y = 3; y > 1; y--){
                if ((!buttons[x][y].getText().toString().equals("")) && (buttons[x][y].getText().toString() == buttons[x+1][y-1].getText().toString()) && (buttons[x][y].getText().toString() == buttons[x+2][y-2].getText().toString())){
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
                if ((!buttons[x][y].getText().toString().equals("")) && (buttons[x][y].getText().toString() == buttons[x][y+1].getText().toString()) && (buttons[x][y].getText().toString() == buttons[x][y+2].getText().toString())) {
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
                if ((!buttons[x][y].getText().toString().equals("")) && (buttons[x][y].getText().toString() == buttons[x+1][y].getText().toString()) && (buttons[x][y].getText().toString() == buttons[x+2][y].getText().toString())) {
                    Log.d("myTag", "WINNER!!!");
                    Log.d("myTag1", buttons[x][y].getText().toString());
                    Log.d("myTag2", buttons[x+1][y].getText().toString());
                    Log.d("myTag3", buttons[x+2][y].getText().toString());
                    return true;
                }
            }
        }
        if (turnCount == 16){
            return true;
        }
        return false;
    }
}
