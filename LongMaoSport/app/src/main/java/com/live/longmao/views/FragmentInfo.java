package com.live.longmao.views;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.BaseApp;
import com.live.longmao.R;
import com.live.longmao.activity.AccountActivity;
import com.live.longmao.activity.AttestationActivity;
import com.live.longmao.activity.BeanActivity;
import com.live.longmao.activity.GradeActivity;
import com.live.longmao.activity.IncomeActivity;
import com.live.longmao.activity.InfoActivity;
import com.live.longmao.activity.MemberActivity;
import com.live.longmao.activity.PersonAttentionActivity;
import com.live.longmao.activity.PersonFansActivity;
import com.live.longmao.activity.PersonalIncomeActivity;
import com.live.longmao.activity.RankActivity;
import com.live.longmao.activity.SettingActivity;
import com.live.longmao.activity.StoreActivity;
import com.live.longmao.activity.TaskActivity;
import com.live.longmao.dlg.AlertDialog;
import com.live.longmao.info.FastBlur;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.CoinInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.PersonInfo;
import com.live.longmao.model.PersonInfoBean;
import com.live.longmao.presenters.BeanHelper;
import com.live.longmao.presenters.CoinHelper;
import com.live.longmao.presenters.LoginHelper;
import com.live.longmao.presenters.PersonHelper;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.longmao.presenters.viewinface.CoinView;
import com.live.longmao.presenters.viewinface.LogoutView;
import com.live.longmao.presenters.viewinface.PersonView;
import com.live.longmao.pullzoom.PullZoomScrollVIew;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.util.ScreenManager;
import com.live.longmao.webview.AllViewActivity;
import com.live.okhttp.OkHttpUtils;
import com.live.okhttp.callback.BitmapCallback;
import com.umeng.analytics.MobclickAgent;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

;

/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class FragmentInfo extends Fragment implements View.OnClickListener, LogoutView,PersonView, CoinView {

    PullZoomScrollVIew scrollView;
    RelativeLayout tv_test1, tv_test2, tv_test3, tv_test5, tv_test6, rl_edit, rl_contribution, tv_test4;
    ImageView bg_view, editInfo, img_girl, img_boy;
    CircleImageView iv_user_head;
    ProgressBar ref_info;
    LinearLayout infoFans, infoFocus;
    private LoginHelper mLoginHeloper;
    private PersonHelper personHelper;
    private TextView tv_user_id, tv_name, tv_tittle, tv_calorie, tv_bean, tv_fan, tv_attention;
    private String photo;
    private CoinHelper coinHelper;
    private Button btnBack;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_info_new, null);
//            initView();
            initHeadView();
            initContentView();
            initBackDialog();
//            applyBlur();
        }
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeView(v);
        }

        return v;
    }

    private void initView() {
        LinearLayout ll_all = (LinearLayout) v.findViewById(R.id.ll_all);
        scrollView = (PullZoomScrollVIew) ll_all.getChildAt(0);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
        int mScreenHeight = localDisplayMetrics.heightPixels;
        int mScreenWidth = localDisplayMetrics.widthPixels;
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(mScreenWidth, mScreenWidth);
        scrollView.setHeaderLayoutParams(localObject);
        bg_view = (ImageView) v.findViewById(R.id.iv_zoom);
        mLoginHeloper = new LoginHelper(getActivity().getApplicationContext(),this);
    }

    private void initHeadView() {

        mLoginHeloper = new LoginHelper(getActivity().getApplicationContext(), this);

        ref_info = (ProgressBar) v.findViewById(R.id.ref_info);
        rl_edit = (RelativeLayout) v.findViewById(R.id.rl_edit);
        tv_tittle = (TextView) v.findViewById(R.id.tv_tittle);
        img_girl = (ImageView) v.findViewById(R.id.img_girl);
        img_boy = (ImageView) v.findViewById(R.id.img_boy);
        tv_user_id = (TextView) v.findViewById(R.id.tv_user_id);
        tv_name = (TextView) v.findViewById(R.id.tv_name);
        tv_calorie = (TextView) v.findViewById(R.id.tv_calorie);
        tv_bean = (TextView) v.findViewById(R.id.tv_bean);
        tv_attention = (TextView) v.findViewById(R.id.tv_attention);
        tv_fan = (TextView) v.findViewById(R.id.tv_fan);

        btnBack = (Button) v.findViewById(R.id.setting_back);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(getActivity(), rl_edit);
        }
