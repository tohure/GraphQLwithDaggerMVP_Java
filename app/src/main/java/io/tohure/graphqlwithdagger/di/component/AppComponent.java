package io.tohure.graphqlwithdagger.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import io.tohure.graphqlwithdagger.di.anotations.PerActivity;
import io.tohure.graphqlwithdagger.di.module.ActivityModule;
import io.tohure.graphqlwithdagger.di.module.GraphModule;

@PerActivity
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        GraphModule.class,
        ActivityModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
