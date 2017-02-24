package com.live.longmao.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.adapter.RankAdapter;
import com.live.longmao.adapter.person.PersonRankAdapter;
import com.live.longmao.model.PersonRankInfo;
import com.live.longmao.model.PersonRankInfoBean;
import com.live.longmao.presenters.PersonRankHelper;
import com.live.longmao.presenters.viewinface.PersonRankView;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HPDN on 2016/7/9.
 */
public class PersonRankActivity extends BaseActivity implements View.OnClickListener, PersonRankView {

    private PersonRankAdapter rankAdapter;
    private ListView lv_rank;
    private ArrayList<PersonRankInfoBean> list = new ArrayList<>();
    private PersonRankHelper personRankHelper;
    private ImageView history_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_rank_list);
        setTitle("卡路里贡献榜");

        lv_rank = (ListView) findViewById(R.id.rank_list);
        lv_rank.setDividerHeight(0);

        history_img = (ImageView) findViewById(R.id.history_img);

        personRankHelper = new PersonRankHelper(this);
        personRankHelper.getPersonRank();

        rankAdapter = new PersonRankAdapter(list, this);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }


    @Override
    public void onPersonRankSucc(PersonRankInfo result) {
        if (null != result && null != result.getBody()) {
            List<PersonRankInfoBean> body = result.getBody();
            for (PersonRankInfoBean item : body) {
                list.add(item);
            }
         //   rankAdapter = new RankAdapter(list, this);
            lv_rank.setAdapter(rankAdapter);
            rankAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(PersonRankActivity.this, "你还没有没有给主播礼物，快去送礼物给你的心仪主播", Toast.LENGTH_SHORT).show();
            history_img.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPersonRankError(String msg) {
        Toast.makeText(PersonRankActivity.this, "主播需要你的礼物", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("个人中心卡路里贡献榜查看界面");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("个人中心卡路里贡献榜查看界面");
        MobclickAgent.onPause(this);
    }

}
