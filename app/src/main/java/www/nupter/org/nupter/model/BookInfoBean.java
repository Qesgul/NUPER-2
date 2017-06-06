package www.nupter.org.nupter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fangzhenyi on 16/3/19.
 */
public class BookInfoBean implements Serializable{

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
        private String tiwenpizhu;
        private String douban;
        private String urltu;
        private List<String> suoshuhao;
        private List<String> guancangdi;
        private List<String> zhuangtai;

        public String getTiwenpizhu() {
            return tiwenpizhu;
        }

        public void setTiwenpizhu(String tiwenpizhu) {
            this.tiwenpizhu = tiwenpizhu;
        }

        public String getDouban() {
            return douban;
        }

        public void setDouban(String douban) {
            this.douban = douban;
        }

        public String getUrltu() {
            return urltu;
        }

        public void setUrltu(String urltu) {
            this.urltu = urltu;
        }

        public List<String> getSuoshuhao() {
            return suoshuhao;
        }

        public void setSuoshuhao(List<String> suoshuhao) {
            this.suoshuhao = suoshuhao;
        }

        public List<String> getGuancangdi() {
            return guancangdi;
        }

        public void setGuancangdi(List<String> guancangdi) {
            this.guancangdi = guancangdi;
        }

        public List<String> getZhuangtai() {
            return zhuangtai;
        }

        public void setZhuangtai(List<String> zhuangtai) {
            this.zhuangtai = zhuangtai;
        }
    }
}
