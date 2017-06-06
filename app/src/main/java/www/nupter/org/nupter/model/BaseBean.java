package www.nupter.org.nupter.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by fangzhenyi on 15/11/15.
 */
public class BaseBean implements Serializable{

    /*
     * status : error
     * info : {"info":"参数不合法"}
     */

    private String status;
    /**
     * info : 参数不合法
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
        private String info;

        public void setInfo(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }


}
