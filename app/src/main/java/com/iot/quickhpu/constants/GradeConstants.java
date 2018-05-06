package com.iot.quickhpu.constants;

import com.iot.quickhpu.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description
 */

public class GradeConstants {

    public static final int GRADE_RESULT = 1;
    public static final String[] ALL_TERM= { "大一上", "大一下", "大二上", "大二下", "大三上", "大三下", "大四上", "大四下" };
    public static final Map<String,String> TERM_MAP = new HashMap<>();
    public static final int[] TEXT_VIEW_COLORS = {R.color.google_blue,
            R.color.google_green,
            R.color.google_red,
            R.color.google_yellow,
            R.color.google_blue,
            R.color.google_green,
            R.color.google_yellow
    };

    static {
        TERM_MAP.put(ALL_TERM[0],"0");
        TERM_MAP.put(ALL_TERM[1],"1");
        TERM_MAP.put(ALL_TERM[2],"2");
        TERM_MAP.put(ALL_TERM[3],"3");
        TERM_MAP.put(ALL_TERM[4],"4");
        TERM_MAP.put(ALL_TERM[5],"5");
        TERM_MAP.put(ALL_TERM[6],"6");
    }
}
