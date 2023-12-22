package com.yq.maker.template.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author lyq
 * @description: 文件过滤规则枚举
 * @date 2023/12/8 17:05
 */
public enum FileFilterRulerEnum {

    CONTAINS("包含","contains"),
    STARTS_WITH("前缀包含","startsWith"),
    ENDS_WITH("后缀包含","endsWith"),
    REGEX("正则","regex"),
    EQUALS("相等","equals");

    private final String text;

    private final String value;

    FileFilterRulerEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    /**
     * 根据value获取枚举
     * @param value
     * @return
     */
    public static FileFilterRulerEnum getEnumsByValue(String value){
        if (ObjectUtil.isEmpty(value)){
            return null;
        }
        for (FileFilterRulerEnum enums : FileFilterRulerEnum.values()) {
            if (enums.value.equals(value)){
                return enums;
            }
        }
        return null;
    }
}

