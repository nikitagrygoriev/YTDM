package com.example.ytmd.Services;

import com.example.ytmd.DAL.Entities.Music;
import com.example.ytmd.Models.MyMusicResult;

import java.util.List;

public interface OnMusicDownloadedListener {
    void OnMusicDownloadedReady(List<MyMusicResult> result);
}
