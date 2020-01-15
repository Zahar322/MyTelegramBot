package com.bot;


import com.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot  {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Value("${response.url}")
    private String url;

    @Value("${bot.default.message}")
    private String defaultResponseValue;

    private String responseMessage;

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message=update.getMessage();
        responseMessage=defaultResponseValue;
        if(message!=null&&message.hasText()){
            setResponseMessage(message);
            sendMessage(message,responseMessage);
        }
    }

    private void sendMessage(Message message, String text) {
        try {
            execute(getSendMessage(message,text));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void setResponseMessage(Message message){
        City city=restTemplate.getForObject(url+message.getText(), City.class);
        if(city!=null){
            responseMessage=city.getCityDescription();
        }
    }

    private SendMessage getSendMessage(Message message,String text){
        SendMessage sendMessage=new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(message.getChatId());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
