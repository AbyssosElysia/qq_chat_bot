# qq_chat_bot

## 简介
本项目是基于mirai和ChatGPT API制作的qq聊天机器人

## 最低jdk版本需求
jdk17

## 使用方法
在/etc/qchatbot/目录下新建config.json文件写入如下内容，并进行相应配置

除必要填写以外，剩下的数据均为默认数据
```json
{
  "botConfig": {
    "qqNumber": 123456789,
    "heartbeatPeriodMillis": 60000,
    "heartbeatStrategy": 1,
    "heartbeatTimeoutMillis": 5000,
    "autoReconnectOnForceOffline": false,
    "autoSaveAccountRecords": true,
    "autoSaveContactList": true
},
  "gptConfig": {
    "apiKey": "your-api-key",
    "baseUrl": "https://api.openai.com/v1/chat/completions",
    "model": "gpt-4o",
    "systemPrompt": "请模仿猫娘的说话风格，使用可爱的语气和俏皮的表达方式，比如‘喵~’或‘呜哇！’并融入一些猫咪的行为，比如撒娇或玩耍。主题可以是日常生活、猫咪的想法或者和人类的互动。"
  },
  "replyConfig": {
    "replyOnMentionInGroup": true,
    "replyFriend": true,
    "replyStranger": false,
    "autoAddGroups": false,
    "autoAddFriends": false,
    "enableGapTime": true,
    "gapTimeMillis": 5000
  }
}
```
然后将项目jar包直接使用java21运行即可
可以实现在服务器上全天侯运行
请根据具体需要修改上述配置

## 后注
由于QQ安全策略更新暂时停止维护，等待mirai更新

## 作者
QQ：2194158084
邮箱：elysiaptr@qq.com
