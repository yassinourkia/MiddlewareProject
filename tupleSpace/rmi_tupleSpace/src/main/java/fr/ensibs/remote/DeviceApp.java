/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.remote;

import fr.ensibs.river.RiverLookup;
import java.util.ArrayList;
import java.util.List;
import net.jini.space.JavaSpace;

/**
 *
 * @author Kaarar
 */
public class DeviceApp implements DeviceManager {
    static final String host = "localhost";
    static final int port = 5663;
    static RiverLookup finder;
    static JavaSpace space ;
    public IToken managerToken ;
    
    public DeviceApp() throws Exception {
    finder=new RiverLookup();
    space = (JavaSpace) finder.lookup(host,port, JavaSpace.class);
    }
       
    @Override
    public Boolean createUser(String username, String password, Integer balance, Role role) throws Exception  {
        Boolean ret=false  ;
        User template =new User();
        template.username =username ;
        //Verifier s'il existe deja 
        User result =(User) space.readIfExists(template,null,60*60*1000);
        if(result == null) {
                User user = new User (username, password, balance, role);
                space.write(user, null, 60*60*1000);
                ret=true ;
         }
         else { 
                System.out.println("User already exist ");
                ret=true;
         }
        return true ;
    }

    @Override
    public Boolean deleteUser(String username) throws Exception {
        User template = new User();
        template.username =username ; 
        space.takeIfExists(template,null,60*60*1000);
        return true ;
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        User template=new User();
        template.username =username ; 
        User user = (User)space.readIfExists(template,null,60*60*1000);
        return user;
    }

    @Override
    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<User>();
        User template = new User();
        User result = (User)space.takeIfExists(template,null,60*60*1000);
        while(result != null){
            users.add(result);
            result=(User)space.takeIfExists(template,null,60*60*1000);
        }
        for(User us : users){
                space.write(us,null,60*60*1000);
            }
        return users;
    }

    @Override
    public Boolean updateUser(User user) throws Exception {
        Boolean ret=false ;
        User template=new User();
        template.username =user.username ; 
        User result =(User)space.takeIfExists(template,null,60*60*1000);
        if(result != null) {
                space.write(user, null, 60*60*1000);
                ret=true ;
         }
        return ret;
    }

    @Override
    public User login(String username, String password)throws Exception {
        User template=new User();
        template.username =username ; 
        template.password=password;
        User result =(User)space.readIfExists(template,null,60*60*1000);
        Token token ;
        if(result != null) {
                token =this.managerToken.generateToken(result);
                result.setToken(token);
         }
        return result;
    }
    
    @Override
    public Boolean logout(Token token) throws Exception {
        Boolean ret=false ; 
        User template=new User();
        template.token =token ; 
        User result =(User)space.readIfExists(template,null,60*60*1000);
        if(result != null){
            this.managerToken.deleteToken(token);
            ret= true;
        }
        return ret ;
    }
    

    @Override
    public Boolean createProduct(String name, Category category, Integer quantity, Integer price)throws Exception {
        Boolean ret=false  ;
        Product template =new Product();
        template.name =name ;
        //Verifier s'il existe deja 
        Product result =(Product) space.readIfExists(template,null,60*60*1000);
        if(result == null) {
                Product product = new Product (name, category , quantity, price);
                space.write(product, null, 60*60*1000);
                ret=true ;
         }
         else { 
                System.out.println("User already exist ");
                ret=true;
         }
        return true ;
    }

    @Override
    public Boolean deleteProduct(Product produit) throws Exception {
        Product template = new Product();
        template.name =produit.name ; 
        space.takeIfExists(template,null,60*60*1000);
        return true ;
    }

    @Override
    public Boolean getProducts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getProductByCategory(Category category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean updateProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean payProduct(User user, Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
