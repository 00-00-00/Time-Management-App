package com.incture.leaveme.data;

/**
 * Created by Mohammed on 10/8/2015.
 */
public class LeaveHistoryData {

    String type;
    String desc;
    String numdays;
    String head;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public LeaveHistoryData(String month,String date,String t,String d,String n, String head,String st){
        this.month=month;
        this.date=date;
        this.type=t;
        this.desc=d;
        this.numdays=n;
        this.head=head;
        this.status=st;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String month;
    String date;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumdays() {
        return numdays;
    }

    public void setNumdays(String numdays) {
        this.numdays = numdays;
    }


}
