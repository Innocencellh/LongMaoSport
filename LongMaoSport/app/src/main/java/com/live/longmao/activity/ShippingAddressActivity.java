package com.live.longmao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.live.longmao.R;
import com.live.longmao.adapter.ShippingAddressAdapter;
import com.live.longmao.swipemenulistview.SwipeMenu;
import com.live.longmao.swipemenulistview.SwipeMenuCreator;
import com.live.longmao.swipemenulistview.SwipeMenuItem;
import com.live.longmao.swipemenulistview.SwipeMenuListView;
import com.live.longmao.views.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class ShippingAddressActivity extends BaseActivity {
    private SwipeMenuListView swipe_list;
    List<String> list;
    ShippingAddressAdapter addressAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("收货地址");
        setRigthText("添加");
        setView(R.layout.activity_shopping_address);
        initView();
        initListMenu();
    }

    private void initView()
    {
        swipe_list = (SwipeMenuListView) findViewById(R.id.swipe_list);
        list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        addressAdapter = new ShippingAddressAdapter(this,list);
        swipe_list.setAdapter(addressAdapter);
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    private void initListMenu() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(R.color.paleRed);
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.mipmap.icon_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        swipe_list.setMenuCreator(creator);

        swipe_list.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                del(position);
            }
        });
    }

    private void del(int position)
    {
        list.remove(position);
        addressAdapter.notifyDataSetChanged();
    }

    @Override
    protected void setRigthClick(View v) {
        startActivity(new Intent(this, AddAddressAtivity.class));
    }
}
