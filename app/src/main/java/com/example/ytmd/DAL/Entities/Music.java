package com.example.ytmd.DAL.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.ytmd.DAL.Converters;


import java.util.Date;


@Entity
public class Music {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "path")
    public String path;

  //  @ColumnInfo(name = "length")
  //  public Duration length;

    @ColumnInfo(name = "downloadDate")
    public Date downloadDate;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    public byte[] image;

    public Music(String title, /*String path, Duration length,*/  byte[] image,Date downloadDate) {
        this.title = title;
        this.path = path;
 //       this.length = length;
        this.downloadDate = downloadDate;
        this.image = image;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(Date downloadDate) {
        this.downloadDate = downloadDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
