package me.rei_m.daggersampleapplication.module;

import android.app.Activity;
import android.support.annotation.NonNull;

import dagger.Module;
import me.rei_m.daggersampleapplication.ListActivity;

@Module
public class ListActivityModule {

    private final Activity activity;
    
    public ListActivityModule(@NonNull ListActivity activity) {
        this.activity = activity;
    }
}
