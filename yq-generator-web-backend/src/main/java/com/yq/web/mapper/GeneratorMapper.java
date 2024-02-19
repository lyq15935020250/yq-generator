package com.yq.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yq.web.model.entity.Generator;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lyq15935020250
 * @description 针对表【generator(代码生成器)】的数据库操作Mapper
 * @createDate 2024-01-12 12:29:19
 * @Entity com.yq.web.model.entity.Generator
 */
public interface GeneratorMapper extends BaseMapper<Generator> {

    /**
     * 查询已经被逻辑删除的代码生成器
     *
     * @return
     */
    @Select("SELECT id,distPath FROM yq_generator.generator WHERE isDelete = 1")
    List<Generator> listDeletedGenerator();

}




