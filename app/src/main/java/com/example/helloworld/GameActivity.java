package com.example.helloworld;


import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private ImageButton imageButton, imageButton2, imageButton3, imageButton4;
    private int diamondPosition;
    private int attempts = 0;
    private FloatingActionButton restartButton;
    private int foundDiamonds = 0;
    private int score = 0;
    private Random random;
    private DataStoreHelper dataStoreHelper;
    private int bestScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        random = new Random();

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(this::handleOnClickEvents);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(this::handleOnClickEvents);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(this::handleOnClickEvents);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(this::handleOnClickEvents);

        restartButton = findViewById(R.id.floatingActionButton);
        restartButton.setOnClickListener(v -> restartActivity());

        setRandomDiamondPosition();
        setRandomCardWithDiamond();

        Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animaatiot);
        imageButton.startAnimation(rotateAnimation);
    }
    private void setRandomDiamondPosition() {
        diamondPosition = random.nextInt(4) + 1;
    }

    private void setRandomCardWithDiamond() {
        int randomCard;
        do {
            randomCard = random.nextInt(4) + 1;
        } while (randomCard == diamondPosition);

        ImageButton randomImageButton = null;
        switch (randomCard) {
            case 1:
                randomImageButton = imageButton;
                break;
            case 2:
                randomImageButton = imageButton2;
                break;
            case 3:
                randomImageButton = imageButton3;
                break;
            case 4:
                randomImageButton = imageButton4;
                break;
        }
        if (randomImageButton != null) {
            randomImageButton.setTag("diamond");
        }
    }

    private void handleOnClickEvents(View view) {
        ImageButton clickedButton = (ImageButton) view;
        attempts++;
        if (view.getTag() != null && view.getTag().equals("diamond")) {
            clickedButton.setImageResource(R.mipmap.ic_launcher);
            Toast.makeText(this, "Löytyi! Yrityksiä: " + attempts, Toast.LENGTH_SHORT).show();
            if (attempts < bestScore || bestScore == 0) {
                bestScore = attempts;
            }
            int currentScore = calculateScore();
            Toast.makeText(this, "Pisteet: " + currentScore, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ei löytynyt. Yrityksiä: " + attempts, Toast.LENGTH_SHORT).show();
        }
    }
    private int calculateScore() {
        score = 0 - (foundDiamonds + 1);
        return score;
    }
    private void restartActivity() {
        attempts = 0;
        setRandomDiamondPosition();
        setRandomCardWithDiamond();

        imageButton.setImageResource(android.R.color.transparent);
        imageButton2.setImageResource(android.R.color.transparent);
        imageButton3.setImageResource(android.R.color.transparent);
        imageButton4.setImageResource(android.R.color.transparent);

        Toast.makeText(this, "Peli uudelleenkäynnistyy", Toast.LENGTH_SHORT).show();

        finish();
        startActivity(getIntent());
    }
}
