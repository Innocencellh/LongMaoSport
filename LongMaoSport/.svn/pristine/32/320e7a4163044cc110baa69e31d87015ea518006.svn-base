package com.live.longmao.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.live.longmao.R;
import com.live.longmao.pickerview.OptionsPickerView;
import com.live.longmao.views.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class AddAddressAtivity extends BaseActivity implements View.OnClickListener {
    // 省数据集合
    private ArrayList<String> mListProvince = new ArrayList<String>();
    // 市数据集合
    private ArrayList<ArrayList<String>> mListCiry = new ArrayList<ArrayList<String>>();
    // 区数据集合
    private ArrayList<ArrayList<ArrayList<String>>> mListArea = new ArrayList<ArrayList<ArrayList<String>>>();

    private OptionsPickerView<String> mOpv;
    private JSONObject mJsonObj;
    private TextView mCity,line,line1;
    private EditText addadress_name, addadress_phone, address_et;
    private String addadressName, addadressPhone, City, addressEt;
    private Button address_btn;
    private RadioButton rbtn_nan, rbtn_man;
    private ImageView back;
    private RelativeLayout rl_sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("收货地址");
        setRigthText("保存");
        setView(R.layout.activity_add_address);
        mCity = (TextView) findViewById(R.id.et_shouhuoren);
        addadress_name = (EditText) findViewById(R.id.addadress_name);
        addadress_phone = (EditText) findViewById(R.id.addadress_phone);
        address_et = (EditText) findViewById(R.id.address_et);
        address_btn = (Button) findViewById(R.id.address_btn);
        rbtn_nan = (RadioButton) findViewById(R.id.rbtn_nan);
        rbtn_man = (RadioButton) findViewById(R.id.rbtn_man);
        back = (ImageView) findViewById(R.id.iv_back);
        rl_sex = (RelativeLayout) findViewById(R.id.rl_sex);
        line = (TextView) findViewById(R.id.line);
        line1 = (TextView) findViewById(R.id.line1);
        back.setOnClickListener(this);
        address_btn.setOnClickListener(this);
        initCity();
    }

    private void initCity() {
        // 初始化Json对象
        initJsonData();
        // 初始化Json数据
        initJsonDatas();

        // 创建选项选择器对象
        mOpv = new OptionsPickerView<String>(this);

        // 设置标题
        mOpv.setTitle("选择城市");

        // 设置三级联动效果
        mOpv.setPicker(mListProvince, mListCiry, mListArea, true);

        // 设置是否循环滚动
        mOpv.setCyclic(false, false, false);

        // 设置默认选中的三级项目
        mOpv.setSelectOptions(0, 0, 0);

        // 监听确定选择按钮
        mOpv.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                // 返回的分别是三个级别的选中位置
                String tx = mListProvince.get(options1) + mListCiry.get(options1).get(option2) + mListArea.get(options1).get(option2).get(options3);
                mCity.setText(tx);
            }
        });

        // 点击弹出选项选择器
        mCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOpv.show();
                InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputmanger.hideSoftInputFromWindow(v.getWindowToken(), 0);

            }
        });
    }

    /**
     * 从assert文件夹中读取省市区的json文件，然后转化为json对象
     */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = getAssets().open("city.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "UTF-8"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化Json数据，并释放Json对象
     */
    private void initJsonDatas() {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 获取每个省的Json对象
                String province = jsonP.getString("name");

                ArrayList<String> options2Items_01 = new ArrayList<String>();
                ArrayList<ArrayList<String>> options3Items_01 = new ArrayList<ArrayList<String>>();
                JSONArray jsonCs = jsonP.getJSONArray("city");
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonC = jsonCs.getJSONObject(j);// 获取每个市的Json对象
                    String city = jsonC.getString("name");
                    options2Items_01.add(city);// 添加市数据

                    ArrayList<String> options3Items_01_01 = new ArrayList<String>();
                    JSONArray jsonAs = jsonC.getJSONArray("area");
                    for (int k = 0; k < jsonAs.length(); k++) {
                        options3Items_01_01.add(jsonAs.getString(k));// 添加区数据
                    }
                    options3Items_01.add(options3Items_01_01);
                }
                mListProvince.add(province);// 添加省数据
                mListCiry.add(options2Items_01);
                mListArea.add(options3Items_01);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.address_btn:
                addadressName = addadress_name.getText().toString().trim();
                addadressPhone = addadress_phone.getText().toString().trim();
                City = mCity.getText().toString().trim();
                addressEt = address_et.getText().toString().trim();
                if (TextUtils.isEmpty(addadressName)) {
                    Toast.makeText(AddAddressAtivity.this, "您的联系人姓名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(addadressPhone)) {
                    Toast.makeText(AddAddressAtivity.this, "您的手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(City)) {
                    Toast.makeText(AddAddressAtivity.this, "您的收货地址不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(addressEt)) {
                    Toast.makeText(AddAddressAtivity.this, "您的详细地址不能为空", Toast.LENGTH_SHORT).show();
                }
                if (rbtn_nan.isChecked() || rbtn_man.isChecked()) {
                } else {
                    Toast.makeText(AddAddressAtivity.this, "您还没有选择您的性别", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void setRigthClick(View v) {
        if (((TextView) v).getText().toString().equals("保存")) {
            ((TextView) v).setText("编辑");
            rl_sex.setVisibility(View.GONE);
            address_btn.setVisibility(View.GONE);
            line.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            addadress_name.setEnabled(false);
            addadress_phone.setEnabled(false);
            address_et.setEnabled(false);
            mCity.setEnabled(false);
        } else if (((TextView) v).getText().toString().equals("编辑")) {
            ((TextView) v).setText("保存");
            rl_sex.setVisibility(View.VISIBLE);
            address_btn.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            line1.setVisibility(View.VISIBLE);
            addadress_name.setEnabled(true);
            addadress_phone.setEnabled(true);
            address_et.setEnabled(true);
            mCity.setEnabled(true);
            Toast.makeText(AddAddressAtivity.this, "请填写您的收货地址", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("SplashScreen");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        MobclickAgent.onPageEnd("SplashScreen");
        super.onPause();
        MobclickAgent.onPause(this);
    }

}