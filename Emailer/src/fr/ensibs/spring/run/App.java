package fr.ensibs.spring.run;

import fr.ensibs.spring.interfaces.IAddressBook;
import fr.ensibs.spring.interfaces.IEmailer;
import fr.ensibs.spring.interfaces.IEncrypt;
import fr.ensibs.spring.interfaces.ISpellChecker;
import fr.ensibs.spring.interfaces.ITextEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Ourkia Yassin
 */
public class App {
        public static void main(String[] args) throws Exception {
        //path pour conf.xml
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("fr\\ensibs\\spring\\run\\beans.xml");
        //Adress du fichier adressBook
        IAddressBook adressBook = (IAddressBook) applicationContext.getBean("addressBook");
        ITextEditor textEditor = (ITextEditor) applicationContext.getBean("textEditor");
        ISpellChecker spellChecker = (ISpellChecker) applicationContext.getBean("spellCheckerFR");
        IEncrypt encrypt = (IEncrypt) applicationContext.getBean("encrpyt");
        textEditor.setSpellChecker(spellChecker);
        IEmailer eMailer = (IEmailer) applicationContext.getBean("eMailer");
        eMailer.setAddressBook(adressBook);
        eMailer.setTextEditor(textEditor);
        eMailer.setEncrypt(encrypt);
        //Choix du nom du destinataire depuis la liste d'adresse book
        eMailer.send("etud", "");
    }
}
