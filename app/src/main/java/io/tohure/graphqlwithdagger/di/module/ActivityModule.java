package io.tohure.graphqlwithdagger.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.tohure.graphqlwithdagger.ui.FeedActivity;
import io.tohure.graphqlwithdagger.ui.FeedActivityModule;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FeedActivityModule.class)
    abstract FeedActivity bindFeedActivity();

}
