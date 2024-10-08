package com.elysiaptr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 消息类
 */
@AllArgsConstructor
@Data
public class Message {
    /**
     * 角色
     */
    private String role;

    /**
     * 提示语
     */
    private String message;
}
