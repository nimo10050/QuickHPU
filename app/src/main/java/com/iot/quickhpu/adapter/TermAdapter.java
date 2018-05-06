package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.constants.GradeConstants;
import com.iot.quickhpu.pojo.Grade;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 分数列表适配器
 */

public class TermAdapter extends BaseAdapter {


    private List<List<Grade>>  gradeAllList;

    public TermAdapter(List<List<Grade>>  gradeAllList) {
        this.gradeAllList = gradeAllList;
    }

    @Override
    public int getCount() {
        return gradeAllList.size();
    }

    @Override
    public Object getItem(int i) {
        return gradeAllList.get(i);
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
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_term,viewGroup,false);
            //创建记事本类
            vHolder = new ViewHolder();
            //查找控件，保存控件的引用
            vHolder.tvTerm = ((TextView) convertView.findViewById(R.id.tv_term));
            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        }else{
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvTerm.setText(GradeConstants.ALL_TERM[position]);

        return convertView;
    }

    class ViewHolder{
        TextView tvTerm;
    }

}
