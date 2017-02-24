package com.live.longmao.activity.person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.live.longmao.R;
import com.live.longmao.progress.DashedCircularProgress;
import com.live.longmao.views.BaseActivity;

public class PersonProgressActivity extends BaseActivity {

    //  private Button restartButton;
    private DashedCircularProgress dashedCircularProgress;
    private TextView numbers;
    private TextView creditTimeTV;
    private TextView creditNumTv;
    private LinearLayout ruleIv ,historyIv ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_progress);


        //   restartButton = (Button)findViewById(R.id.restart);
        dashedCircularProgress = (DashedCircularProgress) findViewById(R.id.simple);
        numbers = (TextView) findViewById(R.id.number);
        setTitle("主播信誉");
        animate();
        initCreditView();

//            restartButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    restart();
//                }
//            });

        dashedCircularProgress.setOnValueChangeListener(
                new DashedCircularProgress.OnValueChangeListener() {
                    @Override
                    public void onValueChange(float value) {
                        numbers.setText((int) value + "");
                    }
                });
    }


    private void initCreditView(){
        historyIv = (LinearLayout) findViewById(R.id.credit_history);
        historyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击跳转到信用历史记录
                Intent intent = new Intent(PersonProgressActivity.this,PersonCreditHistory.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    //    private void restart() {
//        dashedCircularProgress.reset();
//        animate();
//    }

    private void animate() {
        dashedCircularProgress.setValue(1000);
    }


}
