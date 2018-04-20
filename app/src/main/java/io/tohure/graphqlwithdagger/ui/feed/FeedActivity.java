package io.tohure.graphqlwithdagger.ui.feed;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
import io.tohure.graphqlwithdagger.FeedQuery;
import io.tohure.graphqlwithdagger.R;

public class FeedActivity extends DaggerAppCompatActivity implements FeedContract.View {

    @Inject
    Lazy<FeedPresenter> presenter;

    private FeedAdapter feedAdapter;
    private ProgressBar progresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        init();
    }

    private void init() {

        //init recycler
        RecyclerView rvFeeds = findViewById(R.id.rvFeeds);
        rvFeeds.setHasFixedSize(true);
        feedAdapter = new FeedAdapter();
        rvFeeds.setAdapter(feedAdapter);

        progresBar = findViewById(R.id.progresBar);

        presenter.get().getFeed(10);
    }

    @Override
    public void showLoading() {
        progresBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progresBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(List<FeedQuery.FeedEntry> feedEntries) {

        //With Handler way
        feedAdapter.addData(feedEntries);

        //Without Handler way
        //FeedActivity.this.runOnUiThread(() -> feedAdapter.addData(feedEntries));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.get().detachView();
    }
}
