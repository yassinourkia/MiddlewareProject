
package fr.ensibs.spring.interfaces;

import java.util.Observer;

/**
 *
 * @author Mehdi
 */
public interface ITextEditor {
    
    public void callTextEditor(String s);
    
    public String getMessage();
    
    public boolean  getValueMailBox();
    
    public void setObserver(Observer observer);
    
    public void setSpellChecker(ISpellChecker spellChecker);
}
