
package fr.ensibs.spring.impl;

import fr.ensibs.spring.interfaces.IAddressBook;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.springframework.stereotype.Component;

/**
 *
 * @author Mehdi
 */
@Component
public class AddressBook implements IAddressBook {

    private final String path = "src/fr/ensibs/spring/run/adressBook";
    public HashMap<String, String> adresses;

    public AddressBook() {
        this.adresses = new HashMap<>();
        try {
            InputStream ips = new FileInputStream(path);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            String[] contenue;
            while ((ligne = br.readLine()) != null) {
                contenue = ligne.split(" : ");
                adresses.put(contenue[0], contenue[1]);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public String getAddressBook(String user) {
        return adresses.get(user);
    }

}
