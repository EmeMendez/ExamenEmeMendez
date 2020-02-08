package com.example.emelinda.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.emelinda.Controller.BasicInfo;
import com.example.emelinda.Controller.DetailInfo;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int num;


    public FragmentAdapter(FragmentManager fm, int num) {
        super(fm);
        this.num=num;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 : return new BasicInfo();
            case 1 : return new DetailInfo();
        }
        return null;

    }

    @Override
    public int getCount() {

        return num;
    }

    @Override
    public CharSequence getPageTitle(int i) {
        switch (i){
            case 0 : return "INFO-BÁSICA";
            case 1 : return "INFO-DETALLADA";
        }
        return null;        }


}
