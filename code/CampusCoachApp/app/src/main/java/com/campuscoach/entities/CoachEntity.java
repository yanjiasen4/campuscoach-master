package com.campuscoach.entities;

/*本类为预约教练界面显示的条目信息
 *包括运动类型、教练名字、教练图片
 */
public class CoachEntity {
    int imgId;
    String imgUrl;
    String coachName;
    String sport;

    public CoachEntity(String sport, String coachName, String imgUrl){
        this.sport = sport;
        this.coachName = coachName;
        this.imgUrl = imgUrl;
    }

    public int getImgId() {
        return imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getCoachName() {
        return coachName;
    }
    public String getSport() {
        return sport;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public void setSport(String sport) {
        this.sport = sport;
    }
    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }
}
