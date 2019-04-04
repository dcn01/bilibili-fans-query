package com.plain.bilibilitools.bean;

/**
 * Create by Plain on 2019/4/4 10:07 PM
 * Description:
 */
public class ResultBean {

    /**
     * code : 0
     * message : 0
     * ttl : 1
     * data : {"mid":16094700,"following":118,"whisper":0,"black":0,"follower":1}
     */

    private int code;
    private String message;
    private int ttl;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mid : 16094700
         * following : 118
         * whisper : 0
         * black : 0
         * follower : 1
         */

        private int mid;
        private int following;
        private int whisper;
        private int black;
        private int follower;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public int getFollowing() {
            return following;
        }

        public void setFollowing(int following) {
            this.following = following;
        }

        public int getWhisper() {
            return whisper;
        }

        public void setWhisper(int whisper) {
            this.whisper = whisper;
        }

        public int getBlack() {
            return black;
        }

        public void setBlack(int black) {
            this.black = black;
        }

        public int getFollower() {
            return follower;
        }

        public void setFollower(int follower) {
            this.follower = follower;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "mid=" + mid +
                    ", following=" + following +
                    ", whisper=" + whisper +
                    ", black=" + black +
                    ", follower=" + follower +
                    '}';
        }
    }
}

