package com.elysiaptr.entity;

import com.elysiaptr.config.BotConfig;
import com.elysiaptr.handler.GptReplier;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;

@Slf4j
public class QBot {

    /**
     * 机器人实例
     */
    private final Bot bot;

    /**
     * 初始化机器人实例
     * 保证可以正常启动
     * 默认启用随机设备信息
     *
     * @param botConfig 从配置文件中读取的机器人信息
     */
    public QBot(BotConfig botConfig) {
        this.bot = BotFactory.INSTANCE.newBot(botConfig.getQqNumber(), BotAuthorization.byQRCode(), configuration -> {
            // 设置系统为MacOS（稳定提供二维码）
            configuration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);

            // 设置连接心跳包周期
            configuration.setHeartbeatPeriodMillis(botConfig.getHeartbeatPeriodMillis());

            // 设置心跳策略
            if (botConfig.getHeartbeatStrategy() == 2) {
                configuration.setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.REGISTER);
            } else if (botConfig.getHeartbeatStrategy() == 0) {
                configuration.setHeartbeatStrategy(BotConfiguration.HeartbeatStrategy.NONE);
            }

            // 设置每次心跳时等待结果的时间
            configuration.setHeartbeatTimeoutMillis(botConfig.getHeartbeatTimeoutMillis());

            // 设置断线是否自动重联
            if (botConfig.getAutoReconnectOnForceOffline()) {
                configuration.autoReconnectOnForceOffline();
            }

            // 设置是否保存会话信息
            if (!botConfig.getAutoSaveAccountRecords()) {
                configuration.disableAccountSecretes();
            }

            // 设置自动保存联系人信息
            if (!botConfig.getAutoSaveContactList()) {
                configuration.disableContactCache();
            } else {
                configuration.enableContactCache();
            }

            // 设置随机设备信息
            configuration.randomDeviceInfo();

        });
        log.info("init chatting robot successfully, id: {}", botConfig.getQqNumber());

        // 进行登陆
        bot.login();
        log.info("init chatting robot successfully, id: {}", botConfig.getQqNumber());
    }

    /**
     * 回复群聊中的at信息
     */
    public void groupAnswer() {
        // 回复群聊中的at
        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event -> {
            String prompt = event.getMessage().contentToString();
            if (prompt.contains("@" + bot.getId())) {
                String answer = GptReplier.getAnswer(prompt);
                log.info("group get prompt successfully, message: {}", prompt);
                event.getSubject().sendMessage(answer);
                log.info("group answer chatting robot successfully, message: {}", answer);
            }
        });
    }

    /**
     * 排除自动回复的干扰 回复好友信息
     */
    public void friendAnswer() {
        // 回复好友私聊信息
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, event -> {
            String prompt = event.getMessage().contentToString();
            if (!prompt.contains("自动回复")) {
                String answer = GptReplier.getAnswer(prompt);
                log.info("friend get prompt successfully, message: {}", prompt);
                event.getSubject().sendMessage(answer);
                log.info("friend answer chatting robot successfully, message: {}", answer);
            }
        });
    }

    /**
     * 回复陌生人
     */
    // TODO 回复陌生人
    public void strangerAnswer() {
    }

    /**
     * 自动添加群
     */
    // TODO 自动添加群
    public void autoAddGroups() {
    }

    /**
     * 自动添加好友
     */
    // TODO 自动添加好友
    public void AutoAddFriends() {
    }

    /**
     * 设置间隔时间
     */
    // TODO 设置间隔时间
    public void setGapTime() {
    }

}
