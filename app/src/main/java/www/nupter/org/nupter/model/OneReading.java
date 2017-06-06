package www.nupter.org.nupter.model;

/**
 * Created by fangzhenyi on 15/11/21.
 */
public class OneReading {
    private String status;

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
        private String id;
        private String title;
        private String author;
        private String tag;
        private String item_img;
        private String content;
        private String createTime;
        private String updateTime;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public void setItem_img(String item_img) {
            this.item_img = item_img;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getTag() {
            return tag;
        }

        public String getItem_img() {
            return item_img;
        }

        public String getContent() {
            return content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }
    }
}
