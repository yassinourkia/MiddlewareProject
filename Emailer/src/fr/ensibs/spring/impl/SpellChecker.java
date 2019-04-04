
package fr.ensibs.spring.impl;

import com.inet.jortho.FileUserDictionary;
import fr.ensibs.spring.interfaces.ISpellChecker;
import javax.swing.text.JTextComponent;
import org.springframework.stereotype.Component;


/**
 *
 * @author ourkia yassin
 */
@Component
public  class SpellChecker implements ISpellChecker {
    //String path ="\\dictionary\\";
    @Override
    public void register(JTextComponent textComponent) {
        com.inet.jortho.SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
        //Enregsitrement des dictionnaires
        com.inet.jortho.SpellChecker.registerDictionaries(this.getClass().getResource("dictionary_fr.ortho"), "fr");
        com.inet.jortho.SpellChecker.registerDictionaries(this.getClass().getResource("dictionary_en.ortho"), "en");
        com.inet.jortho.SpellChecker.register(textComponent, true, true, true, true);
    }
}
