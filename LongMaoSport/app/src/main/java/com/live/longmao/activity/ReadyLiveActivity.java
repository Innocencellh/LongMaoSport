package com.live.longmao.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.BaseApp;
import com.live.longmao.LocationManager;
import com.live.longmao.R;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.presenters.ReadLiveHelper;
import com.live.longmao.presenters.viewinface.ReadLiveView;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.util.Constants;
import com.live.longmao.views.customviews.BaseActivity;
import com.live.longmao.webview.UserViewActivity;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by Administrator on 2016/8/22.
 */
public class ReadyLiveActivity extends BaseActivity implements View.OnClickListener, ReadLiveView {
    private static String TAG = "ReadyLiveActivity";
    private RelativeLayout activity_base_title_rl;
    private ImageView img_close, img_qq, img_wechat, img_pyq, img_weibo;
    private Button btn_start_live;
    private EditText et_title;
    private ReadLiveHelper readLiveHelper;
    private TextView txt_direct_location, tv_zb_xieyi;
    private int addressSel = 1;//1代表定位，2代表没有定位

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_direct_seeding);
        initView();
        initData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeLH(this, activity_base_title_rl);
        }
    }

    private void initQQShare() {
        QQ.ShareParams qq_share = new QQ.ShareParams();
        qq_share.setTitle("龙猫运动");
        qq_share.setTitleUrl("http://www.tvlongmao.com/share.html?uid=id"); // 标题的超链接
        qq_share.setText("龙猫直播，让你高潮的直播");
        qq_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
        qq_share.setSite("龙猫运动");
        qq_share.setSiteUrl("http://www.tvlongmao.com/share.html?uid=id");
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        platform.share(qq_share);
    }

    private void initWeChatShare() {
        Wechat.ShareParams wechat_share = new Wechat.ShareParams();
        wechat_share.setTitle("龙猫运动");
        wechat_share.setText("龙猫直播，让你高潮的直播");
        wechat_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
        wechat_share.setShareType(Platform.SHARE_WEBPAGE);
        wechat_share.setUrl("http://www.tvlongmao.com/share.html?uid=id");
        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
        platform.share(wechat_share);
    }

    private void initWeChatMomentsShare() {
        WechatMoments.ShareParams wechatMoments_share = new WechatMoments.ShareParams();
        wechatMoments_share.setTitle("龙猫运动");
        wechatMoments_share.setText("龙猫直播，让你高潮的直播");
        wechatMoments_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
        wechatMoments_share.setShareType(Platform.SHARE_WEBPAGE);
        wechatMoments_share.setUrl("http://www.tvlongmao.com/share.html?uid=id");
        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
        platform.share(wechatMoments_share);
    }

    private void initSinaWeiboShare() {
        SinaWeibo.ShareParams sina_share = new SinaWeibo.ShareParams();
        sina_share.setTitle("龙猫运动");
        sina_share.setText("龙猫直播，让你高潮的直播");
        sina_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
        sina_share.setSiteUrl("http://www.tvlongmao.com/share.html?uid=id");
        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
        platform.share(sina_share);
    }

    private void initQQZoneShare() {
        QZone.ShareParams qzone_share = new QZone.ShareParams();
        qzone_share.setTitle("龙猫运动");
        qzone_share.setTitleUrl("http://www.tvlongmao.com/share.html?uid=id"); // 标题的超链接
        qzone_share.setText("龙猫直播，让你高潮的直播");
        qzone_share.setImageUrl("http://99touxiang.com/public/upload/nvsheng/125/27-011820_433.jpg");
        qzone_share.setSite("龙猫运动");
        qzone_share.setSiteUrl("http://www.tvlongmao.com/share.html?uid=id");
        Platform platform = ShareSDK.getPlatform(cn.sharesdk.tencent.qzone.QZone.NAME);
        platform.share(qzone_share);
    }

    private void initData() {
        LocationManager.getInstance().addOnLocationListener(new LocationManager.OnLocationListener() {
            @Override
            public void onReceiveLocation(double longitude, double latitude, String cityCode, String city, String province, String country, String district, final String address) {
                txt_direct_location.setText(city);
                addressSel = 1;
                BaseApp.getInstance().setLongitude(longitude);
                BaseApp.getInstance().setLatitude(latitude);
                BaseApp.getInstance().setCity(city);
                Log.d(TAG, "定位成功：" + longitude + "\t" + latitude + "地址:" + address);
            }

            @Override
            public void onFailLocation(int errorCode, String errorInfo) {
                Log.d(TAG, "定位失败，错误码：" + errorCode + "\t错误信息:" + errorInfo);
                addressSel = 2;
            }
        }).start();
    }

    private void initView() {
        readLiveHelper = new ReadLiveHelper(this);
        activity_base_title_rl = (RelativeLayout) findViewById(R.id.activity_base_title_rl);
        img_close = (ImageView) findViewById(R.id.img_close);
        img_qq = (ImageView) findViewById(R.id.img_qq);
        img_wechat = (ImageView) findViewById(R.id.img_wechat);
        img_pyq = (ImageView) findViewById(R.id.img_pyq);
        img_weibo = (ImageView) findViewById(R.id.img_weibo);
        tv_zb_xieyi = (TextView) findViewById(R.id.tv_zb_xieyi);
        img_qq.setSelected(true);
        img_wechat.setSelected(false);
        img_pyq.setSelected(false);
        img_weibo.setSelected(false);
        btn_start_live = (Button) findViewById(R.id.btn_start_live);
        et_title = (EditText) findViewById(R.id.et_title);
        img_close.setOnClickListener(this);
        img_qq.setOnClickListener(this);
        img_wechat.setOnClickListener(this);
        img_pyq.setOnClickListener(this);
        img_weibo.setOnClickListener(this);
        btn_start_live.setOnClickListener(this);
        txt_direct_location = (TextView) findViewById(R.id.txt_direct_location);
        txt_direct_location.setOnClickListener(this);
        tv_zb_xieyi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close:
                this.finish();
                break;
            case R.id.btn_start_live:
                readLiveHelper.getStartLive
                        (MySelfInfo.getInstance().getId(),
                                et_title.getText().toString().trim(),
                                BaseApp.getInstance().getLongitude() + "",
                                BaseApp.getInstance().getLatitude() + "",
                                BaseApp.getInstance().getCity(),
                                MySelfInfo.getInstance().getMyRoomNum() + "");
                break;
            case R.id.img_qq:
                if (img_qq.isSelected()) {
                    img_qq.setSelected(false);
                    img_qq.setImageResource(R.mipmap.icon_qq_un);
                } else {
                    img_qq.setSelected(true);
                    img_wechat.setSelected(false);
                    img_pyq.setSelected(false);
                    img_weibo.setSelected(false);
                    img_qq.setImageResource(R.mipmap.icon_qq_on);
                    img_wechat.setImageResource(R.mipmap.icon_wechat_un);
                    img_pyq.setImageResource(R.mipmap.icon_pyq_un);
                    img_weibo.setImageResource(R.mipmap.icon_weibo_un);
                    initQQShare();
                }
                break;
            case R.id.img_wechat:
                if (img_wechat.isSelected()) {
                    img_wechat.setSelected(false);
                    img_wechat.setImageResource(R.mipmap.icon_wechat_un);
                } else {
                    img_qq.setSelected(false);
                    img_wechat.setSelected(true);
                    img_pyq.setSelected(false);
                    img_weibo.setSelected(false);
                    img_qq.setImageResource(R.mipmap.icon_qq_un);
                    img_wechat.setImageResource(R.mipmap.icon_wechat_on);
                    img_pyq.setImageResource(R.mipmap.icon_pyq_un);
                    img_weibo.setImageResource(R.mipmap.icon_weibo_un);
                    initWeChatShare();
                }
                break;
            case R.id.img_pyq:
                if (img_pyq.isSelected()) {
                    img_pyq.setSelected(false);
                    img_pyq.setImageResource(R.mipmap.icon_pyq_un);
                } else {
                    img_qq.setSelected(false);
                    img_wechat.setSelected(false);
                    img_pyq.setSelected(true);
                    img_weibo.setSelected(false);
                    img_qq.setImageResource(R.mipmap.icon_qq_un);
                    img_wechat.setImageResource(R.mipmap.icon_wechat_un);
                    img_pyq.setImageResource(R.mipmap.icon_pyq_on);
                    img_weibo.setImageResource(R.mipmap.icon_weibo_un);
                    initWeChatMomentsShare();
                }
                break;
            case R.id.img_weibo:
                if (img_weibo.isSelected()) {
                    img_weibo.setSelected(false);
                    img_weibo.setImageResource(R.mipmap.icon_weibo_un);
                } else {
                    img_qq.setSelected(false);
                    img_wechat.setSelected(false);
                    img_pyq.setSelected(false);
                    img_weibo.setSelected(true);
                    img_qq.setImageResource(R.mipmap.icon_qq_un);
                    img_wechat.setImageResource(R.mipmap.icon_wechat_un);
                    img_pyq.setImageResource(R.mipmap.icon_pyq_un);
                    img_weibo.setImageResource(R.mipmap.icon_weibo_on);
                    initSinaWeiboShare();
                }
                break;
            case R.id.txt_direct_location:
                if (addressSel == 1) {
                    addressSel = 2;
                    txt_direct_location.setText("");
                } else if (addressSel == 2) {
                    addressSel = 2;
                    initData();
                }
                break;
            case R.id.tv_zb_xieyi:
                startActivity(new Intent(ReadyLiveActivity.this, UserViewActivity.class));
                break;

        }
    }

    @Override
    public void finish() {
        super.finish();
        //关闭窗体动画显示
        this.overridePendingTransition(0, R.anim.activity_close);
    }

    @Override
    public void onReadLiveSucc() {
        Intent intent = new Intent(this, HostLiveActivity.class);
        intent.putExtra(Constants.ID_STATUS, Constants.HOST);
        MySelfInfo.getInstance().setIdStatus(Constants.HOST);
        CurLiveInfo.setTitle(et_title.getText().toString());
        CurLiveInfo.setHostID(MySelfInfo.getInstance().getId());
        CurLiveInfo.setRoomNum(MySelfInfo.getInstance().getMyRoomNum());
        startActivity(intent);
        this.finish();
    }

    @Override
    public void onReadLiveError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
