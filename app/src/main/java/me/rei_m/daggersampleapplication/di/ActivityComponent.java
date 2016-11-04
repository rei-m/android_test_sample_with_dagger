package me.rei_m.daggersampleapplication.di;

import dagger.Subcomponent;
import me.rei_m.daggersampleapplication.FirstActivity;

@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
    void inject(FirstActivity activity);
}
