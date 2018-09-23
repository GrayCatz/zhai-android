package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;

    @BindView(R.id.pager)
    ViewPager mViewPager;

    Map<Pager, Fragment> mPages;
    FragmentManager fragmentManager;

    PagerAdapter adapter = new PagerAdapter() {

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Fragment fragment = mPages.get(Pager.getPagerFromPositon(position));
            if (!fragment.isAdded()) { // 如果fragment还没有added
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.add(fragment, fragment.getClass().getSimpleName());
                ft.commit();
                /**
                 * 在用FragmentTransaction.commit()方法提交FragmentTransaction对象后
                 * 会在进程的主线程中，用异步的方式来执行。
                 * 如果想要立即执行这个等待中的操作，就要调用这个方法（只能在主线程中调用）。
                 * 要注意的是，所有的回调和相关的行为都会在这个调用中被执行完成，因此要仔细确认这个方法的调用位置。
                 */
                fragmentManager.executePendingTransactions();
            }


            if (fragment.getView().getParent() == null) {
                container.addView(fragment.getView()); // 为viewpager增加布局
            }
            return fragment.getView();
        }

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mPages.get(Pager.getPagerFromPositon(position)).getView()); // 移出viewpager两边之外的page布局
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mTabSegment = findViewById(R.id.tabs);
//        mViewPager = findViewById(R.id.pager);


        QMUITabSegment.Tab component = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_tabbar_component),
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_tabbar_component_selected),
                "社区", false
        );

        QMUITabSegment.Tab util = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_tabbar_util),
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_tabbar_util_selected),
                "动态", false
        );
        QMUITabSegment.Tab lab = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_tabbar_lab),
                ContextCompat.getDrawable(getApplicationContext(), R.mipmap.icon_tabbar_lab_selected),
                "个人中心", false
        );
        mTabSegment.addTab(component)
                .addTab(util)
                .addTab(lab);
        mTabSegment.notifyDataChanged();
        fragmentManager = getSupportFragmentManager();
        initPagers();

        mTabSegment.addOnTabSelectedListener(new QMUITabSegment.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int index) {
                Log.i("sexxx", String.valueOf(index));
                mViewPager.setCurrentItem(index);
            }

            @Override
            public void onTabUnselected(int index) {

            }

            @Override
            public void onTabReselected(int index) {

            }

            @Override
            public void onDoubleTap(int index) {

            }
        });
    }

    private void initPagers() {
        mPages = new HashMap<>();
        mPages.put(Pager.FORUM, new ForumFragment());
        mPages.put(Pager.DYNAMIC, new ForumFragment());
        mPages.put(Pager.PERSONAL_CENTER, new PersonalCenterFragment());

        mViewPager.setAdapter(adapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
    }

    enum Pager {
        FORUM, DYNAMIC, PERSONAL_CENTER;

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return FORUM;
                case 1:
                    return DYNAMIC;
                case 2:
                    return PERSONAL_CENTER;
                default:
                    return PERSONAL_CENTER;
            }
        }
    }
}
