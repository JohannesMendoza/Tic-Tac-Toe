package com.example.a4x4tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button boardButtons[] = new Button[2];
    Button difficultyButtons[] = new Button[3];
    Button firstMoveButtons[] = new Button[3];
    int gameBoardChosen = 0;
    String difficultyChosen = "easy";
    String firstMoveChosen = "X";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        boardButtons[0] = findViewById(R.id.board3x3button);
        boardButtons[1] = findViewById(R.id.board4x4button);
        difficultyButtons[0] = findViewById(R.id.easybutton);
        difficultyButtons[1] = findViewById(R.id.mediumbutton);
        difficultyButtons[2] = findViewById(R.id.hardbutton);
        firstMoveButtons[0] = findViewById(R.id.Xbutton);
        firstMoveButtons[1] = findViewById(R.id.Obutton);
        firstMoveButtons[2] = findViewById(R.id.XObutton);
    }

    public void changeBoard(View v) {
        switch (v.getId()) {
            case (R.id.board3x3button):
                ViewCompat.setBackgroundTintList(v, ContextCompat.getColorStateList(getApplicationContext(), android.R.color.holo_orange_light));
                ViewCompat.setBackgroundTintList(boardButtons[1], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                break;
            case (R.id.board4x4button):
                ViewCompat.setBackgroundTintList(v, ContextCompat.getColorStateList(getApplicationContext(), android.R.color.holo_orange_light));
                ViewCompat.setBackgroundTintList(boardButtons[0], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                break;
        }
    }

    public void changeDifficulty(View v){
        switch (v.getId()) {
            case (R.id.easybutton):     //Easy Difficulty is Chosen
                difficultyChosen = "easy";
                ViewCompat.setBackgroundTintList(v, ContextCompat.getColorStateList(getApplicationContext(), android.R.color.holo_orange_light));
                ViewCompat.setBackgroundTintList(difficultyButtons[1], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                ViewCompat.setBackgroundTintList(difficultyButtons[2], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                break;
            case (R.id.mediumbutton):   //Medium Difficulty is Chosen
                difficultyChosen = "medium";
                ViewCompat.setBackgroundTintList(v, ContextCompat.getColorStateList(getApplicationContext(), android.R.color.holo_orange_light));
                ViewCompat.setBackgroundTintList(difficultyButtons[0], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                ViewCompat.setBackgroundTintList(difficultyButtons[2], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                break;
            case (R.id.hardbutton):     //Hard difficulty us Chosen
                difficultyChosen = "hard";
                ViewCompat.setBackgroundTintList(v, ContextCompat.getColorStateList(getApplicationContext(), android.R.color.holo_orange_light));
                ViewCompat.setBackgroundTintList(difficultyButtons[0], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                ViewCompat.setBackgroundTintList(difficultyButtons[1], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                break;
        }
    }

    public void changeFirstMove(View v){
        switch (v.getId()) {
            case (R.id.Xbutton):
                firstMoveChosen = "X";
                ViewCompat.setBackgroundTintList(v, ContextCompat.getColorStateList(getApplicationContext(), android.R.color.holo_orange_light));
                ViewCompat.setBackgroundTintList(firstMoveButtons[1], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                ViewCompat.setBackgroundTintList(firstMoveButtons[2], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                break;
            case (R.id.Obutton):
                firstMoveChosen = "O";
                ViewCompat.setBackgroundTintList(v, ContextCompat.getColorStateList(getApplicationContext(), android.R.color.holo_orange_light));
                ViewCompat.setBackgroundTintList(firstMoveButtons[0], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                ViewCompat.setBackgroundTintList(firstMoveButtons[2], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                break;
            case (R.id.XObutton):
                firstMoveChosen = "random";
                ViewCompat.setBackgroundTintList(v, ContextCompat.getColorStateList(getApplicationContext(), android.R.color.holo_orange_light));
                ViewCompat.setBackgroundTintList(firstMoveButtons[0], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                ViewCompat.setBackgroundTintList(firstMoveButtons[1], ContextCompat.getColorStateList(getApplicationContext(), android.R.color.transparent));
                break;
        }
    }

    public void launchGame(View v){
        Intent intent = new Intent(this, TicTacToe4x4Activity.class);
        intent.putExtra("difficulty", difficultyChosen);
        intent.putExtra("firstMove", firstMoveChosen);
        startActivity(intent);
    }
}