package utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;


/**
*1.类的用途
*2.zhanghaisheng
*3.2017/2/27
**/

public class OkHttpUtils {

    private static OkHttpUtils okHttpUtils;
    private final Gson gson;
    private final Handler handler;
    private final OkHttpClient okHttpClient;

    OkHttpUtils() {
        gson = new Gson();
        handler = new Handler(Looper.getMainLooper());
        okHttpClient = new OkHttpClient();
    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public <T> void getRequest(String url, final Class<T> bean, final RequestJsonBeanCallBack<T> callBack) {
        //实例化Request
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("Okhttp",e.getMessage());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.error();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
               String s=response.body().string();
               final T result=gson.fromJson(s,bean);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                     callBack.success(result);
                    }
                });
            }
        });


    }

    /**
     * post请求
     *
     * @param url      请求的Url
     * @param callBack 回调借口  OkHttp默认为子线程已更换为主线程 可在回调的方法中直接更新UI
     * @param param    参数对象
     * @param <T>      Bean类型
     */
    public <T> void postRequest(String url, final Class<T> bean,
                                  final RequestJsonBeanCallBack<T> callBack, Param... param) {
        //调用自定义封装方法 该方法将请求参数封装到Request对象中 并返回
        Request request = builderPostRequest(url, param);
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.error();
                        }
                    });
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String s=response.body().string();
                    final T result=gson.fromJson(s,bean);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(result);
                        }
                    });
                }
            });
    }

    /**
     * @param url    请求的Url
     * @param params post请求的参数  存储在对象中
     * @return 封装好的Request对象
     */
    private Request builderPostRequest(String url, Param[] params) {
        //实例化FormEncodingBuilder对象
        FormEncodingBuilder builder = new FormEncodingBuilder();
        //对请求参数进行判空
        if (params == null) {
            params = new Param[0];
        }
        for (Param param : params) {
            builder.add(param.key, param.value);
        }
        RequestBody requestBody = builder.build();
        //创建Request对象
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return request;
    }

    public interface RequestJsonBeanCallBack<T> {
        void success(T result);

        void error();
    }

    //自定义存储类 存储类型为键值对 用于Post请求
    public static class Param {
        public String key;
        public String value;

        public Param() {

        }

        public Param(String key, String value) {

            this.key = key;
            this.value = value;
        }
    }

}
/*
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:23.0.0'
    testCompile 'junit:junit:4.12'
    compile 'com.squareup.okhttp3:okhttp:3.2.0'
    compile 'com.squareup.okio:okio:1.7.0'
    compile 'com.google.code.gson:gson:2.8.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.5.0'
    compile 'com.android.support:recyclerview-v7:23.0.0'
    compile 'com.github.bumptech.glide:glide:3.7.0'
    compile 'com.squareup.okhttp:okhttp:2.4.0'
}
*/
