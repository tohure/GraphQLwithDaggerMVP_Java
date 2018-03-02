package io.tohure.graphqlwithdagger.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import dagger.Lazy;
import io.tohure.graphqlwithdagger.FeedQuery;
import io.tohure.graphqlwithdagger.R;
import io.tohure.graphqlwithdagger.di.component.DaggerFeedComponent;
import io.tohure.graphqlwithdagger.di.module.FeedModule;
import io.tohure.graphqlwithdagger.di.module.GraphModule;

public class FeedActivity extends AppCompatActivity implements FeedContract.View {

    @Inject
    Lazy<FeedPresenter> presenter;

    private FeedAdapter feedAdapter;
    private ProgressBar progresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        setInyection();

        //init recycler
        RecyclerView rvFeeds = findViewById(R.id.rvFeeds);
        rvFeeds.setHasFixedSize(true);
        feedAdapter = new FeedAdapter();
        rvFeeds.setAdapter(feedAdapter);

        progresBar = findViewById(R.id.progresBar);

        presenter.get().getFeed(10);
    }

    private void setInyection() {
        DaggerFeedComponent.builder()
                .feedModule(new FeedModule(this))
                .graphModule(new GraphModule())
                .build()
                .inject(this);
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
    public void showResult(final List<FeedQuery.FeedEntry> feedEntries) {

        //With Handler way
        feedAdapter.addData(feedEntries);

        //Without Handler way
        /*FeedActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                feedAdapter.addData(feedEntries);
            }
        });*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.get().detachView();
    }
}
