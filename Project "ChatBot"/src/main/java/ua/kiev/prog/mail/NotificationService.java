package ua.kiev.prog.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.kiev.prog.model.User;
import ua.kiev.prog.service.UserService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:telegram.properties")
public class NotificationService {

    private final UserService userService;
    private final JavaMailSender emailSender;

    @Value("${bot.email.subject}")
    private String emailSubject;

    @Value("${bot.email.from}")
    private String emailFrom;

    @Value("${bot.email.to}")
    private String emailTo;

    public NotificationService(UserService userService, JavaMailSender emailSender) {
        this.userService = userService;
        this.emailSender = emailSender;
    }

    @Scheduled(fixedRate = 10000)
    public void sendNewApplications() {
        List<User> users = userService.findNewUsers();
        if (users.size() == 0)
            return;

        StringBuilder sb = new StringBuilder();

        users.forEach(user ->
            sb.append("Phone: ")
                    .append(user.getPhone())
                    .append("\r\n")
                    .append("Email: ")
                    .append(user.getEmail())
                    .append("\r\n\r\n")
        );

        sendEmail(sb.toString());
    }

    @Scheduled(fixedRate = 10000)
    public void sendBirthDayMail(){
        List<User> users = new ArrayList<>();
        try {
            users = userService.findBirthdayUsers();
        }catch (ParseException ex){
            ex.printStackTrace();
        }
        if(users.isEmpty()) return;
        users.forEach(this::sendBirthdayText);
    }

    private void sendEmail(String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailTo);
        message.setFrom(emailFrom);
        message.setSubject(emailSubject);
        message.setText(text);

        emailSender.send(message);
    }

    private void sendBirthdayText(User user){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom(emailFrom);
        message.setSubject("Happy Birthday Wishes");
        message.setText("Dear, user! \n Happy Birthday! Wishing you all the best on your special day!");

        emailSender.send(message);
    }
}
