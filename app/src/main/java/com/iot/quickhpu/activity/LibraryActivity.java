package com.iot.quickhpu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.iot.quickhpu.R;
import com.iot.quickhpu.adapter.BookAdapter;
import com.iot.quickhpu.callback.CommonCallback;
import com.iot.quickhpu.constants.LoginConstants;
import com.iot.quickhpu.constants.ResultConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.pojo.Book;
import com.iot.quickhpu.utils.BookJsonUtils;
import com.iot.quickhpu.utils.LogUtils;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;
import com.iot.quickhpu.utils.StringUtils;
import com.iot.quickhpu.utils.ToastUtils;

import java.util.List;

public class LibraryActivity extends BaseActivity {

    private EditText etBookname;
    private ListView lvLibrary;
    private Button btnSearch;
    private Button btnPre;
    private Button btnNext;

    private int page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        etBookname = findViewById(R.id.et_library_name);
        lvLibrary = findViewById(R.id.lv_book);
        btnSearch = findViewById(R.id.btn_library_search);
        btnPre = findViewById(R.id.btn_library_pre);
        btnNext = findViewById(R.id.btn_library_next);

        btnSearch.setOnClickListener(this);
        btnPre.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (StringUtils.isNotEmpty(etBookname.getText().toString())) {
            ToastUtils.showLong(this,"请输入书名进行查询");
            return;
        }
        switch (view.getId()) {
            case R.id.btn_library_search:
                searchBooks(1);
                break;
            case R.id.btn_library_pre:
                --page;
                page = page < 1 ? 1 : page;
                searchBooks(page);
                break;
            case R.id.btn_library_next:
                searchBooks(page++);
                break;
        }
    }

    private void searchBooks(int page) {
        String bookname = etBookname.getText().toString().trim();
        String url = URLConstants.LIBRARY_SEARCH;
        LogUtils.d("请求图书查询接口  " + url);
        String cookie = (String) SpUtils.get(this, LoginConstants.LOGIN_COOKIE, "cookie");
        OkHttpUtils.postWithForm(url, cookie, new CommonCallback(mHandler), bookname, page + "");
    }


    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == ResultConstants.RESULT_OK) {
                LogUtils.d("图书查询结果  " + msg.obj);
                List<Book> bookList = BookJsonUtils.jsonToList(msg.obj.toString());
                LogUtils.d(" 图书json数据 " + bookList.toString());
                if (bookList == null || bookList.size() == 0) {
                    ToastUtils.showLong(LibraryActivity.this, "查询为空");
                    return false;
                }
                lvLibrary.setAdapter(new BookAdapter(bookList));
            } else {
                ToastUtils.showLong(LibraryActivity.this, "请求图书查询接口失败");
            }
            return false;
        }
    });


}
