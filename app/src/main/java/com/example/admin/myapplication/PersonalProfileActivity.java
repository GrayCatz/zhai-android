package com.example.admin.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalProfileActivity extends Activity {

    @BindView(R.id.groupListView2)
    QMUIGroupListView mGroupListView;
    @BindView(R.id.top_bar)
    QMUITopBarLayout mTopBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_profile_activity);
        ButterKnife.bind(this);
        /*用户名 唯一
关联手机号 唯一
关联邮箱
出生日期
性别
签名
头像*/
        //用户名
        QMUICommonListItemView item1 = mGroupListView.createItemView("账号资料");
        item1.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        item1.setDetailText("叶志坚");

        //手机号
        QMUICommonListItemView item2 = mGroupListView.createItemView("手机号");
        item2.setAccessoryType(QMUICommonListItemView.HORIZONTAL);
        item2.setDetailText("17858766909");

        //邮箱
        QMUICommonListItemView item3 = mGroupListView.createItemView("邮箱");
        item3.setAccessoryType(QMUICommonListItemView.HORIZONTAL);
        item3.setDetailText("1103038194@qq.com");

        //出生日期
        QMUICommonListItemView item4 = mGroupListView.createItemView("出生日期");
        item4.setAccessoryType(QMUICommonListItemView.HORIZONTAL);
        item4.setDetailText("1103038194@qq.com");


        //性别
        QMUICommonListItemView item5 = mGroupListView.createItemView("性别");
        item5.setAccessoryType(QMUICommonListItemView.HORIZONTAL);
        item5.setDetailText("1103038194@qq.com");

        //签名
        QMUICommonListItemView item6 = mGroupListView.createItemView("签名");
        item6.setAccessoryType(QMUICommonListItemView.HORIZONTAL);
        item6.setDetailText("1103038194@qq.com");

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    CharSequence text = ((QMUICommonListItemView) v).getText();
//                    Toast.makeText(getActivity(), text + " is Clicked", Toast.LENGTH_SHORT).show();
                    switch (String.valueOf(text)){
                        case "账号资料":
                            break;
                    }
                }
            }
        };
//
        QMUIGroupListView.newSection(getBaseContext())
//                .setTitle("Section 1: 默认提供的样式")
//                .setDescription("Section 1 的描述")
                .addItemView(item1, onClickListener)
                .addItemView(item2, onClickListener)
                .addItemView(item3, onClickListener)
                .addItemView(item4, onClickListener)
                .addItemView(item5, onClickListener)
                .addItemView(item6, onClickListener)
                .addTo(mGroupListView);

        initTopBar();
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
        popBackStack();
    }

}
