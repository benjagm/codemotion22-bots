import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Message;
import spark.Spark;

public class sampleWebhook {
    public static void main(String[] args) {

        Spark.post("/wapp", (req, res) -> {
            MessagingResponse response = new MessagingResponse.Builder().message(
                    new Message.Builder("Gracias por escribir a first dates. Hemos anotado tu teléfono y nuestro equipo de casting se pondra en contacto contigo lo antes posible").
                            build()).build();
            return response.toXml();
        });

    }
}
