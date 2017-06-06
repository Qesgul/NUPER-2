package www.nupter.org.nupter.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingyipeng on 15/11/19.
 */
public class LostBean implements Parcelable {


    private String status;
    private List<InfoEntity> info;

    LostBean(Parcel in) {
        this.status = in.readString();
        this.info = new ArrayList<InfoEntity>();
        in.readTypedList(this.info, InfoEntity.CREATOR);

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InfoEntity> getInfo() {
        return info;
    }

    public void setInfo(List<InfoEntity> info) {
        this.info = info;
    }

    public static class InfoEntity implements Parcelable {
        private String uuid;
        private String userName;
        private String avatar_url;
        private String sex;
        private String lostId;
        private String description;
        private String image_url1;
        private String image_url2;
        private String image_url3;
        private String name;
        private String phone;
        private String site;
        private String time;
        private String imgNum;
        private String createTime;

        public InfoEntity(Parcel in) {
            this.uuid = in.readString();
            this.userName = in.readString();
            this.avatar_url = in.readString();
            this.sex = in.readString();
            this.lostId = in.readString();
            this.description = in.readString();
            this.image_url1 = in.readString();
            this.image_url2 = in.readString();
            this.image_url3 = in.readString();
            this.name = in.readString();
            this.phone = in.readString();
            this.site = in.readString();
            this.time = in.readString();
            this.imgNum = in.readString();
            this.createTime = in.readString();
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getLostId() {
            return lostId;
        }

        public void setLostId(String lostId) {
            this.lostId = lostId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage_url1() {
            return image_url1;
        }

        public void setImage_url1(String image_url1) {
            this.image_url1 = image_url1;
        }

        public String getImage_url2() {
            return image_url2;
        }

        public void setImage_url2(String image_url2) {
            this.image_url2 = image_url2;
        }

        public String getImage_url3() {
            return image_url3;
        }

        public void setImage_url3(String image_url3) {
            this.image_url3 = image_url3;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getImgNum() {
            return imgNum;
        }

        public void setImgNum(String imgNum) {
            this.imgNum = imgNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {

            parcel.writeString(uuid);
            parcel.writeString(userName);
            parcel.writeString(avatar_url);
            parcel.writeString(sex);
            parcel.writeString(lostId);
            parcel.writeString(description);
            parcel.writeString(image_url1);
            parcel.writeString(image_url2);
            parcel.writeString(image_url3);
            parcel.writeString(name);
            parcel.writeString(phone);
            parcel.writeString(site);
            parcel.writeString(time);
            parcel.writeString(imgNum);
            parcel.writeString(createTime);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        static final Parcelable.Creator<InfoEntity> CREATOR = new Creator<InfoEntity>() {
            @Override
            public InfoEntity createFromParcel(Parcel parcel) {
                return new InfoEntity(parcel);
            }

            @Override
            public InfoEntity[] newArray(int size) {
                return new InfoEntity[size];
            }
        };

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(i);
    }

    static final Parcelable.Creator<LostBean> CREATOR = new Creator<LostBean>() {
        @Override
        public LostBean createFromParcel(Parcel parcel) {
            return new LostBean(parcel);
        }

        @Override
        public LostBean[] newArray(int size) {
            return new LostBean[size];
        }
    };
}
