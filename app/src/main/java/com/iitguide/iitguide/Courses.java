package com.iitguide.iitguide;

import java.util.ArrayList;

/**
 * Created by shalsaxe on 12/28/2015.
 */
public class Courses {
    String courseId;
    String courseName;
    String courseStatus;
    ArrayList<Videos> chemVideos = new ArrayList<Videos>();
    ArrayList<Videos> mathVideos = new ArrayList<Videos>();
    ArrayList<Videos> physicsVideos = new ArrayList<Videos>();

    public Courses(String courseId, String courseName, String courseStatus, ArrayList<Videos> chemVideos, ArrayList<Videos> mathVideos, ArrayList<Videos> physicsVideos){


        this.courseId = courseId;
        this.courseName = courseName;
        this.courseStatus = courseStatus;
        this.chemVideos = chemVideos;
        this.physicsVideos = physicsVideos;
        this.mathVideos = mathVideos;

    }
}
