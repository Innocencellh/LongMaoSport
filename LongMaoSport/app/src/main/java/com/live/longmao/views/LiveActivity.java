package com.live.longmao.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.live.longmao.R;
import com.live.longmao.activity.AccountActivity;
import com.live.longmao.activity.CheckInfoActivity;
import com.live.longmao.activity.ZBURankActivity;
import com.live.longmao.adapter.UKGuessingAdapter;
import com.live.longmao.adapter.LiveForMsgAdapter;
import com.live.longmao.adapter.WatcherRecycAdapter;
import com.live.longmao.avcontrollers.QavsdkControl;
import com.live.longmao.bean.GiftBean;
import com.live.longmao.bean.GiftData;
import com.live.longmao.dlg.AlertDialog;
import com.live.longmao.dlg.BetDlg;
import com.live.longmao.dlg.InfoDlg;
import com.live.longmao.dlg.guessingdlg.UKGuessNoDialog;
import com.live.longmao.dlg.guessingdlg.UKGuessYesDialog;
import com.live.longmao.dlg.guessingdlg.UKPKGuessingDlg1;
import com.live.longmao.dlg.guessingdlg.UKReadingGuessDialog;
import com.live.longmao.dlg.guessingdlg.UKYaZhuDlg1;
import com.live.longmao.fragment.gif.DaBoatFragment;
import com.live.longmao.fragment.gif.FireworksFragment;
import com.live.longmao.fragment.gif.FlyingHouseFragment;
import com.live.longmao.fragment.gif.RedRainFragment;
import com.live.longmao.fragment.gif.RoadsterFragment;
import com.live.longmao.fragment.gif.SportsrCarFragment;
import com.live.longmao.fragment.gif.XinDongFragment;
import com.live.longmao.gifdlg.FormatUtil;
import com.live.longmao.gifdlg.GiftDlg;
import com.live.longmao.gifdlg.PngNameUtil;
import com.live.longmao.model.BeanInfo;
import com.live.longmao.model.ChatEntity;
import com.live.longmao.model.CoinInfo;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.GetGuessingInfoBean;
import com.live.longmao.model.GuessJieGuoInfo;
import com.live.longmao.model.GuessingInfo;
import com.live.longmao.model.IncomeInfo;
import com.live.longmao.model.LiveInfoJson;
import com.live.longmao.model.MemberInfo;
import com.live.longmao.model.MySelfInfo;
import com.live.longmao.model.SeeLiveInfo;
import com.live.longmao.presenters.BeanHelper;
import com.live.longmao.presenters.CoinHelper;
import com.live.longmao.presenters.EnterLiveHelper;
import com.live.longmao.presenters.FollowHelper;
import com.live.longmao.presenters.GetGuessHelper;
import com.live.longmao.presenters.GetMemberListHelper;
import com.live.longmao.presenters.GiftGivingHelper;
import com.live.longmao.presenters.GuessJieGuoHelper;
import com.live.longmao.presenters.GuessingHelper;
import com.live.longmao.presenters.IncomeHelper;
import com.live.longmao.presenters.LiveHelper;
import com.live.longmao.presenters.OKhttpHelper;
import com.live.longmao.presenters.ProfileInfoHelper;
import com.live.longmao.presenters.SeeLiveHelper;
import com.live.longmao.presenters.UKPKHelper;
import com.live.longmao.presenters.viewinface.BeanView;
import com.live.longmao.presenters.viewinface.CoinView;
import com.live.longmao.presenters.viewinface.EnterQuiteRoomView;
import com.live.longmao.presenters.viewinface.FollowView;
import com.live.longmao.presenters.viewinface.GetGuessingView;
import com.live.longmao.presenters.viewinface.GiftGivingView;
import com.live.longmao.presenters.viewinface.GuessJieGuoView;
import com.live.longmao.presenters.viewinface.GuessingView;
import com.live.longmao.presenters.viewinface.IncomeView;
import com.live.longmao.presenters.viewinface.LiveView;
import com.live.longmao.presenters.viewinface.ProfileView;
import com.live.longmao.presenters.viewinface.SeeLiveView;
import com.live.longmao.presenters.viewinface.UserListView;
import com.live.longmao.util.Constants;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.util.InputUtil;
import com.live.longmao.util.ScreenUtil;
import com.live.longmao.util.SxbLog;
import com.live.longmao.util.UIUtils;
import com.live.longmao.view.EditTextPreimeView;
import com.live.longmao.view.NotCancelableDialog;
import com.live.longmao.view.StrokeTextView;
import com.live.longmao.views.customviews.BaseActivity;
import com.live.longmao.views.customviews.HeartLayout;
import com.live.longmao.views.customviews.MembersDialog;
import com.tencent.TIMMessage;
import com.tencent.TIMTextElem;
import com.tencent.TIMUserProfile;
import com.tencent.av.TIMAvManager;
import com.tencent.av.sdk.AVView;
import com.umeng.analytics.MobclickAgent;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Live直播类
 */
public class LiveActivity extends BaseActivity implements UserListView, EnterQuiteRoomView, BeanView, GetGuessingView, LiveView, View.OnClickListener, ProfileView, View.OnTouchListener, GuessingView, GiftGivingView, FollowView, GuessJieGuoView, SeeLiveView, CoinView, IncomeView {
    private static final String TAG = LiveActivity.class.getSimpleName();
    private static final int GETPROFILE_JOIN = 0x200;

    private EnterLiveHelper mEnterRoomHelper;
    private ProfileInfoHelper mUserInfoHelper;
    private LiveHelper mLiveHelper;

    private ArrayList<ChatEntity> mArrayListChatEntity;
    private LiveForMsgAdapter mLiveForMsgAdapter;
    private static final int MINFRESHINTERVAL = 500;
    private static final int UPDAT_WALL_TIME_TIMER_TASK = 1;
    private static final int TIMEOUT_INVITE = 2;
    private boolean mBoolRefreshLock = false;
    private boolean mBoolNeedRefresh = false;
    private final Timer mTimer = new Timer();
    private ArrayList<ChatEntity> mTmpChatList = new ArrayList<ChatEntity>();//缓冲队列
    private TimerTask mTimerTask = null;
    private static final int REFRESH_LISTVIEW = 5;
    private Dialog mMemberDg, closeCfmDg, inviteDg;
    private HeartLayout mHeartLayout;
    private TextView mLikeTv;
    private HeartBeatTask mHeartBeatTask;//心跳
    private ImageView mHeadIcon;
    private TextView mHostNameTv;
    private LinearLayout mHostLayout;
    //竞猜
    private ListView lv_guess_coll;
    private UKGuessingAdapter ukAdapter;
    private GetGuessHelper getGuessHelper;
    private GuessJieGuoHelper guessJieGuoHelper;
    private CoinHelper coinHelper;
    private String GetguessId;
    private GetGuessingInfoBean getGuessingInfoBean;

    private long mSecond = 0;
    private String formatTime;
    private Timer mHearBeatTimer, mVideoTimer;
    //    private VideoTimerTask mVideoTimerTask;//计时器
    private TextView mVideoTime;
    private ObjectAnimator mObjAnim;
    private ImageView mRecordBall;
    private int thumbUp = 0;
    private long admireTime = 0;
    private int watchCount = 0;

    private boolean bCleanMode = false;

    private String backGroundId;

    private TextView tvMembers;
//    private TextView tvAdmires;

    private Dialog mDetailDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   // 不锁屏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_live);

        //进出房间的协助类
        mEnterRoomHelper = new EnterLiveHelper(this, this);
        //房间内的交互协助类
        mLiveHelper = new LiveHelper(this, this);
        // 用户资料类
        mUserInfoHelper = new ProfileInfoHelper(this);

        guessingHelper = new GuessingHelper(this);

        beanHelper = new BeanHelper(this);

        incomeHelper = new IncomeHelper(this);
        incomeHelper.getLiveIncomeHelper();

        coinHelper = new CoinHelper(this);

        giftGivingHelper = new GiftGivingHelper(this);

        followHelper = new FollowHelper(this);
        initFrame();
        initView();
        initQueue();
        initMap();
        registerReceiver();
        backGroundId = CurLiveInfo.getHostID();
        //进入房间流程
        mEnterRoomHelper.startEnterRoom();

        //QavsdkControl.getInstance().setCameraPreviewChangeCallback();
        mLiveHelper.setCameraPreviewChangeCallback();
    }

//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            switch (msg.what) {
//                case UPDAT_WALL_TIME_TIMER_TASK:
//                    updateWallTime();
//                    break;
//                case REFRESH_LISTVIEW:
//                    doRefreshListView();
//                    break;
//                case TIMEOUT_INVITE:
//                    String id = "" + msg.obj;
//                    cancelInviteView(id);
//                    mLiveHelper.sendGroupMessage(Constants.AVIMCMD_MULTI_HOST_CANCELINVITE, id);
//                    break;
//            }
//            return false;
//        }
//    });

    /**
     * 时间格式化
     */
