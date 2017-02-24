package com.live.longmao.activity.person;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.live.longmao.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class  SubmitImageActivity extends Activity implements View.OnClickListener {
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private Button button_cancle;// 取消按钮
    private static int screenHeight;
    private RelativeLayout rl_photobycamer;
    private RelativeLayout rl_photobygallery;
    public String imageName;
    private SubmitImageActivity context;
    private Uri photoUri;
    private File file;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Activity标题不显示
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏显示
        setContentView(R.layout.activity_submitimage);

        context = this;
        init();
        if (savedInstanceState != null) {
            imageName = savedInstanceState.getString("imageName");
        }
    }

    private void init() {
        screenHeight = getWindow().getWindowManager().getDefaultDisplay().getHeight();// 获取屏幕高度
        WindowManager.LayoutParams lp = getWindow().getAttributes();// //lp包含了布局的很多信息，通过lp来设置对话框的布局
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.gravity = Gravity.BOTTOM;
        lp.height = screenHeight / 3;// lp高度设置为屏幕的一半(改为1/4)
        getWindow().setAttributes(lp);// 将设置好属性的lp应用到对话框

        button_cancle = (Button) findViewById(R.id.button_cancle);
        rl_photobycamer = (RelativeLayout) findViewById(R.id.rl_photobycamer);//拍照
        rl_photobygallery = (RelativeLayout) findViewById(R.id.rl_photobygallery);//从相册
        button_cancle.setOnClickListener(this);// 取消按钮的点击事件监听
        rl_photobycamer.setOnClickListener(this);
        rl_photobygallery.setOnClickListener(this);
        button_cancle.setHeight(lp.height / 6);// 将button的高度设置为对话框的1/6

    }

    // 重写finish（）方法，加入关闭时的动画
    public void finish() {
        super.finish();
        SubmitImageActivity.this.overridePendingTransition(0, R.anim.dialog_phone_exit);
    }

    @SuppressLint("SimpleDateFormat")
    private String getNowTime() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmssSS");
        return dateFormat.format(date);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            // 取消按钮的点击事件，关闭对话框
            case R.id.button_cancle:
                finish();
                break;
            case R.id.rl_photobygallery:
                imageName = getNowTime() + ".png";
                intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                break;
            case R.id.rl_photobycamer:
                imageName = getNowTime() + ".png";
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, imageName);
                photoUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                // 指定调用相机拍照后照片的储存路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File("/sdcard/lzjs/", imageName)));
                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("imageName", imageName);
    }

    @SuppressLint("SdCardPath")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_REQUEST_TAKEPHOTO:
                    if (data != null) {
                        startPhotoZoom(
                                Uri.fromFile(new File("/sdcard/lzjs/", imageName)),
                                600);
                    } else {
                        startPhotoZoom(
                                Uri.fromFile(new File("/sdcard/lzjs/", imageName)),
                                600);
                    }
                    break;
                case PHOTO_REQUEST_GALLERY:
                    if (data != null)
                        startPhotoZoom(data.getData(), 600);
                    break;
                case PHOTO_REQUEST_CUT:
                  /*  file = new File("/sdcard/lzjs/", imageName);
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    Log.i("图片地址bitmap", bitmap + "");
                    Log.i("图片地址imageName", imageName + "");*/
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("imageName", imageName);
                    this.setResult(RESULT_OK,resultIntent);
                    this.finish();
                    break;
            }
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @SuppressLint("SdCardPath")
    private void startPhotoZoom(Uri uri1, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri1, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
//        intent.putExtra("crop", "true");
//
//        // aspectX aspectY 是宽高的比例
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//
//        // outputX,outputY 是剪裁图片的宽高
//        intent.putExtra("outputX", size);
//        intent.putExtra("outputY", size);
//        intent.putExtra("return-data", false);
//        intent.putExtra("scale", true);

        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File("/sdcard/lzjs/", imageName)));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

}
