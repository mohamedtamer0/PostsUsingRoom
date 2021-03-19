package com.example.postsusingroom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PostsDao {
    @Insert
    void insertPost(Post post);

    @Query("SELECT * FROM posts_table")
    List<Post> getPosts();


}
