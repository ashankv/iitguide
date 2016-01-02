package com.iitguide.iitguide;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Ashank on 8/10/2015.
 */
public class CourseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Button btnChemVid;
    private Button btnMathVid;
    private Button btnPhysicsVid;
    private Button chemAssignmentBtn;
    private LinearLayout btnLinLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);

        btnChemVid = (Button) findViewById(R.id.btnChemVid);
        btnLinLayout = (LinearLayout) findViewById(R.id.btnLinLayout);
        btnChemVid.setClickable(false);
    /*    btnChemVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseActivity.this, ChemGridActivity.class);
                startActivity(intent);
            }
        }); */

        int courseSize = VimeoHelper.completeCoursesArray.size();
        Button[] myButtons = new Button[courseSize];
        int i = 0;
        int x = 20;


        for(Button tempButton : myButtons){

            tempButton = new Button(this);
            final String tempCourseName = VimeoHelper.completeCoursesArray.get(i).courseName;
            System.out.println(tempCourseName);
            tempButton.setText(tempCourseName);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(0, x, 0, 0);
            tempButton.setWidth(200);

            btnLinLayout.addView(tempButton, params);
            x+=10;

            final int tempI = i;

            tempButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CourseActivity.this, ChemGridActivity.class);
                    intent.putExtra("COURSE_NAME", tempCourseName);
                    intent.putExtra("COURSE_INDEX", tempI);
                    startActivity(intent);
                }
            });

            i++;
        }

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent = null;
        if(menuItem.getItemId() == R.id.student_portal){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        if(menuItem.getItemId() == R.id.faculty_directory){
            Intent facintent = new Intent(CourseActivity.this, FacDirect.class);
            startActivity(facintent);
        }

        if(menuItem.getItemId() == R.id.logout){
            Intent logIntent = new Intent(CourseActivity.this, MainActivity.class);
            VimeoHelper.completeCoursesArray.clear();
            VimeoHelper.justCourseName.clear();
            startActivity(logIntent);

        }

        if(menuItem.getItemId() == R.id.question_answer){
            Intent facintent = new Intent(CourseActivity.this, QuestionAnswer.class);
            startActivity(facintent);
        }

        if(menuItem.getItemId() == R.id.logout){
            Intent facintent = new Intent(CourseActivity.this, QuestionAnswer.class);
            startActivity(facintent);
        }


        return false;
    }
}
