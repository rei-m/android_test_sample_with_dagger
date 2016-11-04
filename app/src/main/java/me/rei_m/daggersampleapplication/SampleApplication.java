package me.rei_m.daggersampleapplication;

import android.app.Application;

import me.rei_m.daggersampleapplication.di.ApplicationComponent;
import me.rei_m.daggersampleapplication.di.ApplicationModule;
import me.rei_m.daggersampleapplication.di.DaggerApplicationComponent;

public class SampleApplication extends Application {

    private ApplicationComponent component;

    public ApplicationComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);
    }
}
