package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.helloworld.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public TextView hellotext;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        hellotext = findViewById(R.id.button);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleOnClickEvents(v);
            }
        });
        Button gamebutton = findViewById(R.id.gamebutton);
        gamebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleOnClickEvents(v);
            }
        });
    }
    private void handleOnClickEvents(View view) {
        if (view.getId() == R.id.button) {
            Log.d(TAG, "Tulostelehti");
            if (hellotext.getVisibility() == View.VISIBLE) {
                hellotext.setVisibility(View.INVISIBLE);
            } else {
                hellotext.setVisibility(View.VISIBLE);
            }
        } else if (view.getId() == R.id.gamebutton) {
            Log.d(TAG, "Gamebutton clicked");
            Intent i = new Intent(this, GameActivity.class);
            startActivity(i);
        }
    }
}