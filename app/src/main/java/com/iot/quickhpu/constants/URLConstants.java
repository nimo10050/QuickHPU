package com.iot.quickhpu.constants;

/**
 * @Author m1563
 * @Date 2018/2/28
 * @Description URL接口常量
 */

public class URLConstants {

    private static final String KEY = "0e252616229cf190376bf954c8e7ade7";
    private static final String BASE_URL = "http://39.106.201.41/hpu/index.php";

    // 登录接口
    public static final String LOGIN_URL = BASE_URL + "?p=front&c=Login&a=API_landing&key=" + KEY;

    // 本学期成绩接口
    // http://39.106.201.41/hpu/index.php?p=front&c=Score&a=API_getCurScore&key=0e252616229cf190376bf954c8e7ade7
    public static final String FINAL_GRADE = BASE_URL + "?p=front&c=Score&a=API_getCurScore&key=" + KEY;

    // 历年成绩接口
    public static final String ALL_GRADE = BASE_URL + "?p=front&c=Score&a=API_getEverScore&key=" + KEY;

    // 学生信息接口
    public static final String STUDENT_INFO = BASE_URL + "?p=front&c=Info&a=API_getPersonalInfo&key=" + KEY;

    // 空教室接口
    public static final String EMPTY_CLASSROOM = BASE_URL + "?p=front&c=Classroom&a=API_getBuildingEmpty&key=" + KEY;

    // 本地服务器地址
    public static final String LOCAL_URL = "http://192.168.31.216:8081/";

    // 班级管理
    public static final String CLASS_MANAGER_URL = LOCAL_URL + "class/";

    // 学生管理
    public static final String STUDENT_MANAGER_URL = LOCAL_URL + "user/";


}
