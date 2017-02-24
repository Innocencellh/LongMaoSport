package com.live.longmao.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.adapter.AddressAdapter;
import com.live.longmao.adapter.MovementAdapter;
import com.live.longmao.bean.MovementBean;
import com.live.longmao.gifdlg.EmotionPanel;
import com.live.longmao.views.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public class LiveClassificationActivity extends BaseActivity {
    private static final String TAG = LiveClassificationActivity.class.getSimpleName();
    private EmotionPanel emotionPanel;
    private MovementAdapter adapter;
    List<MovementBean> movementBeanList;
    MovementBean selMovementBean;
    private ListView listView;
    private TextView txt_commit;
    private HashMap<Integer, Boolean> selectMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_live_classification);
        setTitle("直播分类");
        selectMap = new HashMap<>();
        initView();
    }
    private void initView()
    {
        listView = (ListView) findViewById(R.id.list_address);
        List<String> list = new ArrayList<>();
        list.add("全部");
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");
        list.add("武汉");
        list.add("杭州");
        list.add("大连");
        list.add("安徽");
        final AddressAdapter addressAdapter = new AddressAdapter(list,this,selectMap);
        listView.setAdapter(addressAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "" + position);
                selectMap.clear();
                selectMap.put(position, true);
                addressAdapter.notifyDataSetChanged();
            }
        });
        txt_commit = (TextView)findViewById(R.id.txt_commit);
        txt_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_commit.setSelected(true);
                finish();
            }
        });
        emotionPanel =(EmotionPanel) findViewById(R.id.em_panel);
        //emotionPanel.hideSend();
        movementBeanList = new ArrayList<>();
        for (int i=0;i<24;i++)
        {
            MovementBean bean = new MovementBean();
            bean.setId(i);
            bean.setImg("http://f.hiphotos.baidu.com/image/h%3D200/sign=236c94ef2c381f3081198aa999004c67/242dd42a2834349bbe78c852cdea15ce37d3beef.jpg");
            bean.setName("瑜伽");
            movementBeanList.add(bean);
        }
        adapter = new MovementAdapter(this, movementBeanList, new MovementAdapter.PickListener() {
            @Override
            public void onPick(MovementBean bean) {
                selMovementBean = bean;
            }
        });
        emotionPanel.setAdapter(adapter);
    }

    @Override
    public void finish() {
        super.finish();
        //关闭窗体动画显示
        this.overridePendingTransition(0, R.anim.activity_close);
    }
}
