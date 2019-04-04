/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.remote;

import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author Kaarar
 */
public interface DeviceManager extends Remote {
    
    // create user / product
    
    // modifie user / product 
    
    // delete user / product
    
    //read user / product
    
    //login /logout 
    
    // payment systeme 
    
       
    
    /**
     * create user by 
     * @param username
     * @param password
     * @param balance
     * @param role
     */
    public Boolean createUser(String username,String password ,Integer balance, Role role ) throws Exception;
    
    /**
     * delete user 
     */
    public Boolean deleteUser(String username ) throws Exception;
    
    /**
     * read user by username
     */
    public User getUserByUsername(String username)throws Exception;
    
    /**
     * read all users
     */
    public List<User> getUsers() throws Exception;
    
    /**
     * update user 
     */
    public Boolean updateUser(User user ) throws Exception;
    
    
    /**
     * login by 
     * @param username
     * @param password
     */
    public User login(String username , String password )throws Exception;
    
    /**
     * logout by  
     * @param token
     */
    public Boolean logout(Token token );
    
        
    
    /**
     * create product by 
     * @param name
     * @param category
     * @param quantity
     * @param price
     */
    public Boolean createProduct(String name,Category category ,Integer quantity , Integer price ) throws Exception;
    
    
    /**
     * delete product by 
     * @param name
     */
    public Boolean deleteProduct(Product produit )throws Exception;
    
    /**
     * read all products
     */
    public Boolean getProducts( ) throws Exception;
    
    /**
     * read product by category
     */
    public Boolean getProductByCategory(Category category )throws Exception ;
    
    /**
     * update product 
     */
    public Boolean updateProduct(Product product ) throws Exception;
    
    
    /**
     * pay product by
     * @param user
     * @param product
     */
    public Boolean payProduct(User user,Product product ) throws Exception;
    
    
}
