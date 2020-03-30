package controller;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class Bot extends TelegramLongPollingBot {
    final String HELP = "/help - список команд\n /setting - настройки \n " +
            "для поиска информации введите любой город на русском языке";


    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot());
        }
        catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "/help":
                    sendMsg(message, HELP);
                    break;
                case "/setting":
                    sendMsg(message, "что будем настраивать?");
                    break;
                default:sendMsg(message, Contrl.findInf(message.getText()));
                break;
            }
        }
    }

    public String getBotUsername() {
        return "TouristBotForCity";
    }

    public String getBotToken() {
        return "1016516084:AAHtCRUxyDb1mdl836ik69a0Q-PZRJyE_A4";
    }
}
