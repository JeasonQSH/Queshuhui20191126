package com.bawei.queshuhui.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bawei.queshuhui.Bean;
import com.bawei.queshuhui.MyAdapter;
import com.bawei.queshuhui.NetUtil;
import com.bawei.queshuhui.R;
import com.bawei.queshuhui.SecondActivity;
import com.bawei.queshuhui.base.BaseFragment;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;
/**
 *  * @ActivityName: HomeFragment
 *  * @Description: Activity介绍
 *  * @author: 阙树辉
 *  * @date: 2019/11/26
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    int page = 1;

    private PullToRefreshListView pull;
    private RelativeLayout relativeLayout;
    List<Bean.ListdataBean> list = new ArrayList<>();

    @Override
    protected void initView(View view) {
        pull = view.findViewById(R.id.pull);//找控件
        relativeLayout = view.findViewById(R.id.rl);

        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {//设置监听
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                list.clear();
                getData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                getData();

            }
        });

        pull.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("key","https://www.thepaper.cn/newsDetail_forward_4923534");
                startActivity(intent);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        if(NetUtil.getInstance().hasNet(getContext())){
            pull.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);

            String jsonurl = "";
            if(page==1){
                jsonurl = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai1.json";
            }else if(page==2){
                jsonurl = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai2.json";
            }else{
                jsonurl = "http://blog.zhaoliang5156.cn/api/pengpainews/pengpai3.json";
            }

            NetUtil.getInstance().getJson(jsonurl, new NetUtil.MyCallBack() {
                @Override
                public void ongetString(String s) {
                    Bean bean = new Gson().fromJson(s, Bean.class);
                    List<Bean.ListdataBean> newlist = bean.getListdata();
                    list.addAll(newlist);
                    pull.onRefreshComplete();
                    MyAdapter adapter = new MyAdapter(list);
                    pull.setAdapter(adapter);
                }
            });
        }else{
            pull.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }
}
