package me.rei_m.daggersampleapplication;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import javax.inject.Inject;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import me.rei_m.daggersampleapplication.di.ApplicationComponent;

@RunWith(AndroidJUnit4.class)
public class FirstActivityTest {

    @Inject
    Date mockDate;

    @Component(modules = MockApplicationModule.class)
    public interface TestApplicationComponent extends ApplicationComponent {
        void inject(FirstActivityTest activityTest);
    }

    @Module
    public class MockApplicationModule {
        @Provides
        Date provideDate() {
            Date date = new Date();
            date.setTime(date.getTime() - (24 * 60 * 60 * 1000));
            return date;
        }
    }

    @Rule
    public ActivityTestRule<FirstActivity> activityRule = new ActivityTestRule<>(FirstActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        SampleApplication app = (SampleApplication) instrumentation.getTargetContext().getApplicationContext();
        TestApplicationComponent component = DaggerFirstActivityTest_TestApplicationComponent.builder()
                .mockApplicationModule(new MockApplicationModule())
                .build();
        app.setComponent(component);
        ((TestApplicationComponent) app.getComponent()).inject(this);
    }

    @Test
    public void today() {
        activityRule.launchActivity(new Intent());
    }
}
