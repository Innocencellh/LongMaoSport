package com.live.longmao;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by joncran001 on 4/4/16.
 * 定位框架
 */
public class LocationManager implements AMapLocationListener {

    private static LocationManager instance;
    private final static long INTERVAL = 2 * 1000;// 定位间隔

    private AMapLocationClient mLocationClient = null;
    private OnLocationListener mOnLocationListener;

    public static LocationManager getInstance() {
        if (null == instance)
            instance = new LocationManager();
        return instance;
    }

    private LocationManager() {
        mLocationClient = new AMapLocationClient(BaseApp.getContext());
        mLocationClient.setLocationListener(this);
        AMapLocationClientOption locOption = new AMapLocationClientOption();
        locOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locOption.setOnceLocation(false);
        locOption.setInterval(INTERVAL);
        mLocationClient.setLocationOption(locOption);
    }

    public void start() {
        if (null == mOnLocationListener)
            throw new UnsupportedOperationException("onLocationListener must not be null so you can't locate");
        if (null != mLocationClient)
            mLocationClient.startLocation();
    }

    public void stop() {
        if (null != mLocationClient)
            mLocationClient.stopLocation();
    }

    public void destory() {
        if (null != mLocationClient)
            mLocationClient.onDestroy(); //注意：销毁定位客户端之后，若要重新开启定位请重新New一个AMapLocationClient对象
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                double latitude = aMapLocation.getLatitude();//获取纬度
                double longitude = aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                String address = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                String country = aMapLocation.getCountry();//国家信息
                String province = aMapLocation.getProvince();//省信息
                String city = aMapLocation.getCity();//城市信息
                String district = aMapLocation.getDistrict();//城区信息
                String cityCode = aMapLocation.getCityCode();//城市编码
                mOnLocationListener.onReceiveLocation(longitude, latitude, cityCode, city, province, country, district, address);
                stop();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                //高德定位失败，直接经纬度传0，其他传空
                mOnLocationListener.onFailLocation(aMapLocation.getErrorCode(), aMapLocation.getErrorInfo());
                stop();
            }
        }
    }

    public LocationManager addOnLocationListener(OnLocationListener onLocationListener) {
        mOnLocationListener = onLocationListener;
        return this;
    }

    public interface OnLocationListener {
        void onReceiveLocation(double longitude, double latitude, String cityCode, String city, String province, String country, String district, String address);

        void onFailLocation(int errorCode, String errorInfo);
    }
}
