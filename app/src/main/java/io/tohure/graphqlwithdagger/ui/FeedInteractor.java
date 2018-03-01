package io.tohure.graphqlwithdagger.ui;

import com.apollographql.apollo.ApolloCall;
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

    private ApolloClient apolloClient;

    @Inject
    public FeedInteractor(ApolloClient apolloClient) {
        this.apolloClient = apolloClient;
    }

    @Override
    public void getFeedFromApollo(int limit, final FeedContract.Callback callback) {

        FeedQuery feedQuery = FeedQuery.builder()
                .limit(limit)
                .type(FeedType.HOT)
                .build();

        apolloClient
                .query(feedQuery)
                .responseFetcher(ApolloResponseFetchers.NETWORK_FIRST)
                .enqueue(new ApolloCall.Callback<FeedQuery.Data>() {
                    @Override
                    public void onResponse(@Nonnull Response<FeedQuery.Data> response) {
                        callback.getFeedSucces(response.data().feedEntries());
                    }

                    @Override
                    public void onFailure(@Nonnull ApolloException e) {
                        callback.getFeedError(e.getMessage());
                    }
                });

    }

}
