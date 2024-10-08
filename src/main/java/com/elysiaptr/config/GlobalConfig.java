package com.elysiaptr.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局设置
 * 用来实现序列化和反序列化
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GlobalConfig {
    private BotConfig botConfig;
    private GptConfig gptConfig;
    private ReplyConfig replyConfig;
}
