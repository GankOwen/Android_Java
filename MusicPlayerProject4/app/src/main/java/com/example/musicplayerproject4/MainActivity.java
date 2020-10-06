package com.example.musicplayerproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.main_listView);

        Song song1 = new Song(R.string.song1Name,R.string.song1Singer, R.string.song1Album,R.raw.jeremy_zucker_chelsea_cutler_you_were_good_to_me,R.drawable.maxresdefault);

        Song song2 = new Song(R.string.song2Name,R.string.song2Singer, R.string.song2Album,R.raw.jeremy_zucker_comethru,R.drawable.comethrx);

        Song song3 = new Song(R.string.song3Name,R.string.song3Singer, R.string.song3Album,R.raw.jeremy_zucker_not_ur_friend,R.drawable.not_your_friend);

        Song song4 = new Song(R.string.song4Name,R.string.song4Singer, R.string.song4Album,R.raw.lauv_anne_marie_fuck_i_m_lonely, R.drawable.lonely);

        Song song5 = new Song(R.string.song5Name,R.string.song5Singer, R.string.song5Album, R.raw.lauv_enemies, R.drawable.enemies);

        Song song6 = new Song(R.string.song6Name,R.string.song6Singer, R.string.song6Album, R.raw.lauv_feelings,R.drawable.feeling);

        Song song7 = new Song(R.string.song7Name,R.string.song7Singer, R.string.song7Album, R.raw.lauv_sims,R.drawable.sims);

        Song song8 = new Song(R.string.song8Name,R.string.song8Singer, R.string.song8Album,R.raw.demxntia_tonight,R.drawable.tonight);

        Song song9 = new Song(R.string.song9Name,R.string.song9Singer, R.string.song9Album,R.raw.myylo_sad_boys,R.drawable.sad_boys);

        Song song10 = new Song(R.string.song10Name,R.string.song10Singer, R.string.song10Album,R.raw.the_1975_sincerity_is_scary,R.drawable.sincerity);

        songList = new ArrayList<>(Arrays.asList(song1,song2,song3,song4,song5,song6,song7,song8,song9,song10));

        MainArrayAdapter adapter = new MainArrayAdapter(this, songList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), MusicPlayerActivity.class);
                intent.putExtra("songList", (Serializable)songList);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }
}