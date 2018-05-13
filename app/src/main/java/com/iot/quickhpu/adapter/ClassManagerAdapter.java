package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Grade;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 分数列表适配器
 */

public class ClassManagerAdapter extends BaseAdapter {


    private List<String> list;

    public ClassManagerAdapter(List<String> list) {
        LogUtils.d("我的班级管理列表填充到Adapter: " + list.size());
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vHolder;

        if (convertView == null) {
            //初始化item布局
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_class, viewGroup, false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvClassName = ((TextView) convertView.findViewById(R.id.tv_class_name));

            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        } else {
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvClassName.setText(list.get(position));

        return convertView;
    }

    class ViewHolder {
        TextView tvClassName;
    }

}
