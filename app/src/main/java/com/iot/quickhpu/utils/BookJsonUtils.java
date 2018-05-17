package com.iot.quickhpu.utils;

import com.iot.quickhpu.pojo.Book;
import com.iot.quickhpu.pojo.Grade;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author m1563
 * @Date 2018/3/3
 * @Description 图书转化为集合
 */

public class BookJsonUtils {

    public static List<Book> jsonToList(String json) {
        List<Book> bookList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONArray bookInfo = array.getJSONArray(i);
                Book book = new Book();

                book.setName(bookInfo.getString(0));
                book.setAuth(bookInfo.getString(1));
                book.setPubilsh(bookInfo.getString(2));
                book.setDate(bookInfo.getString(3));

                bookList.add(book);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bookList;
    }


}
