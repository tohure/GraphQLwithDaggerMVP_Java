package io.tohure.graphqlwithdagger.ui;

import java.util.List;

import javax.inject.Inject;

import io.tohure.graphqlwithdagger.FeedQuery;

/**
 * Created by tohure on 28/02/18.
 */

public class FeedPresenter implements FeedContract.Presenter, FeedContract.Callback {

    private FeedContract.View view;
    private FeedInteractor interactor;

    @Inject
    public FeedPresenter(FeedContract.View view, FeedInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getFeed(int limit) {
        view.showLoading();
        interactor.getFeedFromApollo(limit, this);
    }

    @Override
    public void detachView() {
        interactor.cancelCalls();
        this.view = null;
    }

    @Override
    public void getFeedSuccess(List<FeedQuery.FeedEntry> feedEntries) {
        view.hideLoading();
        view.showResult(feedEntries);
    }

    @Override
    public void getFeedError(String error) {
        view.hideLoading();
        view.showError(error);
    }
}
