package com.web.qa.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 
public class EmailReports {
 
    public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress, String[] cc_mails,
            String subject, String message, List<String> attachfile)
            throws AddressException, MessagingException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session); 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc_mails[0]));
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc_mails[1]));
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc_mails[2]));
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc_mails[3]));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachfile != null && attachfile.size() > 0) {
            for (String filePath : attachfile) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
 
    /**
     * Test sending e-mail with attachments
     */
    public static void Email_Report() 
    
//    public static void main(String args[])
    {
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "docintactautomation@gmail.com";
        String password = "Sherwin@1";
        
		String hostname = "Unknown";
		String user = null;

		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		    System.out.println(hostname);
		    if(hostname.equalsIgnoreCase("DESKTOP-B9VHH3K"))
		    {
		    	user="Manoj Medapati";
		    }
		    else if(hostname.equalsIgnoreCase("DESKTOP-H8AJKT7"))
		    {
		    	user="Dinesh Varanasi";
		    }
		    else if(hostname.equalsIgnoreCase("DESKTOP-L8ENT8S"))
		    {
		    	user="Varalakshmi K";
		    }
		    else if(hostname.equalsIgnoreCase("DESKTOP-01IIECI"))
		    {
		    	user="SriSri T";
		    }
		    else
		    {
		    	user=hostname;
		    }
		    
		}
		catch (UnknownHostException ex)
		{
		    System.out.println("Hostname can not be resolved");
		    user=hostname;
		}
        
 
        // message info
        String mailTo = "tummu.srisri@cognitiveinnovations.in";
        String[] cc_mails= {"paramesh.dadi@cognitiveinnovations.in","manojkumar.medapati@cognitiveinnovations.in","dinesh.varanasi@cognitiveinnovations.in","varalakshmi.kotyada@cognitiveinnovations.in"};
        String subject = "Automation Report";
        String message = "Test Reports of Automation Framework <br><br> This Test is Triggered by "+user+"..<br><br>For detailed report, Please contact QA Team.<br><br> Regards, <br>QA Team";

        
        //attachments
        
        
        String logs_dir = System.getProperty("user.dir")+"\\logs";
        String logs_dir_zip = System.getProperty("user.dir")+"\\target\\logs.zip";

        TestUtil zip = new TestUtil();
        zip.compressDirectory(logs_dir, logs_dir_zip);

        List<String> attachfile = new ArrayList<String>();
		attachfile.add(System.getProperty("user.dir")+"\\target\\cucumber-reports\\report.html");
		attachfile.add(logs_dir_zip);
 
        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                cc_mails, subject, message, attachfile);
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
}