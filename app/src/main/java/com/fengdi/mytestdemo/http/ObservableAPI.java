package com.fengdi.mytestdemo.http;

import com.fengdi.mytestdemo.entity.BaseResponseEntity;
import com.fengdi.mytestdemo.entity.LoginResponseEntity;
import com.fengdi.mytestdemo.entity.RegisterEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 接口服务
 **/
public interface ObservableAPI {

    /**
     * 登录
     */
    @POST("user/login")
    Observable<BaseResponseEntity<String>> login(@Body RequestBody body);

    /**
     * 注册
     */
    @POST("member/register")
    Observable<BaseResponseEntity<RegisterEntity>> register(@Body RequestBody body);

}