package com.live.longmao.util;

import com.live.longmao.R;

/**
 * Created by Administrator on 2016/7/6.
 */
public class LevelBgColor {
    //等级图标背景色
    public static int LEVELCOLOR(int level) {
        int color = R.drawable.level_shape;
        if (level < 10) {
            color = R.drawable.level_shape;
        } else if (level > 10 && level < 21) {
            color = R.drawable.level_shape_blue;
        } else if (level > 20 && level < 31) {
            color = R.drawable.level_shape_pink;
        } else if (level > 30 && level < 41) {
            color = R.drawable.level_shape_red;
        } else if (level > 40 && level < 51) {
            color = R.drawable.level_shape_purple;
        } else if (level > 50 && level < 61) {
            color = R.drawable.level_shape_yellow;
        } else if (level > 60 && level < 71) {
            color = R.drawable.level_shape_black;
        }
        return color;
    }
}
