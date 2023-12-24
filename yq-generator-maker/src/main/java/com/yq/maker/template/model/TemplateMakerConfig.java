package com.yq.maker.template.model;

import com.yq.maker.meta.Meta;
import lombok.Data;

/**
 * @author lyq
 * @description:
 * @date 2023/12/24 12:01
 */
@Data
public class TemplateMakerConfig {

    private Long id;

    private Meta meta = new Meta();

    private String originProjectPath;

    TemplateMakerFileConfig fileConfig = new TemplateMakerFileConfig();

    TemplateMakerModelConfig modelConfig = new TemplateMakerModelConfig();

    TemplateMakerOutputConfig outputConfig = new TemplateMakerOutputConfig();

}
