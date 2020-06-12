package com.example.miwokappmultiscreenappsudacitybasics;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
              releaseResources();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int color = R.color.category_numbers;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);


        final List<Word> words = new ArrayList<>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_three));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   releaseResources();
                   Word word = words.get(position);
                   mediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getAudioResource());
                   mediaPlayer.start();
                Log.v("media",mediaPlayer.toString() + " a " + word.getAudioResource());
                   mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){//Important to check as user can switch away from the activity
            //without playing any of the audio.
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }

    private void releaseResources(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}
