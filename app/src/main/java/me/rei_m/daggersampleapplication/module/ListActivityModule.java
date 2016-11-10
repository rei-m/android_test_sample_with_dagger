package me.rei_m.daggersampleapplication.module;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import me.rei_m.daggersampleapplication.ListActivity;

@Module
public class ListActivityModule {

    private final Context context;

    public ListActivityModule(@NonNull ListActivity activity) {
        this.context = activity;
    }
}
