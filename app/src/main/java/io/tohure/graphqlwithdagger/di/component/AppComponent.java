package io.tohure.graphqlwithdagger.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;
import io.tohure.graphqlwithdagger.app.GraphApplication;
import io.tohure.graphqlwithdagger.di.anotations.PerActivity;
import io.tohure.graphqlwithdagger.di.module.ActivityBuilder;
import io.tohure.graphqlwithdagger.di.module.AppModule;
import io.tohure.graphqlwithdagger.di.module.GraphModule;

@PerActivity
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        GraphModule.class,
        ActivityBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    void inject(GraphApplication app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
