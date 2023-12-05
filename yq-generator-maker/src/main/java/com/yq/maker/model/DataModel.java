package com.yq.maker.model;

import lombok.Data;


@Data
public class DataModel {
    /**
     * 是否生成循环
     */
    private boolean loop;
    /**
     * 作者信息
     */
    private String author = "lyqq";
    /**
     * 输出信息
     */
    private String outputText = "求和结果：";
}
