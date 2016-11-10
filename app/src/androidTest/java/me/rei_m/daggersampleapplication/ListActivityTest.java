package me.rei_m.daggersampleapplication;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import me.rei_m.daggersampleapplication.component.ApplicationComponent;
import me.rei_m.daggersampleapplication.component.ListActivityComponent;
import me.rei_m.daggersampleapplication.component.ListFragmentComponent;
import me.rei_m.daggersampleapplication.dao.ListDataDao;
import me.rei_m.daggersampleapplication.module.ListActivityModule;
import me.rei_m.daggersampleapplication.module.ListFragmentModule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    @Mock
    ApplicationComponent applicationComponent;

    @Mock
    ListActivityComponent activityComponent;

    @Mock
    ListFragmentComponent fragmentComponent;

    @Mock
    ListDataDao mockDao;

    @Rule
    public ActivityTestRule<ListActivity> activityRule = new ActivityTestRule<>(ListActivity.class, true, false);

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        SampleApplication app = (SampleApplication) InstrumentationRegistry
                .getTargetContext()
                .getApplicationContext();

        app.setComponent(applicationComponent);

        when(applicationComponent.plus(any(ListActivityModule.class)))
                .thenReturn(activityComponent);

        when(applicationComponent.plus(any(ListActivityModule.class)))
                .thenReturn(activityComponent);

        when(activityComponent.plus(any(ListFragmentModule.class)))
                .thenReturn(fragmentComponent);

        doAnswer(invocation -> {
            ListFragment fragment = (ListFragment) invocation.getArguments()[0];
            fragment.dao = mockDao;
            return fragment;
        }).when(fragmentComponent).inject(any(ListFragment.class));
    }

    @Test
    public void データがある時() {

        List<String> mockData = new ArrayList<>();
        mockData.add("hoge");
        mockData.add("fuga");
        mockData.add("piyo");

        when(mockDao.getData()).thenReturn(mockData);

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.recycler_view))
                .check(matches(hasDescendant(withText("hoge"))));
        onView(withId(R.id.recycler_view))
                .check(matches(hasDescendant(withText("fuga"))));
        onView(withId(R.id.recycler_view))
                .check(matches(hasDescendant(withText("piyo"))));

        onView(withId(R.id.empty))
                .check(matches(not(isDisplayed())));
    }

    @Test
    public void データが空の時() {

        List<String> mockData = new ArrayList<>();

        when(mockDao.getData()).thenReturn(mockData);

        activityRule.launchActivity(new Intent());

        onView(withId(R.id.empty))
                .check(matches(isDisplayed()));

        onView(withId(R.id.recycler_view))
                .check(matches(not(isDisplayed())));
    }
}
