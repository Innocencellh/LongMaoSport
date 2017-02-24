package com.live.longmao.activity.person;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.live.longmao.R;
import com.live.longmao.adapter.person.PersonCreditAdapter;
import com.live.longmao.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class PersonCreditHistory extends BaseActivity {
    private ListView credutLs;
    private PersonCreditAdapter personCreditAdapter;
    private List<String> date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_person_credit_history);
        setTitle("信用记录");
        initCreView();
    }

    private void initCreView() {
        credutLs = (ListView) findViewById(R.id.credit_listview);
        date = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            date.add(i + "");
        }
        personCreditAdapter = new PersonCreditAdapter(this,date);
        credutLs.setAdapter(personCreditAdapter);


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }
}
