import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;
public class SendMail implements Address {
    public static void main(String[] args) {
        Message message = new Message()
//                .from(mail1)
                .to(mail1)
                .subject("subject")
                .text("text")
                .build();
        Send.send(message, Host.SEZNAM);
    }
}

interface Send {
    static void send(Message message, Host host) {
        JavaMailSenderImpl sender = Sender.getJavaMailSender(host);
        Property.getProperties(sender);
        sender.send(message);
    }
}

class Message extends SimpleMailMessage {
    Message() {
    }
    Message from(String from) {
        this.setFrom(from);
        return this;
    }
    Message replyTo(String replyTo) {
        this.setTo(replyTo);
        return this;
    }
    Message to(String to) {
        this.setTo(to);
        return this;
    }
    Message to(String[] to) {
        this.setTo(to);
        return this;
    }
    Message cc(String cc) {
        this.setCc(cc);
        return this;
    }
    Message cc(String[] cc) {
        this.setCc(cc);
        return this;
    }
    Message bcc(String bcc) {
        this.setBcc(bcc);
        return this;
    }
    Message subject(String subject) {
        this.setSubject(subject);
        return this;
    }
    Message text(String text) {
        this.setText(text);
        return this;
    }
    Message build() {
        return this;
    }
}

interface Address {
    String mail1 = "<user1>@<host>";
    String mail2 = "<user2>@<host>";
    String mail3 = "<user3>@<host>";
    String group[] = {mail1, mail2, mail3};
}

interface Property {
    static void getProperties(JavaMailSenderImpl sender) {
        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");
    }
}

interface Sender {
    static JavaMailSenderImpl getJavaMailSender(Host host) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        switch (host) {
            case GMAIL:
                sender.setHost("smtp.gmail.com");
                sender.setPort(587);
                sender.setUsername("<username>@gmail.com");
                sender.setPassword("<password>");
                break;
            case SEZNAM:
                sender.setHost("smtp.seznam.cz");
                sender.setPort(587);
                sender.setUsername("<username>@seznam.cz");
                sender.setPassword("<password>");
                break;
            default:
                break;
        }
        return sender;
    }
}

enum Host {
    GMAIL,
    SEZNAM
}
