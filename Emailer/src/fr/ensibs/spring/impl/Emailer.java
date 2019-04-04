package fr.ensibs.spring.impl;

import fr.ensibs.spring.interfaces.IAddressBook;
import fr.ensibs.spring.interfaces.IEmailer;
import fr.ensibs.spring.interfaces.IEncrypt;
import fr.ensibs.spring.interfaces.ITextEditor;
import java.math.BigInteger;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mehdi
 */
@Component
public class Emailer implements IEmailer, Observer {

    public String address;
    public IAddressBook addressBook;
    public ITextEditor textEditor;
    public IEncrypt encrypt;

    @Override
    public boolean send(String user, String mail) {
        if ((address = addressBook.getAddressBook(user)) != null) {
            textEditor.setObserver(this);
            textEditor.callTextEditor(mail);
        }
        return true;
    }

    private boolean sendMessage(String address, String msg, boolean selected) throws Exception {
        System.out.println("Envoie : \"" + msg + "\" à " + address);
        String destinateur = address; //destiataire
        String destinataire = "yassin.ourkia@gmail.com"; //destinateur
        String host = "smtp.univ-ubs.fr"; //envoie de mail pour une adresse mail étudiant
        Properties properties = System.getProperties(); //system properties
        properties.setProperty("mail.smtp.host", host); //mail server
        Session session = Session.getDefaultInstance(properties); //default Session object.

        try {
            encrypt.generateKey(1024); //longueur de clé est 1024
            MimeMessage message = new MimeMessage(session); //default MimeMessage object.
            message.setFrom(new InternetAddress(destinataire)); //Edition En-tête destinateur 
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinateur)); //Edition En-tête destinataire
            message.setSubject("Mail de test pour le projet spring"); //object
            //if mailbox is checked 
            if (selected) {
                BigInteger ciphertext = encrypt.encrypt(new BigInteger(msg.getBytes()));//chiffrement du message
                String answer = encrypt.numberToString(ciphertext);
                message.setText(answer); //Edition du message
                Transport.send(message); //Envoie de message
            } else {
                message.setText(msg); //Edition du message
                Transport.send(message); //Envoie de message
            }

            System.out.println("Message envoyé avec succès...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return true;
    }

    @Override
    public void setTextEditor(ITextEditor textEditor) {
        this.textEditor = textEditor;
    }

    @Override
    public void setEncrypt(IEncrypt encrypt) {
        this.encrypt = encrypt;
    }

    @Override
    public void setAddressBook(IAddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public void update(Observable o, Object arg) {
        boolean selected = textEditor.getValueMailBox();
        String msg = textEditor.getMessage();
        try {
            sendMessage(address, msg, selected);
        } catch (Exception ex) {
            Logger.getLogger(Emailer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
