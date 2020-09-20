package com.lambdaschool.marketplace.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The entity allowing interaction with the users table
 */
@Entity
@Table(name = "users")
public class User extends Auditable {
  /**
   * The primary key (long) of the users table.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userId;

  /**
   * The password (String) for this user. Cannot be null. Never get displayed
   */
  @Column(nullable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  /**
   * Primary email account of user. Could be used as the userid. Cannot be null and must be unique.
   */
  @Column(nullable = false, unique = true)
  @Email
  private String primaryEmail;

  /**
   * Users full name, or any name the user wants to be addressed as.
   */
  private String name;

  /**
   * A list of emails for this user
   */
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties(value = "user", allowSetters = true)
  private List<UserEmail> userEmails = new ArrayList<>();

  /**
   * Part of the join relationship between user and role
   * connects users to the user role combination
   */
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties(value = "user", allowSetters = true)
  private Set<UserRoles> roles = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnoreProperties(value = "user", allowSetters = true)
  private List<Item> items = new ArrayList<>();

  /**
   * Default constructor used primarily by the JPA.
   */
  public User() {}

  /**
   * Given the params, create a new user object
   * <p>
   * userid is autogenerated
   *
   * @param password     The password (String) of the user
   * @param primaryEmail The primary email (String) of the user
   */
  public User(String password, String primaryEmail, String name) {
    setPassword(password);
    this.primaryEmail = primaryEmail;
    this.name = name;
  }

  /**
   * Set the user's full name, or any other name they would like to be addressed
   * by.
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for userid
   *
   * @return the userid (long) of the user
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Setter for userid. Used primary for seeding data
   *
   * @param userId the new userid (long) of the user
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Getter for username - returns primaryEmail
   *
   * @return the username (String) lowercase
   */
  public String getUsername() {
    return primaryEmail;
  }

  /**
   * getter for primary email
   *
   * @return the primary email (String) for the user converted to lowercase
   */
  public String getPrimaryEmail() {
    return primaryEmail;
  }

  /**
   * setter for primary email
   *
   * @param primaryEmail the new primary email (String) for the user converted to lowercase
   */
  public void setPrimaryEmail(String primaryEmail) {
    this.primaryEmail = primaryEmail.toLowerCase();
  }

  /**
   * Getter for the password
   *
   * @return the password (String) of the user
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setter for password to be used internally, after the password has already been encrypted
   *
   * @param password the new password (String) for the user. Comes in encrypted and stays that way
   */
  public void setPasswordNoEncrypt(String password) {
    this.password = password;
  }

  /**
   * @param password the new password (String) for this user. Comes in plain text and goes out encrypted
   */
  public void setPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    this.password = passwordEncoder.encode(password);
  }

  /**
   * Getter for the list of userEmails for this user
   *
   * @return the list of userEmails (List<UserEmail>) for this user
   */
  public List<UserEmail> getUserEmails() {
    return userEmails;
  }

  /**
   * Setter for list of userEmails for this user
   *
   * @param userEmails the new list of userEmails (List<UserEmail>) for this user
   */
  public void setUserEmails(List<UserEmail> userEmails) {
    this.userEmails = userEmails;
  }

  /**
   * Getter for user role combinations
   *
   * @return A list of user role combinations associated with this user
   */
  public Set<UserRoles> getRoles() {
    return roles;
  }

  /**
   * Setter for user role combinations
   *
   * @param roles Change the list of user role combinations associated with this user to this one
   */
  public void setRoles(Set<UserRoles> roles) {
    this.roles = roles;
  }

  /**
   * Getter for user items combinations
   *
   * @return A list of user item combinations associated with this user
   */
  public List<Item> getItems() {
    return items;
  }

  /**
   * Setter for user items combinations
   *
   * @param items Change the list of user item combinations associated with this user to this one
   */
  public void setItems(List<Item> items) {
    this.items = items;
  }

  /**
   * Internally, user security requires a list of authorities, roles, that the user has. This method is a simple way to provide those.
   * Note that SimpleGrantedAuthority requests the format ROLE_role name all in capital letters!
   *
   * @return The list of authorities, roles, this user object has
   */
  @JsonIgnore
  public List<SimpleGrantedAuthority> getAuthority() {
    List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

    for (UserRoles r : this.roles) {
      String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
      rtnList.add(new SimpleGrantedAuthority(myRole));
    }

    return rtnList;
  }
}
