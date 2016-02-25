package video;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import in.wptrafficanalyzer.actionbarnavtabswipe.R;

/**
 * Created by hlee on 2/19/16.
 */
public class VideoPage extends FragmentActivity {

    MyPageAdapter pageAdapter;


    //int NUM_PAGES = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videopage);
        System.out.println("VideoPage");


//            /** Getting a reference to the ViewPager defined the layout file */
//            ViewPager pager = (ViewPager) findViewById(R.id.pager);
//
//            /** Getting fragment manager */
//            FragmentManager fm = getSupportFragmentManager();
//
//            /** Instantiating FragmentPagerAdapter */
//            MyPagerAdapter pagerAdapter = new MyPagerAdapter(fm);
//
//            /** Setting the pagerAdapter to the pager object */
//            pager.setAdapter(pagerAdapter);

        List<Fragment> fragments = getFragments();

        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager)findViewById(R.id.pager);

        pager.setAdapter(pageAdapter);


    }

    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();
        Bundle b = getIntent().getExtras(); // Getting the Bundle object that pass from another activity
       ArrayList<String> list =  b.getStringArrayList("channels");
        //System.out.println("##############LIST   "+list);
        Log.e("###","000");
        for(int i = 0; i < list.size(); i++) {
        Log.e("###","111");
            String url = list.get(i);
            //System.out.println("##############   "+ url);
            fList.add(MyFragment.newInstance(url));
            System.out.println("##############   "+ fList);
        }

        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            getMenuInflater().inflate(R.menu.activity_main, menu);
//            return true;
//        }

}

