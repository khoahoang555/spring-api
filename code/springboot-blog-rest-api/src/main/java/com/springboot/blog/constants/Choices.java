package com.springboot.blog.constants;

public class Choices {

    public enum HEDGE_ORDER_STATUS {
        NOT_RUN("NOT_RUN", "実行"),
        RUNNING("RUNNING", "実行中"),
        PLANNING("PLANNING", "実行予定"),
        DONE("DONE", "実行済");

        private final String key;

        private final String value;

        HEDGE_ORDER_STATUS(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}
