package www.nupter.org.nupter.model;

/**
 * Created by fangzhenyi on 16/4/12.
 */
public class UserInfoBean {
    private String status;

    private InfoEntity info;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public static class InfoEntity {
        private String info;
        private int isExistStuNum;
        private int isExistTrueName;
        private String isVerifyEmail;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getIsExistStuNum() {
            return isExistStuNum;
        }

        public void setIsExistStuNum(int isExistStuNum) {
            this.isExistStuNum = isExistStuNum;
        }

        public int getIsExistTrueName() {
            return isExistTrueName;
        }

        public void setIsExistTrueName(int isExistTrueName) {
            this.isExistTrueName = isExistTrueName;
        }

        public String getIsVerifyEmail() {
            return isVerifyEmail;
        }

        public void setIsVerifyEmail(String isVerifyEmail) {
            this.isVerifyEmail = isVerifyEmail;
        }
    }
}
