package com.example.postsusingroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView postsRecyclerView;
    private Button insertBtn, getBtn;
    private EditText titleEt, bodyEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postsRecyclerView = findViewById(R.id.posts_recyclerView);

        insertBtn = findViewById(R.id.insertButton);
        getBtn = findViewById(R.id.getBuuton);
        titleEt = findViewById(R.id.editTextTextTitle);
        bodyEt = findViewById(R.id.editTextTextBody);

        PostsAdapter adapter = new PostsAdapter();
        postsRecyclerView.setAdapter(adapter);

       final PostsDatabase postsDatabase = PostsDatabase.getInstance(this);


        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsDatabase.postsDao().insertPost(new Post(2, "Tamer", "Hello World"))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });
            }
        });


        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsDatabase.postsDao().getPosts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Post>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@NonNull List<Post> posts) {
                                adapter.setList(posts);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });

            }
        });




    }


}













