package br.com.naka.dogs.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.naka.dogs.R;
import br.com.naka.dogs.adapter.TabsAdapter;
import livroandroid.lib.utils.Prefs;

public class DogListActivity extends AppCompatActivity {

    private Context context;
    private DogListActivity activity;
    private DrawerLayout ll;
    private FragmentManager fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);

        context = this;
        setUpToolbar();
        setupViewPagerTabs();

    }

    protected void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.include_toolbar_btn_voltar);
            //toolbar.setBackgroundColor(getResources().getColor(R.color.RGB2f3336));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private void setupViewPagerTabs() {

        final ViewPager viewPager = (ViewPager) findViewById(R.id.activity_dog_list_viewPager);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new TabsAdapter(context, getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.activity_dog_list_tabLayout);

        tabLayout.setupWithViewPager(viewPager);
        int cor = ContextCompat.getColor(context, R.color.white);

        tabLayout.setTabTextColors(cor, cor);

        int tabIdx = Prefs.getInteger(context, "tabIdx");
        viewPager.setCurrentItem(tabIdx);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                Prefs.setInteger(context, "tabIdx", viewPager.getCurrentItem());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
