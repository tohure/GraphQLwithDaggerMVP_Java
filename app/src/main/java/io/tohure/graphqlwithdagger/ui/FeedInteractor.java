package io.tohure.graphqlwithdagger.ui;

import android.os.Handler;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloCallback;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.fetcher.ApolloResponseFetchers;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import io.tohure.graphqlwithdagger.FeedQuery;
import io.tohure.graphqlwithdagger.type.FeedType;

/**
 * Created by tohure on 1/03/18.
 */

public class FeedInteractor implements FeedContract.Interactor {

    private final ApolloClient apolloClient;
    private final Handler handler;
    private ApolloCall<FeedQuery.Data> dataApolloCall;

    @Inject
    FeedInteractor(ApolloClient apolloClient, Handler handler) {
        this.apolloClient = apolloClient;
        this.handler = handler;
    }

    @Override
    public void getFeedFromApollo(int limit, final FeedContract.Callback callback) {

        final FeedQuery feedQuery = FeedQuery.builder()
                .limit(limit)
                .type(FeedType.HOT)
                .build();

        //With Handler way
        dataApolloCall =
                apolloClient
                        .query(feedQuery)
                        .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST);

        dataApolloCall.enqueue(new ApolloCallback<>(new ApolloCall.Callback<FeedQuery.Data>() {
            @Override
            public void onResponse(@Nonnull Response<FeedQuery.Data> response) {
                callback.getFeedSuccess(response.data().feedEntries());
            }

            @Override
            public void onFailure(@Nonnull ApolloException e) {
                callback.getFeedError(e.getMessage());
            }
        }, handler));

        //Without Handler way
        /*apolloClient
                .query(feedQuery)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
                .enqueue(new ApolloCall.Callback<FeedQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response<FeedQuery.Data> response) {
                        callback.getFeedSuccess(response.data().feedEntries());
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                        callback.getFeedError(e.getMessage());
                    }
                });*/
    }

    @Override
    public void cancelCalls() {
        if (dataApolloCall != null) {
            dataApolloCall.cancel();
        }
    }

}
