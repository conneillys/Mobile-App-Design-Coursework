/* Adapted from tutorial at github.com/codepath/android_guides/wiki/Fragment-Navigation-Drawer */
/* Adaptations: some different variable names I liked better and used while I was following the
    tutorial, didn't feel like I needed to include anything from transparency onward, used my own
    fragments instead of the example fragments
 */

package com.example.mealtrackingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navDrawer;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        navDrawer = (NavigationView) findViewById(R.id.navView);

        setupDrawerContent(navDrawer);

        FragmentManager fragManager = getSupportFragmentManager();
        fragManager.beginTransaction().replace(R.id.frameContent, HomeFragment.newInstance("", "")).commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupDrawerContent(NavigationView navView) {
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        selectDrawerItem(item);
                        return true;
                    }
                }
        );
    }

    public void selectDrawerItem(MenuItem item) {
        Fragment frag = null;
        Class fragClass;
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragClass = HomeFragment.class;
                break;
            case R.id.nav_meals:
                fragClass = MealsFragment.class;
                break;
            case R.id.nav_calories:
                fragClass = CaloriesFragment.class;
                break;
            case R.id.nav_nutrition:
                fragClass = NutritionFragment.class;
                break;
            case R.id.nav_weight:
                fragClass = WeightFragment.class;
                break;
            default:
                fragClass = HomeFragment.class;
        }

        try {
            frag = (Fragment) fragClass.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragManager = getSupportFragmentManager();
        fragManager.beginTransaction().replace(R.id.frameContent, frag).commit();

        item.setChecked(true);
        setTitle(item.getTitle());
        drawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}