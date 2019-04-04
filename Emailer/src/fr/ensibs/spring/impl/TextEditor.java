package fr.ensibs.spring.impl;

import fr.ensibs.spring.interfaces.ISpellChecker;
import fr.ensibs.spring.interfaces.ITextEditor;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mehdi
 */
@Component
public class TextEditor extends JFrame implements ITextEditor {

    @Autowired
    ISpellChecker spellChecker;
    JPanel container = new JPanel();
    JPanel jPanel = new JPanel();
    JButton jButton1;
    JTextComponent jTextArea;
    Observer observer;
    JCheckBox jCheckBox;

    public TextEditor() throws HeadlessException {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(400, 400, 600, 300);
        //this.setResizable(false);

        container.setLayout(new BorderLayout());
        jPanel.setLayout(new BorderLayout());
        jButton1 = new JButton("Send");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                method();
            }
        });

        //Ajout du bouton Send
        jPanel.add(jButton1, BorderLayout.SOUTH);
        jTextArea = new JTextArea();
        //Ajout du JTextFields
        container.add(jTextArea, BorderLayout.CENTER);
        jCheckBox = new JCheckBox("Envoyer votre mail chiffré");
        //Ajout du checkBox
        jPanel.add(jCheckBox, BorderLayout.NORTH);
        //Ajout du panel au container
        container.add(jPanel, BorderLayout.SOUTH);
        this.add(container);
    }

    protected void method() {
        observer.update(null, null);
        setVisible(false);
        this.dispose();

    }

    @Override
    public void callTextEditor(String s) {
        jTextArea.setText(s);
        this.setVisible(true);
    }

    @Override
    public String getMessage() {
        return jTextArea.getText();
    }

    @Override
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void setSpellChecker(ISpellChecker spellChecker) {
        this.spellChecker = spellChecker;
        spellChecker.register(jTextArea);
    }

    @Override
    public boolean getValueMailBox() {
        boolean isSelected = jCheckBox.isSelected();

        return isSelected;
    }
}
