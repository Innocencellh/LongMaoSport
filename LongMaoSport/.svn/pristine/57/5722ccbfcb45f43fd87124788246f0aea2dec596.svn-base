package com.live.longmao.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.adapter.RecyclerViewAdapter;
import com.live.longmao.util.ChangeHeight;
import com.live.longmao.view.ObservableScrollView;
import com.live.longmao.views.customviews.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/7/10 0010.
 */
public class StoreActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout activity_base_title_rl;
    private ImageButton iv_back;
    private RecyclerViewAdapter adapter;
    private List<String> urlList;
    private RecyclerView recyclerView;
    private ObservableScrollView scroll_main;
    private ImageView person_image;
    private int imageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_store);
        initView();
        init();
        initListeners();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ChangeHeight.changeRH(this, activity_base_title_rl);
        }
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

    private void init() {
        if (urlList == null) {
            urlList = new ArrayList<>();
            addItem();
        }
        if (adapter == null) {
            adapter = new RecyclerViewAdapter(this, urlList);
        }
        adapter.setOnItemClick(new RecyclerViewAdapter.OnItemClick() {
            @Override
            public void onClick(int position) {
                Log.e("position", "" + position);
                if (position == 0) {
                    showPhotoDialog();
                }
            }

            @Override
            public void onLongClick(int position,View v) {

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

    private void showPhotoDialog() {
        final Dialog pickDialog = new Dialog(this, R.style.floag_dialog);
        pickDialog.setContentView(R.layout.dlg_photo_operation);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Window dlgwin = pickDialog.getWindow();
        WindowManager.LayoutParams lp = dlgwin.getAttributes();
        dlgwin.setGravity(Gravity.BOTTOM);
        lp.width = (int)(display.getWidth()); //设置宽度

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

    private void initView() {
        person_image = (ImageView) findViewById(R.id.person_image);
        scroll_main = (ObservableScrollView) findViewById(R.id.scroll_main);
        scroll_main.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {Log.e("ObservableScrollView", "x:" + x + "y:" + y + "oldx:" + oldx + "oldy:" + oldy);
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
        activity_base_title_rl = (RelativeLayout)findViewById(R.id.activity_base_title_rl);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        iv_back = (ImageButton)findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
    }

    private void addItem() {
        urlList.add("默认图片");
        urlList.add("http://img6.cache.netease.com/3g/2015/9/30/20150930091938133ad.jpg");
        urlList.add("http://img2.cache.netease.com/3g/2015/9/30/2015093000515435aff.jpg");
        urlList.add("http://img5.cache.netease.com/3g/2015/9/30/20150930075225737e5.jpg");
        urlList.add("http://img5.cache.netease.com/3g/2015/9/29/20150929213007cd8cd.jpg");
        urlList.add("http://img3.cache.netease.com/3g/2015/9/29/20150929162747a8bfa.jpg");
        urlList.add("http://img2.cache.netease.com/3g/2015/9/30/20150930091208cf03c.jpg");
        urlList.add("http://img2.cache.netease.com/3g/2015/9/30/2015093000515435aff.jpg");
        urlList.add("http://img5.cache.netease.com/3g/2015/9/29/20150929213007cd8cd.jpg");
        urlList.add("http://img3.cache.netease.com/3g/2015/9/29/20150929162747a8bfa.jpg");
        urlList.add("http://img6.cache.netease.com/3g/2015/9/30/20150930091938133ad.jpg");
        urlList.add("http://img2.cache.netease.com/3g/2015/9/30/2015093000515435aff.jpg");
        urlList.add("http://img5.cache.netease.com/3g/2015/9/30/20150930075225737e5.jpg");
        urlList.add("http://img5.cache.netease.com/3g/2015/9/29/20150929213007cd8cd.jpg");
        urlList.add("http://img3.cache.netease.com/3g/2015/9/29/20150929162747a8bfa.jpg");
        urlList.add("http://img2.cache.netease.com/3g/2015/9/30/20150930091208cf03c.jpg");
        urlList.add("http://img2.cache.netease.com/3g/2015/9/30/2015093000515435aff.jpg");
        urlList.add("http://img5.cache.netease.com/3g/2015/9/29/20150929213007cd8cd.jpg");
        urlList.add("http://img3.cache.netease.com/3g/2015/9/29/20150929162747a8bfa.jpg");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.iv_back:
                this.finish();
                break;
        }
    }
}
