package com.yq.maker.template.model;

import lombok.Data;

/**
 * @author lyq
 * @description:
 * @date 2023/12/24 15:16
 */

@Data
public class TemplateMakerOutputConfig {

    /**
     * 从未分组的文件中去除组内同名的文件
     */
    private boolean removeGroupFilesFromRoot = true;

}
