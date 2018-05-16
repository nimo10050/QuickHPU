package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Comment;
import com.iot.quickhpu.pojo.Topic;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 评论列表适配器
 */

public class CommentAdapter extends BaseAdapter {


    private List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        LogUtils.d("小圈子 评论 填充到Adapter: " + commentList.size());
        this.commentList = commentList;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentList.get(i);
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
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_comment,viewGroup,false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvCommentUsername = ((TextView) convertView.findViewById(R.id.tv_comment_username));
            vHolder.tvCommentContent = ((TextView) convertView.findViewById(R.id.tv_comment_content));
            vHolder.tvCommentDate = ((TextView) convertView.findViewById(R.id.tv_comment_date));
            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        }else{
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvCommentUsername.setText(commentList.get(position).getUsername());
        vHolder.tvCommentContent.setText(commentList.get(position).getCommentContent());
        vHolder.tvCommentDate.setText(commentList.get(position).getCommentDate());

        return convertView;
    }

    class ViewHolder{
        TextView tvCommentUsername;
        TextView tvCommentContent;
        TextView tvCommentDate;
    }

}
