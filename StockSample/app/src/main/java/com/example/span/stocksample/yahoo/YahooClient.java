package com.example.span.stocksample.yahoo;

import com.example.span.stocksample.yahoo.json.YahooStockResult;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YahooClient {
    Retrofit retrofit;
    YahooStockEndpoint yahooStockEndpoint;

    public YahooClient() {
        retrofit = createRetrofit(createHttpClient());
        yahooStockEndpoint = retrofit.create(YahooStockEndpoint.class);
    }

    private OkHttpClient createHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient().newBuilder().addInterceptor(interceptor).build();
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://query.yahooapis.com/v1/public/")
                .build();
        return retrofit;
    }

    public Single<YahooStockResult> query(String query, String env) {
        return yahooStockEndpoint.yqlQuery(query, env);
    }

}
