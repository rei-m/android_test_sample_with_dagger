package me.rei_m.daggersampleapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.rei_m.daggersampleapplication.di.ActivityComponent;
import me.rei_m.daggersampleapplication.di.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent component;

    public ActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = ((SampleApplication) getApplication()).getComponent().plus(new ActivityModule());
    }
}