//    private void updateWallTime() {
//        String hs, ms, ss;
//
//        long h, m, s;
//        h = mSecond / 3600;
//        m = (mSecond % 3600) / 60;
//        s = (mSecond % 3600) % 60;
//        if (h < 10) {
//            hs = "0" + h;
//        } else {
//            hs = "" + h;
//        }
//
//        if (m < 10) {
//            ms = "0" + m;
//        } else {
//            ms = "" + m;
//        }
//
//        if (s < 10) {
//            ss = "0" + s;
//        } else {
//            ss = "" + s;
//        }
//        if (hs.equals("00")) {
//            formatTime = ms + ":" + ss;
//        } else {
//            formatTime = hs + ":" + ms + ":" + ss;
//        }
//
//        if (Constants.HOST == MySelfInfo.getInstance().getIdStatus() && null != mVideoTime) {
//            SxbLog.i(TAG, " refresh time ");
//            mVideoTime.setText(formatTime);
//        }
//    }


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //AvSurfaceView 初始化成功
            if (action.equals(Constants.ACTION_SURFACE_CREATED)) {
                //打开摄像头
                if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
                    mLiveHelper.openCameraAndMic();
                }

            }

            if (action.equals(Constants.ACTION_CAMERA_OPEN_IN_LIVE)) {//有人打开摄像头
                ArrayList<String> ids = intent.getStringArrayListExtra("ids");
                //如果是自己本地直接渲染
                for (String id : ids) {
                    if (id.equals(MySelfInfo.getInstance().getId())) {
                        showVideoView(true, id);
                        return;
//                        ids.remove(id);
                    }
                }
                //其他人一并获取
                int requestCount = CurLiveInfo.getCurrentRequestCount();
                mLiveHelper.requestViewList(ids);
                requestCount = requestCount + ids.size();
                CurLiveInfo.setCurrentRequestCount(requestCount);
//                }
            }

            if (action.equals(Constants.ACTION_SWITCH_VIDEO)) {//点击成员回调
                backGroundId = intent.getStringExtra(Constants.EXTRA_IDENTIFIER);

                if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {//自己是主播
                    if (backGroundId.equals(MySelfInfo.getInstance().getId())) {//背景是自己
                        mHostCtrView.setVisibility(View.VISIBLE);
                        mVideoMemberCtrlView.setVisibility(View.INVISIBLE);
                    } else {//背景是其他成员
                        mHostCtrView.setVisibility(View.INVISIBLE);
                        mVideoMemberCtrlView.setVisibility(View.VISIBLE);
                    }
                } else {//自己成员方式
                    if (backGroundId.equals(MySelfInfo.getInstance().getId())) {//背景是自己
                        mVideoMemberCtrlView.setVisibility(View.VISIBLE);
                        mNomalMemberCtrView.setVisibility(View.INVISIBLE);
                    } else if (backGroundId.equals(CurLiveInfo.getHostID())) {//主播自己
                        mVideoMemberCtrlView.setVisibility(View.INVISIBLE);
                        mNomalMemberCtrView.setVisibility(View.VISIBLE);
                    } else {
                        mVideoMemberCtrlView.setVisibility(View.INVISIBLE);
                        mNomalMemberCtrView.setVisibility(View.INVISIBLE);
                    }

                }

            }
            if (action.equals(Constants.ACTION_HOST_LEAVE)) {//主播结束
                quiteLivePassively();
            }


        }
    };

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_SURFACE_CREATED);
        intentFilter.addAction(Constants.ACTION_HOST_ENTER);
        intentFilter.addAction(Constants.ACTION_CAMERA_OPEN_IN_LIVE);
        intentFilter.addAction(Constants.ACTION_SWITCH_VIDEO);
        intentFilter.addAction(Constants.ACTION_HOST_LEAVE);
        registerReceiver(mBroadcastReceiver, intentFilter);

    }

    private void unregisterReceiver() {
        unregisterReceiver(mBroadcastReceiver);
    }

    /**
     * 初始化UI
     */
    private View avView;
    private ImageView img_gaosi;
    private TextView BtnInput, Btnflash, BtnSwitch, BtnBeauty, BtnMic, BtnScreen, BtnHeart, BtnNormal, mVideoChat, BtnCtrlVideo, BtnCtrlMic, BtnHungup, mBeautyConfirm;
    private TextView inviteView1, inviteView2, inviteView3;
    private RecyclerView mListViewMsgItems;
    private LinearLayout mVideoMemberCtrlView, mBeautySettings;
    private RelativeLayout mNomalMemberCtrView, mFullControllerUi, mHostCtrView;
    private FrameLayout mBackgound;
    private SeekBar mBeautyBar;
    private int mBeautyRate;
    //    private TextView pushBtn;
    private RelativeLayout rl_back;
   // private RecyclerView rv_member_list;//观众
    private RelativeLayout rl_head, rl_inputdlg_view, rl_contribution, topLeftRl;//头布局，聊天布局,贡献榜,关注
    private EditTextPreimeView input_message;//聊天输入框
    private TextView switch_btn, confrim_btn, tv_focus;//聊天弹幕按钮和发送按钮
    private TextView tv_gift;//礼物
    private TextView tv_attention;//关注
    private GiftDlg giftDlg;//礼物dlg
    private InfoDlg infoDlg;//个人信息dlg
    private PowerManager.WakeLock mWakeLock;//电源管理
    //小礼物、弹幕、大礼物最大数
    private int giftlinesCount = 3, barragelinesCount = 3, giflinesCount = 1;
    //送礼父组件的高度(小礼物、弹幕、大礼物)
    private int giftValidHeightSpace, barrageValidHeightSpace, gifValidHeightSpace;
    private RelativeLayout rl_gift, rl_gif, rl_barrage;
    private int lineIndex = 3;
    private HashMap<Integer, Boolean> giftExistLines = new HashMap<>();
    private HashMap<Integer, Boolean> barrageExistLines = new HashMap<>();
    private boolean GifLoadComplete = true;
    private ConcurrentLinkedQueue<GiftData> giftAnimationQueue;  //送礼动画队列
    private ConcurrentLinkedQueue<GiftData> gifAnimationQueue;  //送礼动画队列
    private ConcurrentLinkedQueue<GiftData> barrageAnimationQueue;  //送礼动画队列
    List<GiftData> giftDatas = null;//小礼物动画存储
    private Handler mainHandler = new Handler();
    private Timer giftAnimationTimer;
    // private Timer guessListViewTimer;
    //GuessListViewTaskReal guessListViewTaskReal;
    GiftAnimationTaskReal giftAnimationTaskReal;
    BarrageAnimationTaskReal barrageAnimationTaskReal;
    GifAnimationTaskReal gifAnimationTaskReal;

    private TextView tv_guss, tv_guss_pk;//游客竞猜投注
    private ImageView iv_guess_hand;
    BetDlg betDlg;
    private UKYaZhuDlg1 ukYaZhuDlg1;
    private UKPKGuessingDlg1 ukpkGuessingDlg1;

    private FollowHelper followHelper;
    private GiftGivingHelper giftGivingHelper;
    private GuessingHelper guessingHelper;
    private UKPKHelper ukpkHelper;
    private String yhGuessId;

    // private GuessingInfo mGuessingInfo;
    private List<GuessingInfo> mGuessingInfo = new ArrayList<>();
    private int currIndex = 0;//当前点击的竞猜

    private NotCancelableDialog frameDialog;
    private View rootView;

    private WatcherRecycAdapter watcherAdapter;
    private int page; //用户列表加载更多
    private List<MemberInfo> userList;
    private GetMemberListHelper mGetMemberListHelper;

    private UKGuessNoDialog ukGuessNoDialog;
    private UKGuessYesDialog ukGuessYesDialog;
    private UKReadingGuessDialog ukReadingGuessDialog;
    private BeanHelper beanHelper;
    private IncomeHelper incomeHelper;
    private TextView giftNumberTv;
    private LinearLayout big_gift_tittle;
    private CircleImageView big_gift_hand;
    private TextView big_gift_myname;
    private TextView big_gift_name;
    private Animation acceleraterAnim;

    private void showHeadIcon(ImageView view, String avatar) {
        if (TextUtils.isEmpty(avatar)) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_default_head);
            Bitmap cirBitMap = UIUtils.createCircleImage(bitmap, 0);
            view.setImageBitmap(cirBitMap);
        } else {
            SxbLog.d(TAG, "load icon: " + avatar);
            GlideUtil.getInstance().load(this, view, avatar);
        }
    }

    private void initBetDlg() {
        betDlg = new BetDlg();
        betDlg.setAllClick(new BetDlg.OnClickAllListener() {
            @Override
            public void onClickConfirm(String beanNum, String answer) {
                String userId = MySelfInfo.getInstance().getId();
                String guessId = mGuessingInfo.get(currIndex).getGuessId();
                guessingHelper.userBetGuessing(userId, guessId, answer, beanNum);
            }

            @Override
            public void onClickDismiss() {
                openGiftHideView(false);
            }
        });
    }

    private void initUKYaZhuDlg1() {
        ukYaZhuDlg1 = new UKYaZhuDlg1();
        ukYaZhuDlg1.setAllClick(new UKYaZhuDlg1.OnClickAllListener() {
            @Override
            public void onClickConfirm(String beanNum, String answer) {
                String userId = MySelfInfo.getInstance().getId();
                String guessId = mGuessingInfo.get(currIndex).getGuessId();
                guessingHelper.userBetGuessing(userId, guessId, answer, beanNum);
            }

            @Override
            public void onClickExchange() {
                startActivity(new Intent(LiveActivity.this, AccountActivity.class));
            }

            @Override
            public void onClickDismiss() {
                openGiftHideView(false);
            }
        });
    }

    private void initInfoDlg() {
        infoDlg = new InfoDlg();
        infoDlg.setAllClick(new InfoDlg.OnClickAllListener() {
            @Override
            public void onClickReport() {
                //举报
                showReportDialog();
            }

            @Override
            public void onClickInfo() {
                //个人信息
                startActivity(new Intent(LiveActivity.this, CheckInfoActivity.class));
            }

            @Override
            public void onClickFollow() {
                //关注
            }

            @Override
            public void onClickShielding() {
                //拉黑
            }
        });
    }

    private void initFrame() {
        frameDialog = new NotCancelableDialog(LiveActivity.this, R.style.live_speak_translucent);
        rootView = LayoutInflater.from(LiveActivity.this).inflate(R.layout.dlg_live_player, null);
        frameDialog.setContentView(rootView);
        setDialogFullMode(frameDialog);
        frameDialog.show();
    }

    private void setDialogFullMode(NotCancelableDialog dialog) {
        WindowManager.LayoutParams wl = dialog.getWindow().getAttributes();
        wl.width = WindowManager.LayoutParams.MATCH_PARENT;  //设置宽度
        wl.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(wl);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
        mWakeLock.acquire();
        mWakeLock.setReferenceCounted(false);
        //初始化礼物视图
        rl_gift = (RelativeLayout) rootView.findViewById(R.id.rl_gift);
        //大礼物
        rl_gif = (RelativeLayout) findViewById(R.id.rl_gif);
        rl_barrage = (RelativeLayout) rootView.findViewById(R.id.rl_barrage);

        rootView.findViewById(R.id.rl_lv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 动画设定(指定移动动画) (x1, x2, y1, y2)
//                Animation am = new TranslateAnimation(0,360, 0, 0 )
//                // 动画开始到结束的执行时间(1000 = 1 秒)
//                am.setDuration (2000);
//                am.setFillAfter(true);
//                lv_guess_coll.setAnimation(am);
                lv_guess_coll.setVisibility(View.GONE);
                iv_guess_hand.setVisibility(View.VISIBLE);
            }
        });


        rl_inputdlg_view = (RelativeLayout) rootView.findViewById(R.id.rl_inputdlg_view);
        rl_contribution = (RelativeLayout) rootView.findViewById(R.id.rl_contribution);
        rl_contribution.setOnClickListener(this);
        rl_head = (RelativeLayout) rootView.findViewById(R.id.rl_head);
        input_message = (EditTextPreimeView) rootView.findViewById(R.id.input_message);
        input_message.setClickBack(new EditTextPreimeView.OnClickBackListener() {
            @Override
            public void onClickBack() {
                rl_head.setVisibility(View.VISIBLE);
                rl_inputdlg_view.setVisibility(View.GONE);
            }
        });
        switch_btn = (TextView) rootView.findViewById(R.id.switch_btn);
        confrim_btn = (TextView) rootView.findViewById(R.id.confrim_btn);
        tv_gift = (TextView) rootView.findViewById(R.id.tv_gift);


        lv_guess_coll = (ListView) rootView.findViewById(R.id.lv_guess_coll);
        getGuessHelper = new GetGuessHelper(this);
        lv_guess_coll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (mGuessingInfo.get(position).getStatus() == 1) {
                    currIndex = position;
                    GetguessId = mGuessingInfo.get(position).getGuessId();
                    getGuessHelper.getGuess(GetguessId);
//                    initAnswerDlg();
//                    answerDlg.setTitle(getGuessingInfoBean.getGuessTitle());
//                    answerDlg.setAnA(getGuessingInfoBean.getAnswa());
//                    answerDlg.setAnB(getGuessingInfoBean.getAnswb());
//                    answerDlg.show(getFragmentManager(), "");
//                    getGuessHelper.getGuess(GetguessId);
                } else {
//                    initAnswerDlg();
//                    answerDlg.show(getFragmentManager(), "");
                    currIndex = position;
                    GetguessId = mGuessingInfo.get(position).getGuessId();
                    getGuessHelper.getGuess(GetguessId);
                }
            }
        });

        ukAdapter = new UKGuessingAdapter(mGuessingInfo, this);
        lv_guess_coll.setAdapter(ukAdapter);


        switch_btn.setOnClickListener(this);
        confrim_btn.setOnClickListener(this);
        tv_gift.setOnClickListener(this);
        input_message.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                final String sendStr = input_message.getText().toString().trim();
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    InputUtil.HideKeyboard(rootView);
                    rl_head.setVisibility(View.VISIBLE);
                    rl_inputdlg_view.setVisibility(View.GONE);
                    //do something;
                    if (sendStr == null || "".equals(sendStr)) {
                        return true;
                    } else {
                    }
                    return true;
                }
                return false;
            }
        });
        rl_back = (RelativeLayout) rootView.findViewById(R.id.rl_back);
        rl_back.setOnClickListener(this);
     //   rv_member_list = (RecyclerView) rootView.findViewById(R.id.rv_member_list);
        mHostCtrView = (RelativeLayout) rootView.findViewById(R.id.host_bottom_layout);
        mNomalMemberCtrView = (RelativeLayout) rootView.findViewById(R.id.member_bottom_layout);
        mVideoMemberCtrlView = (LinearLayout) rootView.findViewById(R.id.video_member_bottom_layout);
        mVideoChat = (TextView) rootView.findViewById(R.id.video_interact);
