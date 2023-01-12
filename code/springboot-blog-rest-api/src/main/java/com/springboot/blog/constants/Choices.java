package com.springboot.blog.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;

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

    public enum CURRENCY_PAIR {
        USD_JPY("USD_JPY", "ドル"),
        AUD_JPY("AUD_JPY", "豪ドル"),
        EURO_JPA("EURO_JPA", "ユーロ");

        private final String key;
        private final String name;

        CURRENCY_PAIR(String key, String name) {
            this.key = key;
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        @JsonCreator
        public static CURRENCY_PAIR getEnumFromValue(String value) {
            return Arrays.stream(values()).filter(i -> i.name().equals(value)).findFirst().orElse(CURRENCY_PAIR.USD_JPY);
        }
    }

    public enum BILLING_SELLING {
        FOREIGN_CURRENCY_PAYMENT("FOREIGN_CURRENCY_PAYMENT", "外貨支払"),
        FOREIGN_CURRENCY_RECEIPT("FOREIGN_CURRENCY_RECEIPT", "外貨支払");

        private final String key;
        private final String name;

        BILLING_SELLING(String key, String name) {
            this.key = key;
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        @JsonCreator
        public static BILLING_SELLING getEnumFromValue(String value) {
            return Arrays.stream(values()).filter(i -> i.name().equals(value)).findFirst().orElse(BILLING_SELLING.FOREIGN_CURRENCY_PAYMENT);
        }
    }

    public enum HEDGING_PERIOD {
        THREE_MONTHS("THREE_MONTHS", "3ヶ月"),
        SIX_MONTHS("SIX_MONTHS", "6ヶ月"),

        CUSTOM("CUSTOM", "3ヶ月"),;

        private final String key;
        private final String name;

        HEDGING_PERIOD(String key, String name) {
            this.key = key;
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public String getName() {
            return name;
        }

        @JsonCreator
        public static HEDGING_PERIOD getEnumFromValue(String value) {
            return Arrays.stream(values()).filter(i -> i.name().equals(value)).findFirst().orElse(null);
        }
    }
}
