package io.tohure.graphqlwithdagger.utils;

/**
 * Created by tohure on 28/02/18.
 */

public interface BasePresenter<V> {
    void attachedView(V view);

    void detachView();
}
