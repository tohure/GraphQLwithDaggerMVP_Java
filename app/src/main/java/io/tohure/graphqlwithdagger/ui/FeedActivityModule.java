package io.tohure.graphqlwithdagger.ui;

import android.os.Handler;
import android.os.Looper;

import com.apollographql.apollo.ApolloClient;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by tohure on 1/03/18.
 */

@Module
public abstract class FeedActivityModule {

    @Provides
    static Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    static FeedInteractor provideFeedInteractor(ApolloClient apolloClient, Handler handler) {
        return new FeedInteractor(apolloClient, handler);
    }

    @Provides
    static FeedPresenter provideFeedPresenter(FeedContract.View view, FeedInteractor interactor) {
        return new FeedPresenter(view, interactor);
    }

    @Binds
    abstract FeedContract.View provideFeedView(FeedActivity mainActivity);

}
