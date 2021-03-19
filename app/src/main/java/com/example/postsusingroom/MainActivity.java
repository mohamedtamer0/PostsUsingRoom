package com.example.postsusingroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PostsDatabase postsDatabase = PostsDatabase.getInstance(this);

        postsDatabase.postsDao().insertPost(new Post(2, "Tamer","Hello World"));


    }
}