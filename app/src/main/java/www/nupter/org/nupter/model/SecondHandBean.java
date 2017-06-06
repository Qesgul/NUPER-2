package www.nupter.org.nupter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fangzhenyi on 15/11/18.
 */
public class SecondHandBean {



    private String status;


    private List<InfoEntity> info;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public static class InfoEntity implements Serializable {
        private String userName;
        private String avatar_url;
        private String openId;
        private String platform;
        private String createTime;
        private String updateTime;
        private String trueName;
        private String studentNum;
        private String id;
        private String title;
        private String tag;
        private String description;
        private String ifPrice;
        private String ifNegotiable;
        private String price;
        private String phoneNum;
        private String wechatOrQq;
        private String image_url1;
        private String image_url2;
        private String image_url3;
        private String isShow;

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public void setStudentNum(String studentNum) {
            this.studentNum = studentNum;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setIfPrice(String ifPrice) {
            this.ifPrice = ifPrice;
        }

        public void setIfNegotiable(String ifNegotiable) {
            this.ifNegotiable = ifNegotiable;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public void setWechatOrQq(String wechatOrQq) {
            this.wechatOrQq = wechatOrQq;
        }

        public void setImage_url1(String image_url1) {
            this.image_url1 = image_url1;
        }

        public void setImage_url2(String image_url2) {
            this.image_url2 = image_url2;
        }

        public void setImage_url3(String image_url3) {
            this.image_url3 = image_url3;
        }

        public void setIsShow(String isShow) {
            this.isShow = isShow;
        }

        public String getUserName() {
            return userName;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public String getOpenId() {
            return openId;
        }

        public String getPlatform() {
            return platform;
        }

        public String getCreateTime() {
            return createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public String getTrueName() {
            return trueName;
        }

        public String getStudentNum() {
            return studentNum;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getTag() {
            return tag;
        }

        public String getDescription() {
            return description;
        }

        public String getIfPrice() {
            return ifPrice;
        }

        public String getIfNegotiable() {
            return ifNegotiable;
        }

        public String getPrice() {
            return price;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public String getWechatOrQq() {
            return wechatOrQq;
        }

        public String getImage_url1() {
            return image_url1;
        }

        public String getImage_url2() {
            return image_url2;
        }

        public String getImage_url3() {
            return image_url3;
        }

        public String getIsShow() {
            return isShow;
        }
    }
}
