package com.thegamers.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RgbFilterActivity extends AppCompatActivity {

    private int[][][] image = {
            {{34, 203, 55}, {67, 76, 73}, {99, 105, 93}, {178, 173, 169}, {144, 89, 54}},
            {{22, 20, 18}, {10, 40, 50}, {171, 180, 211}, {150, 150, 90}, {50, 150, 150}},
            {{209, 109, 107}, {111, 117, 212}, {214, 113, 165}, {45, 137, 212}, {182, 240, 245}},
            {{199, 184, 72}, {204, 75, 193}, {140, 132, 139}, {87, 76, 63}, {170, 209, 167}},
            {{1, 90, 20}, {174, 214, 174}, {196, 106, 112}, {173, 166, 167}, {48, 35, 46}}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button filterButton = findViewById(R.id.filterButton);
        Button openPage2 = findViewById(R.id.bt_open);
        TextView resultTextView = findViewById(R.id.resultTextView);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[][][] filteredImage = filterNonColorful(image, 50);
                String result = getImageString(filteredImage);
                resultTextView.setText(result);
            }
        });

        openPage2.setOnClickListener( v -> {
            Intent w = new Intent(this, BlackWhiteRgbFilterActivity.class);
            startActivity(w);
        });

    }

    private boolean isColorful(int r, int g, int b, int threshold) {
        int max = Math.max(r, Math.max(g, b));
        int min = Math.min(r, Math.min(g, b));
        return (max - min) >= threshold;
    }

    private int[][][] filterNonColorful(int[][][] image, int threshold) {
        int[][][] filteredImage = new int[image.length][image[0].length][3];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                int r = image[i][j][0];
                int g = image[i][j][1];
                int b = image[i][j][2];
                if (isColorful(r, g, b, threshold)) {
                    filteredImage[i][j] = new int[]{r, g, b};
                } else {
                    filteredImage[i][j] = new int[]{0, 0, 0};  // Buang pixel yang kurang colorful
                }
            }
        }
        return filteredImage;
    }

    private String getImageString(int[][][] image) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                sb.append("(").append(image[i][j][0]).append(",")
                        .append(image[i][j][1]).append(",")
                        .append(image[i][j][2]).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}