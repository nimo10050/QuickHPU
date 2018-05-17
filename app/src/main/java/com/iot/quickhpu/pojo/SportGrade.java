package com.iot.quickhpu.pojo;

import java.io.Serializable;

/**
 * @Author m1563
 * @Date 2018/5/17
 * @Description
 */

public class SportGrade implements Serializable {


    private String km;
    private String fiftym;
    private String fwl;
    private String height;
    private String ldty;
    private String total;
    private String weight;
    private String year;
    private String ytxs;
    private String zetqq;


    public SportGrade() {
    }

    public SportGrade(String km, String fiftym, String fwl, String height, String ldty, String total, String weight, String year, String ytxs, String zetqq) {
        this.km = km;
        this.fiftym = fiftym;
        this.fwl = fwl;
        this.height = height;
        this.ldty = ldty;
        this.total = total;
        this.weight = weight;
        this.year = year;
        this.ytxs = ytxs;
        this.zetqq = zetqq;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getFiftym() {
        return fiftym;
    }

    public void setFiftym(String fiftym) {
        this.fiftym = fiftym;
    }

    public String getFwl() {
        return fwl;
    }

    public void setFwl(String fwl) {
        this.fwl = fwl;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLdty() {
        return ldty;
    }

    public void setLdty(String ldty) {
        this.ldty = ldty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYtxs() {
        return ytxs;
    }

    public void setYtxs(String ytxs) {
        this.ytxs = ytxs;
    }

    public String getZetqq() {
        return zetqq;
    }

    public void setZetqq(String zetqq) {
        this.zetqq = zetqq;
    }
}
