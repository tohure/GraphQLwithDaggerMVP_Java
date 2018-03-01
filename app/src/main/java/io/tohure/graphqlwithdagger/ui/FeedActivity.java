package io.tohure.graphqlwithdagger.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import io.tohure.graphqlwithdagger.FeedQuery;
import io.tohure.graphqlwithdagger.R;
import io.tohure.graphqlwithdagger.di.component.DaggerFeedComponent;
import io.tohure.graphqlwithdagger.di.module.FeedModule;
import io.tohure.graphqlwithdagger.di.module.GraphModule;

public class FeedActivity extends AppCompatActivity implements FeedContract.View {

    @Inject
    FeedPresenter presenter;

    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        setInyection();
        presenter.getFeed(10);

        //init recycler
        RecyclerView rvFeeds = findViewById(R.id.rvFeeds);
        rvFeeds.setHasFixedSize(true);

        feedAdapter = new FeedAdapter();
        rvFeeds.setAdapter(feedAdapter);
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
        Log.d("thr", "showLoading: ");
    }

    @Override
    public void hideLoading() {
        Log.d("thr", "hideLoading: ");
    }

    @Override
    public void showError(String error) {
        Log.d("thr", "showError: " + error);
    }

    @Override
    public void showResult(final List<FeedQuery.FeedEntry> feedEntries) {

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                feedAdapter.addData(feedEntries);
            }
        });
    }
}
