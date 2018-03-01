package io.tohure.graphqlwithdagger.ui;

import io.tohure.graphqlwithdagger.utils.BasePresenter;

/**
 * Created by tohure on 28/02/18.
 */

public interface FeedContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showError(String error);

    }

    interface Presenter extends BasePresenter<View> {

        void getFeed(int limit);

    }

    interface Callback {

        void getFeedSucces();

        void getFeedError(String error);

    }
}