//        mHeartLayout = (HeartLayout) findViewById(R.id.heart_layout);
        mVideoTime = (TextView) rootView.findViewById(R.id.broadcasting_time);
        mHeadIcon = (ImageView) rootView.findViewById(R.id.head_icon);
        mVideoMemberCtrlView.setVisibility(View.INVISIBLE);
        mHostNameTv = (TextView) rootView.findViewById(R.id.host_name);
        tvMembers = (TextView) rootView.findViewById(R.id.member_counts);
//        tvAdmires = (TextView) findViewById(R.id.heart_counts);

        topLeftRl = (RelativeLayout) rootView.findViewById(R.id.topLeftRl);

        BtnCtrlVideo = (TextView) rootView.findViewById(R.id.camera_controll);
        BtnCtrlMic = (TextView) rootView.findViewById(R.id.mic_controll);
        BtnHungup = (TextView) rootView.findViewById(R.id.close_member_video);
        BtnCtrlVideo.setOnClickListener(this);
        BtnCtrlMic.setOnClickListener(this);
        BtnHungup.setOnClickListener(this);
        TextView roomId = (TextView) rootView.findViewById(R.id.room_id);
        giftNumberTv = (TextView) rootView.findViewById(R.id.giftNumberTv);
        roomId.setText("龙猫号：" + CurLiveInfo.getChatRoomId());
        big_gift_tittle= (LinearLayout) rootView.findViewById(R.id.big_gift_tittle);
        big_gift_myname= (TextView) rootView.findViewById(R.id.big_gift_myname);
        big_gift_name= (TextView) rootView.findViewById(R.id.big_gift_name);
        big_gift_hand= (CircleImageView) rootView.findViewById(R.id.big_gift_hand);
//        inviteView1 = (TextView) rootView.findViewById(R.id.invite_view1);
//        inviteView2 = (TextView) rootView.findViewById(R.id.invite_view2);
//        inviteView3 = (TextView) rootView.findViewById(R.id.invite_view3);
//        inviteView1.setOnClickListener(this);
//        inviteView2.setOnClickListener(this);
//        inviteView3.setOnClickListener(this);
      //  initInfoDlg();
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            mHostCtrView.setVisibility(View.VISIBLE);
            mNomalMemberCtrView.setVisibility(View.GONE);
            mRecordBall = (ImageView) rootView.findViewById(R.id.record_ball);
//            Btnflash = (TextView) rootView.findViewById(R.id.flash_btn);
//            BtnSwitch = (TextView) rootView.findViewById(R.id.switch_cam);
//            BtnBeauty = (TextView) rootView.findViewById(R.id.beauty_btn);
//            BtnMic = (TextView) rootView.findViewById(R.id.mic_btn);
//            BtnScreen = (TextView) rootView.findViewById(R.id.fullscreen_btn);
            mVideoChat.setVisibility(View.VISIBLE);
            Btnflash.setOnClickListener(this);
            BtnSwitch.setOnClickListener(this);
            BtnBeauty.setOnClickListener(this);
            BtnMic.setOnClickListener(this);
            BtnScreen.setOnClickListener(this);
            mVideoChat.setOnClickListener(this);

            //   pushBtn = (TextView) rootView.findViewById(R.id.push_btn);
            //    pushBtn.setVisibility(View.VISIBLE);
