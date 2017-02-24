package com.live.longmao.gifdlg;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.live.longmao.R;

public class EmotionPanel extends LinearLayout implements ViewPager.OnPageChangeListener {

//    public interface AnimListener {
//        void onAnimEnd();
//    }
//
//    public interface SendListener {
//        void onSend();
//    }

    public EmotionPanel(Context context) {
        this(context, null);
    }

    public EmotionPanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmotionPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EmotionPanel(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

//    public void setSendListener(SendListener l) {
//        sendListener = l;
//    }
//
//    public void animShow(final AnimListener listener) {
//        setVisibility(VISIBLE);
//        animShow.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                if (null != listener) {
//                    listener.onAnimEnd();
//                }
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        startAnimation(animShow);
//    }
//
//    public void animHide(final boolean gone, final AnimListener listener) {
//        animHide.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                if (gone) {
//                    setVisibility(GONE);
//                }
//                if (null != listener) {
//                    listener.onAnimEnd();
//                }
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        startAnimation(animHide);
//    }

    // point size is connected with radius
    private static final int P_SIZE = FormatUtil.pixOfDip(4);
  //  private final static int DIP5 = FormatUtil.pixOfDip(5);

//    private SendListener sendListener;
    private ViewPager pager;
    private RadioGroup indicatorBar;
//    private TextView btnSend;
    private int curIdx;
//    private Animation animShow;
//    private Animation animHide;

    private void initView() {
        setGravity(Gravity.CENTER_HORIZONTAL);
        pager = new ViewPager(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
        lp.weight = 1;
        pager.setLayoutParams(lp);
        pager.setOnPageChangeListener(this);
        addView(pager);

//        RelativeLayout rRg = new RelativeLayout(getContext());
//        LayoutParams rRgParams = new LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
//        rRg.setBackgroundColor(getResources().getColor(R.color.gift_bg));
//        rRg.setLayoutParams(rRgParams);
        indicatorBar = new RadioGroup(getContext());
        indicatorBar.setFocusable(false);
        indicatorBar.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams barParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 2 * P_SIZE);
        barParams.bottomMargin = 2 * P_SIZE;
        indicatorBar.setLayoutParams(barParams);
        indicatorBar.setGravity(Gravity.CENTER);
        addView(indicatorBar);

//        btnSend = new TextView(getContext());
//        btnSend.setBackgroundResource(R.mipmap.btn_send);
//        btnSend.setTextColor(getContext().getResources().getColor(R.color.white));
//        btnSend.setTextSize(14);
//        btnSend.setText("发送");
//        btnSend.setPadding(2 * DIP5, DIP5, 2 * DIP5, DIP5);
//        btnSend.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != sendListener) {
//                    sendListener.onSend();
//                }
//            }
//        });
//        LayoutParams lp2 = new LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        lp2.gravity = Gravity.RIGHT;
//        lp2.bottomMargin = DIP5;
//        lp2.rightMargin = 2 * DIP5;
//        addView(btnSend, lp2);

//        animShow = AnimationUtils.loadAnimation(getContext(), R.anim.activity_open);
//        animHide = AnimationUtils.loadAnimation(getContext(), R.anim.activity_close);
    }

    public void setAdapter(final PagerAdapter adapter) {
        if (null != adapter) {
            adapter.registerDataSetObserver(new DataObserver());
            changeCount(adapter.getCount());
        } else {
            changeCount(0);
        }
        pager.setAdapter(adapter);
    }

//    public void showSend() {
//        btnSend.setVisibility(VISIBLE);
//    }
//
//    public void hideSend() {
//        btnSend.setVisibility(GONE);
//    }
//
//    public void setSendEnabled(boolean enabled) {
//        btnSend.setEnabled(enabled);
//    }

    private void changeCount(final int count) {
        int curCount = indicatorBar.getChildCount();
        if (curCount == count) {
            return;
        } else if (curCount < count) {
            for (int i = curCount; i < count; i++) {
                indicatorBar.addView(makePoint());
            }
        } else {
            for (int j = curCount - 1; j >= count; j--) {
                indicatorBar.removeViewAt(j);
            }
        }
        if (curIdx >= count) {
            curIdx = count - 1;
        }
        if (count > 0) {
            setPointSelected(curIdx, true);
        }
    }

    private View makePoint() {
        View btn = new View(getContext());
        btn.setLayoutParams(new FrameLayout.LayoutParams(P_SIZE, P_SIZE));
        btn.setBackgroundResource(R.drawable.selector_emotion_indicator);
        btn.setDuplicateParentStateEnabled(true);

        final FrameLayout fl = new FrameLayout(getContext());
        fl.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        fl.setPadding(P_SIZE, 0, P_SIZE, 0);
        fl.addView(btn);

        return fl;
    }

    private void setPointSelected(int i, boolean selected) {
        indicatorBar.getChildAt(i).setSelected(selected);
    }

    private class DataObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            changeCount(pager.getAdapter().getCount());
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int size = indicatorBar.getChildCount();
        int relPos = position % size;
        while (relPos < 0) {
            relPos = size + relPos;
        }
        setPointSelected(curIdx, false);
        curIdx = relPos;
        setPointSelected(curIdx, true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
