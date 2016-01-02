package com.iitguide.iitguide;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Ashank on 8/16/2015.
 */
public class ChemGridActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{  //, AdapterView.OnItemClickListener  {

    private Toolbar mToolbar;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CoordinatorLayout mCoordinator;
    private ViewPager mPager;
    public static GenericPagerAdapter mGenericAdapter;
    public static TabLayout mTabLayout;
    public static String receivedCourseName;
    public static int receivedCourseIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chem_grid);

        Bundle extras = getIntent().getExtras();
        receivedCourseName = extras.getString("COURSE_NAME");
        receivedCourseIndex = extras.getInt("COURSE_INDEX");

        mCoordinator = (CoordinatorLayout) findViewById(R.id.chem_root_coordinator);

        mToolbar=(Toolbar) findViewById(R.id.chem_app_bar);

        mToolbar.setTitle(VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).courseName);
        setSupportActionBar(mToolbar);



        mDrawer = (NavigationView) findViewById(R.id.chem_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.chem_drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);


        mDrawer.setNavigationItemSelectedListener(this);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setScrimColor(Color.parseColor("#33000000"));
        mDrawerToggle.syncState();

        //Toolbar Stuff

        mTabLayout = (TabLayout) findViewById(R.id.chem_tab_layout);
        mGenericAdapter = new GenericPagerAdapter(getSupportFragmentManager());
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(mGenericAdapter);

        //Notice how the Tab Layout links with the Pager Adapter
        mTabLayout.setTabsFromPagerAdapter(mGenericAdapter);

        //Notice how The Tab Layout and View Pager object are linked
        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout){

            @Override
            public void onPageSelected(int position) {
                mGenericAdapter.notifyDataSetChanged();
            }

        });





        //GridView Stuff

        //myChemGridView = (GridView) findViewById(R.id.myChemGridView);

        //myChemGridView.setAdapter(new ChemAdapter(this));
        //myChemGridView.setOnItemClickListener(this);                // DONT FORGET TO ADD THIS LINE WHEN IMPLEMENTING IN PHYSICS/MATH

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
            Intent menuIntent =  new Intent(ChemGridActivity.this,  CourseActivity.class);
            startActivity(menuIntent);
            return true;
        }

        if(menuItem.getItemId() == R.id.logout){
            Intent logIntent = new Intent(ChemGridActivity.this, MainActivity.class);
            VimeoHelper.completeCoursesArray.clear();
            VimeoHelper.justCourseName.clear();
            startActivity(logIntent);

        }

        return false;
    }

/*    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String myPrivateUrl  = "https://api.vimeo.com/videos/" + VimeoHelper.chemVideosArrayList.get(position).videoId.toString();

        Intent intent = new Intent(this, VimeoActivity.class);
        intent.putExtra("PRIV_URL", myPrivateUrl);
        startActivity(intent);
    } */

    public static class MyFragment extends Fragment {


        private static final String KEY_DISPLAY_TYPE = "KEY_DISPLAY_TYPE";

        public static final int POSITION_SOMETHINHG = 11111;

        public MyFragment(){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.chem_grid_fragment, container, false);

            GridView myChemGridView = (GridView) rootView.findViewById(R.id.myChemGridView);

            myChemGridView.setAdapter(new ChemAdapter(getActivity().getApplicationContext()));

            myChemGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    /*String tempVimeoYearKey = ChemGridActivity.mChemAdapter.getPageTitle(ChemGridActivity.mChemTabLayout.getSelectedTabPosition()).toString();

                    String myPrivateUrl  = "https://api.vimeo.com/videos/" + VimeoHelper.chemLinkedHashMap.get(tempVimeoYearKey).get(position).videoId;

                    Intent intent = new Intent(getActivity(), VimeoActivity.class);
                    intent.putExtra("PRIV_URL", myPrivateUrl);
                    startActivity(intent); */

                    String tempSubject = ChemGridActivity.mGenericAdapter.getPageTitle(ChemGridActivity.mTabLayout.getSelectedTabPosition()).toString().toLowerCase();
                    String myPrivateUrl;
                    Intent intent = new Intent(getActivity(), VimeoActivity.class);
                    if(tempSubject.equals("chemistry")){
                        myPrivateUrl = "https://api.vimeo.com/videos/" + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).chemVideos.get(position).videoId;
                        intent.putExtra("PRIV_URL", myPrivateUrl);
                        startActivity(intent);
                    }

                    if(tempSubject.equals("physics")){
                        myPrivateUrl = "https://api.vimeo.com/videos/" + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).physicsVideos.get(position).videoId;
                        intent.putExtra("PRIV_URL", myPrivateUrl);
                        startActivity(intent);
                    }

                    if(tempSubject.equals("mathematics")){
                        myPrivateUrl = "https://api.vimeo.com/videos/" + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).mathVideos.get(position).videoId;
                        intent.putExtra("PRIV_URL", myPrivateUrl);
                        startActivity(intent);
                    }


                }
            });
            return rootView;

        }

        public static MyFragment newInstance(int display) {
            MyFragment myFragment = new MyFragment();
        //    Bundle bundle = new Bundle();
        //    bundle.addExtra(KEY_DISPLAY_TYPE, display);
        //    myFragment.setArguments(bundle);
            return myFragment;
        }


    }
}

class GenericPagerAdapter extends FragmentStatePagerAdapter {

    public GenericPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ChemGridActivity.MyFragment myFragment = new ChemGridActivity.MyFragment();
        return myFragment;
    }

    @Override
    public int getCount() {
        return 3; //returns number of tabs that need to be created
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) return "Chemistry";
        if (position == 1) return "Mathematics";
        if (position == 2) return "Physics";

        else return null;
    }

    @Override    //ADDED TO DEBUG REFRESHING ISSUE
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
