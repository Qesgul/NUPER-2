package www.nupter.org.nupter.model;

/**
 * Created by dingyipeng on 15/11/19.
 */
public class UserBean {
    String userName;
    String platForm;
    String openId;
    String avatar_url;
    String uuid;
    int isExistStuNum;
    int isExistTrueName;
    int isVerifyEmail;

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public String getOpenId() {
        return openId;
    }

    public String getUuid(){
        return uuid;
    }

    public void setUuid(String uuid){
        this.uuid=uuid;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public int getIsExistStuNum() {
        return isExistStuNum;
    }

    public int getIsExistTrueName() {
        return isExistTrueName;
    }

    public int getIsVerifyEmail() {
        return isVerifyEmail;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setIsExistStuNum(int isExistStuNum) {
        this.isExistStuNum = isExistStuNum;
    }

    public void setIsExistTrueName(int isExistTrueName) {
        this.isExistTrueName = isExistTrueName;
    }

    public void setIsVerifyEmail(int isVerifyEmail) {
        this.isVerifyEmail = isVerifyEmail;
    }
}
