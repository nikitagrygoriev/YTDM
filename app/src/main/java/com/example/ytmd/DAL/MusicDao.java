package com.example.ytmd.DAL;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ytmd.DAL.Entities.Music;

import java.util.List;

@Dao
public interface MusicDao {
    @Query("SELECT * FROM music")
    List<Music> getAll();

    @Query("SELECT * FROM music WHERE uid IN (:userIds)")
    List<Music> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM music WHERE title LIKE :title  LIMIT 1")
    Music findByTitle(String title);

    @Insert
    void insertAll(Music... music);

    @Delete
    void delete(Music music);
}
