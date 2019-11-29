package com.bawei.queshuhui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bawei.queshuhui.base.BaseActivity;
import com.bawei.queshuhui.fragment.HomeFragment;
import com.bawei.queshuhui.fragment.OtherFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *  * @ActivityName: MainActivity
 *  * @Description: Activity介绍
 *  * @author: 阙树辉
 *  * @date: 2019/11/26
 */
public class MainActivity extends BaseActivity {

    private ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeFragment homeFragment;
    private OtherFragment otherFragment;
    private OtherFragment otherFragment1;

    List<Fragment>list = new ArrayList<>();//集合
    List<String>title = new ArrayList<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.image);//找控件
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.vp);

        homeFragment = new HomeFragment();
        otherFragment = OtherFragment.getInstance("要闻");
        otherFragment1 = OtherFragment.getInstance("推荐");

        list.add(homeFragment);//添加到集合
        list.add(otherFragment);
        list.add(otherFragment1);

        title.add("时事");
        title.add("要闻");
        title.add("推荐");

        imageView.setOnClickListener(new View.OnClickListener() {//点击事件
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri data1 = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data1);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