//            pushBtn.setOnClickListener(this);

            initPushDialog();


            mMemberDg = new MembersDialog(this, R.style.floag_dialog, this);
            //showHeadIcon(mHeadIcon, MySelfInfo.getInstance().getAvatar());
            GlideUtil.getInstance().load(this, mHeadIcon, CurLiveInfo.getHandImg());
            mBeautySettings = (LinearLayout) rootView.findViewById(R.id.qav_beauty_setting);
            mBeautyConfirm = (TextView) rootView.findViewById(R.id.qav_beauty_setting_finish);
            mBeautyConfirm.setOnClickListener(this);
            mBeautyBar = (SeekBar) (rootView.findViewById(R.id.qav_beauty_progress));
            mBeautyBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                    SxbLog.d("SeekBar", "onStopTrackingTouch");
                    Toast.makeText(LiveActivity.this, "beauty " + mBeautyRate + "%", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                    SxbLog.d("SeekBar", "onStartTrackingTouch");
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    // TODO Auto-generated method stub
                    mBeautyRate = progress;
                    QavsdkControl.getInstance().getAVContext().getVideoCtrl().inputBeautyParam(getBeautyProgress(progress));

                }
            });
        } else {
//            LinearLayout llRecordTip = (LinearLayout) findViewById(R.id.record_tip);
//            llRecordTip.setVisibility(View.GONE);
            mHostNameTv.setVisibility(View.VISIBLE);
            initInviteDialog();
            initGiftDlg();
            mNomalMemberCtrView.setVisibility(View.VISIBLE);
            mHostCtrView.setVisibility(View.GONE);
            BtnInput = (TextView) rootView.findViewById(R.id.message_input);
            BtnInput.setOnClickListener(this);
//            mLikeTv = (TextView) findViewById(R.id.member_send_good);
//            mLikeTv.setOnClickListener(this);
            mVideoChat.setVisibility(View.GONE);
            BtnScreen = (TextView) rootView.findViewById(R.id.clean_screen);
//            tv_guss = (TextView) rootView.findViewById(R.id.tv_guss);
//            tv_guss.setOnClickListener(this);

            iv_guess_hand = (ImageView) rootView.findViewById(R.id.iv_guess_hand);
            tv_guss_pk = (TextView) rootView.findViewById(R.id.tv_guss_pk);
            tv_guss_pk.setOnClickListener(this);
            iv_guess_hand.setOnClickListener(this);


            tv_focus = (TextView) rootView.findViewById(R.id.tv_focus);
            tv_focus.setOnClickListener(this);

            List<String> ids = new ArrayList<>();
            ids.add(CurLiveInfo.getHostID());
            //  showHeadIcon(mHeadIcon, CurLiveInfo.getHostAvator());
            GlideUtil.getInstance().load(this, mHeadIcon, CurLiveInfo.getHandImg());
            mHostNameTv.setText(UIUtils.getLimitString(CurLiveInfo.getHostName(), 8));
            mHeadIcon.setOnClickListener(this);
            BtnScreen.setOnClickListener(this);
            initBackDialog();
        }
        BtnNormal = (TextView) rootView.findViewById(R.id.normal_btn);
        BtnNormal.setOnClickListener(this);
        mFullControllerUi = (RelativeLayout) rootView.findViewById(R.id.controll_ui);
        mFullControllerUi.setOnTouchListener(this);
        //主布局上的
        img_gaosi = (ImageView) findViewById(R.id.img_gaosi);
        avView = findViewById(R.id.av_video_layer_ui);//surfaceView;

        mListViewMsgItems = (RecyclerView) rootView.findViewById(R.id.im_msg_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListViewMsgItems.setLayoutManager(layoutManager);
        mArrayListChatEntity = new ArrayList<ChatEntity>();
        mLiveForMsgAdapter = new LiveForMsgAdapter(mArrayListChatEntity, this);
        mListViewMsgItems.setAdapter(mLiveForMsgAdapter);
        tvMembers.setText("" + CurLiveInfo.getMembers());
//        tvAdmires.setText("" + CurLiveInfo.getAdmires());
    }

    private void initGiftDlg() {
        giftDlg = new GiftDlg();
        giftDlg.setHandleGive(new GiftDlg.OnClickGiveListener() {
            @Override
            public void onClickDismiss() {
                openGiftHideView(false);
            }

            @Override
            public void onClickExchange() {
                startActivity(new Intent(LiveActivity.this, AccountActivity.class));
            }

            @Override
            public void onClickGive(GiftBean giftBean) {
                String userId = MySelfInfo.getInstance().getId();
                String anchorUserId = CurLiveInfo.getChatRoomId();
                String giftid = String.valueOf(giftBean.getId());
                String liveId = CurLiveInfo.getChatRoomId();
                giftGivingHelper.givingGift(userId, anchorUserId, giftid, liveId, giftBean);
            }
        });
    }

    //小礼物动画
    private void giftAni(final GiftData giftData) {
        final int[] num = {1};
        initGiftValidHeightSpace();
        final View giftView = LayoutInflater.from(LiveActivity.this).inflate(R.layout.cell_live_gift, null);
        final CircleImageView faceIv = (CircleImageView) giftView.findViewById(R.id.iv_face);
        if (null == giftData.getSendImg() || giftData.getSendImg().equals("")) {
            faceIv.setImageResource(R.mipmap.ic_default_head);
        } else {
            GlideUtil.getInstance().load(this, faceIv, giftData.getSendImg());
        }
        final TextView nicknameTv = (TextView) giftView.findViewById(R.id.tv_nickname);
        nicknameTv.setText(giftData.getSendName());
        //计算本条弹幕的topMargin(随机值，但是与屏幕中已有的不重复)
        int verticalMargin = getGiftRandomTopMargin(giftValidHeightSpace, giftlinesCount, giftView);
        if (verticalMargin == -1) {
            return;
        }
        //记录当前仍在显示状态的弹幕的位置（避免重复）
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.bottomMargin = verticalMargin;
        giftView.setLayoutParams(params);
        rl_gift.addView(giftView);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(giftView, "translationX", -UIUtils.getMeasuredWidth(giftView), 0).setDuration(500);

        animator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (null == giftData)
                    return;
                //移动icon动画
                ImageView giftIv = (ImageView) giftView.findViewById(R.id.iv_gift);
                Glide.with(LiveActivity.this).load("file:///android_asset/" + giftData.getGiftImg() + ".png").into(giftIv);
                giftIv.setVisibility(View.VISIBLE);
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(giftIv, "translationX", -(FormatUtil.pixOfDip(115)), 0);
                animator1.setDuration(200);
                animator1.start();
                //X动画
                final StrokeTextView strokeTextView = (StrokeTextView) giftView.findViewById(R.id.txt_stroke);
                TextView giftInfoTv = (TextView) giftView.findViewById(R.id.tv_gift_info);
                strokeTextView.setText("X" + num[0]);
                giftInfoTv.setText(giftData.getGiftName());
                final Animation txtAni = AnimationUtils.loadAnimation(LiveActivity.this, R.anim.ani_live_giftnum);
                Animation.AnimationListener txtAnimationListener = new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        mainHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (num[0] == giftData.getNum()) {
                                    //移除占位
                                    int lineIndex = (int) giftView.getTag();
                                    giftExistLines.put(lineIndex, false);
                                    giftDatas.remove(giftData);
                                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(giftView, "translationY", 0, -UIUtils.getMeasuredHeight(giftView));
                                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(giftView, "alpha", 1.0f, 0f);
                                    AnimatorSet set = new AnimatorSet().setDuration(1000);
                                    set.playTogether(animator1, animator2);
                                    set.addListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            mainHandler.post(new Runnable() {
                                                public void run() {
                                                    rl_gift.removeView(giftView);
                                                }
                                            });
                                        }
                                    });
                                    set.start();

                                } else {
                                    ++num[0];
                                    strokeTextView.setText("X" + num[0]);
                                    strokeTextView.startAnimation(txtAni);
                                }
                            }
                        }, 1000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                };
                txtAni.setAnimationListener(txtAnimationListener);
                strokeTextView.startAnimation(txtAni);
            }
        });
        animator1.start();
    }


    //大礼物弹幕
    private void initBigGiftTittle() {
        acceleraterAnim = AnimationUtils.loadAnimation(this, R.anim.big_gift_anim);
        big_gift_tittle.setAnimation(acceleraterAnim);
        big_gift_tittle.setVisibility(View.VISIBLE);
        mainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                big_gift_tittle.clearAnimation();
                big_gift_tittle.invalidate();
                big_gift_tittle.setVisibility(View.GONE);
            }
        }, 5000);
    }


    //大礼物动画
    private void gifAni(GiftData giftData) {
        GifLoadComplete = false;
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (giftData.getGiftID() == 7) {
            final FlyingHouseFragment flyingHouseFragment = new FlyingHouseFragment();
            flyingHouseFragment.setLoadComplete(new FlyingHouseFragment.LoadComplete() {
                @Override
                public void loadComplete() {
                    GifLoadComplete = true;
                }
            });
            initBigGiftTittle();
            transaction.replace(R.id.rl_gif, flyingHouseFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (giftData.getGiftID() == 8) {
            final RoadsterFragment roadsterFragment = new RoadsterFragment();
            roadsterFragment.setLoadComplete(new RoadsterFragment.LoadComplete() {
                @Override
                public void loadComplete() {
                    GifLoadComplete = true;
                }
            });
            initBigGiftTittle();
            transaction.replace(R.id.rl_gif, roadsterFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (giftData.getGiftID() == 13) {
            final RedRainFragment redRainFragment = new RedRainFragment();
            redRainFragment.setLoadComplete(new RedRainFragment.LoadComplete() {
                @Override
                public void loadComplete() {
                    GifLoadComplete = true;
                }
            });
            initBigGiftTittle();
            transaction.replace(R.id.rl_gif, redRainFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (giftData.getGiftID() == 14) {
            final XinDongFragment xinDongFragment = new XinDongFragment();
            xinDongFragment.setLoadComplete(new XinDongFragment.LoadComplete() {
                @Override
                public void loadComplete() {
                    GifLoadComplete = true;
                }
            });
            initBigGiftTittle();
            transaction.replace(R.id.rl_gif, xinDongFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (giftData.getGiftID() == 15) {
            final FireworksFragment fireworksFragment = new FireworksFragment();
            fireworksFragment.setLoadComplete(new FireworksFragment.LoadComplete() {
                @Override
                public void loadComplete() {
                    GifLoadComplete = true;
                }
            });
            initBigGiftTittle();
            transaction.replace(R.id.rl_gif, fireworksFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (giftData.getGiftID() == 16) {
            final DaBoatFragment daBoatFragment = new DaBoatFragment();
            daBoatFragment.setLoadComplete(new DaBoatFragment.LoadComplete() {
                @Override
                public void loadComplete() {
                    GifLoadComplete = true;
                }
            });
            initBigGiftTittle();
            transaction.replace(R.id.rl_gif, daBoatFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (giftData.getGiftID() == 17) {
            final SportsrCarFragment sportsrCarFragment = new SportsrCarFragment();
            sportsrCarFragment.setLoadComplete(new SportsrCarFragment.LoadComplete() {
                @Override
                public void loadComplete() {
                    GifLoadComplete = true;
                }
            });
            initBigGiftTittle();
            transaction.replace(R.id.rl_gif, sportsrCarFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (giftData.getGiftID() == 18) {
            final DaBoatFragment daBoatFragment = new DaBoatFragment();
            daBoatFragment.setIsSVIP(1);
            daBoatFragment.setLoadComplete(new DaBoatFragment.LoadComplete() {
                @Override
                public void loadComplete() {
                    GifLoadComplete = true;
                }
            });
            initBigGiftTittle();
            transaction.replace(R.id.rl_gif, daBoatFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
//        rl_gif.bringToFront();
    }

    //弹幕动画
    private void barrageAni(GiftData giftData) {
        initBarrageValidHeightSpace();
        final View barrageView = LayoutInflater.from(LiveActivity.this).inflate(R.layout.cell_live_barrage, null);
        TextView nicknameTv = (TextView) barrageView.findViewById(R.id.nicknameTv);
        nicknameTv.setText(giftData.getSendName());
        TextView contentTv = (TextView) barrageView.findViewById(R.id.contentTv);
        contentTv.setText(giftData.getMessage());
        ImageView faceIv = (ImageView) barrageView.findViewById(R.id.faceIv);
        if (null == giftData.getSendImg() || giftData.getSendImg().equals("")) {
            faceIv.setImageResource(R.mipmap.ic_default_head);
        } else {
            GlideUtil.getInstance().load(this, faceIv, giftData.getSendImg());
        }
        int verticalMargin = getBarrageRandomTopMargin(barrageValidHeightSpace, barrageView);
        if (verticalMargin == -1) {
            return;
        }
        //记录当前仍在显示状态的弹幕的位置（避免重复）
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.topMargin = verticalMargin;
//        params.leftMargin = ScreenUtil.getScreenWidth(LiveActivity.this);
        barrageView.setLayoutParams(params);
        rl_barrage.addView(barrageView);
        int width = UIUtils.getMeasuredWidth(barrageView);
        ObjectAnimator animator = ObjectAnimator.ofFloat(barrageView, "translationX", -width, ScreenUtil.getScreenWidth(LiveActivity.this)).setDuration(8000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //移除该组件
                mainHandler.post(new Runnable() {
                    public void run() {
                        rl_barrage.removeView(barrageView);
                    }
                });
                //移除占位
                int lineIndex = (int) barrageView.getTag();
                barrageExistLines.put(lineIndex, false);
            }
        });
        animator.start();
    }

    //存储小礼物
    private void storageGiftList(GiftData giftData) {
        if (null == giftDatas) {
            giftDatas = new ArrayList<>();
        }
        boolean isDodger = false;
        if (giftDatas.size() > 0) {

            for (int i = 0; i < giftDatas.size(); i++) {
                if (giftData.getSendID().equals(giftDatas.get(i).getSendID()) && giftData.getGiftID() == giftDatas.get(i).getGiftID()) {
                    isDodger = true;
                    giftDatas.get(i).setNum(giftDatas.get(i).getNum() + giftData.getNum());
                }
            }
        }
        if (!isDodger) {
            giftDatas.add(giftData);
            giftAnimationQueue.offer(giftData);
        }
    }

    private void sendGiftList() {
        if (null == giftDatas)
            return;
        if (!giftExistLines.get(0) || !giftExistLines.get(1) || !giftExistLines.get(2)) {
            GiftData giftData = giftAnimationQueue.poll();
            if (null != giftData) {
                giftAni(giftData);
            }
        }
    }

    //存储弹幕
    private void storageBarrageList(GiftData giftData) {
        barrageAnimationQueue.offer(giftData);
    }

    private void sendBarrageList() {
        if (!barrageExistLines.get(0) || !barrageExistLines.get(1) || !barrageExistLines.get(2)) {
            GiftData giftData = barrageAnimationQueue.poll();
            if (null != giftData) {
                barrageAni(giftData);
            }
        }
    }

    //存储大礼物
    private void storageGifList(GiftData giftData) {
        gifAnimationQueue.offer(giftData);
    }

    private void sendGifList() {
        if (GifLoadComplete) {
            GiftData giftData = gifAnimationQueue.poll();
            if (null != giftData) {
                gifAni(giftData);
            }
        }
    }

    //初始化队列
    private void initQueue() {
        if (null == giftAnimationQueue) {
            giftAnimationQueue = new ConcurrentLinkedQueue<>();
        }
        if (null == gifAnimationQueue) {
            gifAnimationQueue = new ConcurrentLinkedQueue<>();
        }
        if (null == barrageAnimationQueue) {
            barrageAnimationQueue = new ConcurrentLinkedQueue<>();
        }
    }

    //初始化map
    private void initMap() {
        for (int i = 0; i < giftlinesCount; i++)
            giftExistLines.put(i, false);
        for (int i = 0; i < barragelinesCount; i++)
            barrageExistLines.put(i, false);
    }

    private int initGiftValidHeightSpace() {
        //计算用于弹幕显示的空间高度
        return giftValidHeightSpace = rl_gift.getBottom() - rl_gift.getTop()
                - rl_gift.getPaddingTop() - rl_gift.getPaddingBottom();
    }

    private int initBarrageValidHeightSpace() {
        //计算用于弹幕显示的空间高度
        return barrageValidHeightSpace = rl_barrage.getBottom() - rl_barrage.getTop()
                - rl_barrage.getPaddingTop() - rl_barrage.getPaddingBottom();
    }

    //小礼物位置
    private int getGiftRandomTopMargin(int giftValidHeightSpace, int giftlinesCount, View gift) {
        int seatedSize = 0;
        for (int i = 0; i < giftlinesCount; i++) {
            if (giftExistLines.get(i)) {
                seatedSize++;
            }
        }
        if (seatedSize >= giftlinesCount)
            return -1;      //没有空位
        else {
            for (int i = giftlinesCount - 1; i >= 0; i--) {
                if (!giftExistLines.get(i)) {
                    lineIndex = i;
                    break;
                }
            }
            int marginValue = (giftlinesCount - lineIndex - 1) * (giftValidHeightSpace / this.giftlinesCount);
            giftExistLines.put(lineIndex, true);
            gift.setTag(lineIndex);
            return marginValue;
        }
    }

    //弹幕位置
    private int getBarrageRandomTopMargin(int barrageValidHeightSpace, View barrage) {
        int seatedSize = 0;
        for (int i = 0; i < barragelinesCount; i++) {
            if (barrageExistLines.get(i)) {
                seatedSize++;
            }
        }
        if (seatedSize >= barragelinesCount)
            return -1;
        else {
            for (int i = barragelinesCount - 1; i >= 0; i--) {
                if (!barrageExistLines.get(i)) {
                    lineIndex = i;
                    break;
                }
            }
            int marginValue = lineIndex * (barrageValidHeightSpace / barragelinesCount);
            barrageExistLines.put(lineIndex, true);
            barrage.setTag(lineIndex);
            return marginValue;
        }
    }

    private void initRv() {
        if (null == userList) {
            userList = new ArrayList<>();
            userList = Collections.synchronizedList(userList);
        }
        if (null == watcherAdapter) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LiveActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //    rv_member_list.setLayoutManager(linearLayoutManager);
            watcherAdapter = new WatcherRecycAdapter(this, userList, new WatcherRecycAdapter.WatcherItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    //弹出信息dialog
                    infoDlg.show(getFragmentManager(), "");
                }
            });
//            rv_member_list.setAdapter(watcherAdapter);
//            rv_member_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                //用来标记是否正在向最后一个滑动，既是否向右滑动或向下滑动
//                boolean isSlidingToLast = false;
//
//                @Override
//                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//                    // 当不滚动时
//                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                        //获取最后一个完全显示的ItemPosition
//                        int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
//                        int totalItemCount = manager.getItemCount();
//                        // 判断是否滚动到底部，并且是向右滚动
//                        if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
//                            //加载更多功能的代码
//
//                        }
//                    }
//                }
//
//                @Override
//                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//                    //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
//                    if (dx > 0) {
//                        //大于0表示，正在向右滚动
//                        isSlidingToLast = true;
//                    } else {
//                        //小于等于0 表示停止或向左滚动
//                        isSlidingToLast = false;
//                    }
//
//                }
//            });
        }
        mGetMemberListHelper = new GetMemberListHelper(this, this);
        mGetMemberListHelper.getMemberList(CurLiveInfo.getChatRoomId());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (null != rl_inputdlg_view) {
                    InputUtil.HideKeyboard(rootView);
                    rl_head.setVisibility(View.VISIBLE);
                    rl_inputdlg_view.setVisibility(View.GONE);
                }
                break;
        }
        return true;
    }

    @Override
    public void showMembersList(ArrayList<MemberInfo> data) {
        if (data == null) return;
        userList.clear();
        for (MemberInfo info : data) {
            userList.add(info);
            userList.add(info);
            userList.add(info);
            userList.add(info);
            userList.add(info);
            userList.add(info);
            userList.add(info);
            userList.add(info);
        }
        watcherAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        QavsdkControl.getInstance().onResume();
        MobclickAgent.onPageStart("用户观看直播界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        QavsdkControl.getInstance().onPause();
        MobclickAgent.onPageEnd("用户观看直播界面");
        MobclickAgent.onPause(this);
    }

    @Override
    public void onGuessingSucc() {

    }

    @Override
    public void onGuessingRead(List<GuessingInfo> guessingInfos, boolean isSucc) {

//        if(isSucc&&null!=guessingInfos&&guessingInfos.getStatus()==1) {
//            mGuessingInfo = guessingInfos;
//            openGiftHideView(true);
//            initBetDlg();
//            betDlg.show(getFragmentManager(), "");
//            mainHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    betDlg.setTextViewForProblem(mGuessingInfo.getGuessTitle()+"？");
//                    betDlg.setTextViewForAQ(mGuessingInfo.getAnswa());
//                    betDlg.setTextViewForBQ(mGuessingInfo.getAnswb());
//                    betDlg.setTextViewForCountdown(mGuessingInfo.getSurplusTime());
//                    betDlg.setTextForBetNumChange(mGuessingInfo.getBeanNum(), mGuessingInfo.getCountBeanNum());
//                }
//            }, 100);
//        }else
//        {
//            Toast.makeText(this,"主播当前没有发布竞猜",Toast.LENGTH_SHORT).show();
//        }


        if (guessingInfos != null) {
            mGuessingInfo.clear();
            ukAdapter.notifyDataSetChanged();
            mGuessingInfo.addAll(guessingInfos);
            ukAdapter.notifyDataSetChanged();
        } else {
            mGuessingInfo.clear();
            ukAdapter.notifyDataSetChanged();
            Toast.makeText(LiveActivity.this, "主播未开盘", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGuessingEntertained(GuessingInfo guessingInfo) {

    }

    @Override
    public void onGuessingBet(GuessingInfo guessingInfos) {
        //      openGiftHideView(false);
        GiftData data = new GiftData();
        data.setBeanNum(guessingInfos.getBetBeanNum());
        data.setMessage("下注了" + guessingInfos.getBetBeanNum() + "个龙猫豆");
        data.setType(5);
        data.setLevel(1);
        data.setSendID(MySelfInfo.getInstance().getId());
        data.setSendImg(MySelfInfo.getInstance().getAvatar());
        data.setSendName(MySelfInfo.getInstance().getNickName());
        sendText(data);
        Toast.makeText(LiveActivity.this, "下注成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGuessingJieSuanSucc() {

    }

    @Override
    public void onGuessingError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        openGiftHideView(false);
    }

    @Override
    public void onGiftGivingSucc(GiftBean giftBean) {
        GiftData data = new GiftData();
        data.setMessage("送一个" + giftBean.getGiftName());
        if (giftBean.getIsSend() == 1) {
            data.setType(2);
        } else if (giftBean.getIsSend() == 2 || giftBean.getIsSend() == 3) {
            //发送大礼物IM操作
            data.setType(3);
            Toast.makeText(LiveActivity.this, "你送给了主播一个大礼物，好赞啊！！！", Toast.LENGTH_SHORT).show();

        }
        data.setGiftID(giftBean.getId());
        data.setGiftImg(giftBean.getPngName());
        data.setGiftName("送一个" + giftBean.getGiftName());
        data.setLevel(1);
        data.setNum(1);
        data.setSendID(MySelfInfo.getInstance().getId());
        data.setSendImg(MySelfInfo.getInstance().getAvatar());
        data.setSendName(MySelfInfo.getInstance().getNickName());
        sendText(data);
        coinHelper.getCoin(MySelfInfo.getInstance().getId());
        incomeHelper = new IncomeHelper(this);
        incomeHelper.getLiveIncomeHelper();
    }

    @Override
    public void onCoinSucc(CoinInfo result) {
        if (result.getBody() == null) {
            Toast.makeText(LiveActivity.this, "你还没有龙猫币", Toast.LENGTH_SHORT).show();
        }else {
            giftDlg.setCoin(result.getBody().get(0).getLmCoinNum() + "");
        }

    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFollowSucc(int pos) {

    }

    @Override
    public void onCancelFollowSucc(int pos) {

    }

    @Override
    public void onFollowError(String msg) {

    }

    @Override
    public void onGetGuessingSucc(GetGuessingInfoBean result) {

        if (result.getStatus() == 1) {
            getGuessingInfoBean = result;
            initUKYaZhuDlg1();
            beanHelper.getBean(MySelfInfo.getInstance().getId());
            ukYaZhuDlg1.show(getFragmentManager(), "");
            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ukYaZhuDlg1.setTextTittle(getGuessingInfoBean.getGuessTitle() + "?",
                            "A. " + getGuessingInfoBean.getAnswa(), "B. " + getGuessingInfoBean.getAnswb());

                    ukYaZhuDlg1.setTextViewForCountdown(getGuessingInfoBean.getSurplusTime());
                    ukYaZhuDlg1.setTextForBetNum(getGuessingInfoBean.getCountBeanNum() + "/" + getGuessingInfoBean.getBeanNum());
                    //ukYaZhuDlg1.setTextForBetNumChange(getGuessingInfoBean.getCountBeanNum());
                    ukYaZhuDlg1.setTextBetNumChange();
                }
            }, 100);
        } else if (result.getStatus() == 2) {
            Toast.makeText(LiveActivity.this, "查看盘信息", Toast.LENGTH_SHORT).show();
            ukReadingGuessDialog = new UKReadingGuessDialog();
            getGuessingInfoBean = result;
            ukReadingGuessDialog.setTitle(getGuessingInfoBean.getGuessTitle());
            ukReadingGuessDialog.setAnA(getGuessingInfoBean.getAnswa());
            ukReadingGuessDialog.setAnB(getGuessingInfoBean.getAnswb());
            ukReadingGuessDialog.show(getFragmentManager(), "");
            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ukReadingGuessDialog.setTextForBetNumChange(getGuessingInfoBean.getCountBeanNum(), getGuessingInfoBean.getBeanNum());
                }
            }, 100);

        } else {
            Toast.makeText(LiveActivity.this, "主播未开盘", Toast.LENGTH_SHORT).show();
        }

//        if (result.getStatus() == 1) {
//            initUKYaZhuDlg1();
//            ukYaZhuDlg1.show(getFragmentManager(), "");
//            ukYaZhuDlg1.setTextTittle(result.getGuessTitle() + "?",
//                           "A. " + result.getAnswa(), "B. " + result.getAnswb());
//        }else {
//            Toast.makeText(LiveActivity.this, "查看封盘信息", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public void onGetGuessingError(String msg) {

    }

    @Override
    public void onBeanSucc(BeanInfo result) {

        if (result.getBody() == null) {
            Toast.makeText(LiveActivity.this, "你还没有龙猫豆", Toast.LENGTH_SHORT).show();
        }else {
            ukYaZhuDlg1.setBean(result.getBody().get(0).getLmBeanNum() + "");
        }



    }

    @Override
    public void onBeanError(String msg) {

    }

    @Override
    public void GuessJieGuoSucc(final GuessJieGuoInfo result) {

        if (result.getBody().get(0).isWinFlag() == true) {
            ukGuessYesDialog = new UKGuessYesDialog();
            ukGuessYesDialog.setAnA(result.getBody().get(0).getCntNumA() + "");
            ukGuessYesDialog.setAnB(result.getBody().get(0).getCntNumB() + "");
            ukGuessYesDialog.setAll(result.getBody().get(0).getBetBeanNum() + "");
            ukGuessYesDialog.show(getFragmentManager(), "");
            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ukGuessYesDialog.setTextYesBetNumChange(result.getBody().get(0).getCntNumA(),
                            result.getBody().get(0).getBeanNum());
                    ukGuessYesDialog.setTextNoBetNumChange(result.getBody().get(0).getCntNumB(),
                            result.getBody().get(0).getBeanNum());
                }
            }, 100);


        } else {
            ukGuessNoDialog = new UKGuessNoDialog();
            ukGuessNoDialog.setAnA(result.getBody().get(0).getCntNumA() + "");
            ukGuessNoDialog.setAnB(result.getBody().get(0).getCntNumB() + "");
            ukGuessNoDialog.setAll(result.getBody().get(0).getBetBeanNum() + "");
            ukGuessNoDialog.show(getFragmentManager(), "");
            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ukGuessNoDialog.setTextYesBetNumChange(result.getBody().get(0).getCntNumA(),
                            result.getBody().get(0).getBeanNum());
                    ukGuessNoDialog.setTextNoBetNumChange(result.getBody().get(0).getCntNumB(),
                            result.getBody().get(0).getBeanNum());
                }
            }, 100);


        }

    }

    @Override
    public void GuessJieGuoError(String msg) {
        Toast.makeText(LiveActivity.this, "快来参与竞猜", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSeeLiveSucc(SeeLiveInfo result) {
        if (result.getBody().getAttFlag() == 0) {
            Toast.makeText(LiveActivity.this, "欢迎进入直播间", Toast.LENGTH_SHORT).show();
            tv_focus.setVisibility(View.VISIBLE);
        } else {
            initAttention();
            Toast.makeText(LiveActivity.this, "欢迎进入直播间", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSeeLiveError(String msg) {

    }

    @Override
    public void IncomeViewSucc(IncomeInfo result) {
        giftNumberTv.setText(result.getBody().getCalorie() + "");
    }

    @Override
    public void IncomeViewError(String msg) {

    }


    /**
     * 直播心跳
     */
    private class HeartBeatTask extends TimerTask {
        @Override
        public void run() {
            String host = CurLiveInfo.getHostID();
            SxbLog.i(TAG, "HeartBeatTask " + host);
            OKhttpHelper.getInstance().sendHeartBeat(host, CurLiveInfo.getMembers(), CurLiveInfo.getAdmires(), 0);
        }
    }

    //    /**
//     * 记时器
//     */
//    private class VideoTimerTask extends TimerTask {
//        public void run() {
//            SxbLog.i(TAG, "timeTask ");
//            ++mSecond;
//            if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST)
//                mHandler.sendEmptyMessage(UPDAT_WALL_TIME_TIMER_TASK);
//        }
//    }
    private class GiftAnimationTaskReal extends TimerTask {
        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    sendGiftList();
                }
            });
        }
    }

    private class BarrageAnimationTaskReal extends TimerTask {
        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    sendBarrageList();
                }
            });
        }
    }

    //轮询的方法
    private class GifAnimationTaskReal extends TimerTask {
        @Override
        public void run() {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    //要进行实现轮序的方法
                    sendGifList();
                }
            });
        }
    }

    private class GuessListViewTaskReal extends TimerTask {
        @Override
        public void run() {
            //要进行实现轮序的方法
            guessingHelper.readGuessing(String.valueOf(CurLiveInfo.getRoomNum()));

        }
    }

    @Override
    protected void onDestroy() {

        watchCount = 0;
        super.onDestroy();
        if (null != mHearBeatTimer) {
            mHearBeatTimer.cancel();
            mHearBeatTimer = null;
        }
//        if (null != mVideoTimer) {
//            mVideoTimer.cancel();
//            mVideoTimer = null;
//        }
        if (null != giftAnimationTimer) {
            giftAnimationTimer.cancel();
            giftAnimationTimer = null;
        }
       /* if (null != guessListViewTimer) {
            guessListViewTimer.cancel();
            guessListViewTimer = null;

        }*/
        if (null != mWakeLock)
            mWakeLock.release();//电源管理
        inviteViewCount = 0;
        thumbUp = 0;
        CurLiveInfo.setMembers(0);
        CurLiveInfo.setAdmires(0);
        CurLiveInfo.setCurrentRequestCount(0);
        unregisterReceiver();
        mLiveHelper.onDestory();
        mEnterRoomHelper.onDestory();
        QavsdkControl.getInstance().clearVideoMembers();
        QavsdkControl.getInstance().onDestroy();
    }


    /**
     * 点击Back键
     */
    @Override
    public void onBackPressed() {
        backDlg.show();
    }

    /**
     * 主动退出直播
     */
    private void quiteLiveByPurpose() {
        mLiveHelper.perpareQuitRoom(true);
//            mEnterRoomHelper.quiteLive();
    }

    private AlertDialog backDlg;

    private void initBackDialog() {
        backDlg = new AlertDialog(this).builder();
        backDlg.setTitle("确定退出直播吗?");
        backDlg.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiteLiveByPurpose();
            }
        });
        backDlg.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


