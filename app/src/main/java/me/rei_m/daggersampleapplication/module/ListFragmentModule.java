package me.rei_m.daggersampleapplication.module;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import me.rei_m.daggersampleapplication.ListFragment;

@Module
public class ListFragmentModule {

    private final Context context;

    public ListFragmentModule(@NonNull ListFragment fragment) {
        this.context = fragment.getContext();
    }
}
