package com.pls.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Users table.
 * 
 * @author User
 * 
 */
public class User implements Serializable {
 /**
  * Dummy. 
  */
 private static final int MAX_USERNAME_LENGTH = 25;
 /**
  * Dummy. 
  */
 private static final long serialVersionUID = -2092570781115897267L;

 /**
  * Dummy. 
  */
 @NotNull
 @NotEmpty
 @Size(min = 1, max = MAX_USERNAME_LENGTH, message = "1-25 letters and spaces")
 @Pattern(regexp = "[A-Za-z ]*", message = "Only letters and spaces")
 private String firstName;

 /**
  * Dummy. 
  */
 @NotNull
 @NotEmpty
 @Size(min = 1, max = MAX_USERNAME_LENGTH, message = "1-25 letters and spaces")
 @Pattern(regexp = "[A-Za-z ]*", message = "Only letters and spaces")
 private String lastName;

 /**
  * Dummy. 
  */
 @NotNull
 @NotEmpty
 @Size(min = 1, max = MAX_USERNAME_LENGTH, message = "1-25 letters and spaces")
 @Pattern(regexp = "[A-Za-z0-9]*", message = "Only letters and digits")
 private String password;

 /**
  * Dummy. 
  */
 @NotNull
 @NotEmpty
 @Email(message = "Invalid format")
 private String emailAddress;

 /**
  * Dummy. 
  */
 private String status = "A";

 /**
  * Dummy. 
  */
 private Date dateCreated = new Date();

 /**
  * Dummy. 
  */
 private Date dateModified = new Date();

 /**
  * Dummy. 
  */
 private Long createdBy = 0L;

 /**
  * Dummy. 
  */
 private Long modifiedBy = 0L;

 /**
  * Dummy. 
  */
 private Long userId;

 /**
  * @return the firstName
  */
 public String getFirstName() {
  return firstName;
 }

 /**
  * @param firstName
  *            the firstName to set
  */
 public void setFirstName(String firstName) {
  this.firstName = firstName;
 }

 /**
  * @return the lastName
  */
 public String getLastName() {
  return lastName;
 }

 /**
  * @param lastName
  *            the lastName to set
  */
 public void setLastName(String lastName) {
  this.lastName = lastName;
 }

 /**
  * @return the password
  */
 public String getPassword() {
  return password;
 }

 /**
  * @param password
  *            the password to set
  */
 public void setPassword(String password) {
  this.password = password;
 }

 /**
  * @return the emailAddress
  */
 public String getEmailAddress() {
  return emailAddress;
 }

 /**
  * @param emailAddress
  *            the emailAddress to set
  */
 public void setEmailAddress(String emailAddress) {
  this.emailAddress = emailAddress;
 }

 /**
  * @return the status
  */
 public String getStatus() {
  return status;
 }

 /**
  * @param status
  *            the status to set
  */
 public void setStatus(String status) {
  this.status = status;
 }

 /**
  * @return the dateCreated
  */
 public Date getDateCreated() {
  return dateCreated;
 }

 /**
  * @param dateCreated
  *            the dateCreated to set
  */
 public void setDateCreated(Date dateCreated) {
  this.dateCreated = dateCreated;
 }

 /**
  * @return the dateModified
  */
 public Date getDateModified() {
  return dateModified;
 }

 /**
  * @param dateModified
  *            the dateModified to set
  */
 public void setDateModified(Date dateModified) {
  this.dateModified = dateModified;
 }

 /**
  * @return the createdBy
  */
 public Long getCreatedBy() {
  return createdBy;
 }

 /**
  * @param createdBy
  *            the createdBy to set
  */
 public void setCreatedBy(Long createdBy) {
  this.createdBy = createdBy;
 }

 /**
  * @return the modifiedBy
  */
 public Long getModifiedBy() {
  return modifiedBy;
 }

 /**
  * @param modifiedBy
  *            the modifiedBy to set
  */
 public void setModifiedBy(Long modifiedBy) {
  this.modifiedBy = modifiedBy;
 }

 /**
  * @return the personId
  */
 public Long getUserId() {
  return userId;
 }

 /**
  * @param personId
  *            the personId to set
  */
 public void setUserId(Long userId) {
  this.userId = userId;
 }
}