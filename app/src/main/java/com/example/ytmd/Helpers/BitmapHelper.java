package com.example.ytmd.Helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Bitmap helper
 * Converts between bitmap to bytes (storing ang getting images from database)
 */
public class BitmapHelper {
    public static byte[] BitmapToBytes(Bitmap bitmap){
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, buffer);
        return buffer.toByteArray();
    }

    public static Bitmap BytesToBitmap(byte[] bytes){
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bmp;
    }

}
