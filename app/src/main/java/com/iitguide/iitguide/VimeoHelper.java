package com.iitguide.iitguide;

import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.LinkedHashMap;

/**
 * Created by Ashank on 8/24/2015.
 */
public class VimeoHelper {

    public static final String INVALID_LOGIN = "Invalid Username or Password";
    public static final String BLANK_LOGIN_ENTRY = "One Or More Fields Blank";
    public static final String CONNECTION_MISSING = "Internet Connection Unavailable";
    public static ArrayList<Courses> completeCoursesArray = new ArrayList<Courses>();
    public static ArrayList<String> justCourseName = new ArrayList<String>();

    //oauth code to get the external url from vimeo - start
    public static String getExternalUrl(String privateUrl) {
        String tokenType = "bearer";
        String token = "769aa0dd5a1cb53a432e6bf0a2c23794";


        try {

            //    URL url = new URL("https://api.vimeo.com/videos/126028010");
            URL url = new URL(privateUrl);
            URLConnection yc = url.openConnection();
            yc.setRequestProperty("Accept", "application/vnd.vimeo.*+json; version=3.2");
            yc.setRequestProperty("Authorization", new StringBuffer(tokenType).append(" ").append(token).toString());

            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            StringBuffer jsonString = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                jsonString.append(inputLine);
            }
            in.close();

            JSONObject reader = new JSONObject(jsonString.toString());
            JSONArray filesArray = reader.getJSONArray("files");
            JSONObject filesElement = filesArray.getJSONObject(1);
            String externalURL = filesElement.getString("link");

            System.out.println(externalURL);

            return externalURL;

        } catch (Exception e) {
            System.out.println("------------------------");
            System.out.println("e.getMessage()");
            System.out.println("------------------------");
            e.printStackTrace();
        }

        return null;
    }

    //oauth code to get the external url from vimeo - end
    //Creating video object json from iitguide.com - start
    public static void processAuthJsonResponse(StringBuffer myNewJsonResponse) throws Exception {
        if (completeCoursesArray != null)
            completeCoursesArray.clear();
        if(justCourseName != null)
            justCourseName.clear();

        JSONObject reader = new JSONObject(myNewJsonResponse.toString());
        if (reader.getString("authenticationStatus").equals("Failure") || reader.getString("authenticationStatus").equals("failure") || reader.getString("authenticationStatus").equals("null")) {
            Exception e = new Exception(INVALID_LOGIN);

            throw e;
        }

        JSONObject courseStat = reader.getJSONObject("courseStatus");
        Iterator<String> keys = courseStat.keys();


        while (keys.hasNext()) {
            String tempKey = keys.next();
            //tempKeys now stores all_main_2016_1:<Course Name>:Paid

            String tempValue = (String) courseStat.get(tempKey);
            //tempValue now stores the complete blob of chaptername and videoIds

            Log.d("RESPONSE", tempValue);


            if (tempKey.contains("Paid") || tempKey.contains("paid")) {

                String courseId = tempKey.substring(0, tempKey.indexOf(":"));

                tempKey = tempKey.substring(tempKey.indexOf(":")+1);
                String courseName = tempKey.substring(0, tempKey.indexOf(":"));
                justCourseName.add(courseName);
                tempKey = tempKey.substring(tempKey.indexOf(":")+1);
                String courseStatus = tempKey;

                ArrayList<Videos> chemVideos = new ArrayList<Videos>();
                ArrayList<Videos> mathVideos = new ArrayList<Videos>();
                ArrayList<Videos> physicsVideos = new ArrayList<Videos>();

                //Converting the blob of chaptername and videoId to keyValMap - Start
                String tempJsonString = tempValue;
                tempJsonString = tempJsonString.substring(1, tempJsonString.length() - 1);
                String[] keyValuePairs = tempJsonString.split(",");
                LinkedHashMap<String, String> keyValMap = new LinkedHashMap<String, String>();
                for (String pair : keyValuePairs) {
                    String[] entry = pair.split("=");
                    keyValMap.put(entry[0].trim(), entry[1].trim());
                }
                //Converting the blob of chaptername and videoId to keyValMap - end

                for (String key : keyValMap.keySet()) {


                    String videoId = keyValMap.get(key);
                    String chapterName = key;


                    String imageUrl = "http://iitguide.com/StudentLogin/images/" + courseId + "/" + chapterName + ".png";
                    String flattenedImageName = courseId + "_" + chapterName;

                    //subjectName should be mathematics, chemistry or physics
                    String subjectName = chapterName.substring(0, chapterName.indexOf("_"));

                    Videos tempVid = new Videos(videoId, chapterName, imageUrl, flattenedImageName, subjectName);
                    //adding videoobject to Array List which ultimately will be ArrayListFor Courses


                    if (tempVid.chapterName.contains("chemistry")) {
                        chemVideos.add(tempVid);
                    } else if (tempVid.chapterName.contains("math")) {
                        mathVideos.add(tempVid);
                    } else if (tempVid.chapterName.contains("physics")) {
                        physicsVideos.add(tempVid);
                    }

                }

                for(Videos video : chemVideos){
                    Log.d("Chapter Name", "Chapter Name");
                    System.out.println(video.chapterName);
                }

                for(Videos video : physicsVideos){
                    Log.d("Chapter Name", "Chapter Name");
                    System.out.println(video.chapterName);
                }

                for(Videos video : mathVideos){
                    Log.d("Chapter Name", "Chapter Name");
                    System.out.println(video.chapterName);
                }

                Courses tempCourses = new Courses(courseId, courseName, courseStatus, chemVideos, mathVideos, physicsVideos);
                completeCoursesArray.add(tempCourses);
            }

        //    System.out.println("XXX"+completeCoursesArray);
        //    System.out.println("PHYSICS VIDZ" + VimeoHelper.completeCoursesArray.get(0).physicsVideos.size());
        //    System.out.println("MATH VIDZ" + VimeoHelper.completeCoursesArray.get(0).mathVideos.size());
        //    System.out.println("CHEM VIDZ" + VimeoHelper.completeCoursesArray.get(0).chemVideos.size());

                Log.d("Shalabh", "Hurrah");

        }

    }
    //Creating video object json from iitguide.com - end


    public static Drawable drawableFromUrl(String url, String srcName) throws java.net.MalformedURLException, java.io.IOException {
        return Drawable.createFromStream(((java.io.InputStream) new java.net.URL(url).getContent()), srcName);
    }
}
