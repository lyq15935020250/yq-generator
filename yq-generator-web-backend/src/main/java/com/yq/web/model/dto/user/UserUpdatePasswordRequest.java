package com.yq.web.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户更新个人信息请求
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@Data
public class UserUpdatePasswordRequest implements Serializable {

    /**
     * 用户ID
     */
    private Long id;;

    /**
     * 密码
     */
    private String userPassword;

    private static final long serialVersionUID = 1L;
}