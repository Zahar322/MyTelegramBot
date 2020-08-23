package com.bot;


import com.commands.Command;
import com.commands.CommandFactory;
import com.entity.City;
import com.myAnno.InjectRandomInt;
import com.repo.CityRepo;
import com.service.CityService;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@PropertySource("classpath:telegram.properties")
public class ChatBot extends TelegramLongPollingBot  {

    @Autowired
    private CityService cityService;

    @Autowired
    private CommandFactory commandFactory;

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

    @InjectRandomInt(min = 3,max = 8)
    private int injectValue;

    private String responseMessage;

    private static final String CITIES="cities";

    @Override
    public String getBotToken() {
        return botToken;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message=update.getMessage();
        responseMessage=defaultResponseValue;
        if(message!=null&&message.hasText()) {
            sendMessageByCommand(message);
            System.out.println(message);
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
    private void sendMessageByCommand(Message message){
        com.commands.Message botMessage = createMessage(message);
        Command command=commandFactory.getCommand(botMessage.getCommandName());
        if(command!=null){
            responseMessage=command.execute(cityService, botMessage);
            return;
        }
        setResponseMessage(message);
    }

    private com.commands.Message createMessage(Message message) {
        String commandName = "^/\\w+";
        Pattern pattern = Pattern.compile(commandName);
        Matcher matcher = pattern.matcher(message.getText());
        if (matcher.find()) {
            return new com.commands.Message(message.getText().replaceFirst(commandName, ""), matcher.group());
        }
        return new com.commands.Message();
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
