import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import state.*;

public class stateTelegramBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new stateTelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            State userState = stateUtils.setNextUserState(update.getMessage().getFrom().getId());
            String strMessage = new String();
            switch (userState){
                case STATE_STARTED:
                    //TODO: Store data in DataBase or CRM
                    strMessage = "Hola!! es importante que nos respondas a este mensaje con tus datos personales: Nombre completo, edad, trabajo y ciudad.\n" +
                            "\n" +
                            "Un/a redactor/a del programa se pondrá en contacto contigo lo antes posible, gracias!";
                    break;
                case STATE_COMPLETED:
                    //TODO: Store data in DataBase or CRM
                    strMessage = "Ya hemos recibido tu solicitud. Muchas gracias y suerte con el casting!";
                    break;
            }
            stateUtils.setNextUserState(update.getMessage().getFrom().getId());

            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(strMessage);

            try {
                execute(message); // Call method to send the message
                System.out.println(message.getText());
            } catch (TelegramApiException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "YOUR-BOT-NAME";
    }

    @Override
    public String getBotToken() {
        return "YOUR-TOKEN";
    }
}