package com.elysiaptr;

import com.elysiaptr.config.GlobalConfig;
import com.elysiaptr.entity.QBot;
import com.elysiaptr.handler.ConfigLoader;
import com.elysiaptr.handler.GptReplier;

public class Runnable {
    public static void run() {
        GlobalConfig globalConfig = ConfigLoader.loadConfig();
        if (globalConfig != null) {
            System.out.println(globalConfig.getBotConfig().getQqNumber());
            QBot qBot = new QBot(globalConfig.getBotConfig());
            GptReplier.initialize(globalConfig.getGptConfig());
            qBot.friendAnswer();
            qBot.groupAnswer();
        }
    }
}
