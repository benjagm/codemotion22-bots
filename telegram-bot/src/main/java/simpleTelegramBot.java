import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class simpleTelegramBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new simpleTelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            //TODO: Store data in DataBase or CRM

            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Gracias por escribir a first dates. Hemos anotado tu teléfono y nuestro equipo de casting se pondra en contacto contigo lo antes posible");

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