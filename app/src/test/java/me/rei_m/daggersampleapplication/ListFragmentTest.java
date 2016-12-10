package me.rei_m.daggersampleapplication;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.ArrayList;
import java.util.List;

import me.rei_m.daggersampleapplication.component.HasComponent;
import me.rei_m.daggersampleapplication.component.ListActivityComponent;
import me.rei_m.daggersampleapplication.component.ListFragmentComponent;
import me.rei_m.daggersampleapplication.dao.ListDataDao;
import me.rei_m.daggersampleapplication.module.ListFragmentModule;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
public class ListFragmentTest {

    @Mock
    ListDataDao mockDao;

    private ListFragment fragment;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fragment = ListFragment.newInstance();
        fragment.dao = mockDao;
    }

    @Test
    public void データがある時はリストが表示される() {

        List<String> mockData = new ArrayList<>();
        mockData.add("test1");
        mockData.add("test2");
        mockData.add("test3");

        when(mockDao.getData()).thenReturn(mockData);

        SupportFragmentTestUtil.startFragment(fragment, DriverActivity.class);

        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.recycler_view);
        recyclerView.measure(0, 0);
        recyclerView.layout(0, 0, 100, 1000);

        TextView textEmptyView = (TextView) fragment.getView().findViewById(R.id.empty);
        TextView itemView1 = (TextView) recyclerView.findViewHolderForAdapterPosition(0).itemView.findViewById(R.id.list_item_text);
        TextView itemView3 = (TextView) recyclerView.findViewHolderForAdapterPosition(2).itemView.findViewById(R.id.list_item_text);

        assertThat(recyclerView.getVisibility(), is(View.VISIBLE));
        assertThat(recyclerView.getAdapter().getItemCount(), is(3));
        assertThat(itemView1.getText(), is("test1"));
        assertThat(itemView3.getText(), is("test3"));

        assertThat(textEmptyView.getVisibility(), is(View.GONE));
    }

    @Test
    public void データが空の時はリストが表示されない() {

        List<String> mockData = new ArrayList<>();

        when(mockDao.getData()).thenReturn(mockData);

        SupportFragmentTestUtil.startFragment(fragment, DriverActivity.class);

        RecyclerView recyclerView = (RecyclerView) fragment.getView().findViewById(R.id.recycler_view);
        TextView textEmptyView = (TextView) fragment.getView().findViewById(R.id.empty);

        assertThat(recyclerView.getVisibility(), is(View.GONE));
        assertThat(textEmptyView.getVisibility(), is(View.VISIBLE));
    }

    public static class DriverActivity extends AppCompatActivity implements HasComponent<ListActivityComponent> {

        @Override
        public ListActivityComponent getComponent() {

            ListActivityComponent activityComponent = mock(ListActivityComponent.class);

            ListFragmentComponent fragmentComponent = mock(ListFragmentComponent.class);

            when(activityComponent.plus(any(ListFragmentModule.class))).thenReturn(fragmentComponent);

            return activityComponent;
        }
    }
}
