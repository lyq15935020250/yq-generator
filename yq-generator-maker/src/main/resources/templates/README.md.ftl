# ${name}

> ${description}
>
> 作者：${author}
>
> yq-generator代码生成器

通过命令行交互输入的方式动态生成想要的代码

## 使用说明

```
generator <命令> <选项参数>
```

示例命令

```
generator generator <#list modelConfig.models as modelInfo><#if modelInfo.abbr??>-${modelInfo.abbr}, </#if></#list>
```

## 参数说明

<#list modelConfig.models as modelInfo>
<#if modelInfo.groupKey??>
<#list modelInfo.models as subModelInfo>
${modelInfo?index + 1}.${subModelInfo.fieldName}

类型：${subModelInfo.type}

描述：${subModelInfo.description}

默认值：${subModelInfo.defaultValue?c}

命令缩写：<#if subModelInfo.abbr??>${subModelInfo.abbr}</#if>
</#list>
<#else>
${modelInfo?index + 1}.${modelInfo.fieldName}

类型：${modelInfo.type}

描述：${modelInfo.description}

默认值：${modelInfo.defaultValue?c}

命令缩写：<#if modelInfo.abbr??>${modelInfo.abbr}</#if>
</#if>
</#list>