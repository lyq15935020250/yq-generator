package com.yq.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author lyq
 * @description:
 * @date 2023/12/5 16:44
 */
public class MetaManager {
    private static volatile Meta meta;


    private MetaManager() {
        // 私有构造函数防，防止外部实例化
    }

    public static Meta getMeta() {
        if (meta == null){
            synchronized (MetaManager.class){
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta(){
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        Meta.FileConfig fileConfig = newMeta.getFileConfig();
        // todo 校验和处理默认值
        return newMeta;
    }
}
