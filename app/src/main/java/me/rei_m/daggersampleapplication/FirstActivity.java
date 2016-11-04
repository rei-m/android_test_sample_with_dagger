package me.rei_m.daggersampleapplication;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Date;

import javax.inject.Inject;

public class FirstActivity extends BaseActivity {

    @Inject
    Date currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_first);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        System.out.println(currentDate);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content, FirstFragment.newInstance(), FirstFragment.TAG)
                    .commit();
        }
    }
}
