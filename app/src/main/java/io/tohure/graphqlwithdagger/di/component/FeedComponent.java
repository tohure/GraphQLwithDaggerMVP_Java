package io.tohure.graphqlwithdagger.di.component;

import javax.inject.Singleton;

import dagger.Component;
import io.tohure.graphqlwithdagger.di.anotations.PerActivity;
import io.tohure.graphqlwithdagger.di.module.FeedModule;
import io.tohure.graphqlwithdagger.di.module.GraphModule;
import io.tohure.graphqlwithdagger.ui.FeedActivity;

/**
 * Created by tohure on 1/03/18.
 */

@PerActivity
@Singleton
@Component(
        modules = {
                FeedModule.class,
                GraphModule.class})
public interface FeedComponent {
    void inject(FeedActivity activity);
}