//    private Dialog backDialog;
//
//    private void initBackDialog() {
//        backDialog = new Dialog(this, R.style.dialog);
//        backDialog.setContentView(R.layout.dialog_end_live);
//        TextView tvSure = (TextView) backDialog.findViewById(R.id.btn_sure);
//        tvSure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //如果是直播，发消息
//                if (null != mLiveHelper) {
//                    mLiveHelper.perpareQuitRoom(true);
//                    if (isPushed) {
//                        mLiveHelper.stopPushAction();
//                    }
//                }
//                backDialog.dismiss();
//            }
//        });
//        TextView tvCancel = (TextView) backDialog.findViewById(R.id.btn_cancel);
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                backDialog.cancel();
//            }
//        });
////        backDialog.show();
//    }

    /**
     * 被动退出直播
     */
    private void quiteLivePassively() {
//        Toast.makeText(this, "Host leave Live ", Toast.LENGTH_SHORT);
        mLiveHelper.perpareQuitRoom(false);
//        mEnterRoomHelper.quiteLive();
    }

    @Override
    public void readyToQuit() {
        mEnterRoomHelper.quiteLive();
    }

    /**
     * 完成进出房间流程
     *
     * @param id_status
     * @param isSucc
     */
    @Override
    public void enterRoomComplete(int id_status, boolean isSucc) {
//        Toast.makeText(LiveActivity.this, "EnterRoom  " + id_status + " isSucc " + isSucc, Toast.LENGTH_SHORT).show();
        //必须得进入房间之后才能初始化UI
        mEnterRoomHelper.initAvUILayer(avView);

        //设置预览回调，修正摄像头镜像
        mLiveHelper.setCameraPreviewChangeCallback();
        if (isSucc == true) {
            //IM初始化
            mLiveHelper.initTIMListener("" + CurLiveInfo.getRoomNum());

            if (id_status == Constants.HOST) {//主播方式加入房间成功
                //开启摄像头渲染画面
                SxbLog.i(TAG, "createlive enterRoomComplete isSucc" + isSucc);
            } else {
                //发消息通知上线
                mLiveHelper.sendGroupMessage(Constants.AVIMCMD_EnterLive, "");
            }
            initRv();
            PngNameUtil.getInstance().getGiftList();
            giftAnimationTimer = new Timer();
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    giftAnimationTaskReal = new GiftAnimationTaskReal();
                    giftAnimationTimer.schedule(giftAnimationTaskReal, 0, 1000);
                    barrageAnimationTaskReal = new BarrageAnimationTaskReal();
                    giftAnimationTimer.schedule(barrageAnimationTaskReal, 0, 1000);
                    //TODO ***** 轮询的的方法  ******
                    gifAnimationTaskReal = new GifAnimationTaskReal();
                    giftAnimationTimer.schedule(gifAnimationTaskReal, 0, 1000);

                }
            });
            //  SeeLiveHelper.getInstance().seeLive(MySelfInfo.getInstance().getId(), CurLiveInfo.getChatRoomId(), "1");
            SeeLiveHelper seeLiveHelper;
            seeLiveHelper = new SeeLiveHelper(this);
            seeLiveHelper.seeLive(MySelfInfo.getInstance().getId(), CurLiveInfo.getChatRoomId(), "1");
        }
    }


    @Override
    public void quiteRoomComplete(int id_status, boolean succ, LiveInfoJson liveinfo) {
        finish();
    }


    /**
     * 成员状态变更
     *
     * @param id
     * @param name
     */
    @Override
    public void memberJoin(String id, String name) {
        watchCount++;
        GiftData data = new GiftData();
        data.setMessage("进入了房间");
        refreshTextListView(TextUtils.isEmpty(name) ? id : name, data, Constants.MEMBER_ENTER);

        CurLiveInfo.setMembers(CurLiveInfo.getMembers() + 1);
        tvMembers.setText("" + CurLiveInfo.getMembers());
    }

    @Override
    public void memberQuit(String id, String name) {
        GiftData data = new GiftData();
        data.setMessage("退出了房间");
        refreshTextListView(TextUtils.isEmpty(name) ? id : name, data, Constants.MEMBER_EXIT);

        if (CurLiveInfo.getMembers() > 1) {
            CurLiveInfo.setMembers(CurLiveInfo.getMembers() - 1);
            tvMembers.setText("" + CurLiveInfo.getMembers());
        }

        //如果存在视频互动，取消
        QavsdkControl.getInstance().closeMemberView(id);
    }

    /**
     * 有成员退群
     *
     * @param list 成员ID 列表
     */
    @Override
    public void memberQuiteLive(String[] list) {
        if (list == null) return;
        for (String id : list) {
            SxbLog.i(TAG, "memberQuiteLive id " + id);
            if (CurLiveInfo.getHostID().equals(id)) {
                if (MySelfInfo.getInstance().getIdStatus() == Constants.MEMBER)
                    quiteLivePassively();
            }
        }
    }


    /**
     * 有成员入群
     *
     * @param list 成员ID 列表
     */
    @Override
    public void memberJoinLive(final String[] list) {
    }

    @Override
    public void alreadyInLive(String[] list) {
        for (String id : list) {
            if (id.equals(MySelfInfo.getInstance().getId())) {
                QavsdkControl.getInstance().setSelfId(MySelfInfo.getInstance().getId());
                QavsdkControl.getInstance().setLocalHasVideo(true, MySelfInfo.getInstance().getId());
            } else {
                QavsdkControl.getInstance().setRemoteHasVideo(true, id, AVView.VIDEO_SRC_TYPE_CAMERA);
            }
        }

    }


    private static int index = 0;

    /**
     * 加载视频数据
     *
     * @param isLocal 是否是本地数据
     * @param id      身份
     */
    @Override
    public void showVideoView(boolean isLocal, String id) {
        SxbLog.i(TAG, "showVideoView " + id);

        //渲染本地Camera
        if (isLocal == true) {
            SxbLog.i(TAG, "showVideoView host :" + MySelfInfo.getInstance().getId());
            QavsdkControl.getInstance().setSelfId(MySelfInfo.getInstance().getId());
            QavsdkControl.getInstance().setLocalHasVideo(true, MySelfInfo.getInstance().getId());
            //主播通知用户服务器
            if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
                mEnterRoomHelper.notifyServerCreateRoom();

                //主播心跳
                mHearBeatTimer = new Timer(true);
                mHeartBeatTask = new HeartBeatTask();
                mHearBeatTimer.schedule(mHeartBeatTask, 1000, 3 * 1000);

                //直播时间
//                mVideoTimer = new Timer(true);
//                mVideoTimerTask = new VideoTimerTask();
//                mVideoTimer.schedule(mVideoTimerTask, 1000, 1000);
            }
        } else {
//            QavsdkControl.getInstance().addRemoteVideoMembers(id);
            QavsdkControl.getInstance().setRemoteHasVideo(true, id, AVView.VIDEO_SRC_TYPE_CAMERA);
        }

    }


    private float getBeautyProgress(int progress) {
        SxbLog.d("shixu", "progress: " + progress);
        return (9.0f * progress / 100.0f);
    }


    @Override
    public void showInviteDialog() {
        if ((inviteDg != null) && (getBaseContext() != null) && (inviteDg.isShowing() != true)) {
            inviteDg.show();
        }
    }

    @Override
    public void hideInviteDialog() {
        if ((inviteDg != null) && (inviteDg.isShowing() == true)) {
            inviteDg.dismiss();
        }
    }


    @Override
    public void refreshText(String text, String name) {
        if (text != null) {
            Log.e("Json", text);
            GiftData data = new Gson().fromJson(text, GiftData.class);
            refreshTextListView(name, data, Constants.TEXT_TYPE);
        }
    }

    @Override
    public void refreshThumbUp() {
//        CurLiveInfo.setAdmires(CurLiveInfo.getAdmires() + 1);
//        if (!bCleanMode) {      // 纯净模式下不播放飘星动画
//            mHeartLayout.addFavor();
//        }
//        tvAdmires.setText("" + CurLiveInfo.getAdmires());
    }

    @Override
    public void refreshUI(String id) {
        //当主播选中这个人，而他主动退出时需要恢复到正常状态
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST)
            if (!backGroundId.equals(CurLiveInfo.getHostID()) && backGroundId.equals(id)) {
                backToNormalCtrlView();
            }
    }


    private int inviteViewCount = 0;

    @Override
    public boolean showInviteView(String id) {
//        int index = QavsdkControl.getInstance().getAvailableViewIndex(1);
//        if (index == -1) {
//            Toast.makeText(LiveActivity.this, "the invitation's upper limit is 3", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        int requetCount = index + inviteViewCount;
//        if (requetCount > 3) {
//            Toast.makeText(LiveActivity.this, "the invitation's upper limit is 3", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//
//        if (hasInvited(id)) {
//            Toast.makeText(LiveActivity.this, "it has already invited", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        switch (requetCount) {
//            case 1:
//                inviteView1.setText(id);
//                inviteView1.setVisibility(View.VISIBLE);
//                inviteView1.setTag(id);
//
//                break;
//            case 2:
//                inviteView2.setText(id);
//                inviteView2.setVisibility(View.VISIBLE);
//                inviteView2.setTag(id);
//                break;
//            case 3:
//                inviteView3.setText(id);
//                inviteView3.setVisibility(View.VISIBLE);
//                inviteView3.setTag(id);
//                break;
//        }
//        mLiveHelper.sendC2CMessage(Constants.AVIMCMD_MUlTI_HOST_INVITE, "", id);
//        inviteViewCount++;
//        //30s超时取消
//        Message msg = new Message();
//        msg.what = TIMEOUT_INVITE;
//        msg.obj = id;
//        mHandler.sendMessageDelayed(msg, 30 * 1000);
        return true;
    }


    /**
     * 判断是否邀请过同一个人
     *
     * @param id
     * @return
     */
