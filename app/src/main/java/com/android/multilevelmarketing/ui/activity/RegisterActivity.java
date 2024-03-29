package com.android.multilevelmarketing.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.android.multilevelmarketing.R;
import com.android.multilevelmarketing.ui.fragment.FragmentRegisterAvatar;
import com.android.multilevelmarketing.ui.fragment.FragmentRegisterBank;
import com.android.multilevelmarketing.ui.fragment.FragmentRegisterMain;
import com.android.multilevelmarketing.ui.fragment.FragmentRegisterShipping;
import com.android.multilevelmarketing.view.NonSwipeableViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class RegisterActivity extends BaseAnimActivity {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.view_pager)
    public NonSwipeableViewPager viewPager;

    @BindView(R.id.view_pager_indicator)
    public CircleIndicator viewPagerIndicator;

    @BindView(R.id.app_bar)
    public AppBarLayout appBarLayout;

    private static final int NUM_PAGES = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        setupViewpager();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            movePreviousPage();
    }

    private void setupViewpager() {
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentRegisterMain());
        fragments.add(new FragmentRegisterShipping());
        fragments.add(new FragmentRegisterBank());
        fragments.add(new FragmentRegisterAvatar());

        RegisterPagerAdapter adapter = new RegisterPagerAdapter(getSupportFragmentManager());
        adapter.setDataSet(fragments);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(10);
        viewPagerIndicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == fragments.size() - 1) {
                    appBarLayout.setExpanded(false, false);
                } else {
                    appBarLayout.setExpanded(true, true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public NonSwipeableViewPager getViewPager() {
        return viewPager;
    }

    public void moveNextPage() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
    }

    public void movePreviousPage() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    private class RegisterPagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> pages = null;

        public RegisterPagerAdapter setDataSet(ArrayList<Fragment> pages) {
            this.pages = pages;
            return this;
        }

        public RegisterPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public Fragment getItem(int position) {
            return pages.get(position);
        }
    }
}
