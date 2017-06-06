package www.nupter.org.nupter.model;

/**
 * Created by fangzhenyi on 16/2/15.
 */
public class CookieBean {

    /**
     * status : success
     * info : {"cookie_name":"/home/fang/nupter/temp/cookieZhRVE6","viewstate":"dDwyODE2NTM0OTg7Oz79z5AHAuDo0MguZFxd3+SSDF3LCg=="}
     */

    private String status;
    /**
     * cookie_name : /home/fang/nupter/temp/cookieZhRVE6
     * viewstate : dDwyODE2NTM0OTg7Oz79z5AHAuDo0MguZFxd3+SSDF3LCg==
     */

    private InfoEntity info;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setInfo(InfoEntity info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public InfoEntity getInfo() {
        return info;
    }

    public static class InfoEntity {
        private String cookie_name;
        private String viewstate;

        public void setCookie_name(String cookie_name) {
            this.cookie_name = cookie_name;
        }

        public void setViewstate(String viewstate) {
            this.viewstate = viewstate;
        }

        public String getCookie_name() {
            return cookie_name;
        }

        public String getViewstate() {
            return viewstate;
        }
    }
}
