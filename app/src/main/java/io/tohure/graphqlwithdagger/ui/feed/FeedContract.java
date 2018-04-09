package io.tohure.graphqlwithdagger.ui.feed;

import java.util.List;

import io.tohure.graphqlwithdagger.FeedQuery;

/**
 * Created by tohure on 28/02/18.
 */

public interface FeedContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showError(String error);

        void showResult(List<FeedQuery.FeedEntry> feeds);

    }

    interface Presenter {

        void getFeed(int limit);

        void detachView();

    }

    interface Interactor {

        void getFeedFromApollo(int limit, Callback callback);

        void cancelCalls();

    }

    interface Callback {

        void getFeedSuccess(List<FeedQuery.FeedEntry> feeds);

        void getFeedError(String error);

    }
}
