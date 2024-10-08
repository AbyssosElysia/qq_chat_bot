package com.elysiaptr.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyConfig {
    /**
     * 在群聊里被at时是否回复
     */
    private Boolean replyOnMentionInGroup;

    /**
     * 好友私信是否回复
     */
    private Boolean replyFriend;

    /**
     * 陌生人是否回复
     */
    private Boolean replyStranger;

    /**
     * 是否自动添加群
     */
    private Boolean autoAddGroups;

    /**
     * 是否自动添加朋友
     */
    private Boolean autoAddFriends;

    /**
     * 是否启用回复间隔
     */
    private Boolean enableGapTime;

    /**
     * 回复间隔
     */
    private Long gapTimeMillis;
}
