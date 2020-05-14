package com.example.ytmd.Services;


import com.example.ytmd.Models.VideoSearchResult;

import java.util.ArrayList;

public interface OnDataloadListListener{

    void onDataloadListReady(ArrayList<VideoSearchResult> list);

}