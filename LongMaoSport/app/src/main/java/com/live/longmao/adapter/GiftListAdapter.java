package com.live.longmao.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.live.longmao.R;
import com.live.longmao.bean.GiftBean;
import com.live.longmao.bean.MovementBean;
import com.live.longmao.gifdlg.AbsFitAdapter;
import com.live.longmao.gifdlg.FormatUtil;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.view.GridViewForScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public class GiftListAdapter  extends PagerAdapter {

    public interface PickListener {
        void onPick(GiftBean bean,boolean isSelect);
    }

    private final static int NUM_COL = 4;
    private final static int PAGE_SIZE = 8;
    private final List<GiftBean> giftBeans;
    private final int count;
    private final Context ctx;
    private final PickListener listener;
    private HashMap<String, Boolean> selectMap;

    public GiftListAdapter(Context ctx, List<GiftBean> giftBeans, PickListener listener) {
        this.giftBeans = giftBeans;
        count = (int) Math.ceil(giftBeans.size() / (float) PAGE_SIZE);
        this.ctx = ctx;
        selectMap = new HashMap<>();
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final GridViewForScrollView panel = new GridViewForScrollView(ctx);
        panel.setVerticalSpacing(FormatUtil.pixOfDip(0.5F));
        panel.setHorizontalSpacing(FormatUtil.pixOfDip(0.5F));
        panel.setNumColumns(NUM_COL);
//        panel.setPadding(0, FormatUtil.pixOfDip(5), 0, 0);
        final MovementSelAdapter adapter = new MovementSelAdapter(position, selectMap);
        panel.setAdapter(adapter);
        panel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                boolean isSelect = false;
                String key = position + ":" + i;
                if(selectMap.size()>0)
                {
                    if(selectMap.containsKey(key) && selectMap.get(key))
                    {
                        selectMap.clear();
                        isSelect = false;
                    }else
                    {
                        selectMap.clear();
                        isSelect = true;
                        selectMap.put(key, true);
                    }
                }else
                {
                    selectMap.put(key, true);
                    isSelect = true;
                }
                //一定要刷新状态
                notifyDataSetChanged();
                int selectedIndex = position * PAGE_SIZE + i;
                listener.onPick(giftBeans.get(selectedIndex),isSelect);
            }
        });
        container.addView(panel);
        return panel;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public class MovementSelAdapter extends AbsFitAdapter {

        private int page;
        private final List<GiftBean> beans;
        private HashMap<String, Boolean> selectMap;

        public MovementSelAdapter(final int page, HashMap<String, Boolean> selectMap) {
            int end = PAGE_SIZE * (page + 1);
            if (end > giftBeans.size()) {
                end = giftBeans.size();
            }
            this.page = page;
            this.beans = giftBeans.subList(PAGE_SIZE * page, end);
            this.selectMap = selectMap;
        }

        @Override
        public int getCount() {
            return beans.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (null == convertView) {
                convertView = View.inflate(ctx, R.layout.cell_grid_gift, null);
                holder = new ViewHolder();
                holder.gift_img = (ImageView)convertView.findViewById(R.id.gift_img);
                holder.img_send= (ImageView)convertView.findViewById(R.id.img_send);
                holder.img_select = (ImageView)convertView.findViewById(R.id.img_select);
                holder.img_svip = (ImageView)convertView.findViewById(R.id.img_svip);
                holder.gift_money = (TextView)convertView.findViewById(R.id.gift_money);
                holder.gift_experience = (TextView)convertView.findViewById(R.id.gift_experience);
                holder.bgRl = (RelativeLayout)convertView.findViewById(R.id.bgRl);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.pos = position;
            fillData(holder);
            return convertView;
        }

        private void fillData(ViewHolder holder) {
            final GiftBean bean = beans.get(holder.pos);
            holder.gift_experience.setText(bean.getExperience());
            holder.gift_money.setText(bean.getMoney());
            Glide.with(ctx).load("file:///android_asset/" + bean.getPngName() + ".png").into(holder.gift_img);
            String key = page + ":" + holder.pos;
            if(bean.getIsSend()==1)
            {
                holder.img_send.setVisibility(View.VISIBLE);
                holder.img_svip.setVisibility(View.INVISIBLE);
            }else if(bean.getIsSend()==2)
            {
                holder.img_send.setVisibility(View.INVISIBLE);
                holder.img_svip.setVisibility(View.INVISIBLE);
            }
            else if(bean.getIsSend()==3)
            {
                holder.img_send.setVisibility(View.INVISIBLE);
                holder.img_svip.setVisibility(View.VISIBLE);
            }
            if (selectMap.containsKey(key) && selectMap.get(key)) {
                holder.img_select.setVisibility(View.VISIBLE);
                holder.img_send.setVisibility(View.INVISIBLE);
                holder.img_svip.setVisibility(View.INVISIBLE);
            }
            else {
                holder.img_select.setVisibility(View.INVISIBLE);
            }
        }

        class ViewHolder {
            int pos;
            ImageView gift_img,img_select,img_send,img_svip;
            TextView gift_money,gift_experience;
            RelativeLayout bgRl;
        }
    }
}
