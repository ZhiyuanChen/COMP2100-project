package cs.anu.edu.au.comp2100.weiming.server;

import android.content.Context;
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

  public static HttpClient httpClient;

  private Retrofit programCourseRetrofit = null;
  private Retrofit wattleRetrofit = null;
  private Retrofit isisRetrofit = null;

  public static HttpClient getInstance() {
    if (httpClient == null) {
      httpClient = new HttpClient();
    }
    return httpClient;
  }

  // private static Retrofit storeRetrofit = null;

  public static Retrofit getProgramCourseClient() {
    return getClient(Config.ProgramCourseURL, null);
  }

  public static Retrofit getWattleClient() {
    return getClient(Config.WattleURL, null);
  }

  public static Retrofit getIsisClient() {
    return getClient(Config.IsisURL, null);
  }

  private static Retrofit getClient(String baseUrl, final Context context) {
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    client.readTimeout(60, TimeUnit.SECONDS);
    client.writeTimeout(60, TimeUnit.SECONDS);
    client.connectTimeout(60, TimeUnit.SECONDS);
    client.addInterceptor(
        new Interceptor() {
          @Override
          public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            return chain.proceed(request);
          }
        });

    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client.build())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }
  //
  //  private Retrofit getProgramCourseClient(final Context context) {
  //    OkHttpClient.Builder client = new OkHttpClient.Builder();
  //    client.readTimeout(60, TimeUnit.SECONDS);
  //    client.writeTimeout(60, TimeUnit.SECONDS);
  //    client.connectTimeout(60, TimeUnit.SECONDS);
  //    client.addInterceptor(
  //        new Interceptor() {
  //          @Override
  //          public okhttp3.Response intercept(Chain chain) throws IOException {
  //            Request request = chain.request();
  //            return chain.proceed(request);
  //          }
  //        });
  //
  //    programCourseRetrofit =
  //        new Retrofit.Builder()
  //            .baseUrl(Config.ProgramCourseURL)
  //            .client(client.build())
  //            .addConverterFactory(TikXmlConverterFactory.create())
  //            .build();
  //
  //    return programCourseRetrofit;
  //  }
  //
  //  private Retrofit getWattleClient(final Context context) {
  //
  //    OkHttpClient.Builder client = new OkHttpClient.Builder();
  //    client.readTimeout(60, TimeUnit.SECONDS);
  //    client.writeTimeout(60, TimeUnit.SECONDS);
  //    client.connectTimeout(60, TimeUnit.SECONDS);
  //    client.addInterceptor(
  //        new Interceptor() {
  //          @Override
  //          public okhttp3.Response intercept(Chain chain) throws IOException {
  //            Request request = chain.request();
  //            return chain.proceed(request);
  //          }
  //        });
  //
  //    wattleRetrofit =
  //        new Retrofit.Builder()
  //            .baseUrl(Config.WattleURL)
  //            .client(client.build())
  //            .addConverterFactory(TikXmlConverterFactory.create())
  //            .build();
  //
  //    return wattleRetrofit;
  //  }
  //
  //  private Retrofit getIsisClient(final Context context) {
  //
  //    OkHttpClient.Builder client = new OkHttpClient.Builder();
  //    client.readTimeout(60, TimeUnit.SECONDS);
  //    client.writeTimeout(60, TimeUnit.SECONDS);
  //    client.connectTimeout(60, TimeUnit.SECONDS);
  //    client.addInterceptor(
  //        new Interceptor() {
  //          @Override
  //          public okhttp3.Response intercept(Chain chain) throws IOException {
  //            Request request = chain.request();
  //            return chain.proceed(request);
  //          }
  //        });
  //
  //    isisRetrofit =
  //        new Retrofit.Builder()
  //            .baseUrl(Config.IsisURL)
  //            .client(client.build())
  //            .addConverterFactory(TikXmlConverterFactory.create())
  //            .build();
  //
  //    return isisRetrofit;
  //  }
}
