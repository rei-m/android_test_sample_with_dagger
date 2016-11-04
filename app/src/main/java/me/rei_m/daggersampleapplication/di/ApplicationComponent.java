package me.rei_m.daggersampleapplication.di;

import dagger.Component;
import me.rei_m.daggersampleapplication.SampleApplication;

@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(SampleApplication application);

    ActivityComponent plus(ActivityModule module);
}
