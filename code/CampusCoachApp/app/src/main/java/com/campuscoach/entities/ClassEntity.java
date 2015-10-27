package com.campuscoach.entities;

/*本类为训练班界面显示的条目信息
 *包括运动类型、教练名字、教练图片
 */
public class ClassEntity {
    int id;
    int imgId;
    String sport;
    String time;
    String place;
    String price;


    public ClassEntity(String sport, String time, String place, String price, int imgId){
        this.sport = sport;
        this.time = time;
        this.place = place;
        this.price = price;
        this.imgId = imgId;
    }
    public int getId() {
        return id;
    }
    public int getImgId() {
        return imgId;
    }

    public String getSport() {
        return sport;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getPrice() {
        return price;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setId(int id) { this.id = id; }
}
