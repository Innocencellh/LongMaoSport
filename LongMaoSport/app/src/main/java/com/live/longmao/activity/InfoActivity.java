package com.live.longmao.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.BaseApp;
import com.live.longmao.R;
import com.live.longmao.adapter.RecyclerViewAdapter;
import com.live.longmao.dlg.ActionSheetDialog;
import com.live.longmao.imageselector.MultiImageSelectorActivity;
import com.live.longmao.imageshow.ViewPagerFragment;
import com.live.longmao.model.ChangeEntayInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonInfo;
import com.live.longmao.model.PutPhotoInfo;
import com.live.longmao.model.ReadLiveInfo;
import com.live.longmao.presenters.ExaminePhotoHelper;
import com.live.longmao.presenters.ModificationMessageHelper;
import com.live.longmao.presenters.PersonHelper;
import com.live.longmao.presenters.ProfileInfoHelper;
import com.live.longmao.presenters.UpPhotoHelper;
import com.live.longmao.presenters.UploadHelper;
import com.live.longmao.presenters.viewinface.ExaminePhotoView;
import com.live.longmao.presenters.viewinface.ModificationMessageView;
import com.live.longmao.presenters.viewinface.PersonView;
import com.live.longmao.presenters.viewinface.ProfileView;
import com.live.longmao.presenters.viewinface.UpPhotoView;
import com.live.longmao.presenters.viewinface.UploadView;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.Constants;
import com.live.longmao.util.FileUtil;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.util.SxbLog;
import com.live.longmao.util.UIUtils;
import com.live.longmao.view.ObservableScrollView;
import com.live.longmao.views.EditActivity;
import com.live.longmao.views.FragmentInfo;
import com.live.longmao.views.customviews.BaseFragmentActivity;
import com.live.okhttp.JsonGenericsSerializator;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.GenericsCallback;
import com.tencent.TIMUserProfile;
import com.umeng.analytics.MobclickAgent;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public class InfoActivity extends BaseFragmentActivity implements View.OnClickListener, ModificationMessageView, PersonView, UpPhotoView, ProfileView {

    private static final int REQUEST_IMAGE = 2;
    private static final String TAG = "InfoActivity";
    private RelativeLayout activity_base_title_rl, personProfression, personName, personInter, rl_info_sign, person_sex_rl;
    private ImageButton iv_back;
    private RecyclerViewAdapter adapter;
    private ArrayList<String> urlList;
    private ArrayList<String> urlListTemp;
    private RecyclerView recyclerView;
    private ObservableScrollView scroll_view;
    private TextView tvSign, person_sex, tv_save, tv_profession, tv_hobby, person_sin, tv_name;
    private TextView tv_zhiwei, tv_aihao;
    private TextView info_sign;
    private ImageView content_right1, content_right2, content_right3, content_right4, content_right5;
    private String name, sex = "0", profession, hobby, person, sig, phoneId;
    private ImageView person_image;
    private int imageHeight;
    private PersonHelper personHelper;

    private ModificationMessageHelper modificationMessageHelper;
    private UpPhotoHelper upPhotoHelper;
    private ProfileInfoHelper mProfileInfoHelper;

    private final static int REQ_EDIT_NICKNAME = 0x100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_infomation);
        initView();
        init();
        initListeners();
        initScrollView();

        initChange();
        FileUtil.DELETEFILE(new File(BaseApp.LONGMAOIMG));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, activity_base_title_rl);
        }
        personHelper = new PersonHelper(this);
        upPhotoHelper = new UpPhotoHelper(this);
        mProfileInfoHelper = new ProfileInfoHelper(this);
        personHelper.getPerson();
    }

    private void initChange() {
        //  name = tv_name.getText().toString().trim();
        profession = tv_zhiwei.getText().toString().trim();
        Log.i("dddssafffs", profession);
        hobby = tv_hobby.getText().toString().trim();
        Log.i("dddssafffs", hobby);

    }


    private void initListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = person_image.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                person_image.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = person_image.getHeight() / 2;
            }
        });
    }

    private void initScrollView() {
        scroll_view = (ObservableScrollView) findViewById(R.id.scroll_view);
        scroll_view.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) 0, 91, 78, 97));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) alpha, 91, 78, 97));
                } else {
                    activity_base_title_rl.setBackgroundColor(Color.argb((int) 255, 91, 78, 97));
                }
            }
        });
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }

    private void initView() {
        modificationMessageHelper = new ModificationMessageHelper(this);
        rl_info_sign = (RelativeLayout) findViewById(R.id.rl_info_sign);
        scroll_view = (ObservableScrollView) findViewById(R.id.scroll_view);
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        iv_back = (ImageButton) findViewById(R.id.iv_back);
        person_sex = (TextView) findViewById(R.id.person_sex);
        //tvSign = (TextView) findViewById(R.id.info_sign);
        tv_save = (TextView) findViewById(R.id.tv_save);
        tv_profession = (TextView) findViewById(R.id.tv_profession);
        personProfression = (RelativeLayout) findViewById(R.id.person_profression);
        personName = (RelativeLayout) findViewById(R.id.person_name);
        personInter = (RelativeLayout) findViewById(R.id.peson_test4);
        person_image = (ImageView) findViewById(R.id.person_image);
        person_sex_rl = (RelativeLayout) findViewById(R.id.person_sex_rl);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_hobby = (TextView) findViewById(R.id.tv_hobby);
        person_sin = (TextView) findViewById(R.id.person_sin);
        info_sign = (TextView) findViewById(R.id.info_sign);
        tv_zhiwei = (TextView) findViewById(R.id.tv_zhiwei);
        tv_aihao = (TextView) findViewById(R.id.tv_aihao);


        content_right1 = (ImageView) findViewById(R.id.content_right1);
        content_right2 = (ImageView) findViewById(R.id.content_right2);
        content_right3 = (ImageView) findViewById(R.id.content_right3);
        content_right4 = (ImageView) findViewById(R.id.content_right4);
        content_right5 = (ImageView) findViewById(R.id.content_right5);


        tv_name.setOnClickListener(this);
        person_sex_rl.setOnClickListener(this);
        rl_info_sign.setOnClickListener(this);
        person_sex.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        personProfression.setOnClickListener(this);
        personName.setOnClickListener(this);
        personInter.setOnClickListener(this);

        //   tv_name.getText().toString().trim();
        person_sex.getText().toString().trim();

    }

    /**
     * 图片选择
     */
    private void showPhotoDialog() {
        final Dialog pickDialog = new Dialog(this, R.style.floag_dialog);
        pickDialog.setContentView(R.layout.dlg_photo_operation);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Window dlgwin = pickDialog.getWindow();
        WindowManager.LayoutParams lp = dlgwin.getAttributes();
        dlgwin.setGravity(Gravity.BOTTOM);
        lp.width = (int) (display.getWidth()); //设置宽度

        pickDialog.getWindow().setAttributes(lp);

        TextView camera = (TextView) pickDialog.findViewById(R.id.chos_add_photo);
        TextView picLib = (TextView) pickDialog.findViewById(R.id.chos_delete_photo);
        TextView cancel = (TextView) pickDialog.findViewById(R.id.btn_cancel);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDialog.dismiss();
            }
        });

        picLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDialog.dismiss();
            }
        });

        pickDialog.show();
    }

    private void init() {

        if (urlList == null) {
            urlList = new ArrayList<>();
            addItem();
        }
        if (urlListTemp == null) {
            urlListTemp = new ArrayList<>();
        }
        if (adapter == null) {
            adapter = new RecyclerViewAdapter(this, urlList);
        }
        adapter.setOnItemClick(new RecyclerViewAdapter.OnItemClick() {
            @Override
            public void onClick(int position) {
                Log.e("position", "" + position);
                if (position == 0) {
                    // FileUtil.DELETEFILE(new File(BaseApp.LONGMAOIMG));
                    startImageSelect();
                    //showPhotoDialog();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("imgs", urlListTemp);
                    bundle.putInt("position", position - 1);
                    getSupportFragmentManager().beginTransaction().replace(Window.ID_ANDROID_CONTENT, ViewPagerFragment.getInstance(bundle), "ViewPagerFragment")
                            .addToBackStack(null).commit();
                }
            }

            @Override
            public void onLongClick(int position, View v) {
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动,即是否向右滑动或向下滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();
                    //判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
//                        addItem();
//                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dx > 0) {
                    //大于0表示向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向左滚动
                    isSlidingToLast = false;
                }
            }
        });
    }


    private void startImageSelect() {
        int selectedMode = MultiImageSelectorActivity.MODE_MULTI;

        if (false) {
            selectedMode = MultiImageSelectorActivity.MODE_SINGLE;
        } else {
            selectedMode = MultiImageSelectorActivity.MODE_MULTI;
        }

        boolean showCamera = true;
        //设置最多选择和显示图片的数量
        int maxNum = 1;

        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, showCamera);
        // 最大可选择图片数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
        // 选择模式
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, selectedMode);
        // 默认选择
        if (urlListTemp != null && urlListTemp.size() > 0) {
            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, urlListTemp);
        }
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void addItem() {
        urlList.add("默认图片");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_info_sign:
                startActivity(new Intent(InfoActivity.this, ProfressionSigActivity.class));
                finish();
                break;
            case R.id.person_sex_rl:
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)
                        .addSheetItem("男", ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        person_sex.setText("男");
                                        sex = "1";
                                        modificationMessageHelper.getSex("1");
                                    }
                                })
                        .addSheetItem("女", ActionSheetDialog.SheetItemColor.Blue,
                                 new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        person_sex.setText("女");
                                        sex = "0";
                                        modificationMessageHelper.getSex("0");
                                    }
                                }).show();
                break;
            case R.id.person_profression:
                startActivity(new Intent(InfoActivity.this, ProfressionActivity.class));
                finish();
                break;
            case R.id.person_name:
                //  EditActivity.navToEdit(this, getString(R.string.profile_nickname), MySelfInfo.getInstance().getNickName(), REQ_EDIT_NICKNAME);
                EditActivity.navToEdit(this, getString(R.string.profile_nickname), name, REQ_EDIT_NICKNAME);

                break;
            case R.id.peson_test4:
                startActivity(new Intent(InfoActivity.this, ProfressionHobbiesActivity.class));
                finish();
                break;
            case R.id.tv_save:
                if (urlList.size() == 1) {
                    Toast.makeText(InfoActivity.this, "快来上传图片", Toast.LENGTH_SHORT).show();
                } else {
                    upPhotoHelper.uploadPhoto(urlList.get(1));
                    Toast.makeText(InfoActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
//                sig = info_sign.getText().toString().trim();
//                modificationMessageHelper.getAttentionTwo(hobby, name, sex, sig, profession, "");
//                Log.i("个人信息中心名字", MySelfInfo.getInstance().getNickName());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                urlListTemp = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                urlList.clear();
                addItem();

                //TODO 调用腾讯的 IM 上传图片接口
                //   getPicFrom(IMAGE_STORE);
                urlList.addAll(urlListTemp);
                adapter.notifyDataSetChanged();
                super.onActivityResult(requestCode, resultCode, data);

            }
        }

        switch (requestCode) {
            case REQ_EDIT_NICKNAME:
                mProfileInfoHelper.setMyNickName(data.getStringExtra(EditActivity.RETURN_EXTRA));
                break;
        }
    }

    @Override
    public void onModificationMessageSucc(ChangeEntayInfo result) {
        Toast.makeText(InfoActivity.this, "你的信息已修改成功", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onModificationMessageError(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        personHelper.getPerson();
        MobclickAgent.onPageStart("修改个人信息界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("修改个人信息界面");
        MobclickAgent.onPause(this);
    }

    @Override
    public void onPersonInfoSucc(PersonInfo result) {
        name = result.getBody().getNickName();
        tv_name.setText(result.getBody().getNickName());
        sex = result.getBody().getSex() + "";

        if (result.getBody().getSex() == 0) {
            person_sex.setText("女");
        } else {
            person_sex.setText("男");
        }
        tv_aihao.setText(result.getBody().getHobbies());
        info_sign.setText(result.getBody().getSigned());
        tv_zhiwei.setText(result.getBody().getProfession());
        String url = "http://121.40.65.153/photo/";
        GlideUtil.getInstance().load(this, person_image, url + result.getBody().getPhoneId());
    }

    @Override
    public void onPersonInfoError(String msg) {

    }

    @Override
    public void onUpPhotoSucc(PutPhotoInfo result) {
        modificationMessageHelper.getPhoto(result.getBody());
        Toast.makeText(InfoActivity.this, "头像保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpPhotoError(String msg) {

    }

    @Override
    public void updateProfileInfo(TIMUserProfile profile) {
            modificationMessageHelper.getMyName(profile.getNickName());
    }

    @Override
    public void updateUserInfo(int requestCode, List<TIMUserProfile> profiles) {

    }
}
