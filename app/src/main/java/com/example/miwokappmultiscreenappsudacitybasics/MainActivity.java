package com.example.miwokappmultiscreenappsudacitybasics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView numbersView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numbersView = findViewById(R.id.numbers);
        numbersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NumbersActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.colors).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ColorsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.family).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FamilyActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.phrases).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PhrasesActivity.class);
                startActivity(intent);
            }
        });
    }


}
