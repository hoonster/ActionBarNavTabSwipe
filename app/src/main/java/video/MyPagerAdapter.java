package video;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.wptrafficanalyzer.actionbarnavtabswipe.AndroidFragment;

/**
 * Created by hlee on 2/23/16.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    int PAGE_COUNT = 5;

    /** Constructor of the class */
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /** This method will be invoked when a page is requested to create */
    @Override
    public Fragment getItem(int position) {
        MyFragment myFragment = new MyFragment();
        Bundle data = new Bundle();
        data.putInt("current_page", position+1);
        myFragment.setArguments(data);
        return myFragment;

    }

    /** Returns the number of pages */
    @Override
    public int getCount() {
        System.out.println("MyPagerAdapter");
        AndroidFragment af = new AndroidFragment();
        String urllist = af.line;
        //ArrayList<String> al = new ArrayList<String>(af.lines);
        //System.out.println("$$$$ line $$$$" + urllist);
        //int len = al.size();
        //PAGE_COUNT = len;
        return PAGE_COUNT;
    }
}
