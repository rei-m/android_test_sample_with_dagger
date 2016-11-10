package me.rei_m.daggersampleapplication;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import me.rei_m.daggersampleapplication.component.ApplicationComponent;
import me.rei_m.daggersampleapplication.component.DaggerApplicationComponent;
import me.rei_m.daggersampleapplication.module.ApplicationModule;

public class SampleApplication extends Application {

    private ApplicationComponent component;

    public ApplicationComponent getComponent() {
        return component;
    }

    @VisibleForTesting
    public void setComponent(ApplicationComponent component) {
        this.component = component;
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
