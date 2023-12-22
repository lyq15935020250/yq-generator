package com.yq.maker.template.enums;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author lyq
 * @description: 文件过滤规则枚举
 * @date 2023/12/8 17:05
 */
public enum FileFilterRangeEnum {

    FILE_NAME("文件名称","fileName"),
    FILE_CONTENT("文件内容","fileContent");
    private final String text;

    private final String value;

    FileFilterRangeEnum(String text, String value) {
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
    public static FileFilterRangeEnum getEnumsByValue(String value){
        if (ObjectUtil.isEmpty(value)){
            return null;
        }
        for (FileFilterRangeEnum enums : FileFilterRangeEnum.values()) {
            if (enums.value.equals(value)){
                return enums;
            }
        }
        return null;
    }
}

