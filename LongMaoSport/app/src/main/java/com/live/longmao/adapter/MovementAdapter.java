package com.live.longmao.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.live.longmao.R;
import com.live.longmao.bean.MovementBean;
import com.live.longmao.gifdlg.AbsFitAdapter;
import com.live.longmao.gifdlg.FormatUtil;
import com.live.longmao.util.GlideUtil;
import com.live.longmao.view.GridViewForScrollView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public class MovementAdapter  extends PagerAdapter {

    public interface PickListener {
        void onPick(MovementBean bean);
    }

    private final static int NUM_COL = 4;
    private final static int PAGE_SIZE = 8;
    private final List<MovementBean> movementBeans;
    private final int count;
    private final Context ctx;
    private final PickListener listener;
    private HashMap<String, Boolean> selectMap;

    public MovementAdapter(Context ctx, List<MovementBean> movementBeans, PickListener listener) {
        this.movementBeans = movementBeans;
        count = (int) Math.ceil(movementBeans.size() / (float) PAGE_SIZE);
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
        panel.setNumColumns(NUM_COL);
        panel.setPadding(0, FormatUtil.pixOfDip(5), 0, 0);
        final MovementSelAdapter adapter = new MovementSelAdapter(position, selectMap);
        panel.setAdapter(adapter);
        panel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                selectMap.clear();
                String key = position + ":" + i;
                selectMap.put(key, true);
                //一定要刷新状态
                notifyDataSetChanged();
                int selectedIndex = position * PAGE_SIZE + i;
                listener.onPick(movementBeans.get(selectedIndex));
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
        private final List<MovementBean> beans;
        private HashMap<String, Boolean> selectMap;

        public MovementSelAdapter(final int page, HashMap<String, Boolean> selectMap) {
            int end = PAGE_SIZE * (page + 1);
            if (end > movementBeans.size()) {
                end = movementBeans.size();
            }
            this.page = page;
            this.beans = movementBeans.subList(PAGE_SIZE * page, end);
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
                convertView = View.inflate(ctx, R.layout.cell_grid_movement, null);
                holder = new ViewHolder();
                holder.movement_img = (ImageView)convertView.findViewById(R.id.movement_img);
                holder.img_select = (ImageView)convertView.findViewById(R.id.img_select);
                holder.movement_name = (TextView)convertView.findViewById(R.id.movement_name);
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
            final MovementBean bean = beans.get(holder.pos);
            holder.movement_name.setText(bean.getName());
            GlideUtil.getInstance().load(ctx, holder.movement_img, bean.getImg());
            String key = page + ":" + holder.pos;
            if (selectMap.containsKey(key) && selectMap.get(key))
                holder.img_select.setVisibility(View.VISIBLE);
            else
                holder.img_select.setVisibility(View.GONE);
        }

        class ViewHolder {
            int pos;
            ImageView movement_img,img_select;
            TextView movement_name;
            RelativeLayout bgRl;
        }
    }
}
