package com.elysiaptr.handler;

import com.elysiaptr.config.GptConfig;
import com.elysiaptr.config.ReplyConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    }

    /**
     * 供外界调用的方法
     * 接受从qq中提交的prompt
     * 返回api的回复
     * @param request qq中监听获取的prompt
     * @return 最终应当发送的话
     */
    public static String getAnswer(String request) {
        Request httpRequest = buildRequest(request);

        // TODO 分层解耦
        OkHttpClient client = new OkHttpClient();
        // 发送请求并获取响应
        try (Response response = client.newCall(httpRequest).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // 解析响应
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray choices = jsonResponse.getJSONArray("choices");
            String content = choices.getJSONObject(0).getJSONObject("message").getString("content");
            return content.trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建请求
     * @param request 请求prompt
     * @return 构建好的http请求
     */
    private static Request buildRequest(String request) {
        // 构建请求体
        JSONObject messageSys = new JSONObject();
        messageSys.put("role", "system");
        messageSys.put("message", gptConfig.getSystemPrompt());

        JSONObject messageUsr = new JSONObject();
        messageUsr.put("role", "user");
        messageUsr.put("message", request);

        JSONArray messages = new JSONArray();

        messages.put(messageSys);
        messages.put(messageUsr);

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", gptConfig.getModel());
        jsonBody.put("messages", messages);
        // TODO 完成温度设置
        jsonBody.put("temperature", 0.7);

        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json; charset=utf-8"));

        // 构建请求
        Request httpRequest = new Request.Builder()
                .url(gptConfig.getBaseUrl())
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + gptConfig.getApiKey())
                .post(body)
                .build();

        return httpRequest;
    }

}
