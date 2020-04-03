package net.questcraft.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.questcraft.ConfigReader;
import net.questcraft.ErrorClass;
import net.questcraft.NodeResponse;
import net.questcraft.joinapp.Application;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;

public class NotificationImplementer implements NotificationDAO {
    ConfigReader configReader = new ConfigReader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void sendNotification(String recipiant, String messageTesxt, String subject) throws SendFailedException {
        String password = configReader.readPropertiesFile("questCraftEmailPassword");
        String toEmail = recipiant;
        String fromEmail = "chestly.questcraft@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.starttls.enable", "true");

        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.user", fromEmail);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(properties);


        MimeMessage message = new MimeMessage(session);
        try {


            message.setFrom(new InternetAddress(fromEmail));
            InternetAddress toAddress = new InternetAddress(toEmail);

            // To get the array of addresses

            toAddress = new InternetAddress(toEmail);


            message.addRecipient(Message.RecipientType.TO, toAddress);


            message.setSubject(subject);
            message.setContent(messageTesxt, "text/html; charset=utf-8");


            Transport transport = session.getTransport("smtp");

            System.out.println(fromEmail + ", " + password);
            transport.connect(host, fromEmail, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendDiscordBotApplication(Application application) throws ErrorClass {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(WRAP_ROOT_VALUE, true);
            URL obj = new URL("http://localhost:8081/displayApp?application=" + URLEncoder.encode(objectMapper.writeValueAsString(application), "UTF-8"));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            System.out.println(con.getResponseCode());

            nodeResponseHandler(objectMapper, con);
        } catch (IOException e) {
            throw new ErrorClass("IOException, Issue contacting The discord Bot, Sorry about that!", 12);
        }
    }

    public void nodeResponseHandler(ObjectMapper objectMapper, HttpURLConnection con) throws IOException, ErrorClass {
        StringBuilder content;

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        content = new StringBuilder();

        while ((line = in.readLine()) != null) {

            content.append(line);
            content.append(System.lineSeparator());
        }

        NodeResponse data = objectMapper.readValue(content.toString(), NodeResponse.class);
        System.out.println(data);
        if (!data.getStatus()) {
            if (data.getCode() == 12) {
                throw new ErrorClass("Couldnt Find your discord user name in the QuestCraft discord server, please make sure you have joined", 10);
            }
        }
    }
}
