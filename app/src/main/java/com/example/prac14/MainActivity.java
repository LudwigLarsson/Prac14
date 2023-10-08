package com.example.prac14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,  R.string.navigation_drawer_open,  R.string.navigation_drawer_open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        bottomNav = findViewById(R.id.bottomNavigationView);
        drawer.bringToFront();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        item.setChecked(true);
                        drawer.closeDrawers();

                        int id = item.getItemId();
                        item.setChecked(true);
                        drawer.closeDrawers();

                        Log.d("drawer", "drawer");
                        if (id == R.id.first) {
                            setFragment(new FirstFragment());
                            item.setChecked(true);
                        } else if (id == R.id.second) {
                            setFragment(new SecondFragment());
                            item.setChecked(true);
                        } else if (id == R.id.third) {
                            setFragment(new ThirddFragment());
                            item.setChecked(true);
                        }

                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        return true;
                    }
                });

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();


                if (id == R.id.first) {
                    setFragment(new FirstFragment());
                    item.setChecked(true); //с помощью этого метода связывает фрагменты с navigation bar
                }
                else if (id == R.id.second) {
                    setFragment(new SecondFragment());
                    item.setChecked(true);
                }
                else if (id == R.id.third) {
                    setFragment(new ThirddFragment());
                    item.setChecked(true);
                }
                return false;
            }
        });
        FirstFragment firstFragment = new FirstFragment();
        setFragment(firstFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_drawer) {
            drawer.openDrawer(Gravity.LEFT);
        }
        else if (id == R.id.setting_item) Toast.makeText(this, "cvh", Toast.LENGTH_SHORT).show();
        else if (id == R.id.first_item) setFragment(new FirstFragment());
        else if (id == R.id.second_time) setFragment(new SecondFragment());


        return super.onOptionsItemSelected(item);
    }

    private void setFragment(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }
}