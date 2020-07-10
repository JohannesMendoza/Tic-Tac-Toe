package com.example.a4x4tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe4x4Activity extends AppCompatActivity implements View.OnClickListener{

    public static Context context;
    Player player = new Player();
    AI_Player AI = new AI_Player();
    GameBoard gameBoard = new GameBoard(context, player, AI);
    private Button[][] buttons = new Button[4][4];
    private boolean playerTurn = true;
    private int turnCount;
    private int playerPoints;
    private int AIPoints;
    private boolean gameOver;
    String difficulty = "easy";
    String firstMove = "lol";


    private TextView textViewPlayer;
    private TextView textViewAI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {                                            //method to set up a player, AI player, the internal game board, and the
        //GUI for the game board
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe4x4);
        gameOver = false;
        Intent intent = getIntent();
        difficulty = intent.getStringExtra("difficulty");
        firstMove = intent.getStringExtra("firstMove");

        Log.d("diff", difficulty + " " + firstMove);

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

        if (firstMove.equals("O")){
            Move AIMove = new Move();
            AIMove = AI.playRandomMove(gameBoard);
            gameBoard.setBoard(AIMove);
            setBoard(AIMove);
        }
    }
    @Override
    public void onClick(View v){                                                                    //function for action when a button on the grid is clicked

        if(gameOver == false) {
            if (!((Button) v).getText().toString().equals("")) {                                        //if button that was clicked contains an empty string
                return;
            }
            gameBoard.setBoard(getMoveFromButtonClick(v, "X"));                             //use row/column indices to place mark on internal game_board
            setBoard(getMoveFromButtonClick(v, "X"));                                       //Place mark on graphical game board for user to see
            int status = gameBoard.terminalStateStatus();                                           //check game board to see if the game should be ended
            //Log.d("turnstatus", Integer.toString(gameBoard.turnCount));
            if(status != 0){                                                                        //If the game is over, proceed with the game over process
                gameOverProtocol(status);
            }
            else {                                                                                  //if the game is not over, Allow the AI to make a move
                Move AIMove = new Move();
                if(gameBoard.turnCount > 2) {
                    AIMove = AI.playBestMove(gameBoard);
                    gameBoard.setBoard(AIMove);
                    setBoard(AIMove);
                }
                else {                                                                              //Allow the AI to make a random move for the first 2 moves to speed up time (Will be moved)
                    AIMove = AI.playRandomMove(gameBoard);
                    //gameBoard.setBoard(AIMove);
                    setBoard(AIMove);
                }
                //Log.d("turnstatus", Integer.toString(gameBoard.turnCount));
                //Log.d("status", "below this is the current board");
                //gameBoard.printGameBoard();
                status = gameBoard.terminalStateStatus();                                           //Check if the game is over, and proceed with game over process if it is
                if(status != 0){
                    gameOverProtocol(status);
                }
            }
        }
    }
    public void resetBoard(View v){                                                                //function that resets the whole board to an empty state and the number of turns to 0
        for(int x = 0; x < 4; x++){
            for (int y = 0; y < 4; y++){
                buttons[x][y].setText("");
            }
        }
        turnCount = 0;
        gameOver = false;
        gameBoard.resetBoard();
    }
    //function that determines if the board is full
    /*private boolean board_full(){

    }*/

    public Move getMoveFromButtonClick(View v, String symbol){
        Move move = new Move();
        String IDOfButtonClicked = v.getResources().getResourceName(v.getId());                                                      //set ID of button clicked to variable
        String buttonRowToken = IDOfButtonClicked.substring(IDOfButtonClicked.length()-2, IDOfButtonClicked.length()-1);     //take the row denoted in string and set it to a variable
        String buttonColumnToken = IDOfButtonClicked.substring(IDOfButtonClicked.length()-1);                                   //take the column denoted in string and set it to a variable

        move.setRow(Integer.parseInt(buttonRowToken));                                     //Convert row substring into an integer
        move.setColumn(Integer.parseInt(buttonColumnToken));                               //Convert column substring into an integer
        move.setValue("X");
        return move;

    }
    public void setBoard(Move move){                                                                //function that places marks with the appropriate color on the GUI
        if (move.getValue().equals("X")) {
            buttons[move.getRow()][move.getColumn()].setTextColor(getResources().getColor(R.color.blue));
        }
        else{
            buttons[move.getRow()][move.getColumn()].setTextColor(getResources().getColor(R.color.red));
        }
        buttons[move.getRow()][move.getColumn()].setText(move.getValue());
    }

    public void displayResult(int result){                                                          //Function to display a message of the results of the game
        Context context = getApplicationContext();
        Toast toast;
        int duration = Toast.LENGTH_LONG;

        switch (result){
            case 1:
                toast = Toast.makeText(context, "The game is a draw!", duration);
                toast.show();
                break;
            case -17:
                toast = Toast.makeText(context, "You have won!", duration);
                toast.show();
                break;
            case 17:
                toast = Toast.makeText(context, AI.getName() + " has won!", duration);
                toast.show();
                break;
        }
    }
    public void gameOverProtocol(int status){                                                       //function to be called when the game is over
        gameOver = true;
        displayResult(status);
    }
}