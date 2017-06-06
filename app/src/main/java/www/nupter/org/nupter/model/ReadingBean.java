package www.nupter.org.nupter.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fangzhenyi on 15/11/11.
 */
public class ReadingBean implements Serializable{
    private String status;
    private List<InfoEntity> info;

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

    public static class InfoEntity implements Serializable{
        private String id;
        private String title;
        private String author;
        private String author_img;
        private String tag;
        private String des;
        private String editorValue;
        private String img_url1;
        private String img_url2;
        private String img_url3;
        private String img_num;
        private String ifReprint;
        private String initial_url;
        private String ifPush;
        private String createTime;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor_img() {
            return author_img;
        }

        public void setAuthor_img(String author_img) {
            this.author_img = author_img;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getEditorValue() {
            return editorValue;
        }

        public void setEditorValue(String editorValue) {
            this.editorValue = editorValue;
        }

        public String getImg_url1() {
            return img_url1;
        }

        public void setImg_url1(String img_url1) {
            this.img_url1 = img_url1;
        }

        public String getImg_url2() {
            return img_url2;
        }

        public void setImg_url2(String img_url2) {
            this.img_url2 = img_url2;
        }

        public String getImg_url3() {
            return img_url3;
        }

        public void setImg_url3(String img_url3) {
            this.img_url3 = img_url3;
        }

        public String getImg_num() {
            return img_num;
        }

        public void setImg_num(String img_num) {
            this.img_num = img_num;
        }

        public String getIfReprint() {
            return ifReprint;
        }

        public void setIfReprint(String ifReprint) {
            this.ifReprint = ifReprint;
        }

        public String getInitial_url() {
            return initial_url;
        }

        public void setInitial_url(String initial_url) {
            this.initial_url = initial_url;
        }

        public String getIfPush() {
            return ifPush;
        }

        public void setIfPush(String ifPush) {
            this.ifPush = ifPush;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
