package com.iot.quickhpu;

import com.iot.quickhpu.callback.EmptyClassroomListCallback;
import com.iot.quickhpu.callback.GradeCallback;
import com.iot.quickhpu.callback.LoginCallbackTest;
import com.iot.quickhpu.constants.GradeConstants;
import com.iot.quickhpu.constants.URLConstants;
import com.iot.quickhpu.utils.OkHttpUtils;
import com.iot.quickhpu.utils.SpUtils;

import org.junit.Test;

import okhttp3.Callback;
import okhttp3.Request;

/**
 * @作者 m1563
 * @日期 2018/2/27
 * @描述 http连接测试
 */
public class HttpTest {

    @Test
    public void login() {
        //String url = "http://39.106.201.41/hpu/index.php?p=front&c=Login&a=API_landing&key=0e252616229cf190376bf954c8e7ade7";
        OkHttpUtils.login(URLConstants.LOGIN_URL, new LoginCallbackTest(), "311409080228", "689693",
                "ac2cc46631027bd54c56bcd61f0e1565dd344757c8ccd805a1e300ff1f7d820fb4f8d5db0d2b761d0e1ed28d580fa2d69d92658c3d73a428aa67c50683499976bc53cd8a6d7d11e5516f4b517e3d08ef2ebb5e2d08603353839abaaa065f954f6550bfd48c6d1470c7cb918d15ca89b04db8a0829deb2531d1c00a88e427f1a7");
    }

    @Test
    public void course() {
        String url = "http://39.106.201.41/hpu/index.php?p=front&c=Score&a=API_getCurScore&key=0e252616229cf190376bf954c8e7ade7";
        OkHttpUtils.getByCookie(url);
    }

    @Test
    public void testScore() {
        OkHttpUtils.getByCookie(URLConstants.FINAL_GRADE);
    }


    @Test
    public void testEmptyClassroom() {
        OkHttpUtils.getByCookie(URLConstants.EMPTY_CLASSROOM + "&building=1");
        System.out.println(URLConstants.EMPTY_CLASSROOM + "&building=1");
    }


}
