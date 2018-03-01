package io.tohure.graphqlwithdagger.di.module;

import android.support.annotation.NonNull;

import com.apollographql.apollo.ApolloClient;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.tohure.graphqlwithdagger.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by tohure on 28/02/18.
 */

@Module
public class GraphModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG
                        ? HttpLoggingInterceptor.Level.BODY
                        : HttpLoggingInterceptor.Level.NONE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    ApolloClient provideApolloClient(@NonNull OkHttpClient okHttpClient) {
        return ApolloClient.builder()
                .serverUrl(BuildConfig.BASE_URL)
                .okHttpClient(okHttpClient)
                .build();
    }
}
