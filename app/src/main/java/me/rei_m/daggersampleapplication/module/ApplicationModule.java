package me.rei_m.daggersampleapplication.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import me.rei_m.daggersampleapplication.module.scope.ForApplication;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(@NonNull Application application) {
        this.context = application.getApplicationContext();
    }

    @Provides
    @ForApplication
    public Context provideContext() {
        return context;
    }
}
