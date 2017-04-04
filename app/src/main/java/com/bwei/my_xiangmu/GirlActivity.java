package com.bwei.my_xiangmu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import costomview.library.Glideimageutils;
import uk.co.senab.photoview.PhotoView;

public class GirlActivity extends AppCompatActivity {

    private String imageurl;
    private PhotoView photoview;
    private ImageView xiazai;
    private String pos;
    private File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
        imageview();
    }
    private void imageview() {
        photoview = (PhotoView) findViewById(R.id.photoview);
        ImageView iamgeviewww = (ImageView) findViewById(R.id.iamgeviewww);
        xiazai = (ImageView) findViewById(R.id.xiazai_iamgeview);
        xiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downLoad();
                Toast.makeText(GirlActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
            }
        });
        iamgeviewww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GirlActivity.this.finish();
            }
        });
        Intent intent = getIntent();
        imageurl = intent.getStringExtra("image");
        if (imageurl == null) {
            Toast.makeText(this, "没有图片url", Toast.LENGTH_SHORT).show();
            return;
        }
        Glideimageutils.Imagelode(this, imageurl, photoview);
    }

    private void downLoad() {
        Bitmap bitmap = photoview.getVisibleRectangleBitmap();
        File saveFile = null;
        try {
            saveFile = saveFile(bitmap,pos+".png");
            //MediaStore.Images.Media.insertImage(getContentResolver(), "image path", "title", "description");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // 判断SDK版本是不是4.4或者高于4.4
            String[] paths = new String[]{saveFile.getAbsolutePath()};
            MediaScannerConnection.scanFile(this, paths, null, null);
        } else {
            final Intent intent;
            if (saveFile.isDirectory()) {
                intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
                intent.setClassName("com.android.providers.media", "com.android.providers.media.MediaScannerReceiver");
                intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
            } else {
                intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                intent.setData(Uri.fromFile(saveFile));
            }
            sendBroadcast(intent);
        }
    }
    /**
     * 将Bitmap转换成文件
     * 保存文件
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public File saveFile(Bitmap bm, String fileName) throws IOException {
        path = Environment.getExternalStorageDirectory();
        File dirFile = new File(path,fileName);
        if(!dirFile.exists()){
            dirFile.createNewFile();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dirFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return dirFile;
    }

}
