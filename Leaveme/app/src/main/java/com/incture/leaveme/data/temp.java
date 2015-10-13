package com.incture.leaveme.data;

/**
 * Created by JANANI.N on 04-09-2015.
 */
public class temp {
    public String month;
    public String web;
    public String day;
    public String event;
    public String type;
    public int image;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String link;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int position;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

   //    public temp(String mp,String web1, String day1, String event1, String type1, int image1, int flag)

    public temp(String mp,String web1, String day1, String event1, String type1, String  image1, int flag)
    {
        this.month=mp;
        this.web = web1;
        this.day=day1;
        this.event=event1;
        this.type=type1;
        this.link=image1;
        this.position=flag;
}

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
