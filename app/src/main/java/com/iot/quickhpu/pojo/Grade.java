package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author m1563
 * @Date 2018/3/1
 * @Description
 */

public class Grade implements Serializable {

    private String name;//学科名 [2]
    private String credits;//学分[4]
    private String max;//最高分[6]
    private String avg;//平均分 [8]
    private String total;//我的分数[9]
    private String range;//排名 [10]

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getMax() {
        return max == null || "".equals(max) ? "- -" : max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getAvg() {
        return avg == null || "".equals(avg) ? "- -" : avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getTotal() {
        return total == null || "".equals(total) ? "- -" : total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRange() {
        return range == null || "".equals(range) ? "- -" : range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
