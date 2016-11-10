package me.rei_m.daggersampleapplication;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Date;

import javax.inject.Inject;

import me.rei_m.daggersampleapplication.component.HasComponent;
import me.rei_m.daggersampleapplication.component.ListActivityComponent;
import me.rei_m.daggersampleapplication.module.ListActivityModule;

public class ListActivity extends BaseActivity implements HasComponent<ListActivityComponent> {

    @Inject
    Date currentDate;

    private ListActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        System.out.println(currentDate);

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
    }

    @Override
    public ListActivityComponent getComponent() {
        return component;
    }
}
