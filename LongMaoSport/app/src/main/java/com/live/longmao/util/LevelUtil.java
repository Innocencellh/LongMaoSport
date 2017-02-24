package com.live.longmao.util;

import com.live.longmao.R;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/8/29.
 */
public class LevelUtil {
    private static LevelUtil levelUtil;
    private LevelUtil(){}
    private static HashMap<Integer,Integer> levelMap;

    public static LevelUtil getInstance()
    {
        if(null==levelUtil)
        {
            levelUtil = new LevelUtil();
        }
        if(null==levelMap)
        {
            levelMap = new HashMap<>();
            initMap();
        }
        return levelUtil;
    }
    private static void initMap()
    {
        levelMap.put(1, R.mipmap.rank_1);
        levelMap.put(2, R.mipmap.rank_2);
        levelMap.put(3, R.mipmap.rank_3);
        levelMap.put(4, R.mipmap.rank_4);
        levelMap.put(5, R.mipmap.rank_5);
        levelMap.put(6, R.mipmap.rank_6);
        levelMap.put(7, R.mipmap.rank_7);
        levelMap.put(8, R.mipmap.rank_8);
        levelMap.put(9, R.mipmap.rank_9);
        levelMap.put(10, R.mipmap.rank_10);
        levelMap.put(11, R.mipmap.rank_11);
        levelMap.put(12, R.mipmap.rank_12);
        levelMap.put(13, R.mipmap.rank_13);
        levelMap.put(14, R.mipmap.rank_14);
        levelMap.put(15, R.mipmap.rank_15);
        levelMap.put(16, R.mipmap.rank_16);
        levelMap.put(17, R.mipmap.rank_17);
        levelMap.put(18, R.mipmap.rank_18);
        levelMap.put(19, R.mipmap.rank_19);
        levelMap.put(20, R.mipmap.rank_20);
        levelMap.put(21, R.mipmap.rank_21);
        levelMap.put(22, R.mipmap.rank_22);
        levelMap.put(23, R.mipmap.rank_23);
        levelMap.put(24, R.mipmap.rank_24);
        levelMap.put(25, R.mipmap.rank_25);
        levelMap.put(26, R.mipmap.rank_26);
        levelMap.put(27, R.mipmap.rank_27);
        levelMap.put(28, R.mipmap.rank_28);
        levelMap.put(29, R.mipmap.rank_29);
        levelMap.put(30, R.mipmap.rank_30);
        levelMap.put(31, R.mipmap.rank_31);
        levelMap.put(32, R.mipmap.rank_32);
        levelMap.put(33, R.mipmap.rank_33);
        levelMap.put(34, R.mipmap.rank_34);
        levelMap.put(35, R.mipmap.rank_35);
        levelMap.put(36, R.mipmap.rank_36);
        levelMap.put(37, R.mipmap.rank_37);
        levelMap.put(38, R.mipmap.rank_38);
        levelMap.put(39, R.mipmap.rank_39);
        levelMap.put(40, R.mipmap.rank_40);
        levelMap.put(41, R.mipmap.rank_41);
        levelMap.put(42, R.mipmap.rank_42);
        levelMap.put(43, R.mipmap.rank_43);
        levelMap.put(44, R.mipmap.rank_44);
        levelMap.put(45, R.mipmap.rank_45);
        levelMap.put(46, R.mipmap.rank_46);
        levelMap.put(47, R.mipmap.rank_47);
        levelMap.put(48, R.mipmap.rank_48);
        levelMap.put(49, R.mipmap.rank_49);
        levelMap.put(50, R.mipmap.rank_50);
        levelMap.put(51, R.mipmap.rank_51);
        levelMap.put(52, R.mipmap.rank_52);
        levelMap.put(53, R.mipmap.rank_53);
        levelMap.put(54, R.mipmap.rank_54);
        levelMap.put(55, R.mipmap.rank_55);
        levelMap.put(56, R.mipmap.rank_56);
        levelMap.put(57, R.mipmap.rank_57);
        levelMap.put(58, R.mipmap.rank_58);
        levelMap.put(59, R.mipmap.rank_59);
        levelMap.put(60, R.mipmap.rank_60);
        levelMap.put(61, R.mipmap.rank_61);
        levelMap.put(62, R.mipmap.rank_62);
        levelMap.put(63, R.mipmap.rank_63);
        levelMap.put(64, R.mipmap.rank_64);

    }

    public int getLevel(int level)
    {
        return levelMap.get(level);
    }
}
