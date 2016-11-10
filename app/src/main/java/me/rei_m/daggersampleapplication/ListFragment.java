package me.rei_m.daggersampleapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.rei_m.daggersampleapplication.component.HasComponent;
import me.rei_m.daggersampleapplication.component.ListFragmentComponent;
import me.rei_m.daggersampleapplication.module.ListFragmentModule;

public class ListFragment extends BaseFragment {

    public static final String TAG = ListFragment.class.getSimpleName();

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void setupFragmentComponent() {
        ((HasComponent<Injector>) getActivity()).getComponent()
                .plus(new ListFragmentModule(this))
                .inject(this);
    }

    public interface Injector {
        ListFragmentComponent plus(ListFragmentModule fragmentModule);
    }
}
