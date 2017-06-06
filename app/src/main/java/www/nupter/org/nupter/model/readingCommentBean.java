package www.nupter.org.nupter.model;

import java.util.List;

/**
 * Created by fangzhenyi on 16/2/14.
 */
public class readingCommentBean {

    /**
     * status : success
     * info : {"info":[{"createTime":"2016-02-13 17:02:51","comment":"我的第二条评论","fromWhoUuid":"1","fromWhoName":"方振毅","fromWhoAvatar_url":"B13100102","commentId":"2","isSecondComment":0},{"createTime":"2016-02-13 17:03:25","comment":"我的第二条评论","fromWhoUuid":"1","fromWhoName":"方振毅","fromWhoAvatar_url":"B13100102","commentId":"3","isSecondComment":1,"toWhoName":"周杰伦","toComment":"我的第二条评论"},{"createTime":"2016-02-13 17:01:53","comment":"我的第二条评论","fromWhoUuid":"2","fromWhoName":"周杰伦","fromWhoAvatar_url":"B13100102","commentId":"1","isSecondComment":0,"toWhoName":"周杰伦","toComment":"我的第二条评论"}]}
     */

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
        /**
         * createTime : 2016-02-13 17:02:51
         * comment : 我的第二条评论
         * fromWhoUuid : 1
         * fromWhoName : 方振毅
         * fromWhoAvatar_url : B13100102
         * commentId : 2
         * isSecondComment : 0
         */

        private List<InfoEntity> info;

        public void setInfo(List<InfoEntity> info) {
            this.info = info;
        }

        public List<InfoEntity> getInfo() {
            return info;
        }

        public static class InfoEntityl {
            private String createTime;
            private String comment;
            private String fromWhoUuid;
            private String fromWhoName;
            private String fromWhoAvatar_url;
            private String commentId;
            private int isSecondComment;

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public void setFromWhoUuid(String fromWhoUuid) {
                this.fromWhoUuid = fromWhoUuid;
            }

            public void setFromWhoName(String fromWhoName) {
                this.fromWhoName = fromWhoName;
            }

            public void setFromWhoAvatar_url(String fromWhoAvatar_url) {
                this.fromWhoAvatar_url = fromWhoAvatar_url;
            }

            public void setCommentId(String commentId) {
                this.commentId = commentId;
            }

            public void setIsSecondComment(int isSecondComment) {
                this.isSecondComment = isSecondComment;
            }

            public String getCreateTime() {
                return createTime;
            }

            public String getComment() {
                return comment;
            }

            public String getFromWhoUuid() {
                return fromWhoUuid;
            }

            public String getFromWhoName() {
                return fromWhoName;
            }

            public String getFromWhoAvatar_url() {
                return fromWhoAvatar_url;
            }

            public String getCommentId() {
                return commentId;
            }

            public int getIsSecondComment() {
                return isSecondComment;
            }
        }
    }
}
