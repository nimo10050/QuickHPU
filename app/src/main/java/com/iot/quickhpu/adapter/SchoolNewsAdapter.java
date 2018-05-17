package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Comment;
import com.iot.quickhpu.pojo.SchoolNews;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 学校新闻列表适配器
 */

public class SchoolNewsAdapter extends BaseAdapter {


    private List<SchoolNews> newsList;

    public SchoolNewsAdapter(List<SchoolNews> newsList) {
        LogUtils.d("学校新闻列表 填充到Adapter: " + newsList.size());
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int i) {
        return newsList.get(i);
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
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_school_news,viewGroup,false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvSchoolNewsTitle = ((TextView) convertView.findViewById(R.id.tv_school_title));
            vHolder.tvSchoolNewsDate = ((TextView) convertView.findViewById(R.id.tv_school_date));
            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        }else{
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvSchoolNewsTitle.setText(newsList.get(position).getName());
        vHolder.tvSchoolNewsDate.setText(newsList.get(position).getTime());

        return convertView;
    }

    class ViewHolder{
        TextView tvSchoolNewsTitle;
        TextView tvSchoolNewsDate;
    }

}
