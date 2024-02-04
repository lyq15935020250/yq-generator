package com.yq.web.model.dto.generator;

import lombok.Data;

import java.io.Serializable;

/**
 * 使用代码生成器
 *
 */
@Data
public class GeneratorCacheRequest implements Serializable {

    /**
     * 生成器 ID
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}