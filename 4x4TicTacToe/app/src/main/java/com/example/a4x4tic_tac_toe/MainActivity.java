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

import java.util.Random;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button onePlayerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onePlayerButton = findViewById(R.id.oneplayerbutton);

    }

    public void launchOnePlayerGame(View v){
        Intent intent = new Intent(this, TicTacToe4x4Activity.class);
        startActivity(intent);
    }

}
