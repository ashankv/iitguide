package com.iitguide.iitguide;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by Ashank on 8/11/2015.
 */
public class FacDirect extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    public static ArrayList<Professor> myProfessors = new ArrayList<Professor>();
    private RecyclerView mRecyclerView;
    private RVAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fac_direct);

        myProfessors.add(new Professor("Dr. P. Bahadur - Physical Chemistry", "Author of Numerical Chemistry", R.drawable.bahadur));
        myProfessors.add(new Professor("Prof. D.C. Pandey - Physics", "Author of Mechanics 1 and 2", R.drawable.pandey));
        myProfessors.add(new Professor("Dr. V. Sharan - Organic and Inorganic Chemistry", "Author of JEE Essentials - Organic and Inorganic", R.drawable.sharan));
        myProfessors.add(new Professor("Prof. H.K. Arjaria - Physics", "Author of Together With IIT-JEE Physics", R.drawable.arjaria));
        myProfessors.add(new Professor("Dr. J.P. Arya - Mathematics", "Ph. D. IIT", R.drawable.arya));



        mToolbar=(Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        mDrawer = (NavigationView) findViewById(R.id.main_drawer);
    //    mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setScrimColor(Color.parseColor("#33000000"));
        mDrawerToggle.syncState();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapter = new RVAdapter(myProfessors);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(FacDirect.this));


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent = null;
        if(menuItem.getItemId() == R.id.faculty_directory){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        if(menuItem.getItemId() == R.id.student_portal){
            Intent facintent = new Intent(FacDirect.this, CourseActivity.class);
            startActivity(facintent);
        }

        if(menuItem.getItemId() == R.id.logout){
            Intent logIntent = new Intent(FacDirect.this, MainActivity.class);
            VimeoHelper.completeCoursesArray.clear();
            VimeoHelper.justCourseName.clear();
            startActivity(logIntent);

        }

        if(menuItem.getItemId() == R.id.question_answer){
            Intent facintent = new Intent(FacDirect.this, QuestionAnswer.class);
            startActivity(facintent);
        }


        return false;
    }
}
