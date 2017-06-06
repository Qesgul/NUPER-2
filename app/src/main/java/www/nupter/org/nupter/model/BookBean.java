package www.nupter.org.nupter.model;

import java.util.List;

/**
 * Created by fangzhenyi on 16/3/12.
 */
public class BookBean {

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

    public static class InfoEntity {
        private String bookurl;
        private String bookname;
        private String suoshuhao;
        private String guancang;
        private String chubanshe;
        private String isborrow;
        private String author;
        private String zongshu;

        public void setBookurl(String bookurl) {
            this.bookurl = bookurl;
        }

        public void setBookname(String bookname) {
            this.bookname = bookname;
        }

        public void setSuoshuhao(String suoshuhao) {
            this.suoshuhao = suoshuhao;
        }

        public void setGuancang(String guancang) {
            this.guancang = guancang;
        }

        public void setChubanshe(String chubanshe) {
            this.chubanshe = chubanshe;
        }

        public void setIsborrow(String isborrow) {
            this.isborrow = isborrow;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setZongshu(String zongshu) {
            this.zongshu = zongshu;
        }

        public String getBookurl() {
            return bookurl;
        }

        public String getBookname() {
            return bookname;
        }

        public String getSuoshuhao() {
            return suoshuhao;
        }

        public String getGuancang() {
            return guancang;
        }

        public String getChubanshe() {
            return chubanshe;
        }

        public String getIsborrow() {
            return isborrow;
        }

        public String getAuthor() {
            return author;
        }

        public String getZongshu() {
            return zongshu;
        }
    }
}
