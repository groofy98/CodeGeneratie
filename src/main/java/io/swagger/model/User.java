package io.swagger.model;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * User
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T10:13:19.888Z[GMT]")
public class User   {
  @Id
  @JsonProperty("id")
  @SequenceGenerator(name = "user_seq", initialValue = 100001)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
  private Long id = null;

  @JsonProperty("firstname")
  private String firstname = null;

  @JsonProperty("lastname")
  private String lastname = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("dateOfBirth")
  private Date dateOfBirth = null;

  @JsonProperty("isEmployee")
  private Boolean isEmployee = false;

  @JsonProperty("isCustomer")
  private Boolean isCustomer = false;

  @JsonProperty("isActive")
  private Boolean isActive = true;

  public User() {
  }

  public User(String firstname, String lastname, String email, String username, String password, Date dateOfBirth, Boolean isEmployee, Boolean isCustomer, Boolean isActive) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.username = username;
    this.password = password;
    this.dateOfBirth = dateOfBirth;
    this.isEmployee = isEmployee;
    this.isCustomer = isCustomer;
    this.isActive = isActive;
  }

  public User id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "5", value = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * Get firstname
   * @return firstname
  **/
  @ApiModelProperty(example = "John", required = true, value = "")
      @NotNull

    public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public User lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * Get lastname
   * @return lastname
  **/
  @ApiModelProperty(example = "Doe", required = true, value = "")
      @NotNull

    public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  **/
  @ApiModelProperty(example = "JohnDoe@example.com", required = true, value = "")
      @NotNull

    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
  **/
  @ApiModelProperty(example = "Johnny69", required = true, value = "")
      @NotNull

    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
  **/
  @ApiModelProperty(example = "John123!", required = true, value = "")
      @NotNull

    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User dateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  /**
   * Get dateOfBirth
   * @return dateOfBirth
  **/
  @ApiModelProperty(example = "Mon Oct 17 00:00:00 GMT 7", required = true, value = "")
      @NotNull

    @Valid
    public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public User isEmployee(Boolean isEmployee) {
    this.isEmployee = isEmployee;
    return this;
  }

  /**
   * Get isEmployee
   * @return isEmployee
  **/
  @ApiModelProperty(example = "false", required = true, value = "")
      @NotNull

    public Boolean isIsEmployee() {
    return isEmployee;
  }

  public void setIsEmployee(Boolean isEmployee) {
    this.isEmployee = isEmployee;
  }

  public User isCustomer(Boolean isCustomer) {
    this.isCustomer = isCustomer;
    return this;
  }

  /**
   * Get isCustomer
   * @return isCustomer
  **/
  @ApiModelProperty(example = "true", required = true, value = "")
      @NotNull

    public Boolean isIsCustomer() {
    return isCustomer;
  }

  public void setIsCustomer(Boolean isCustomer) {
    this.isCustomer = isCustomer;
  }

  public User isActive(Boolean isActive) {
    this.isActive = isActive;
    return this;
  }

  /**
   * Get isActive
   * @return isActive
  **/
  @ApiModelProperty(example = "true", value = "")
  
    public Boolean isIsActive() {
    return isActive;
  }

  public void setIsActive(Boolean isActive) {
    this.isActive = isActive;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.firstname, user.firstname) &&
        Objects.equals(this.lastname, user.lastname) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.password, user.password) &&
        Objects.equals(this.dateOfBirth, user.dateOfBirth) &&
        Objects.equals(this.isEmployee, user.isEmployee) &&
        Objects.equals(this.isCustomer, user.isCustomer) &&
        Objects.equals(this.isActive, user.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, email, username, password, dateOfBirth, isEmployee, isCustomer, isActive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
    sb.append("    isEmployee: ").append(toIndentedString(isEmployee)).append("\n");
    sb.append("    isCustomer: ").append(toIndentedString(isCustomer)).append("\n");
    sb.append("    isActive: ").append(toIndentedString(isActive)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
