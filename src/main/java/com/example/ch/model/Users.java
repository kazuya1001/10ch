package com.example.ch.model;

import java.util.Date;

public class Users extends UsersKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.user_name
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.password_hash
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    private String passwordHash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.email
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.users.created_at
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    private Date createdAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.user_name
     *
     * @return the value of public.users.user_name
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.user_name
     *
     * @param userName the value for public.users.user_name
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.password_hash
     *
     * @return the value of public.users.password_hash
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.password_hash
     *
     * @param passwordHash the value for public.users.password_hash
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.email
     *
     * @return the value of public.users.email
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.email
     *
     * @param email the value for public.users.email
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.users.created_at
     *
     * @return the value of public.users.created_at
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.users.created_at
     *
     * @param createdAt the value for public.users.created_at
     *
     * @mbg.generated Sat May 18 16:33:54 JST 2024
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}