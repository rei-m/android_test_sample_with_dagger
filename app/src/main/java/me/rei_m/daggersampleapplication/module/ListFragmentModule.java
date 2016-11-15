package me.rei_m.daggersampleapplication.module;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import dagger.Module;
import me.rei_m.daggersampleapplication.ListFragment;

@Module
public class ListFragmentModule {

    private final Fragment fragment;

    public ListFragmentModule(@NonNull ListFragment fragment) {
        this.fragment = fragment;
    }
}
