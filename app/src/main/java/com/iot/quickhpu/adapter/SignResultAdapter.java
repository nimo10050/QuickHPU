package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Book;
import com.iot.quickhpu.pojo.Sign;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 图书列表适配器
 */

public class SignResultAdapter extends BaseAdapter {


    private Sign sign;

    public SignResultAdapter(Sign sign) {
        LogUtils.d("图书 填充到Adapter, 列表长度为  " + sign.getGroup().size());
        this.sign = sign;
    }

    @Override
    public int getCount() {
        return  sign.getGroup().size();
    }

    @Override
    public Object getItem(int i) {
        return  sign.getGroup().get(i);
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
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_sign_result,viewGroup,false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvSignStudentId = ((TextView) convertView.findViewById(R.id.tv_sign_student_id));
            vHolder.tvSignStudentName = ((TextView) convertView.findViewById(R.id.tv_sign_student_name));
            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        }else{
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvSignStudentId.setText(sign.getGroup().get(position).getStudentId());
        vHolder.tvSignStudentName.setText(sign.getGroup().get(position).getUsername());


        return convertView;
    }

    class ViewHolder{
        TextView tvSignStudentId;
        TextView tvSignStudentName;
    }

}
