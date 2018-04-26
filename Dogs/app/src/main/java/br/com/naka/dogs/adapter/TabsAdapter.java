package br.com.naka.dogs.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import br.com.naka.dogs.R;
import br.com.naka.dogs.fragment.HoudListFragment;
import br.com.naka.dogs.fragment.HuskyListFragment;
import br.com.naka.dogs.fragment.LabradorListFragment;
import br.com.naka.dogs.fragment.PugListFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<Fragment> fragments=new ArrayList<>();

    public TabsAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    public TabsAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.tabs_adapter_str_txt_husky);
        } else if (position == 1){
            return (CharSequence) context.getString(R.string.tabs_adapter_str_txt_hound);
        } else if (position == 2){
            return (CharSequence) context.getString(R.string.tabs_adapter_str_txt_pug);
        }else{
            return (CharSequence) context.getString(R.string.tabs_adapter_str_txt_labrador);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        if (position == 0){
            f = HuskyListFragment.newInstance(R.string.tabs_adapter_str_txt_husky);
        } else if (position == 1){
            f = HoudListFragment.newInstance(R.string.tabs_adapter_str_txt_hound);
        } else if (position == 2){
            f = PugListFragment.newInstance(R.string.tabs_adapter_str_txt_pug);
        } else if (position == 3){
            f = LabradorListFragment.newInstance(R.string.tabs_adapter_str_txt_labrador);
        }

        return f;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