//    private boolean hasInvited(String id) {
//        if (id.equals(inviteView1.getTag())) {
//            return true;
//        }
//        if (id.equals(inviteView2.getTag())) {
//            return true;
//        }
//        if (id.equals(inviteView3.getTag())) {
//            return true;
//        }
//        return false;
//    }
    @Override
    public void cancelInviteView(String id) {
//        if ((inviteView1 != null) && (inviteView1.getTag() != null)) {
//            if (inviteView1.getTag().equals(id)) {
//            }
//            if (inviteView1.getVisibility() == View.VISIBLE) {
//                inviteView1.setVisibility(View.INVISIBLE);
//                inviteView1.setTag("");
//                inviteViewCount--;
//            }
//        }
//
//        if (inviteView2 != null && inviteView2.getTag() != null) {
//            if (inviteView2.getTag().equals(id)) {
//                if (inviteView2.getVisibility() == View.VISIBLE) {
//                    inviteView2.setVisibility(View.INVISIBLE);
//                    inviteView2.setTag("");
//                    inviteViewCount--;
//                }
//            } else {
//                Log.i(TAG, "cancelInviteView inviteView2 is null");
//            }
//        } else {
//            Log.i(TAG, "cancelInviteView inviteView2 is null");
//        }
//
//        if (inviteView3 != null && inviteView3.getTag() != null) {
//            if (inviteView3.getTag().equals(id)) {
//                if (inviteView3.getVisibility() == View.VISIBLE) {
//                    inviteView3.setVisibility(View.INVISIBLE);
//                    inviteView3.setTag("");
//                    inviteViewCount--;
//                }
//            } else {
//                Log.i(TAG, "cancelInviteView inviteView3 is null");
//            }
//        } else {
//            Log.i(TAG, "cancelInviteView inviteView3 is null");
//        }
//

    }

    @Override
    public void cancelMemberView(String id) {
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
        } else {
            //TODO 主动下麦 下麦；
            mLiveHelper.changeAuthandRole(false, Constants.NORMAL_MEMBER_AUTH, Constants.NORMAL_MEMBER_ROLE);
//            mLiveHelper.closeCameraAndMic();//是自己成员关闭
        }
        mLiveHelper.sendGroupMessage(Constants.AVIMCMD_MULTI_CANCEL_INTERACT, id);
        QavsdkControl.getInstance().closeMemberView(id);
        backToNormalCtrlView();
    }


    private void showReportDialog() {
        final Dialog reportDialog = new Dialog(this, R.style.report_dlg);
        reportDialog.setContentView(R.layout.dialog_live_report);

        TextView tvReportDirty = (TextView) reportDialog.findViewById(R.id.btn_dirty);
        TextView tvReportFalse = (TextView) reportDialog.findViewById(R.id.btn_false);
        TextView tvReportVirus = (TextView) reportDialog.findViewById(R.id.btn_virus);
        TextView tvReportIllegal = (TextView) reportDialog.findViewById(R.id.btn_illegal);
        TextView tvReportYellow = (TextView) reportDialog.findViewById(R.id.btn_yellow);
        TextView tvReportCancel = (TextView) reportDialog.findViewById(R.id.btn_cancel);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    default:
                        reportDialog.cancel();
                        break;
                }
            }
        };

        tvReportDirty.setOnClickListener(listener);
        tvReportFalse.setOnClickListener(listener);
        tvReportVirus.setOnClickListener(listener);
        tvReportIllegal.setOnClickListener(listener);
        tvReportYellow.setOnClickListener(listener);
        tvReportCancel.setOnClickListener(listener);

        reportDialog.setCanceledOnTouchOutside(true);
        reportDialog.show();
    }

