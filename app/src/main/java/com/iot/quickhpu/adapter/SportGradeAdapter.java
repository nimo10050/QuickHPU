package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.SportGrade;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 体测适配器
 */

public class SportGradeAdapter extends BaseAdapter {


    private List<SportGrade> sportGradeList;

    public SportGradeAdapter(List<SportGrade> sportGradeList) {
        LogUtils.d("体测成绩 填充到Adapter, 列表长度为  " + sportGradeList.size());
        this.sportGradeList = sportGradeList;
    }

    @Override
    public int getCount() {
        return sportGradeList.size();
    }

    @Override
    public Object getItem(int i) {
        return sportGradeList.get(i);
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
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_sport_grade, viewGroup, false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvSportGradeYear = ((TextView) convertView.findViewById(R.id.tv_sport_year));
            vHolder.tvSportGradeHeight = ((TextView) convertView.findViewById(R.id.tv_sport_height));
            vHolder.tvSportGradeWeight = ((TextView) convertView.findViewById(R.id.tv_sport_weight));
            vHolder.tvSportGradeKm = ((TextView) convertView.findViewById(R.id.tv_sport_1000m));
            vHolder.tvSportGradefiftyM = ((TextView) convertView.findViewById(R.id.tv_sport_50m));
            vHolder.tvSportGradeYtxs = ((TextView) convertView.findViewById(R.id.tv_sport_ytxs));
            vHolder.tvSportGradeZwtqq = ((TextView) convertView.findViewById(R.id.tv_sport_zwtqq));
            vHolder.tvSportGradeFhl = ((TextView) convertView.findViewById(R.id.tv_sport_fwl));
            vHolder.tvSportGradeLdty = ((TextView) convertView.findViewById(R.id.tv_sport_ldty));
            vHolder.tvSportGradeTotal = ((TextView) convertView.findViewById(R.id.tv_sport_total));
            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        } else {
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvSportGradeYear.setText(sportGradeList.get(position).getYear());
        vHolder.tvSportGradeHeight.setText("身高：" + sportGradeList.get(position).getHeight());
        vHolder.tvSportGradeWeight.setText("体重：" + sportGradeList.get(position).getYear() + " 年");
        vHolder.tvSportGradeKm.setText("800/1000m长跑：" + sportGradeList.get(position).getKm());
        vHolder.tvSportGradefiftyM.setText("50m短跑： " + sportGradeList.get(position).getFiftym());
        vHolder.tvSportGradeYtxs.setText("仰卧起坐/引体向上：" + sportGradeList.get(position).getYtxs());
        vHolder.tvSportGradeZwtqq.setText("坐位体前屈：" + sportGradeList.get(position).getZetqq());
        vHolder.tvSportGradeFhl.setText("肺活量： " + sportGradeList.get(position).getFwl());
        vHolder.tvSportGradeLdty.setText("立定跳远：" + sportGradeList.get(position).getLdty());
        vHolder.tvSportGradeTotal.setText("总成绩：" + sportGradeList.get(position).getTotal());

        return convertView;
    }

    class ViewHolder {
        TextView tvSportGradeYear;
        TextView tvSportGradeHeight;
        TextView tvSportGradeWeight;
        TextView tvSportGradeKm;
        TextView tvSportGradefiftyM;
        TextView tvSportGradeYtxs;
        TextView tvSportGradeZwtqq;
        TextView tvSportGradeFhl;
        TextView tvSportGradeLdty;
        TextView tvSportGradeTotal;

    }

}
