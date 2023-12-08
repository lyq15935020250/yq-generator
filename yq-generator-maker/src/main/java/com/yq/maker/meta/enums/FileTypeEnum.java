package com.yq.maker.meta.enums;

/**
 * @author lyq
 * @description: 文件类型枚举
 * @date 2023/12/8 17:05
 */
public enum FileTypeEnum {

    DIR("目录","dir"),
    FILE("文件","file");

    private final String text;

    private final String value;

    FileTypeEnum(String text, String value) {
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

