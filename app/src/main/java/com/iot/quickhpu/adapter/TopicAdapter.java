package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Topic;
import com.iot.quickhpu.pojo.Topic;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 分数列表适配器
 */

public class TopicAdapter extends BaseAdapter {


    private List<Topic> topicList;

    public TopicAdapter(List<Topic> topicList) {
        LogUtils.d("小圈子 话题圈 填充到Adapter: " + topicList.size());
        this.topicList = topicList;
    }

    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public Object getItem(int i) {
        return topicList.get(i);
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
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_topic,viewGroup,false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvUsername = ((TextView) convertView.findViewById(R.id.tv_username));
            vHolder.tvContent = ((TextView) convertView.findViewById(R.id.tv_content));
            vHolder.tvDate = ((TextView) convertView.findViewById(R.id.tv_date));
            vHolder.tvTitle = ((TextView) convertView.findViewById(R.id.tv_title));
            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        }else{
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvUsername.setText(topicList.get(position).getUsername());
        vHolder.tvContent.setText(topicList.get(position).getContent());
        vHolder.tvDate.setText(topicList.get(position).getDate());
        vHolder.tvTitle.setText(topicList.get(position).getTitle());

        return convertView;
    }

    class ViewHolder{
        TextView tvUsername;
        TextView tvContent;
        TextView tvDate;
        TextView tvTitle;
    }

}
