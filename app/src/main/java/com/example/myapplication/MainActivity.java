package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button playButton;
    private Handler handler = new Handler();
    private int countdown = 5;
    private int[] images = {
            R.drawable.rock, // Add your images to the res/drawable folder
            R.drawable.papper,
            R.drawable.sizzors,
            R.drawable.getready,
            R.drawable.tree,
            R.drawable.two,
            R.drawable.one,
            R.drawable.go
    };
    private boolean playing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout); // Updated to use layout.xml

        imageView = findViewById(R.id.image_view);
        playButton = findViewById(R.id.play_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!playing) {
                    playing = true;
                    playButton.setVisibility(View.GONE); // Hide the button
                    startCountdown();
                } else {
                    System.out.println("Game is already playing");
                }
            }
        });
    }

    private void startCountdown() {
        countdown = 5;
        imageView.setImageDrawable(null); // Clear previous image

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (countdown > 0) {
                    int imageIndex = 8 - countdown; // Adjust index for countdown images
                    imageView.setImageResource(images[imageIndex]);
                    countdown--;
                    handler.postDelayed(this, 1000);
                } else {
                    showRandomImage();
                }
            }
        }, 1000);
    }

    private void showRandomImage() {
        Random random = new Random();
        int randomIndex = random.nextInt(3); // Only choose from rock, paper, scissors
        imageView.setImageResource(images[randomIndex]);
        playing = false;
        playButton.setVisibility(View.VISIBLE); // Show the button again
    }
}