package me.rei_m.daggersampleapplication;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import me.rei_m.daggersampleapplication.component.HasComponent;
import me.rei_m.daggersampleapplication.component.ListActivityComponent;
import me.rei_m.daggersampleapplication.module.ListActivityModule;

public class ListActivity extends BaseActivity implements HasComponent<ListActivityComponent> {

    private ListActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, ListFragment.newInstance(), ListFragment.TAG)
                    .commit();
        }
    }
    
    @Override
    protected void setupActivityComponent() {
        component = ((SampleApplication) getApplication()).getComponent()
                .plus(new ListActivityModule(this));
        component.inject(this);
    }

    @Override
    public ListActivityComponent getComponent() {
        if (component == null) {
            setupActivityComponent();
        }
        return component;
    }
}
