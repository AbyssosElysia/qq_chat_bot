package com.elysiaptr;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        // 新建bot实例
        Bot bot = BotFactory.INSTANCE.newBot(2700941690L, BotAuthorization.byQRCode(), configuration -> {
            configuration.setProtocol(BotConfiguration.MiraiProtocol.MACOS);
            configuration.enableContactCache();
            configuration.randomDeviceInfo();
        });

        bot.login();

        // 回复群聊中的at
        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event -> {
            if (event.getMessage().contentToString().contains("@" + bot.getId())) {
                event.getSubject().sendMessage("你疯了？");
            }
        });

        // 回复个人
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, event -> {
            event.getSubject().sendMessage("你疯了？");
        });
    }
}