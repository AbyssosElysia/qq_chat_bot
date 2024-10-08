package com.elysiaptr.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * gpt设置
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GptConfig {
    /**
     * api访问密钥
     */
    private String apiKey;

    /**
     * 访问基地址
     */
    private String baseUrl;

    /**
     * 模型
     */
    private String model;

    /**
     * 系统提示语
     */
    private String systemPrompt;

}

