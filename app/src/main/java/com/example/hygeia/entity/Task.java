package com.example.hygeia.entity;

//import com.amap.api.location.AMapLocation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task {
    private String title;
    // 高德地图位置

    // 当前位置 经纬度(current)
    private double curLatitude = 0.0D;
    private double curLongitude = 0.0D;

    // 目的地 经纬度(destination)
    private double dstLatitude = 0.0D;
    private double dstLongitude = 0.0D;

    // 时间格式，对于begin或end通用
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Calendar beginTime;
    Calendar endTime;

    Priority priority;
    Label label;
    String place;

    // 初始奖励设置为10，可增加可扣除
    private int credit = 10;

    // 任务描述
    private String description;
/*
    public double getCurLatitude() {
        return this.aMapLocation.getLatitude();
    }

 */

    public void setCurLatitude(double curLatitude) {
        this.curLatitude = curLatitude;
    }
/*
    public double getCurLongitude() {
        return this.aMapLocation.getLongitude();
    }


 */
    public void setTitle(String title) {
    this.title = title;
}

    public String getTitle(){return title;}

    public void setCurLongitude(double curLongitude) {
        this.curLongitude = curLongitude;
    }

    public double getDstLatitude() {
        return dstLatitude;
    }

    public void setDstLatitude(double dstLatitude) {
        this.dstLatitude = dstLatitude;
    }

    public double getDstLongitude() {
        return dstLongitude;
    }

    public void setDstLongitude(double dstLongitude) {
        this.dstLongitude = dstLongitude;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime) {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public static void main(String arg[]){
        Task task=new Task();
        task.setCredit(10);
    }
}




