package com.symedia.stepalarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.symedia.stepalarm.Fragments.StepsFragment;
import com.symedia.stepalarm.Fragments.TasksFragment;
import com.symedia.stepalarm.Fragments.Time.AlarmFragment;
import com.symedia.stepalarm.Fragments.Time.StopwatchFragment;
import com.symedia.stepalarm.Fragments.Time.TimerFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottom;
    FloatingActionButton add_fab;
    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottom = findViewById(R.id.bottom_nav_view);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerFragmentAdapter(this));
        bottom.setSelectedItemId(R.id.bar_alarm);
        viewPager.setCurrentItem(2);

        add_fab = findViewById(R.id.add_fab);

        Toolbar toolbar = findViewById(R.id.menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bottom.getSelectedItemId() == R.id.bar_alarm){
                    startActivity(new Intent(MainActivity.this, AddAlarmActivity.class));
                }else if (bottom.getSelectedItemId() == R.id.bar_goals){
                    startActivity(new Intent(MainActivity.this, AddTasksActivity.class));
                }
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int pos) {
                switch (pos) {
                    case 0:
                        bottom.setSelectedItemId(R.id.bar_steps);
                        break;
                    case 1:
                        bottom.setSelectedItemId(R.id.bar_timer);
                        break;
                    case 2:
                        bottom.setSelectedItemId(R.id.bar_alarm);
                        break;
                    case 3:
                        bottom.setSelectedItemId(R.id.bar_stopwatch);
                        break;
                    case 4:
                        bottom.setSelectedItemId(R.id.bar_goals);
                        break;
                }
            }
        });
        bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bar_steps) {
                    viewPager.setCurrentItem(0);
                } else if (id == R.id.bar_timer) {
                    viewPager.setCurrentItem(1);
                } else if (id == R.id.bar_alarm) {
                    viewPager.setCurrentItem(2);
                } else if (id == R.id.bar_stopwatch) {
                    viewPager.setCurrentItem(3);
                } else if (id == R.id.bar_goals) {
                    viewPager.setCurrentItem(4);
                }

                if(id == R.id.bar_alarm || id == R.id.bar_goals){
                    add_fab.setVisibility(View.VISIBLE);
                }else{
                    add_fab.setVisibility(View.GONE);
                }

                return true;
            }
        });
    }

    private static class ViewPagerFragmentAdapter extends FragmentStateAdapter {
        public ViewPagerFragmentAdapter(@NonNull AppCompatActivity activity) {
            super(activity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment frag = new Fragment();
            switch (position) {
                case 0:
                    frag = new StepsFragment();
                    break;
                case 1:
                    frag = new TimerFragment();
                    break;
                case 2:
                    frag = new AlarmFragment();
                    break;
                case 3:
                    frag = new StopwatchFragment();
                    break;
                case 4:
                    frag = new TasksFragment();
                    break;
            }
            return frag;
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_user){
            startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
        }else if (id == R.id.menu_settings){
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        return true;
    }
}