package com.yq.model;

import lombok.Data;

/**
 * @author lyqq
 * @description: 接收动态模板参数
 * @date 2023/11/13 21:28
 */
@Data
public class MainTemplateConfig {
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
