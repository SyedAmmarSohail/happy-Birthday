package com.example.viewpagercircle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*import com.viewpagerindicator.IconPagerAdapter;*/

class TestFragmentAdapter extends FragmentPagerAdapter {
    protected static final String[] CONTENT = new String[] { "This", "Is", "A", "Test", "yes"};
    protected static final int[] ICONS = new int[] {
            R.drawable.perm_group_calendar,
            R.drawable.perm_group_camera,
            R.drawable.perm_group_device_alarms,
            R.drawable.perm_group_location
    };
    protected static final int[] wallpaper = new int[] {
    	R.layout.wallpaper1,
    	R.layout.wallpaper2,
    	R.layout.getstarted
    	
       /* R.drawable.friends,
        R.drawable.happyfamily*/
        
        
        
};

    private int mCount = wallpaper.length;

    public TestFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       /* return TestFragment.newInstance(CONTENT[position % CONTENT.length]);*/
        return TestFragment.newInstance(wallpaper[position % wallpaper.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    public int getBackground(int b_i) {
        return wallpaper[b_i % wallpaper.length];
    }
    
   
    
    @Override
    public CharSequence getPageTitle(int position) {
      return TestFragmentAdapter.CONTENT[position % CONTENT.length];
    }

   /* @Override
    public int getIconResId(int index) {
      return ICONS[index % ICONS.length];
    }*/

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}