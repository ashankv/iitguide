package com.iitguide.iitguide;

        import android.content.Context;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.graphics.drawable.Drawable;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageButton;
        import android.widget.ImageView;

/**
 * Created by Ashank on 8/16/2015.
 */
public class ChemAdapter extends BaseAdapter {


    private Context context;
    // private int[] images;
    LayoutInflater inflater;
    ImageButton myChemImage;


    public ChemAdapter(Context context){
        this.context = context;
        //   this.images = images;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        // TODO Auto-generated method stub

    /*    int count = 0;
        int i = 0;
        for (Videos tempVid : VimeoHelper.completeCoursesArray.get(0).videosArrayForACourse){

            if(ifBelongsToTab(i, tempVid)) {
                count++;
            }

            i++;
        }

        return count; //Returns the number of images that need to be displayed on the grid. */

        String tempSubject = ChemGridActivity.mGenericAdapter.getPageTitle(ChemGridActivity.mTabLayout.getSelectedTabPosition()).toString().toLowerCase();

        if(tempSubject.equals("chemistry")){

            return VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).chemVideos.size();

        }

        else if(tempSubject.equals("physics")){
            return VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).physicsVideos.size();
        }

        else if(tempSubject.equals("mathematics")){
            return VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).mathVideos.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int i) {
        // TODO Auto-generated method stub
        //String tempItemYearKey = ChemGridActivity.mChemAdapter.getPageTitle(ChemGridActivity.mChemTabLayout.getSelectedTabPosition()).toString();
        //return VimeoHelper.chemLinkedHashMap.get(tempItemYearKey).get(i);
        String tempSubject = ChemGridActivity.mGenericAdapter.getPageTitle(ChemGridActivity.mTabLayout.getSelectedTabPosition()).toString();

        if(tempSubject.equals("chemistry")){

            return VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).chemVideos.get(i);
        }

        else if(tempSubject.equals("physics")){
            return VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).physicsVideos.get(i);
        }

        else if(tempSubject.equals("mathematics")){
            return VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).mathVideos.get(i);
        }

        return null;

    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    class ViewHolder{

        /*    ImageButton myChemImage;
            public ViewHolder(View v){

                myChemImage = (ImageButton) v.findViewById(R.id.myChemImage);
            }
        */
        ImageView myChemImage;
        public ViewHolder(View v){

            myChemImage = (ImageView) v.findViewById(R.id.myChemImage);
        }
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        String tempSubject = ChemGridActivity.mGenericAdapter.getPageTitle(ChemGridActivity.mTabLayout.getSelectedTabPosition()).toString().toLowerCase();
        ViewHolder holder = null;

        if (view == null) {
            view = inflater.inflate(R.layout.chem_single_item, viewGroup, false);

            holder = new ViewHolder(view);
            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }

        //    String tempYearKey = ChemGridActivity.mGenericAdapter.getPageTitle(ChemGridActivity.mGenericTabLayout.getSelectedTabPosition()).toString();

        //    holder.myChemImage.setImageResource(VimeoHelper.chemLinkedHashMap.get(tempYearKey).get(i).imageId);
        //setImageDrawable

    //    if(ifBelongsToTab(i, VimeoHelper.completeCoursesArray.get(0).videosArrayForACourse.get(i))) {  //IF AND ONLY IF THE TAB SELECTED  TITLE IS WITHIN THE VIDEO, THEN EXECUTE THE CODE

            Drawable imageDrawable = null;
            boolean fileFound = true;
            try {
                Resources r = context.getResources();

                if(tempSubject.equals("chemistry")) {

                    imageDrawable = r.getDrawable(r.getIdentifier(VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).chemVideos.get(i).flattenedImageName, "drawable", context.getPackageName()));
                    System.out.println("I is " + i + " Chapter Name is " + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).chemVideos.get(i).chapterName + ";      Image Name is " + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).chemVideos.get(i).flattenedImageName);


                }
                else if(tempSubject.equals("physics")) {
                    imageDrawable = r.getDrawable(r.getIdentifier(VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).physicsVideos.get(i).flattenedImageName, "drawable", context.getPackageName()));
                    System.out.println("I is " + i + " Chapter Name is " + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).physicsVideos.get(i).chapterName + ";      Image Name is " + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).physicsVideos.get(i).flattenedImageName);
                }
                else if(tempSubject.equals("mathematics")){
                    imageDrawable = r.getDrawable(r.getIdentifier(VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).mathVideos.get(i).flattenedImageName, "drawable", context.getPackageName()));
                    System.out.println("I is " + i + " Chapter Name is " + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).mathVideos.get(i).chapterName + ";      Image Name is " + VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).mathVideos.get(i).flattenedImageName);
                }
            } catch (Exception e) {
                fileFound = false;
            }

            System.out.println("FILEFOUND" + fileFound);


            if (fileFound) {
                holder.myChemImage.setImageDrawable(imageDrawable);
            } else {
                try {
                    if(tempSubject.equals("chemistry")) {
                        holder.myChemImage.setImageDrawable(VimeoHelper.drawableFromUrl(VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).chemVideos.get(i).imageUrl, "IITGuide"));
                    }

                    else if(tempSubject.equals("physics"))
                        holder.myChemImage.setImageDrawable(VimeoHelper.drawableFromUrl(VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).physicsVideos.get(i).imageUrl, "IITGuide"));

                    else if(tempSubject.equals("mathematics"))
                        holder.myChemImage.setImageDrawable(VimeoHelper.drawableFromUrl(VimeoHelper.completeCoursesArray.get(ChemGridActivity.receivedCourseIndex).mathVideos.get(i).imageUrl, "IITGuide"));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        return view;

    }
}

