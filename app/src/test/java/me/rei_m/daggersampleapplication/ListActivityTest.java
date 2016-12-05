package me.rei_m.daggersampleapplication;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class ListActivityTest {

    ActivityController<ListActivity> activityController;

    ListActivity activity;

    @Before
    public void setUp() throws Exception {
        activityController = Robolectric.buildActivity(ListActivity.class);
        activity = activityController.get();

        // ここでモックを注入する.

        activityController.create().start().resume().visible();
    }
}
