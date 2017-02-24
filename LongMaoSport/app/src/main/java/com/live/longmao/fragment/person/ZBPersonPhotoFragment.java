package com.live.longmao.fragment.person;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.live.longmao.R;
import com.live.longmao.adapter.person.PersonPhotoAdapter;
import com.live.longmao.photobig.ImagPagerUtil;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;

/**
 * Created by HPDN on 2017/1/9.
 */
public class ZBPersonPhotoFragment extends Fragment implements PersonPhotoAdapter.OnItemClick {
    private RecyclerView mRecyclerView;
    private PersonPhotoAdapter mAdapter;

    private ArrayList<String> liveList = new ArrayList<String>();
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.frament_person_photo_zb, null);
            isPrepared = true;
            liveList.add("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-001.jpg");
            liveList.add("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-002.jpg");
            liveList.add("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-003.jpg");
            liveList.add("http://img.ivsky.com/img/bizhi/pre/201601/27/february_2016-004.jpg");
            liveList.add("http://img.ivsky.com/img/tupian/pre/201511/16/chongwugou.jpg");
            initImageLoader();
            initView();
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;

    }

    private void initView() {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 3);

        mRecyclerView.setLayoutManager(gridLayoutManager);

        mAdapter = new PersonPhotoAdapter(liveList, getActivity());

        mAdapter.setOnItemClick(this);

        mRecyclerView.setAdapter(mAdapter);

    }

    protected void lazyLoad() {
        if (!isPrepared || mHasLoadedOnce) {
            return;
        }

    }

    @Override
    public void onItemClick(int pos) {
       // String[] sddd = new String[pos+1];
        ImagPagerUtil imagPagerUtil = new ImagPagerUtil(getActivity(),liveList);
        imagPagerUtil.show();
    }

    public void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getContext()).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }

}
