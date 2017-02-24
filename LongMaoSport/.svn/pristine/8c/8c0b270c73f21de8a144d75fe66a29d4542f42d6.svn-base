package com.live.longmao.imageselector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;


import com.live.longmao.BaseApp;
import com.live.longmao.R;
import com.live.longmao.imageselector.bean.Image;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.FileUtil;
import com.live.longmao.util.ImageUtil;
import com.live.longmao.views.customviews.BaseFragmentActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 多图选择
 * Created by Nereo on 2015/4/7.
 * Updated by nereo on 2016/1/19.
 */
public class MultiImageSelectorActivity extends BaseFragmentActivity implements MultiImageSelectorFragment.Callback {

    /**
     * 最大图片选择次数，int类型，默认9
     */
    public static final String EXTRA_SELECT_COUNT = "max_select_count";
    /**
     * 图片选择模式，默认多选
     */
    public static final String EXTRA_SELECT_MODE = "select_count_mode";
    /**
     * 是否显示相机，默认显示
     */
    public static final String EXTRA_SHOW_CAMERA = "show_camera";
    /**
     * 选择结果，返回为 ArrayList&lt;String&gt; 图片路径集合
     */
    public static final String EXTRA_RESULT = "select_result";
    /**
     * 默认选择集
     */
    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";

    /**
     * 单选
     */
    public static final int MODE_SINGLE = 0;
    /**
     * 多选
     */
    public static final int MODE_MULTI = 1;

    private ArrayList<String> resultList = new ArrayList<>();
    private ArrayList<String> tempList = new ArrayList<>();
    private Button mSubmitButton;
    private int mDefaultCount;
    private RelativeLayout activity_base_title_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_default);

        Intent intent = getIntent();
        mDefaultCount = intent.getIntExtra(EXTRA_SELECT_COUNT, 9);
        int mode = intent.getIntExtra(EXTRA_SELECT_MODE, MODE_MULTI);
        boolean isShow = intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        if (mode == MODE_MULTI && intent.hasExtra(EXTRA_DEFAULT_SELECTED_LIST)) {
            resultList = intent.getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
            tempList.addAll(resultList);
        }

        Bundle bundle = new Bundle();
        bundle.putInt(MultiImageSelectorFragment.EXTRA_SELECT_COUNT, mDefaultCount);
        bundle.putInt(MultiImageSelectorFragment.EXTRA_SELECT_MODE, mode);
        bundle.putBoolean(MultiImageSelectorFragment.EXTRA_SHOW_CAMERA, isShow);
        bundle.putStringArrayList(MultiImageSelectorFragment.EXTRA_DEFAULT_SELECTED_LIST, resultList);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.image_grid, Fragment.instantiate(this, MultiImageSelectorFragment.class.getName(), bundle))
                .commit();

        // 返回按钮
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        // 完成按钮
        mSubmitButton = (Button) findViewById(R.id.commit);
        if (resultList == null || resultList.size() <= 0) {
            mSubmitButton.setText(R.string.action_done);
//            mSubmitButton.setEnabled(true);
        } else {
            updateDoneText();
//            mSubmitButton.setEnabled(true);
        }
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultList != null) {
                    // 返回已选择的图片数据
                    compressionAll();
                    Intent data = new Intent();
                    data.putStringArrayListExtra(EXTRA_RESULT, resultList);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeLH(this, activity_base_title_rl);
        }
    }

    private void compressionAll() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (resultList.size() > tempList.size()) {
                    for (int i = 0; i < resultList.size(); i++) {
                        if (tempList.size() > i) {
                            if (tempList.get(i) != resultList.get(i)) {
                                FileUtil.DELETEFILE(new File(BaseApp.LONGMAOIMG + i + ".jpg"));
                                compression(resultList.get(i), i);
                            }
                        } else {
                            compression(resultList.get(i), i);
                        }
                    }
                } else if (resultList.size() < tempList.size()) {
                    for (int i = 0; i < tempList.size(); i++) {
                        if (resultList.size() > i) {
                            FileUtil.DELETEFILE(new File(BaseApp.LONGMAOIMG + i + ".jpg"));
                            compression(resultList.get(i), i);
                        } else {
                            FileUtil.DELETEFILE(new File(BaseApp.LONGMAOIMG + i + ".jpg"));
                        }
                    }
                }else
                {
                    for (int i = 0; i < resultList.size(); i++) {
                            if (tempList.get(i) != resultList.get(i)) {
                                FileUtil.DELETEFILE(new File(BaseApp.LONGMAOIMG + i + ".jpg"));
                                compression(resultList.get(i), i);
                            }
                    }
                }
            }
        }).start();
    }

    // 压缩图片
    private void compression(String filepath, int pos) {
        // // 定义一个file，为压缩后的图片
        String photoUrl = pos + ".jpg";
        File f = new File(BaseApp.LONGMAOIMG, photoUrl);
        Bitmap image = null;
        try {
            image = ImageUtil.imgUri2MatriBitmap(this, filepath, 960);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 这里100表示不压缩，将不压缩的数据存放到baos中
        int per = 100;
        while (baos.toByteArray().length / 1024 > 200) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, per, baos);// 将图片压缩为原来的(100-per)%，把压缩后的数据存放到baos中
            per -= 10;// 每次都减少10

        }
        // 回收图片，清理内存
        if (image != null && !image.isRecycled()) {
            image.recycle();
            image = null;
            System.gc();
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(f);
            os.write(baos.toByteArray());
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        try {
            baos.close();
            // os.close();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

    private void updateDoneText() {
        mSubmitButton.setText(String.format("%s(%d/%d)",
                getString(R.string.action_done), resultList.size(), mDefaultCount));
    }

    @Override
    public void onSingleImageSelected(String path) {
        compressionAll();
        Intent data = new Intent();
        resultList.add(path);
        data.putStringArrayListExtra(EXTRA_RESULT, resultList);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onImageSelected(String path) {
        if (!resultList.contains(path)) {
            resultList.add(path);
        }
        // 有图片之后，改变按钮状态
        if (resultList.size() > 0) {
            updateDoneText();
//            if (!mSubmitButton.isEnabled()) {
//                mSubmitButton.setEnabled(true);
//            }
        }
    }

    @Override
    public void onImageUnselected(String path) {
        if (resultList.contains(path)) {
            resultList.remove(path);
        }
        updateDoneText();
        // 当为选择图片时候的状态
        if (resultList.size() == 0) {
            mSubmitButton.setText(R.string.action_done);
//            mSubmitButton.setEnabled(false);
        }
    }

    @Override
    public void onCameraShot(File imageFile) {
        if (imageFile != null) {
            // notify system
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imageFile)));
            compressionAll();
            Intent data = new Intent();
            resultList.add(imageFile.getAbsolutePath());
            data.putStringArrayListExtra(EXTRA_RESULT, resultList);
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
