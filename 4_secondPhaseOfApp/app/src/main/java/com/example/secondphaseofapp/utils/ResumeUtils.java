package com.example.secondphaseofapp.utils;

import android.content.Context;
import android.net.Uri;

import androidx.core.content.FileProvider;

import com.example.secondphaseofapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ResumeUtils {

    private static final String RESUME_FILE_NAME = "COMP_4630_Resume.pdf";

    public static Uri getResumeUri(Context context) {
        File filesDir = context.getExternalFilesDir(null);
        File file = new File(filesDir, RESUME_FILE_NAME);
        if (file.exists()) {
            return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
        } else {
            try {
                file.createNewFile();
                InputStream in = context.getResources().openRawResource(R.raw.resume);
                OutputStream out = new FileOutputStream(file);
                byte[] data = new byte[in.available()];
                in.read(data);
                out.write(data);
                in.close();
                out.close();
                return FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
