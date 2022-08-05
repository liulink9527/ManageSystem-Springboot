package com.link.controller.dto;

import lombok.Data;

/**
 * 接收前端登录请求的参数
 * @author Link
 * @Description
 * @date 2022-08-04 16:41
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
}
