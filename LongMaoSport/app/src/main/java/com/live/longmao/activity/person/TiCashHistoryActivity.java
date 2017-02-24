package com.live.longmao.activity.person;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.live.longmao.R;
import com.live.longmao.adapter.person.HistoryCashListAdapter;
import com.live.longmao.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
public class TiCashHistoryActivity extends BaseActivity {
    private ListView historyListView;
    private List<String> date;
    private HistoryCashListAdapter historyCashListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setView(R.layout.activity_ti_cash_history);
        setTitle("提现记录");
        initHistoryView();
    }

    private void initHistoryView() {
        historyListView = (ListView) findViewById(R.id.history_listview);
        date = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            date.add(i + "");
        }
        historyCashListAdapter = new HistoryCashListAdapter(date, this);
        historyListView.setAdapter(historyCashListAdapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
