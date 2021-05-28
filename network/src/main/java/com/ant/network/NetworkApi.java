package com.ant.network;

import com.ant.network.environment.EnvironmentActivity;
import com.ant.network.environment.IEnvironment;
import com.blankj.utilcode.util.AppUtils;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class NetworkApi implements IEnvironment {

    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();
    private String mBaseUrl;
    private static boolean mIsFormal = true;

    public NetworkApi() {
        if (mIsFormal) {
            mBaseUrl = getFormal();
        } else {
            mBaseUrl = getTest();
        }
    }

    public static void init() {
        mIsFormal = EnvironmentActivity.isOfficialEnvironment();
    }

    Retrofit getRetrofit(Class service) {
        if (retrofitHashMap.get(mBaseUrl + service.getName()) != null) {
            return retrofitHashMap.get(mBaseUrl + service.getName());
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(getOKHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitHashMap.put(mBaseUrl + service.getName(), retrofit);
        return retrofit;
    }

    public <T> T getService(Class<T> service) {
        return getRetrofit(service).create(service);
    }

    private static OkHttpClient getOKHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (AppUtils.isAppDebug()) {
            okHttpClientBuilder.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        return okHttpClientBuilder.build();
    }

    public static <T> ObservableTransformer<T, T> applySchedulers(final Observer<T> observer) {
        return upstream -> {
            Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                    .subscribeOn(AndroidSchedulers.mainThread());
            observable.subscribe(observer);
            return observable;
        };
    }
}