package com.whatsapp;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("webhook")
public class MessageController {
    @PostMapping
    public String AnswerWebhook(@RequestBody RequestWebhook hook) throws IOException, IOException {
        for (var message : hook.getMessages()) {
            if (message.getFromMe())
                continue;
            String option = message.getBody().split(" ")[0].toLowerCase();
            switch (option) {
                case "chatid":
                    ApiWA.sendChatId(message.getChatId());
                    break;
                case "ogg":
                    ApiWA.sendOgg(message.getChatId());
                    break;
                case "geo":
                    ApiWA.sendGeo(message.getChatId());
                    break;
                case "group":
                    ApiWA.createGroup(message.getAuthor());
                    break;
                default:
                    ApiWA.sendDefault(message.getChatId());
                    break;
            }
        }
        return "ok";
    }
}