package com.iot.quickhpu.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iot.quickhpu.R;

/**
 * Created by m1563 on 2018/2/21.
 */

public class MainGridViewAdapter extends BaseAdapter {

    private Context mContext;
    public MainGridViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return gridViewItemTitles.length;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_main_gridview_item, viewGroup, false);
            TextView textView = convertView.findViewById(R.id.main_gridview_item_title);
            ImageView imageView = convertView.findViewById(R.id.main_gridview_item_image);
            textView.setText(gridViewItemTitles[position]);
            imageView.setImageResource(gridViewItemImages[position]);
        }
        return convertView;
    }
    private Integer[] gridViewItemImages = {
            R.drawable.ic_main_grideview_item_grade, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background
    };

    private int[] gridViewItemTitles = {
            R.string.main_gridview_title_grade,R.string.main_gridview_title_classroom,
            R.string.main_gridview_title_calender,R.string.main_gridview_title_lesson,
            R.string.main_gridview_title_manager,R.string.main_gridview_title_pingjiao,
            R.string.main_gridview_title_sign,R.string.main_gridview_title_sport,
            R.string.main_gridview_title_test
    };

}
