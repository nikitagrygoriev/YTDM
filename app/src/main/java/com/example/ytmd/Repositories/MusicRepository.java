package com.example.ytmd.Repositories;

import com.example.ytmd.DAL.Entities.Music;
import com.example.ytmd.DAL.MusicDao;

import java.util.List;

import javax.inject.Inject;

public class MusicRepository {
    private MusicDao musicDao;

    @Inject
    public MusicRepository(MusicDao musicDao) {
        this.musicDao = musicDao;
    }

    public List<Music> GetAllMusic(){
        return musicDao.getAll();
    }

    public void InsertMusic(Music... music){
        musicDao.insertAll(music);
    }
}
