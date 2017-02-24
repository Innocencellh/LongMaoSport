package com.live.longmao.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.adapter.ZBRankAdapter;
import com.live.longmao.model.CurLiveInfo;
import com.live.longmao.model.ZBRankInfo;
import com.live.longmao.model.ZBRankInfoBean;
import com.live.longmao.presenters.ZBRankHelper;
import com.live.longmao.presenters.viewinface.ZBRankView;
import com.live.longmao.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HPDN on 2016/7/9.
 */
public class ZBURankActivity extends BaseActivity implements View.OnClickListener,ZBRankView{

    private ZBRankAdapter rankAdapter;
    private ListView lv_rank;
    private ArrayList<ZBRankInfoBean> list = new ArrayList<>();
    private ZBRankHelper zbRankHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_rank_list);
        setTitle("卡路里贡献榜");

        lv_rank = (ListView) findViewById(R.id.rank_list);

        zbRankHelper = new ZBRankHelper(this);
        zbRankHelper.getZBRank(CurLiveInfo.getChatRoomId());

        rankAdapter = new ZBRankAdapter(list, this);
     //   lv_rank.setAdapter(rankAdapter);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }



    @Override
    public void onZBRankSucc(ZBRankInfo result) {
        if (null != result && null != result.getBody()) {
            List<ZBRankInfoBean> body = result.getBody();
            for (ZBRankInfoBean item : body) {
                list.add(item);
            }
          //  rankAdapter = new ZBRankAdapter(list, this);
            lv_rank.setAdapter(rankAdapter);
            rankAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(ZBURankActivity.this, "你还没有没有给主播礼物，快去送礼物给你的心仪主播", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onZBRankError(String msg) {
        Toast.makeText(ZBURankActivity.this, "主播需要你的礼物", Toast.LENGTH_SHORT).show();
    }
}