//        rl_contribution = (RelativeLayout) v.findViewById(R.id.rl_contribution);
//        rl_contribution.setOnClickListener(this);
        iv_user_head = (CircleImageView) v.findViewById(R.id.iv_user_head);
        iv_user_head.setOnClickListener(this);
        editInfo = (ImageView) v.findViewById(R.id.edit_info);
        infoFans = (LinearLayout) v.findViewById(R.id.ll_info_fans);
        infoFocus = (LinearLayout) v.findViewById(R.id.ll_info_focus);
        editInfo.setOnClickListener(this);
        infoFans.setOnClickListener(this);
        infoFocus.setOnClickListener(this);
        personHelper = new PersonHelper(this);
        personHelper.getPerson();
        coinHelper = new CoinHelper(this);
        coinHelper.getCoin(MySelfInfo.getInstance().getId());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiteLiveByPurpose();
            }
        });

    }

    private void quiteLiveByPurpose() {
        backDlg.show();
    }

    private AlertDialog backDlg;

    private void initBackDialog() {
        backDlg = new AlertDialog(getContext()).builder();
        backDlg.setTitle("确认退出登录?");
        backDlg.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginHeloper.imLogout();
            }
        });
        backDlg.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initContentView() {
//        tv_test6 = (RelativeLayout) v.findViewById(R.id.tv_test6);
//        tv_test6.setOnClickListener(this);
        tv_test5 = (RelativeLayout) v.findViewById(R.id.tv_test5);
        tv_test5.setOnClickListener(this);
        tv_test3 = (RelativeLayout) v.findViewById(R.id.tv_test3);
        tv_test3.setOnClickListener(this);
        tv_test4 = (RelativeLayout) v.findViewById(R.id.tv_test4);
        tv_test4.setOnClickListener(this);
        tv_test2 = (RelativeLayout) v.findViewById(R.id.tv_test2);
        tv_test2.setOnClickListener(this);
        tv_test1 = (RelativeLayout) v.findViewById(R.id.tv_test1);
        tv_test1.setOnClickListener(this);
    }

    private void applyBlur() {
        bg_view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                bg_view.getViewTreeObserver().removeOnPreDrawListener(this);
                bg_view.buildDrawingCache();
                Bitmap bmp = bg_view.getDrawingCache();
                blur(bmp, bg_view, 6);
                return true;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void blur(Bitmap bkg, View view, float radius) {
        float scaleFactor = 8;
        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth() / scaleFactor),
                (int) (view.getMeasuredHeight() / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.translate(-view.getLeft() / scaleFactor, -view.getTop() / scaleFactor);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(bkg, 0, 0, paint);
        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        bg_view.setImageBitmap(overlay);
        //view.setBackground(new BitmapDrawable(getResources(), overlay));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_test6:
//                //mLoginHeloper.imLogout();
//                startActivity(new Intent(getActivity(), SettingActivity.class));
//                break;
            case R.id.iv_user_head:
            case R.id.edit_info:
                startActivity(new Intent(getActivity(), InfoActivity.class));
                break;
            case R.id.ll_info_fans:
                startActivity(new Intent(getActivity(), PersonFansActivity.class));
                break;
            case R.id.ll_info_focus:
                startActivity(new Intent(getActivity(), PersonAttentionActivity.class));
                break;
            case R.id.tv_test3:
                startActivity(new Intent(getActivity(), GradeActivity.class));
                break;
            case R.id.tv_test4:
                startActivity(new Intent(getActivity(), AllViewActivity.class));
                break;
            case R.id.tv_test2:
                startActivity(new Intent(getActivity(), AccountActivity.class));
                break;
            case R.id.tv_test1:
                startActivity(new Intent(getActivity(), PersonalIncomeActivity.class));
                break;
//            case R.id.ll_info_rank://测试门店修改信息界面
//                startActivity(new Intent(getActivity(), StoreActivity.class));
//                break;
            case R.id.tv_test5:
                startActivity(new Intent(getActivity(), RankActivity.class));
                break;
//            case R.id.ll_info_work:
//                startActivity(new Intent(getActivity(), TaskActivity.class));
//                break;
//            case R.id.rl_contribution:
//                startActivity(new Intent(getActivity(), MemberActivity.class));
//                break;
        }
    }

    @Override
    public void logoutSucc() {
        ScreenManager.getScreenManager().popAllActivity();
    }

    @Override
    public void logoutFail() {

    }

    @Override
    public void onPersonInfoSucc(PersonInfo result) {
        tv_tittle.setText(result.getBody().getSigned());
        tv_name.setText(result.getBody().getNickName());
        tv_user_id.setText("龙猫ID:" + result.getBody().getId());
        photo = result.getBody().getPhotoUrl();
        tv_fan.setText(result.getBody().getAttentionednum() + "");
        tv_attention.setText(result.getBody().getAttentionnum() + "");

        tv_calorie.setText(result.getBody().getCalorie() + "");

        BaseApp.getInstance().setLMId(result.getBody().getId());
        if (result.getBody().getSex() == 0) {
            img_girl.setVisibility(View.VISIBLE);
            img_boy.setVisibility(View.GONE);
        } else {
            img_girl.setVisibility(View.GONE);
            img_boy.setVisibility(View.VISIBLE);
        }
        if (result.getBody().getPhotoUrl() != null) {
            String url = "http://121.40.65.153/photo/";
            GlideUtil.getInstance().load(getActivity(), iv_user_head, url + result.getBody().getPhoneId());
        }
//        if (photo != null) {
//            getImage(photo);
//        }

    }

//    public void getImage(String photo) {
//        //String url = "http://images.csdn.net/20150817/1.jpg";
//        String url = "http://121.40.65.153/photo/" + photo;
//        OkHttpUtils
//                .get()//
//                .url(url)//
//                .tag(this)//
//                .build()//
//                .connTimeOut(20000)
//                .readTimeOut(20000)
//                .writeTimeOut(20000)
//                .execute(new BitmapCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Bitmap bitmap, int id) {
//                        Log.e("TAG", "onResponse：complete");
//                        iv_user_head.setImageBitmap(bitmap);
//                    }
//                });
//    }

    @Override
    public void onPersonInfoError(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心界面");
    }

    @Override
    public void onResume() {
        super.onResume();
        personHelper.getPerson();
        coinHelper.getCoin(MySelfInfo.getInstance().getId());
        MobclickAgent.onPageStart("个人中心界面");
    }

    @Override
    public void onCoinSucc(CoinInfo result) {
        if (result.getBody() == null) {
            tv_bean.setText(0 + "");
        } else {
            tv_bean.setText(result.getBody().get(0).getLmCoinNum() + "");
        }
    }

    @Override
    public void onError(String msg) {

    }
}
