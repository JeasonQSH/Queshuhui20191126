package com.bawei.queshuhui.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.queshuhui.R;
import com.bawei.queshuhui.base.BaseFragment;
/**
 *  * @ActivityName: OtherFragment
 *  * @Description: Activity介绍
 *  * @author: 阙树辉
 *  * @date: 2019/11/26
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class OtherFragment extends BaseFragment {


    private TextView textView;

    @Override
    protected void initView(View view) {
        textView = view.findViewById(R.id.t);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_other;
    }

    @Override
    protected void initData() {
        String key = getArguments().getString("key");
        textView.setText(key);
    }

    public static OtherFragment getInstance(String value) {
        Bundle bundle = new Bundle();
        bundle.putString("key",value);
        OtherFragment otherFragment = new OtherFragment();
        otherFragment.setArguments(bundle);
        return otherFragment;
    }
}
