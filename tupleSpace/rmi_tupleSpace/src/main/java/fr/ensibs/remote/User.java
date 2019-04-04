/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ensibs.remote;

import net.jini.core.entry.Entry;



/**
 *
 * @author KAARAR
 */
public class User implements Entry {
    public String username;
    public String password ;
    public Integer balance;
    public Role role ;
    public Token token ;

    public User() {
    }

    public User(String username, String password, Integer balance, Role role) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.role = role;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public User(String username, Integer balance, Role role, Token token) {
        this.username = username;
        this.balance = balance;
        this.role = role;
        this.token = token;
    }

    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}