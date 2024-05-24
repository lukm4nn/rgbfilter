package com.thegamers.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BlackWhiteRgbFilterActivity extends AppCompatActivity {

    private int[][][] image = {
            {{87, 76, 63}, {67, 76, 73}, {99, 105, 93}, {178, 173, 169}, {48, 35, 46}},
            {{22, 20, 18}, {10, 40, 50}, {67, 76, 73}, {173, 166, 167}, {87, 76, 63}},
            {{10, 40, 50}, {99, 105, 93}, {178, 173, 169}, {67, 76, 73}, {22, 20, 18}},
            {{22, 20, 18}, {87, 76, 63}, {140, 132, 139}, {87, 76, 63}, {99, 105, 93}},
            {{99, 105, 93}, {87, 76, 63}, {67, 76, 73}, {173, 166, 167}, {48, 35, 46}}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_white_rgb_filter);

        Button checkButton = findViewById(R.id.checkButton);
        TextView resultTextView = findViewById(R.id.resultTextView);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isColorful = isImageColorful(image, 50);
                if (isColorful) {
                    resultTextView.setText("Gambar ini colorful.");
                } else {
                    resultTextView.setText("Gambar ini lebih mirip dengan hitam-putih.");
                }
            }
        });
    }

    private boolean isColorful(int r, int g, int b, int threshold) {
        int max = Math.max(r, Math.max(g, b));
        int min = Math.min(r, Math.min(g, b));
        return (max - min) >= threshold;
    }

    private boolean isImageColorful(int[][][] image, int threshold) {
        int colorfulPixelCount = 0;
        int totalPixelCount = image.length * image[0].length;

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                int r = image[i][j][0];
                int g = image[i][j][1];
                int b = image[i][j][2];
                if (isColorful(r, g, b, threshold)) {
                    colorfulPixelCount += 1;
                }
            }
        }

        // Jika lebih dari setengah pixel berwarna, gambar dianggap colorful
        return colorfulPixelCount > (totalPixelCount / 2);
    }
}