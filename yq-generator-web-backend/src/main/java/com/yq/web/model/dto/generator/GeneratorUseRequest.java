package com.yq.web.model.dto.generator;

import com.yq.web.meta.Meta;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 使用代码生成器
 *
 */
@Data
public class GeneratorUseRequest implements Serializable {

    /**
     * 生成器 ID
     */
    private Long id;

    /**
     * 数据模型
     */
    Map<String,Object> dataModel;

    private static final long serialVersionUID = 1L;
}