package io.tohure.graphqlwithdagger.di.module;

import android.os.Handler;
import android.os.Looper;

import com.apollographql.apollo.ApolloClient;

import dagger.Module;
import dagger.Provides;
import io.tohure.graphqlwithdagger.ui.FeedContract;
import io.tohure.graphqlwithdagger.ui.FeedInteractor;
import io.tohure.graphqlwithdagger.ui.FeedPresenter;

/**
 * Created by tohure on 1/03/18.
 */

@Module
public class FeedModule {

    private FeedContract.View view;

    public FeedModule(FeedContract.View view) {
        this.view = view;
    }

    @Provides
    FeedContract.View provideFeedView() {
        return view;
    }

    @Provides
    Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    @Provides
    FeedInteractor provideFeedInteractor(ApolloClient apolloClient, Handler handler) {
        return new FeedInteractor(apolloClient, handler);
    }

    @Provides
    FeedPresenter provideFeedPresenter(FeedContract.View view, FeedInteractor interactor) {
        return new FeedPresenter(view, interactor);
    }
}
