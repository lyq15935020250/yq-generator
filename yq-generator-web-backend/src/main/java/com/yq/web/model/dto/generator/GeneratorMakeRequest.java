package com.yq.web.model.dto.generator;

import com.yq.maker.meta.Meta;
import lombok.Data;

import java.io.Serializable;

/**
 * 使用代码生成器
 *
 */
@Data
public class GeneratorMakeRequest implements Serializable {

    /**
     * 元信息
     */
    private Meta meta;

    /**
     * 压缩文件路径
     */
    private String zipFilePath;

    private static final long serialVersionUID = 1L;
}