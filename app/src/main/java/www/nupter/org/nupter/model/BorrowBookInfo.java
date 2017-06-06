package www.nupter.org.nupter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fangzhenyi on 16/3/24.
 */
public class BorrowBookInfo implements Serializable{

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

    public static class InfoEntity implements Serializable{
        private String cookie;
        private List<String> name;
        private List<String> date1;
        private List<String> date2;
        private List<String> barcode;
        private List<String> check;

        public String getCookie() {
            return cookie;
        }

        public void setCookie(String cookie) {
            this.cookie = cookie;
        }

        public List<String> getName() {
            return name;
        }

        public void setName(List<String> name) {
            this.name = name;
        }

        public List<String> getDate1() {
            return date1;
        }

        public void setDate1(List<String> date1) {
            this.date1 = date1;
        }

        public List<String> getDate2() {
            return date2;
        }

        public void setDate2(List<String> date2) {
            this.date2 = date2;
        }

        public List<String> getBarcode() {
            return barcode;
        }

        public void setBarcode(List<String> barcode) {
            this.barcode = barcode;
        }

        public List<String> getCheck() {
            return check;
        }

        public void setCheck(List<String> check) {
            this.check = check;
        }
    }
}
