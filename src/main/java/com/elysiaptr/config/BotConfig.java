package com.elysiaptr.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机器人设置
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BotConfig {
    /**
     * qq号
     */
    private Long qqNumber;

    /**
     * 心跳周期
     * 过长会导致被服务器断开连接
     */
    private Long heartbeatPeriodMillis;

    /**
     * 心跳策略
     * 1 STAT_HB 状态心跳 （推荐）
     * 2 REGISTER 发送切换在线状态 （可能会导致频繁的好友或客户端上线提示）
     * 0 NONE 不主动维护会话 （可能会有短暂的不可用时间）
     */
    private Integer heartbeatStrategy;

    /**
     * 每次心跳时等待结果的时间
     * 心跳超时服务将停止
     */
    private Long heartbeatTimeoutMillis;

    /**
     * 被挤下线是否自动重连
     */
    private Boolean autoReconnectOnForceOffline;

    /**
     * 是否保存账号的会话信息
     * 可加速登陆
     */
    private Boolean autoSaveAccountRecords;

    /**
     * 是否保存账号的联系人信息
     * 可加速登陆
     */
    private Boolean autoSaveContactList;

}
