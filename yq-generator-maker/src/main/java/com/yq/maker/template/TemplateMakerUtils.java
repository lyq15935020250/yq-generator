package com.yq.maker.template;

import cn.hutool.core.util.StrUtil;
import com.yq.maker.meta.Meta;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lyq
 * @description: 模板制作工具类
 * @date 2023/12/24 15:20
 */
public class TemplateMakerUtils {

    /**
     * 从未分组的文件中去除组内同名的文件
     *
     * @param fileInfoList
     * @return
     */
    public static List<Meta.FileConfig.FileInfo> removeGroupFilesFromRoot(List<Meta.FileConfig.FileInfo> fileInfoList){
        // 获取所有分组
        List<Meta.FileConfig.FileInfo> groupList = fileInfoList.stream()
                .filter(fileInfo -> StrUtil.isNotBlank(fileInfo.getGroupKey()))
                .collect(Collectors.toList());
        // 获取所有分组内的文件列表
        List<Meta.FileConfig.FileInfo> groupInnerFilesList = groupList.stream()
                .flatMap(fileInfo -> fileInfo.getFiles().stream())
                .collect(Collectors.toList());
        // 获取所有分组内文件的输入路径集合
        Set<String> groupFilesInputPathSet = groupInnerFilesList.stream()
                .map(Meta.FileConfig.FileInfo::getInputPath)
                .collect(Collectors.toSet());
        // 移除所有名称在路径集合中的组外文件
        return fileInfoList.stream()
                .filter(fileInfo -> !groupFilesInputPathSet.contains(fileInfo.getInputPath()))
                .collect(Collectors.toList());
    }
}