//    private void showHostDetail() {
//        Dialog hostDlg = new Dialog(this, R.style.host_info_dlg);
//        hostDlg.setContentView(R.layout.host_info_layout);
//
//        WindowManager windowManager = getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        Window dlgwin = hostDlg.getWindow();
//        WindowManager.LayoutParams lp = dlgwin.getAttributes();
//        dlgwin.setGravity(Gravity.TOP);
//        lp.width = (int) (display.getWidth()); //设置宽度
//
//        hostDlg.getWindow().setAttributes(lp);
//        hostDlg.show();
//
//        TextView tvHost = (TextView) hostDlg.findViewById(R.id.tv_host_name);
//        tvHost.setText(CurLiveInfo.getHostName());
//        ImageView ivHostIcon = (ImageView) hostDlg.findViewById(R.id.iv_host_icon);
//        showHeadIcon(ivHostIcon, CurLiveInfo.getHostAvator());
//        TextView tvLbs = (TextView) hostDlg.findViewById(R.id.tv_host_lbs);
//        tvLbs.setText(UIUtils.getLimitString(CurLiveInfo.getAddress(), 6));
//        ImageView ivReport = (ImageView) hostDlg.findViewById(R.id.iv_report);
//        ivReport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showReportDialog();
//            }
//        });
//    }

//    private boolean checkInterval() {
//        if (0 == admireTime) {
//            admireTime = System.currentTimeMillis();
//            return true;
//        }
//        long newTime = System.currentTimeMillis();
//        if (newTime >= admireTime + 1000) {
//            admireTime = newTime;
//            return true;
//        }
//        return false;
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                quiteLiveByPurpose();
                // SeeLiveHelper.getInstance().outLive(MySelfInfo.getInstance().getId(), CurLiveInfo.getChatRoomId(), "0");
                SeeLiveHelper seeLiveHelper;
                seeLiveHelper = new SeeLiveHelper(this);
                seeLiveHelper.outLive(MySelfInfo.getInstance().getId(), CurLiveInfo.getChatRoomId(), "0");
                break;
            case R.id.message_input:
                rl_inputdlg_view.setVisibility(View.VISIBLE);
                InputUtil.ShowKeyboard(rootView);
                input_message.requestFocus();
                rl_head.setVisibility(View.GONE);
                // inputMsgDialog();
                break;
//            case R.id.member_send_good:
//                 //添加飘星动画
//                mHeartLayout.addFavor();
//                if (checkInterval()) {
//                    mLiveHelper.sendC2CMessage(Constants.AVIMCMD_Praise, "", CurLiveInfo.getHostID());
//                    CurLiveInfo.setAdmires(CurLiveInfo.getAdmires() + 1);
//                    tvAdmires.setText("" + CurLiveInfo.getAdmires());
//                } else {
//                    //Toast.makeText(this, getString(R.string.text_live_admire_limit), Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case R.id.flash_btn:
//                if (mLiveHelper.isFrontCamera() == true) {
//                    Toast.makeText(LiveActivity.this, "this is front cam", Toast.LENGTH_SHORT).show();
//                } else {
//                    mLiveHelper.toggleFlashLight();
//                }
//                break;
//            case R.id.switch_cam:
//                mLiveHelper.switchCamera();
//                break;
//            case R.id.mic_btn:
//                if (mLiveHelper.isMicOpen() == true) {
//                    BtnMic.setBackgroundResource(R.mipmap.icon_mic_close);
//                    mLiveHelper.muteMic();
//                } else {
//                    BtnMic.setBackgroundResource(R.mipmap.icon_mic_open);
//                    mLiveHelper.openMic();
//                }
//                break;
            case R.id.head_icon:
//                showHostDetail();
              //  infoDlg.show(getFragmentManager(), "");
                break;
            case R.id.clean_screen:
//            case R.id.fullscreen_btn:
                bCleanMode = true;
                mFullControllerUi.setVisibility(View.INVISIBLE);
                BtnNormal.setVisibility(View.VISIBLE);
                break;
            case R.id.normal_btn:
                bCleanMode = false;
                mFullControllerUi.setVisibility(View.VISIBLE);
                BtnNormal.setVisibility(View.GONE);
                break;
            case R.id.video_interact:
                mMemberDg.setCanceledOnTouchOutside(true);
                mMemberDg.show();
                break;
            case R.id.camera_controll:
//                Toast.makeText(LiveActivity.this, "切换" + backGroundId + "camrea 状态", Toast.LENGTH_SHORT).show();
                if (backGroundId.equals(MySelfInfo.getInstance().getId())) {//自己关闭自己
                    mLiveHelper.toggleCamera();
                } else {
                    mLiveHelper.sendC2CMessage(Constants.AVIMCMD_MULTI_HOST_CONTROLL_CAMERA, backGroundId, backGroundId);//主播关闭自己
                }
                break;
            case R.id.mic_controll:
//                Toast.makeText(LiveActivity.this, "切换" + backGroundId + "mic 状态", Toast.LENGTH_SHORT).show();
                if (backGroundId.equals(MySelfInfo.getInstance().getId())) {//自己关闭自己
                    mLiveHelper.toggleMic();
                } else {
                    mLiveHelper.sendC2CMessage(Constants.AVIMCMD_MULTI_HOST_CONTROLL_MIC, backGroundId, backGroundId);//主播关闭自己
                }
                break;
            case R.id.close_member_video://主动关闭成员摄像头
                cancelMemberView(backGroundId);
                break;
//            case R.id.beauty_btn:
//                if (mBeautySettings != null) {
//                    if (mBeautySettings.getVisibility() == View.GONE) {
//                        mBeautySettings.setVisibility(View.VISIBLE);
//                        mFullControllerUi.setVisibility(View.INVISIBLE);
//                    } else {
//                        mBeautySettings.setVisibility(View.GONE);
//                        mFullControllerUi.setVisibility(View.VISIBLE);
//                    }
//                } else {
//                    SxbLog.i(TAG, "beauty_btn mTopBar  is null ");
//                }
//                break;
            case R.id.qav_beauty_setting_finish:
                mBeautySettings.setVisibility(View.GONE);
                mFullControllerUi.setVisibility(View.VISIBLE);
                break;
