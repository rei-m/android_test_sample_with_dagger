package me.rei_m.daggersampleapplication.component;

import dagger.Component;
import me.rei_m.daggersampleapplication.SampleApplication;
import me.rei_m.daggersampleapplication.module.ApplicationModule;
import me.rei_m.daggersampleapplication.module.ListActivityModule;
import me.rei_m.daggersampleapplication.module.scope.ForApplication;

@ForApplication
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(SampleApplication application);

    ListActivityComponent plus(ListActivityModule module);
}
