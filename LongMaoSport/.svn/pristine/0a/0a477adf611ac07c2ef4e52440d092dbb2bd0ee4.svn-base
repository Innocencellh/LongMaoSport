package com.live.longmao;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by HPDN on 2016/12/13.
 */
public class TimeTextView extends TextView implements Runnable {

    Paint mPaint; //画笔,包含了画几何图形、文本等的样式和颜色信息

    private long[] times;

    private long mmin, msecond;//天，小时，分钟，秒

    private boolean run = false; //是否启动了

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimeTextView);

        array.recycle(); //一定要调用，否则这次的设定会对下次的使用造成影响
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mPaint = new Paint();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TimeTextView);

        array.recycle(); //一定要调用，否则这次的设定会对下次的使用造成影响
    }

    public TimeTextView(Context context) {
        super(context);
    }

    public long[] getTimes() {
        return times;
    }

    public void setTimes(long[] times) {
        this.times = times;
        mmin = times[0];
        msecond = times[1];
    }

    /**
     * 倒计时计算
     */
    private void ComputeTime() {
        msecond--;
        if (msecond < 0) {
            mmin--;
            msecond = 59;
            if (mmin < 0) {
                setRun(false);
            }
//        mmin--;
//        if(mmin<0)
//        {
//            setRun(false);
//        }
            //setRun(false);
        }
    }

    public boolean isRun() {
        return run;
    }



    public void setRun(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {
        //标示已经启动
        run = true;

        ComputeTime();

        String fen, miao;
        if (mmin < 10) {
            fen = "0" + mmin;
        } else {
            fen = "" + mmin;

        }
        if (msecond < 10) {
            miao = "0" + msecond;
        } else {
            miao = "" + msecond;

        }

//        String strTime="还剩"+mday+"天" +mhour+ "小时" + mmin+ "分钟" + msecond+"秒";
        if (run) {
            String strTime = fen + ":" + miao;
            this.setText(Html.fromHtml(strTime));

            postDelayed(this, 1000);
        }

    }

}
