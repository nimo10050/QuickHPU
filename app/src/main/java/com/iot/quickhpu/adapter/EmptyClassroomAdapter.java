package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.EmptyClassroom;
import com.iot.quickhpu.pojo.Grade;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 分数列表适配器
 */

public class EmptyClassroomAdapter extends BaseAdapter {


    private List<EmptyClassroom> emptyClassroomList;

    public EmptyClassroomAdapter(List<EmptyClassroom> emptyClassroomList) {
        LogUtils.d("空教室数据填充到Adapter: " + emptyClassroomList.size());
        this.emptyClassroomList = emptyClassroomList;
    }

    @Override
    public int getCount() {
        return emptyClassroomList.size();
    }

    @Override
    public Object getItem(int i) {
        return emptyClassroomList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vHolder;

        if(convertView==null){
            //初始化item布局
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_empty_classroom,viewGroup,false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvClassNumber = ((TextView) convertView.findViewById(R.id.tv_class_number));
            vHolder.tvEmptyClassroomNumber = ((TextView) convertView.findViewById(R.id.tv_empty_classroom_number));

            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        }else{
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvClassNumber.setText(emptyClassroomList.get(position).getClassNumber());
        vHolder.tvEmptyClassroomNumber.setText(emptyClassroomList.get(position).getEmptyClassroomNumber());

        return convertView;
    }

    class ViewHolder{
        TextView tvClassNumber;
        TextView tvEmptyClassroomNumber;
    }

}
