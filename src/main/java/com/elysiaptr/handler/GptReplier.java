package com.elysiaptr.handler;

import com.elysiaptr.config.GptConfig;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.internal.deps.okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

@Slf4j
public class GptReplier {
    private static GptConfig gptConfig;

    public static void initialize(GptConfig config) {
        gptConfig = config; // 使用正确的赋值
        log.info("chat bot initialized successfully, config: {}", gptConfig.getSystemPrompt());
    }

    /**
     * 供外界调用的方法
     * 接受从qq中提交的prompt
     * 返回api的回复
     *
     * @param request qq中监听获取的prompt
     * @return 最终应当发送的话
     */
    public static String getAnswer(String request) {
        Request httpRequest = buildRequest(request);
        return getResponse(httpRequest);
    }

    /**
     * 构建请求
     *
     * @param request 请求prompt
     * @return 构建好的http请求
     */
    private static Request buildRequest(String request) {
        // 构建请求体
        JSONObject jsonBody = getJsonObject(request);
        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json; charset=utf-8"));

        // 构建请求
        return new Request.Builder()
                .url(gptConfig.getBaseUrl())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + gptConfig.getApiKey())
                .post(body)
                .build();
    }

    /**
     * 构建具体json数据
     *
     * @param request 请求prompt
     * @return 请求体数据字段
     */
    private static JSONObject getJsonObject(String request) {
        JSONObject messageSys = new JSONObject();
        messageSys.put("role", "system");
        messageSys.put("content", gptConfig.getSystemPrompt());

        JSONObject messageUsr = new JSONObject();
        messageUsr.put("role", "user");
        messageUsr.put("content", request);

        JSONArray messages = new JSONArray();

        messages.put(messageSys);
        messages.put(messageUsr);

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", gptConfig.getModel());
        jsonBody.put("messages", messages);

        // TODO 完成温度设置相关配置解析
        jsonBody.put("temperature", 0.7);

        return jsonBody;
    }

    /**
     * 接受并处理返回信息
     *
     * @param httpRequest http请求
     * @return content字段的值
     */
    private static String getResponse(Request httpRequest) {
        OkHttpClient client = new OkHttpClient();

        // 发送请求并获取响应
        try (Response response = client.newCall(httpRequest).execute()) {
            // 解析响应
            String responseBody = null;
            if (response.body() != null) {
                responseBody = response.body().string();
            } else {
                log.error("Response body is null");
            }

            // 使用json包装
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray choices = jsonResponse.getJSONArray("choices");
            String content = choices.getJSONObject(0).getJSONObject("message").getString("content");
            return content.trim();

        } catch (IOException e) {
            log.error("Request failed: {}", e.getMessage());
            return null;
        }
    }

}
