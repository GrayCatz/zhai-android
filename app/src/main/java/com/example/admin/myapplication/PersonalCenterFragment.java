package com.example.admin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

public class PersonalCenterFragment extends Fragment {
    QMUIGroupListView mGroupListView;
    QMUITopBarLayout mTopBar;

//    QMUIRadiusImageView mRadiusImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.personal_center_fragment, container, false);
        mGroupListView = root.findViewById(R.id.groupListView);
        mTopBar = root.findViewById(R.id.top_bar);
        initTopBar();
        initGroupListView();
        return root;
    }

    private void initGroupListView() {

        QMUICommonListItemView personalProfileItem = mGroupListView.createItemView("账号资料");
        personalProfileItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView collectionItem = mGroupListView.createItemView("收藏");
        collectionItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView historyItem = mGroupListView.createItemView("浏览历史");
        historyItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        QMUICommonListItemView settingItem = mGroupListView.createItemView("设置");
        settingItem.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    Toast.makeText(getActivity(), text + " is Clicked", Toast.LENGTH_SHORT).show();
                    switch (String.valueOf(text)){
                        case "账号资料":
                            Intent intent = new Intent(getContext(),PersonalProfileActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            }
        };
//
        QMUIGroupListView.newSection(getContext())
//                .setTitle("Section 1: 默认提供的样式")
//                .setDescription("Section 1 的描述")
                .addItemView(personalProfileItem, onClickListener)
                .addItemView(collectionItem, onClickListener)
                .addItemView(historyItem, onClickListener)
                .addItemView(settingItem, onClickListener)
                .addTo(mGroupListView);

//        QMUIGroupListView.newSection(getContext())
//                .setTitle("Section 2: 自定义右侧 View")
//                .addItemView(itemWithCustom, onClickListener)
//                .addTo(mGroupListView);
    }

    
    private void initTopBar() {
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

//        mTopBar.setTitle(QDDataManager.getInstance().getName(this.getClass()));
        mTopBar.setTitle("测试");
    }

    private void popBackStack() {
        this.popBackStack();
    }
}
