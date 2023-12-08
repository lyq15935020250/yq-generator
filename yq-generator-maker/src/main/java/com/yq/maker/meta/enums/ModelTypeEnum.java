package com.yq.maker.meta.enums;

/**
 * @author lyq
 * @description: 模型类型枚举
 * @date 2023/12/8 17:05
 */
public enum ModelTypeEnum {

    STRING("字符串","string"),
    BOOLEAN("布尔","boolean");

    private final String text;

    private final String value;

    ModelTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}

