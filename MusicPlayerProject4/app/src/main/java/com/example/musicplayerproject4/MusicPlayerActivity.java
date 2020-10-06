package com.example.musicplayerproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MusicPlayerActivity extends AppCompatActivity {
    TextView songNameTextView;
    TextView singerTextView;
    TextView albumTextView;
    ImageView coverImageView;
    FloatingActionButton playButton;
    FloatingActionButton nextButton;
    FloatingActionButton lastButton;

    MediaPlayer mediaPlayer;
    boolean isPlaying = true;
    int position;
    List<Song> songList;
    Song currentSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        songNameTextView = findViewById(R.id.music_song_name_textView);
        singerTextView = findViewById(R.id.music_singer_textView);
        albumTextView = findViewById(R.id.music_Album_textView);
        coverImageView = findViewById(R.id.music_cover_imageView);

        playButton = findViewById(R.id.music_play_floatingButton);
        nextButton = findViewById(R.id.music_next_song_floatingButton);
        lastButton = findViewById(R.id.music_last_song_floatingButton);

        songList = (List<Song>) getIntent().getSerializableExtra("songList");
        position = getIntent().getIntExtra("position",0);
        currentSong = songList.get(position);
        mediaPlayer = MediaPlayer.create(this, currentSong.getFileResID());
        mediaPlayer.start();

        coverImageView.setImageResource(currentSong.getCover());
        songNameTextView.setText(currentSong.getSongNameResId());
        singerTextView.setText(currentSong.getSingerResId());
        albumTextView.setText(currentSong.getAlbumId());

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying){
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.ic_play);
                    isPlaying = false;
                }else{
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.ic_pause);
                    isPlaying = true;
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.release();
                position+=1;
                if(position == songList.size()){
                    position = 0;
                }
                currentSong = songList.get(position);
                songNameTextView.setText(currentSong.getSongNameResId());
                singerTextView.setText(currentSong.getSingerResId());
                albumTextView.setText(currentSong.getAlbumId());
                coverImageView.setImageResource(currentSong.getCover());
                mediaPlayer = MediaPlayer.create(view.getContext(),currentSong.getFileResID());
                mediaPlayer.start();
            }
        });

        lastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.release();
                position-=1;
                if(position == songList.size()){
                    position = songList.size()-1;
                }
                currentSong = songList.get(position);
                songNameTextView.setText(currentSong.getSongNameResId());
                singerTextView.setText(currentSong.getSingerResId());
                albumTextView.setText(currentSong.getAlbumId());
                coverImageView.setImageResource(currentSong.getCover());
                mediaPlayer = MediaPlayer.create(view.getContext(),currentSong.getFileResID());
                mediaPlayer.start();
            }
        });
    }
}