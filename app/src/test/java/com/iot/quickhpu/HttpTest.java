package com.iot.quickhpu;

import com.iot.quickhpu.utils.OkHttpUtils;

import org.junit.Test;

/**
 * Created by m1563 on 2018/2/27.
 */

public class HttpTest {

    @Test
    public void login(){
        String url = "http://39.106.201.41/hpu/index.php?p=front&c=Login&a=API_landing&key=0e252616229cf190376bf954c8e7ade7&stu_id=311409080228&vpn_password=35cad53b24d2bfd4eb4e1c933815cc861db35b1d7e5edba307a3342527f85ab7c610370f8d2411cb19fe9d5507a4cef5bf8a9c401a369367115bf360a82fa5dab8b9cac37b279ab9a99caab8bc745f17cbb247cc0b22a2db1fd62854cff6b6b9e7ee438b3b330de32d810310b6625cea2619bfe3de869ad25ea5df8dfd2e2c0d&jw_password=689693";
        String body = OkHttpUtils.getData(url);
    }

    @Test
    public void course(){
        String url = "http://39.106.201.41/hpu/index.php?p=front&c=Score&a=API_getCurScore&key=0e252616229cf190376bf954c8e7ade7";
        OkHttpUtils.getByCookie(url);
    }





}
