package com.example.administrator.mvptodo.bean;

import java.util.List;

/**
 * @autour: wanbin
 * date: 2018/1/4 0004 15:27
 * version: ${version}
 * des:
 */
public class MainIndexBean {


    /**
     * code : 0
     * message : OK
     * total : 100
     * page_size : 6
     * data : [{"goodsId":0,"spanSize":4,"banners":["https://i8.mifile.cn/v1/a1/251f0880-423e-fa2d-3c18-1d3ec23f9912.webp","https://i8.mifile.cn/v1/a1/49dfd019-9504-abb5-34bb-26f425b67e45.webp","https://cdn.cnbj0.fds.api.mi-img.com/b2c-mimall-media/b9540da01aef9a00a5c640b1c98b955a.jpg"]},{"goodsId":1,"text":"明星单品","imageUrl":"https://i8.mifile.cn/v1/a1/1d338200-1be1-f868-9695-9d5ae0d6c2c6.webp","spanSize":4},{"goodsId":2,"text":"小米5c  64GB 移动版","imageUrl":"https://i8.mifile.cn/v1/a1/04629084-7810-d1fb-6f4a-a0c52433ca29.webp?width=360&height=360","spanSize":2},{"goodsId":3,"text":"米家智能摄像机","imageUrl":"https://i8.mifile.cn/v1/a1/375bd3a4-aab9-f77b-f6a1-5dbf01087495.webp?width=360&height=360","spanSize":2},{"goodsId":4,"imageUrl":"https://i8.mifile.cn/v1/a1/656a5863-6af1-6302-4e36-a33bd49e45cb.webp?width=360&height=360","spanSize":2},{"goodsId":5,"imageUrl":"https://cdn.cnbj0.fds.api.mi-img.com/b2c-mimall-media/cbcdb6c054d45c1afc955c87329e96f1.jpg?width=360&height=360","spanSize":2},{"goodsId":6,"imageUrl":"https://i8.mifile.cn/v1/a1/f093e0a8-e3d8-4fc8-deb7-af25ea3d8663.webp?width=360&height=360","spanSize":2},{"goodsId":7,"imageUrl":"https://i8.mifile.cn/v1/a1/b6c55f3b-d4ac-b2be-8d7c-3c818a79030a.webp?width=360&height=360","spanSize":2},{"goodsId":5,"text":"智能生活，从这里开始","spanSize":4},{"goodsId":5,"text":"测试描述5","imageUrl":"https://i8.mifile.cn/v1/a1/6cc739d8-ae51-779a-3707-2f1d20a558bf.webp?width=720&heihgt=440","spanSize":4},{"goodsId":6,"text":"测试描述6","imageUrl":"http://imgsrc.baidu.com/baike/pic/item/0b55b319ebc4b745a58395aecffc1e178a821576.jpg","spanSize":4},{"goodsId":5,"text":"我又是野广告","spanSize":4}]
     */

    private int code;
    private String message;
    private int total;
    private int page_size;
    private List<DataBean> data;

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goodsId : 0
         * spanSize : 4
         * banners : ["https://i8.mifile.cn/v1/a1/251f0880-423e-fa2d-3c18-1d3ec23f9912.webp","https://i8.mifile.cn/v1/a1/49dfd019-9504-abb5-34bb-26f425b67e45.webp","https://cdn.cnbj0.fds.api.mi-img.com/b2c-mimall-media/b9540da01aef9a00a5c640b1c98b955a.jpg"]
         * text : 明星单品
         * imageUrl : https://i8.mifile.cn/v1/a1/1d338200-1be1-f868-9695-9d5ae0d6c2c6.webp
         */

        private int goodsId;
        private int spanSize;
        private String text;
        private String imageUrl;
        private List<String> banners;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getSpanSize() {
            return spanSize;
        }

        public void setSpanSize(int spanSize) {
            this.spanSize = spanSize;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public List<String> getBanners() {
            return banners;
        }

        public void setBanners(List<String> banners) {
            this.banners = banners;
        }
    }
}
