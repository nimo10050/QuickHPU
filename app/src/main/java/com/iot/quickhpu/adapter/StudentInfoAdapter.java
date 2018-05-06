package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Grade;
import com.iot.quickhpu.pojo.Student;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 分数列表适配器
 */

public class StudentInfoAdapter extends BaseAdapter {


    private List<Student> studentList;

    public StudentInfoAdapter(List<Student> studentList) {
        LogUtils.d("学生信息数据填充到Adapter: " + studentList.size());
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
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
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_student_info,viewGroup,false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvStudentName = ((TextView) convertView.findViewById(R.id.tv_student_name));

            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        }else{
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvStudentName.setText(studentList.get(position).getName());

        return convertView;
    }

    class ViewHolder{
        TextView tvStudentName;

    }

}
