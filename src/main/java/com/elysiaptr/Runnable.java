package com.elysiaptr;

import com.elysiaptr.config.GlobalConfig;
import com.elysiaptr.entity.QBot;
import com.elysiaptr.handler.ConfigLoader;

public class Runnable {
    public static void run() {
        GlobalConfig globalConfig = ConfigLoader.loadConfig();
        if (globalConfig != null) {
            System.out.println(globalConfig.getBotConfig().getQqNumber());
            QBot qBot = new QBot(globalConfig.getBotConfig());
        }
    }
}
