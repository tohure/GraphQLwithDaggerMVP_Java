package io.tohure.graphqlwithdagger.app;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.tohure.graphqlwithdagger.di.component.AppComponent;
import io.tohure.graphqlwithdagger.di.component.DaggerAppComponent;

public class GraphApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

}
