package edu.ntu.ccds.sc2002.entity;

import java.io.Serializable;

/**
 * Abstract base class representing a user in the system.
 * Implements Serializable for data persistence.
 * Demonstrates encapsulation and abstraction OOP principles.
 * 
 * @author Group X
 * @version 1.0
 */
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String userId;
    private String name;
    private String password;
    private UserRole role;
    
    /**
     * Constructs a User with specified details.
     * 
     * @param userId Unique identifier for the user
     * @param name Full name of the user
     * @param password User's password (default: "password")
     * @param role User's role in the system
     */
    public User(String userId, String name, String password, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.role = role;
    }
    
    /**
     * Gets the user ID.
     * 
     * @return User ID
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * Gets the user's name.
     * 
     * @return User's full name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the user's name.
     * 
     * @param name New name for the user
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the user's password.
     * 
     * @return User's password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Changes the user's password.
     * 
     * @param newPassword New password to set
     */
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
    
    /**
     * Gets the user's role.
     * 
     * @return User's role
     */
    public UserRole getRole() {
        return role;
    }
    
    /**
     * Abstract method to display user-specific menu.
     * To be implemented by concrete user classes.
     */
    public abstract void displayMenu();
    
    @Override
    public String toString() {
        return String.format("User[ID=%s, Name=%s, Role=%s]", userId, name, role);
    }
}