package com.example.john.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btn;
    private String TAG="xiaochaoge";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
    }

    private void getDataFromService(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service=retrofit.create(ApiService.class);
        Call<List<ResponseBody>> call =service.getInfo("square","retrofit");
        call.enqueue(new Callback<List<ResponseBody>>() {
            @Override
            public void onResponse(Call<List<ResponseBody>> call, Response<List<ResponseBody>> response) {
                Log.d(TAG, response.body().get(1).getLogin());
                textView.setText(response.body().get(1).getLogin());
            }

            @Override
            public void onFailure(Call<List<ResponseBody>> call, Throwable t) {

            }
        });
    }

    private void getDataFromService1() {
        /**
         *第一种方法 使用get方式，@Query 注解来构建的完整URL
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<Info> call = service.getInfo1("15201339284", "46066e52ff348681e2e3b22669c0cd89");
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                textView.setText(response.body().getResult().getCity());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });


    }

    /**
     * 第二种方法 使用get方式  @Querymap 注解来构建完整的URL
     */
    public void getDataFromService2() {

        Map<String, String> map = new HashMap<>();
        map.put("phone", "13762529063");
        map.put("key", "46066e52ff348681e2e3b22669c0cd89");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<Info> call = service.getInfo2(map);
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Log.d("TAG", response.body().getResultcode());
                textView.setText(response.body().getResult().getCity());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });
    }

    /**
     * 第三种方法 使用post 方式 使用@Field 注解来构建完整的URL
     */

    public void getDataFromService3() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apis.juhe.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<Info> call = service.getInfo3("15201339284", "46066e52ff348681e2e3b22669c0cd89");
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                textView.setText(response.body().getResult().getCity());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });
    }

    /**
     * 第四种方法 使用post 方式 使用@Fieldmap 注解来构建完整的URL
     */
    public void getDataFromService4() {


        Map<String, String> map = new HashMap<>();
        map.put("phone", "13762529063");
        map.put("key", "46066e52ff348681e2e3b22669c0cd89");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://apis.juhe.cn/")
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<Info> call = service.getInfo4(map);
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                Log.d("TAG", response.body().getResultcode());
                textView.setText(response.body().getResult().getCard());
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {

            }
        });

    }

    private void initview() {
        textView = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn_query);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataFromService1();
            }
        });
    }

}

