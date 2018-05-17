package com.iot.quickhpu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.pojo.Book;
import com.iot.quickhpu.utils.LogUtils;

import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description 图书列表适配器
 */

public class BookAdapter extends BaseAdapter {


    private List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        LogUtils.d("图书 填充到Adapter, 列表长度为  " + bookList.size());
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int i) {
        return bookList.get(i);
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
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view_library,viewGroup,false);

            //创建记事本类
            vHolder = new ViewHolder();

            //查找控件，保存控件的引用
            vHolder.tvBookName = ((TextView) convertView.findViewById(R.id.tv_book_name));
            vHolder.tvBookAuth = ((TextView) convertView.findViewById(R.id.tv_book_author));
            vHolder.tvBookDate = ((TextView) convertView.findViewById(R.id.tv_book_date));
            vHolder.tvBookPublish = ((TextView) convertView.findViewById(R.id.tv_book_publish));
            //将当前viewHolder与converView绑定
            convertView.setTag(vHolder);
        }else{
            //如果不为空，获取
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.tvBookName.setText(bookList.get(position).getName());
        vHolder.tvBookAuth.setText(bookList.get(position).getAuth()+" 著");
        vHolder.tvBookDate.setText(bookList.get(position).getDate() +" 年");
        vHolder.tvBookPublish.setText(bookList.get(position).getPubilsh());

        return convertView;
    }

    class ViewHolder{
        TextView tvBookName;
        TextView tvBookAuth;
        TextView tvBookDate;
        TextView tvBookPublish;
    }

}
