
package fr.ensibs.spring.interfaces;

/**
 *
 * @author Mehdi
 */
public interface IEmailer {
    
    public boolean send(String user, String mail);
    
    public void setTextEditor(ITextEditor textEditor);
    
    public void setAddressBook(IAddressBook addressBook);
    
    public void setEncrypt(IEncrypt encrypt);
}
