package com.example.helloworld.ui.notifications;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.helloworld.R;

public class NotificationsFragment extends Fragment implements NumberPicker.OnValueChangeListener {
    private TextView timertextView;
    private NumberPicker numberpicker1;
    private Button start;
    private Button pause;
    private Button stop;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private boolean timerRunning;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        timertextView = view.findViewById(R.id.timertextView);
        numberpicker1 = view.findViewById(R.id.numberpicker1);
        start = view.findViewById(R.id.start);
        pause = view.findViewById(R.id.pause);
        stop = view.findViewById(R.id.stop);

        numberpicker1.setMinValue(0);
        numberpicker1.setMaxValue(60);
        String[] displayedValues = new String[61];
        for (int i = 0; i <= 60; i++) {
            displayedValues[i] = String.valueOf(i);
        }
        numberpicker1.setDisplayedValues(displayedValues);
        numberpicker1.setOnValueChangedListener(this);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerRunning) {
                    startTimer();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        return view;
    }

    private void startTimer() {
        timeLeftInMillis = numberpicker1.getValue() * 1000L;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                updateButtons();
                timertextView.setText("Aika on kulunut!");
            }
        }.start();

        timerRunning = true;
        updateButtons();
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        updateButtons();
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        timeLeftInMillis = 0;
        updateCountDownText();
        updateButtons();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timertextView.setText(timeLeftFormatted);
    }

    private void updateButtons() {
        if (timerRunning) {
            start.setVisibility(View.INVISIBLE);
            pause.setVisibility(View.VISIBLE);
            stop.setVisibility(View.VISIBLE);
        } else {
            start.setVisibility(View.VISIBLE);
            pause.setVisibility(View.INVISIBLE);
            stop.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
    }
}
