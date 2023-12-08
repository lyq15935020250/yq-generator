package com.yq.maker.meta.enums;

/**
 * @author lyq
 * @description: 文件生成类型枚举
 * @date 2023/12/8 17:05
 */
public enum FileGenerateTypeEnum {

    DYNAMIC("动态","dynamic"),
    STATIC("静态","static");

    private final String text;

    private final String value;

    FileGenerateTypeEnum(String text, String value) {
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

