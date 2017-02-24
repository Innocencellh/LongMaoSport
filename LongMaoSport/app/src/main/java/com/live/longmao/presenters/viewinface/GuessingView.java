package com.live.longmao.presenters.viewinface;

import com.live.longmao.model.GuessingInfo;
import com.live.longmao.model.ListGuessingInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/9/10.
 */
public interface GuessingView {
    void onGuessingSucc();//竞猜设置成功
    void onGuessingRead(List<GuessingInfo> guessingInfos,boolean isSucc);//读是否有竞猜
    //void onGuessingListRead(ListGuessingInfo listGuessingInfo,boolean isSucc);//读是否有竞猜
    void onGuessingEntertained(GuessingInfo guessingInfo);//竞猜封盘
    void onGuessingBet(GuessingInfo guessingInfos);//下注
    void onGuessingJieSuanSucc();//结算成功
    void onGuessingError(String msg);
}
