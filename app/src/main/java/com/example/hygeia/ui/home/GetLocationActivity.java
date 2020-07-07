package com.example.hygeia.ui.home;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.hygeia.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;


public class GetLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        writeData();
    }

    private void writeData() {
        String fileName = "data.txt";
        String writeStr = "39.976014 116.317799\n" +
                "39.983456 116.3154950\n";

        String filePath = null;
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) {
            filePath = Environment.getExternalStorageDirectory().toString() + File.separator + fileName;
            Log.e("TestFile", "有SD！！！！");
        } else {
            filePath = Environment.getDownloadCacheDirectory().toString() + File.separator + fileName;
            Log.e("TestFile", "没有SD！！！！");
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
            }
//            FileOutputStream outStream = new FileOutputStream(file);
//            outStream.write("Wx:lcti1314");
//            outStream.w
//            outStream.close();
//            outStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 写入
        String strContent = writeStr;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                Log.e("TestFile", "Create the file:" + filePath);
                file.getParentFile().mkdirs();
                boolean cre = file.createNewFile();
                if (!cre) {
                    Log.e("TestFile", "生成文件失败！！！！");
                };
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes()); //将字符串写入
            raf.close();
            Log.e("TestFile", "写入成功" );
        } catch (Exception e) {
            Log.e("TestFile", "Error on write File:" + e);
        }

    }
}