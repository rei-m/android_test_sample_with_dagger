package me.rei_m.daggersampleapplication.component;

import dagger.Subcomponent;
import me.rei_m.daggersampleapplication.ListFragment;
import me.rei_m.daggersampleapplication.module.ListFragmentModule;

@Subcomponent(modules = {ListFragmentModule.class})
public interface ListFragmentComponent {
    void inject(ListFragment fragment);
}
