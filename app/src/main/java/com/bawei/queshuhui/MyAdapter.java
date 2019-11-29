package com.bawei.queshuhui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author 阙树辉
 * @description:
 * @date :2019/11/26 14:42
 */
public class MyAdapter extends BaseAdapter {
    private List<Bean.ListdataBean> list;

    public MyAdapter(List<Bean.ListdataBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getItemType();
        if(type==1){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view==null){
            if(getItemViewType(i)==0){
                view = View.inflate(viewGroup.getContext(),R.layout.item1,null);
            }else{
                view = View.inflate(viewGroup.getContext(),R.layout.item2,null);
            }
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.img);
            viewHolder.textView1 = view.findViewById(R.id.tv1);
            viewHolder.textView2 = view.findViewById(R.id.tv2);
            viewHolder.textView3 = view.findViewById(R.id.tv3);
            viewHolder.textView4 = view.findViewById(R.id.tv4);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Bean.ListdataBean listdataBean = list.get(i);
        viewHolder.textView1.setText(listdataBean.getTitle());
        viewHolder.textView2.setText(listdataBean.getContent());
        viewHolder.textView3.setText(listdataBean.getType());
        viewHolder.textView4.setText(listdataBean.getPublishedAt());
        NetUtil.getInstance().getPhoto(listdataBean.getImageurl(),viewHolder.imageView);
        return view;
    }
    class ViewHolder{
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
    }
}
