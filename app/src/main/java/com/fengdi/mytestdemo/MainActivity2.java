package com.fengdi.mytestdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fengdi.mytestdemo.entity.BaseResponseEntity;
import com.fengdi.mytestdemo.entity.RegisterEntity;
import com.fengdi.mytestdemo.http.BaseObserver;
import com.fengdi.mytestdemo.http.RetrofitHandler;
import com.fengdi.mytestdemo.http.RxTransformerHelper;
import com.google.gson.Gson;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainActivity2 extends AppCompatActivity {

    Button btLogin;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(view -> {
            login();
        });

        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(view -> {
            register();
        });

    }

    //注册
    void register() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("email", "2295360491@qq.com");
        map.put("password", "12345678");


        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),
                        new Gson().toJson(map));

        RetrofitHandler.getInstance().getAPIService()
                .register(requestBody)
                .compose(RxTransformerHelper.observableIO2Main(this))
                .subscribe(new BaseObserver<RegisterEntity>() {
                    @Override
                    protected void onSuccess(BaseResponseEntity<RegisterEntity> responseEntity) {
//                        showSuccessDialog("Success");
                        Log.v("MainAcrivity", "onSuccess:" + responseEntity.getData().toString());
                    }

                    @Override
                    protected void onFailure(String errorMessage) {
//                        showErrorDialog(errorMessage);
                        Log.v("MainAcrivity", "onFailure:" + errorMessage);

                    }

                });
    }

    private void login() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("account", "20123");
        map.put("password", "e10adc3949ba59abbe56e057f20f883e");


        RequestBody requestBody =
                RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),
                        new Gson().toJson(map));


        RetrofitHandler.getInstance().getAPIService()
                .login(requestBody)
                .compose(RxTransformerHelper.observableIO2Main(this))
                .subscribe(new BaseObserver<String>() {
                    @Override
                    protected void onSuccess(BaseResponseEntity<String> responseEntity) {
//                        showSuccessDialog("Success");
                        Log.v("MainAcrivity", "onSuccess:" + responseEntity.getData());
                    }

                    @Override
                    protected void onFailure(String errorMessage) {
//                        showErrorDialog(errorMessage);
                        Log.v("MainAcrivity", "onFailure:" + errorMessage);
                    }

                });
    }
}