//            case R.id.invite_view1:
//                inviteView1.setVisibility(View.INVISIBLE);
//                mLiveHelper.sendGroupMessage(Constants.AVIMCMD_MULTI_CANCEL_INTERACT, "" + inviteView1.getTag());
//                break;
//            case R.id.invite_view2:
//                inviteView2.setVisibility(View.INVISIBLE);
//                mLiveHelper.sendGroupMessage(Constants.AVIMCMD_MULTI_CANCEL_INTERACT, "" + inviteView2.getTag());
//                break;
//            case R.id.invite_view3:
//                inviteView3.setVisibility(View.INVISIBLE);
//                mLiveHelper.sendGroupMessage(Constants.AVIMCMD_MULTI_CANCEL_INTERACT, "" + inviteView3.getTag());
//                break;
            case R.id.push_btn:
                pushStream();
                break;
            case R.id.switch_btn:
                if (switch_btn.isSelected()) {
                    switch_btn.setSelected(false);
                    switch_btn.setBackgroundResource(R.mipmap.message_barrage_off);
                } else {
                    switch_btn.setSelected(true);
                    switch_btn.setBackgroundResource(R.mipmap.message_barrage_on);
                }
                break;
            case R.id.confrim_btn:
                if (input_message.getText().toString().trim().length() > 0) {
                    GiftData data = new GiftData();
                    data.setMessage(input_message.getText().toString());
                    if (switch_btn.isSelected()) {
                        data.setType(4);
                    } else {
                        data.setType(1);
                    }
                    //等级的设置
                    data.setLevel(1);
                    data.setSendID(MySelfInfo.getInstance().getId());
                    data.setSendImg(MySelfInfo.getInstance().getAvatar());
                    data.setSendName(MySelfInfo.getInstance().getNickName());
                    sendText(data);
                    input_message.setText("");
                } else {
                    InputUtil.HideKeyboard(rootView);
                    rl_head.setVisibility(View.VISIBLE);
                    rl_inputdlg_view.setVisibility(View.GONE);
                }
                break;
            case R.id.tv_gift:
                openGiftHideView(true);
                giftDlg.show(getFragmentManager(), "");
                coinHelper.getCoin(MySelfInfo.getInstance().getId());
                break;
            case R.id.rl_contribution:
                startActivity(new Intent(LiveActivity.this, ZBURankActivity.class));
                break;
            case R.id.iv_guess_hand:
                // openGiftHideView(true);
                guessingHelper.readGuessing(String.valueOf(CurLiveInfo.getRoomNum()));
                iv_guess_hand.setVisibility(View.GONE);
                ObjectAnimator animatorguess = ObjectAnimator.ofFloat(lv_guess_coll, "translationX", UIUtils.getMeasuredWidth(lv_guess_coll) + 500, 0).setDuration(1000);
                animatorguess.start();
                lv_guess_coll.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_guss_pk:
                // openGiftHideView(true);
                //  guessingHelper.readGuessing(String.valueOf(CurLiveInfo.getRoomNum()));
                initUKPK();
                break;

            case R.id.tv_focus:
                //在代码中控制布局中的宽度
                followHelper.getFollow(MySelfInfo.getInstance().getId(), String.valueOf(CurLiveInfo.getRoomNum()));
                initAttention();
                break;
        }
    }

    private void initUKPK() {
        ukpkGuessingDlg1 = new UKPKGuessingDlg1();
        ukpkGuessingDlg1.show(getFragmentManager(), "");
        ukpkGuessingDlg1.setAllClick(new UKPKGuessingDlg1.OnClickAllListener() {
            @Override
            public void onClickConfirm(String tittle, String answerA, String answerB, String numBean, String time) {
                String userId = MySelfInfo.getInstance().getId();
                ukpkHelper.getUKPK(userId, tittle, numBean, answerA, answerB, time);
            }

            @Override
            public void onClickExchange() {
                startActivity(new Intent(LiveActivity.this, AccountActivity.class));
            }

            @Override
            public void onClickDismiss() {

            }
        });
    }

    private void initAttention() {
        RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams) topLeftRl.getLayoutParams();
        //p.width = 200;
        //topLeftRl 布局的宽度 - tv_focus 布局的宽度
        p.width = (UIUtils.getMeasuredWidth(topLeftRl) - UIUtils.getMeasuredWidth(tv_focus));
        topLeftRl.setLayoutParams(p);
        tv_focus.setVisibility(View.GONE);
    }

    //打开礼物面板，隐藏底部菜单和消息列表
    private void openGiftHideView(boolean isHide) {
        if (isHide) {
            mNomalMemberCtrView.setVisibility(View.INVISIBLE);
            mListViewMsgItems.setVisibility(View.INVISIBLE);
        } else {
            mNomalMemberCtrView.setVisibility(View.VISIBLE);
            mListViewMsgItems.setVisibility(View.VISIBLE);
        }
    }

    private void sendText(GiftData data) {
        if (data.getMessage().length() == 0)
            return;
        try {
            byte[] byte_num = data.getMessage().getBytes("utf8");
            if (byte_num.length > 160) {
//                Toast.makeText(this, "input message too long", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        TIMMessage Nmsg = new TIMMessage();
        TIMTextElem elem = new TIMTextElem();
        elem.setText(new Gson().toJson(data).toString());
        if (Nmsg.addElement(elem) != 0) {
            return;
        }
        mLiveHelper.sendGroupText(Nmsg);
    }


    private void backToNormalCtrlView() {
        if (MySelfInfo.getInstance().getIdStatus() == Constants.HOST) {
            backGroundId = CurLiveInfo.getHostID();
            mHostCtrView.setVisibility(View.VISIBLE);
            mVideoMemberCtrlView.setVisibility(View.GONE);
        } else {
            backGroundId = CurLiveInfo.getHostID();
            mNomalMemberCtrView.setVisibility(View.VISIBLE);
            mVideoMemberCtrlView.setVisibility(View.GONE);
        }
    }


    /**
     * 发消息弹出框
     */
//    private void inputMsgDialog() {
//        InputTextMsgDialog inputMsgDialog = new InputTextMsgDialog(this, R.style.inputdialog, mLiveHelper, this);
//        WindowManager windowManager = getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        WindowManager.LayoutParams lp = inputMsgDialog.getWindow().getAttributes();
//
//        lp.width = (int) (display.getWidth()); //设置宽度
//        inputMsgDialog.getWindow().setAttributes(lp);
//        inputMsgDialog.setCancelable(true);
//        inputMsgDialog.show();
//    }


    /**
     * 主播邀请应答框
     */
    private void initInviteDialog() {
        inviteDg = new Dialog(this, R.style.dialog);
        inviteDg.setContentView(R.layout.invite_dialog);
        TextView hostId = (TextView) inviteDg.findViewById(R.id.host_id);
        hostId.setText(CurLiveInfo.getHostID());
        TextView agreeBtn = (TextView) inviteDg.findViewById(R.id.invite_agree);
        TextView refusebtn = (TextView) inviteDg.findViewById(R.id.invite_refuse);
        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mVideoMemberCtrlView.setVisibility(View.VISIBLE);
//                mNomalMemberCtrView.setVisibility(View.INVISIBLE);

                //上麦 ；TODO 上麦 上麦 上麦 ！！！！！；
                backGroundId = MySelfInfo.getInstance().getId();
                mLiveHelper.changeAuthandRole(true, Constants.VIDEO_MEMBER_AUTH, Constants.VIDEO_MEMBER_ROLE);
                inviteDg.dismiss();
            }
        });

        refusebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLiveHelper.sendC2CMessage(Constants.AVIMCMD_MUlTI_REFUSE, "", CurLiveInfo.getHostID());
                inviteDg.dismiss();
            }
        });

        Window dialogWindow = inviteDg.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
    }


    /**
     * 消息刷新显示
     *
     * @param name 发送者
     * @param data 内容
     * @param type 类型 （上线线消息和 聊天消息）
     */
    public void refreshTextListView(String name, GiftData data, int type) {
        ChatEntity entity = new ChatEntity();
        entity.setSenderName(name);
        entity.setContext(data.getMessage());
        entity.setType(type);
        entity.setLevel(data.getLevel());
        if (data.getType() == 2) {
            storageGiftList(data);
        } else if (data.getType() == 3) {
            storageGifList(data);
            GlideUtil.getInstance().load(this, big_gift_hand, data.getSendImg());
            big_gift_myname.setText(data.getSendName());
            big_gift_name.setText(data.getGiftName());

        } else if (data.getType() == 4) {
            storageBarrageList(data);
        } else if (data.getType() == 5) {
            bettingState(data);
        } else if (data.getType() == 6) {
            guessingHelper.readGuessing(String.valueOf(CurLiveInfo.getRoomNum()));
        } else if (data.getType() == 7) {
            guessingHelper.readGuessing(String.valueOf(CurLiveInfo.getRoomNum()));
        } else if (data.getType() == 8) {
            guessingHelper.readGuessing(String.valueOf(CurLiveInfo.getRoomNum()));
            guessJieGuoHelper = new GuessJieGuoHelper(this);
            yhGuessId = data.getGuessId();
            guessJieGuoHelper.GuessJieGuo(MySelfInfo.getInstance().getId(), yhGuessId);
        }
        mTmpChatList.add(entity);
        mArrayListChatEntity.addAll(mTmpChatList);
        mTmpChatList.clear();
        mLiveForMsgAdapter.notifyItemInserted(mArrayListChatEntity.size() - 1);
        mListViewMsgItems.scrollToPosition(mArrayListChatEntity.size() - 1);
        //mArrayListChatEntity.add(entity);
//        notifyRefreshListView(entity);
        //mChatMsgListAdapter.notifyDataSetChanged();

//        mListViewMsgItems.setVisibility(View.VISIBLE);
//        SxbLog.d(TAG, "refreshTextListView height " + mListViewMsgItems.getHeight());
    }

    private void bettingState(GiftData data) {

        if (ukYaZhuDlg1 != null && ukYaZhuDlg1.getDialog() != null
                && ukYaZhuDlg1.getDialog().isShowing()) {
            ukYaZhuDlg1.setTextForBetNumChange(data.getBeanNum());
        } else {

        }
    }


    /**
     * 通知刷新消息ListView
     */
//    private void notifyRefreshListView(ChatEntity entity) {
//        mBoolNeedRefresh = true;
//        mTmpChatList.add(entity);
//        if (mBoolRefreshLock) {
//            return;
//        } else {
//            doRefreshListView();
//        }
//    }


    /**
     * 刷新ListView并重置状态
     */
//    private void doRefreshListView() {
//        if (mBoolNeedRefresh) {
//            mBoolRefreshLock = true;
//            mBoolNeedRefresh = false;
//            mArrayListChatEntity.addAll(mTmpChatList);
//            mTmpChatList.clear();
//            mLiveForMsgAdapter.notifyItemInserted(mArrayListChatEntity.size()-1);
//            mListViewMsgItems.scrollToPosition(mArrayListChatEntity.size()-1);
//
//            if (null != mTimerTask) {
//                mTimerTask.cancel();
//            }
//            mTimerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    SxbLog.v(TAG, "doRefreshListView->task enter with need:" + mBoolNeedRefresh);
//                    mHandler.sendEmptyMessage(REFRESH_LISTVIEW);
//                }
//            };
//            //mTimer.cancel();
//            mTimer.schedule(mTimerTask, MINFRESHINTERVAL);
//        } else {
//            mBoolRefreshLock = false;
//        }
//    }
    @Override
    public void updateProfileInfo(TIMUserProfile profile) {

    }

    @Override
    public void updateUserInfo(int requestCode, List<TIMUserProfile> profiles) {
        if (null != profiles) {
            switch (requestCode) {
                case GETPROFILE_JOIN:
                    for (TIMUserProfile user : profiles) {
                        tvMembers.setText("" + CurLiveInfo.getMembers());
                        SxbLog.w(TAG, "get nick name:" + user.getNickName());
                        SxbLog.w(TAG, "get remark name:" + user.getRemark());
                        SxbLog.w(TAG, "get avatar:" + user.getFaceUrl());
                        GiftData data = new GiftData();
                        data.setMessage("进入了房间");
                        if (!TextUtils.isEmpty(user.getNickName())) {
                            refreshTextListView(user.getNickName(), data, Constants.MEMBER_ENTER);
                        } else {
                            refreshTextListView(user.getIdentifier(), data, Constants.MEMBER_ENTER);
                        }
                    }
                    break;
            }

        }
    }

    //旁路直播
    private static boolean isPushed = false;

    /**
     * 旁路直播 退出房间时必须退出推流。否则会占用后台channel。
     */
    public void pushStream() {
        if (!isPushed) {
            if (mPushDialog != null)
                mPushDialog.show();
        } else {
            mLiveHelper.stopPushAction();
        }
    }

    private Dialog mPushDialog;

    private void initPushDialog() {
        mPushDialog = new Dialog(this, R.style.dialog);
        mPushDialog.setContentView(R.layout.push_dialog_layout);
        final TIMAvManager.StreamParam mStreamParam = TIMAvManager.getInstance().new StreamParam();
        final EditText pushfileNameInput = (EditText) mPushDialog.findViewById(R.id.push_filename);
        final RadioGroup radgroup = (RadioGroup) mPushDialog.findViewById(R.id.push_type);


        Button recordOk = (Button) mPushDialog.findViewById(R.id.btn_record_ok);
        recordOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pushfileNameInput.getText().toString().equals("")) {
//                    Toast.makeText(LiveActivity.this, "name can't be empty", Toast.LENGTH_SHORT);
                    return;
                } else {
                    mStreamParam.setChannelName(pushfileNameInput.getText().toString());
                }

                if (radgroup.getCheckedRadioButtonId() == R.id.hls) {
                    mStreamParam.setEncode(TIMAvManager.StreamEncode.HLS);
                } else {
                    mStreamParam.setEncode(TIMAvManager.StreamEncode.RTMP);
                }
//                mStreamParam.setEncode(TIMAvManager.StreamEncode.HLS);
                mLiveHelper.pushAction(mStreamParam);
                mPushDialog.dismiss();
            }
        });


        Button recordCancel = (Button) mPushDialog.findViewById(R.id.btn_record_cancel);
        recordCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPushDialog.dismiss();
            }
        });

        Window dialogWindow = mPushDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);
        dialogWindow.setAttributes(lp);
        mPushDialog.setCanceledOnTouchOutside(false);
    }


    /**
     * 推流成功
     *
     * @param streamRes
     */
    @Override
    public void pushStreamSucc(TIMAvManager.StreamRes streamRes) {
        List<TIMAvManager.LiveUrl> liveUrls = streamRes.getUrls();
        isPushed = true;
//        pushBtn.setBackgroundResource(R.mipmap.icon_stop_push);
        int length = liveUrls.size();
        String url = null;
        String url2 = null;
        if (length == 1) {
            TIMAvManager.LiveUrl avUrl = liveUrls.get(0);
            url = avUrl.getUrl();
        } else if (length == 2) {
            TIMAvManager.LiveUrl avUrl = liveUrls.get(0);
            url = avUrl.getUrl();
            TIMAvManager.LiveUrl avUrl2 = liveUrls.get(1);
            url2 = avUrl2.getUrl();
        }
        ClipToBoard(url, url2);
    }

    /**
     * 将地址黏贴到黏贴版
     *
     * @param url
     * @param url2
     */
    private void ClipToBoard(final String url, final String url2) {
        SxbLog.i(TAG, "ClipToBoard url " + url);
        SxbLog.i(TAG, "ClipToBoard url2 " + url2);
        if (url == null) return;
        final Dialog dialog = new Dialog(this, R.style.dialog);
        dialog.setContentView(R.layout.clip_dialog);
        TextView urlText = ((TextView) dialog.findViewById(R.id.url1));
        TextView urlText2 = ((TextView) dialog.findViewById(R.id.url2));
        Button btnClose = ((Button) dialog.findViewById(R.id.close_dialog));
        urlText.setText(url);
        urlText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clip = (ClipboardManager) getApplicationContext().getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
                clip.setText(url);
                Toast.makeText(LiveActivity.this, getResources().getString(R.string.clip_tip), Toast.LENGTH_SHORT).show();
            }
        });
        if (url2 == null) {
            urlText2.setVisibility(View.GONE);
        } else {
            urlText2.setText(url2);
            urlText2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClipboardManager clip = (ClipboardManager) getApplicationContext().getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
                    clip.setText(url2);
                    Toast.makeText(LiveActivity.this, getResources().getString(R.string.clip_tip), Toast.LENGTH_SHORT).show();
                }
            });
        }
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    /**
     * 停止推流成功
     */
    @Override
    public void stopStreamSucc() {
        isPushed = false;
        //   pushBtn.setBackgroundResource(R.mipmap.icon_push_stream);
    }
}
