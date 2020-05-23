package com.example.ytmd.DAL;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ytmd.DAL.Entities.Music;

@Database(entities = {Music.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract MusicDao musicDao();
}