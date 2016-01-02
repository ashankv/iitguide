package com.iitguide.iitguide;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by Ashank on 12/31/2015.
 */
public class QuestionAnswer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frequent_questions);

        mToolbar=(Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setScrimColor(Color.parseColor("#33000000"));
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent = null;
        if (menuItem.getItemId() == R.id.student_portal) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        if (menuItem.getItemId() == R.id.faculty_directory) {
            Intent facintent = new Intent(QuestionAnswer.this, FacDirect.class);
            startActivity(facintent);
        }

        if (menuItem.getItemId() == R.id.logout) {
            Intent logIntent = new Intent(QuestionAnswer.this, MainActivity.class);
            VimeoHelper.completeCoursesArray.clear();
            VimeoHelper.justCourseName.clear();
            startActivity(logIntent);

        }

        if (menuItem.getItemId() == R.id.question_answer) {
            Intent facintent = new Intent(QuestionAnswer.this, QuestionAnswer.class);
            startActivity(facintent);
        }

        if (menuItem.getItemId() == R.id.logout) {
            Intent facintent = new Intent(QuestionAnswer.this, QuestionAnswer.class);
            startActivity(facintent);
        }

        return false;
    }
}
