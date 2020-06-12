package com.example.miwokappmultiscreenappsudacitybasics;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private boolean canPlay = false;
    private int audioRequest;

    private boolean audioLossPermanent = false;

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseResources();
        }
    };

    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        final List<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this,words,R.color.category_phrases);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(wordAdapter);


        audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
              switch (focusChange){
                  case AudioManager.AUDIOFOCUS_GAIN:
                      canPlay = true;
                      Toast.makeText(PhrasesActivity.this, "Play now",
                              Toast.LENGTH_SHORT).show();
                      Log.v("checkPhoneCall","can play again");
                      break;

                  case AudioManager.AUDIOFOCUS_LOSS:
                      canPlay = false;
                      pauseMediaPlayer();
                      //audioLossPermanent = true;//done to check from on start again....
                      Toast.makeText(PhrasesActivity.this, "Stop playing",
                              Toast.LENGTH_SHORT).show();
                      Log.v("checkPhoneCall","stop playing,urgent,(found on youtube playing video");

                      break;

                  case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                      canPlay=false;
                      pauseMediaPlayer();
                      Toast.makeText(PhrasesActivity.this, "Stop playing",
                              Toast.LENGTH_SHORT).show();
                      Log.v("checkPhoneCall,noti","stop playing tempo(found on call)");
                      break;

                  case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                      canPlay = false;
                      pauseMediaPlayer();
                      Toast.makeText(PhrasesActivity.this, "Whatsapp coming",
                              Toast.LENGTH_SHORT).show();
                      Log.v("checkPhoneCall,noti","stop playing,Acan duck");
                      break;
              }
            }
        };

        audioManager = (AudioManager) this.getSystemService(this.AUDIO_SERVICE);

//        if(!audioLossPermanent) {
//            audioRequest = audioManager.requestAudioFocus(audioFocusChangeListener,
//                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//            Log.v("checkPhoneCall", "requesting audio focus,from on create");
//        }


        if(audioRequest==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            canPlay = true;
        }
        //Note that the return value is never AUDIOFOCUS_REQUEST_DELAYED
        // when focus is requested without building the AudioFocusRequest with AudioFocusRequest.

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(canPlay) {
                    releaseResources();
                    Word word = words.get(position);
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),
                            word.getAudioResource());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
            audioRequest = audioManager.requestAudioFocus(audioFocusChangeListener,
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            Log.v("checkPhoneCall", "requesting audio focus,from on start");
            if(audioRequest==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                canPlay = true;
                Log.v("checkPhoneCall","now we can play audio " + canPlay);
            }

    }

    private void releaseResources(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }

    private void pauseMediaPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }

}
