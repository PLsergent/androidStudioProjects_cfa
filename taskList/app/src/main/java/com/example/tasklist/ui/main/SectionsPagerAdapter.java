package com.example.tasklist.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tasklist.CalendarFragment;
import com.example.tasklist.NewTaskFragment;
import com.example.tasklist.R;
import com.example.tasklist.TaskListFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private final Context mContext;
    public static ArrayList<String> items;
    public static ArrayList<String> deadlines;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        items = new ArrayList<>();
        deadlines = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = TaskListFragment.newInstance();
                break;
            case 1:
                fragment = NewTaskFragment.newInstance();
                break;
            case 2:
                fragment = CalendarFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }

    public static String formatDate(Date date) {
        String pattern = " dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formatDate = simpleDateFormat.format(date);
        return formatDate;
    }

    public static void addItem(String taskName, Date deadline) {
        items.add(taskName);
        String date = SectionsPagerAdapter.formatDate(deadline);
        deadlines.add(date);
    }

    public static void removeItem(int position) {
        items.remove(position);
        deadlines.remove(position);
    }

    public static ArrayList getItems() {
        return items;
    }

    public static ArrayList getDeadlines() {
        return deadlines;
    }
}