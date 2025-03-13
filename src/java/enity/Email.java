package enity;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    //password: ufmf vevq qxzp yhot
    
//    static final String from = "leminhnguyet22072003@gmail.com";
//    static final String password = "ufmfvevqqxzpyhot";
    static final String from = "nampqhe173479@fpt.edu.vn";
    static final String password = "hldy peln bcod gidj";
    
    public boolean sendEmail(String to, String title, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };
        
        // Tao session
        Session session = Session.getInstance(props, auth);
        
        // Gui mail
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(from);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // -------------------
            msg.setSubject(title, "UTF-8");
            //--------------------
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html");
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String generateRandomOTP(int length){
        String numbers = "0123456789";
        Random random = new Random();
        char[] pin = new char[length];
        for(int i = 0; i < length; i++){
            pin[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return new String(pin);
    }
    
    public static void main(String[] args) {
        Email m = new Email();
        m.generateRandomOTP(6);
        m.sendEmail("nampqhe173479@fpt.edu.vn", "NEW PASSWORD: ", m.generateRandomOTP(6));
    }
    
}