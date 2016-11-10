package me.rei_m.daggersampleapplication;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import me.rei_m.daggersampleapplication.component.HasComponent;
import me.rei_m.daggersampleapplication.component.ListFragmentComponent;
import me.rei_m.daggersampleapplication.dao.ListDataDao;
import me.rei_m.daggersampleapplication.module.ListFragmentModule;

public class ListFragment extends BaseFragment implements RecyclerAdapter.OnRecyclerViewInteraction {

    public static final String TAG = ListFragment.class.getSimpleName();

    @Inject
    ListDataDao dao;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        List<String> itemData = dao.getData();

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        TextView emptyView = (TextView) getView().findViewById(R.id.empty);

        if (itemData.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new RecyclerAdapter(getContext(), itemData, this));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void setupFragmentComponent() {
        ((HasComponent<Injector>) getActivity()).getComponent()
                .plus(new ListFragmentModule(this))
                .inject(this);
    }

    @Override
    public void onItemClicked(View v, int position) {

    }

    public interface Injector {
        ListFragmentComponent plus(ListFragmentModule fragmentModule);
    }
}
