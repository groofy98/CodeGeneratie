package io.swagger.model;

import java.math.BigDecimal;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-31T06:47:48.298Z[GMT]")
public class Account   {
  public Account() {
  }

  public static final BigDecimal dayLimit = BigDecimal.valueOf(250000);

  public Account(String accountID, Long absoluteLimit, Long accountHolder, AccountTypeEnum accountType, Boolean isActive) {
    this.accountID = accountID;
    this.accountType = accountType;
    this.accountHolder = accountHolder;
    this.absoluteLimit = absoluteLimit;
    this.isActive = isActive;
  }

  public Account(String accountID, AccountTypeEnum accountType, Long accountHolder, Boolean isActive, long absoluteLimit) {
    this.accountID = accountID;
    this.accountType = accountType;
    this.accountHolder = accountHolder;
    this.isActive = isActive;
    this.absoluteLimit = absoluteLimit;
  }

  @Id
  @JsonProperty("AccountID")
  private String accountID = null;

  /**
   * Gets or Sets accountType
   */
  public enum AccountTypeEnum {
    SAVING("Saving"),
    
    CURRENT("Current");

    private String value;

    AccountTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccountTypeEnum fromValue(String text) {
      for (AccountTypeEnum b : AccountTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("accountType")
  private AccountTypeEnum accountType = null;

  @JsonProperty("accountHolder")
  private Long accountHolder = null;

  @JsonProperty("absoluteLimit")
  private Long absoluteLimit = null;

  @JsonProperty("isActive")
  private Boolean isActive = null;

  public Account accountID(String accountID) {
    this.accountID = accountID;
    return this;
  }

  /**
   * Get accountID
   * @return accountID
  **/
  @ApiModelProperty(example = "ingb-219009315", value = "")
  
    public String getAccountID() {
    return accountID;
  }

  public void setAccountID(String accountID) {
    this.accountID = accountID;
  }

  public Account accountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * Get accountType
   * @return accountType
  **/
  @ApiModelProperty(example = "Saving", value = "")
  
    public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  public Account accountHolder(Long accountHolder) {
    this.accountHolder = accountHolder;
    return this;
  }

  /**
   * Get accountHolder
   * @return accountHolder
  **/
  @ApiModelProperty(example = "1234567890", value = "")
  
    public Long getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(Long accountHolder) {
    this.accountHolder = accountHolder;
  }

  public Account absoluteLimit(Long absoluteLimit) {
    this.absoluteLimit = absoluteLimit;
    return this;
  }

  /**
   * Get absoluteLimit
   * @return absoluteLimit
  **/
  @ApiModelProperty(example = "0", value = "")
  
    public Long getAbsoluteLimit() {
    return absoluteLimit;
  }

  public void setAbsoluteLimit(Long absoluteLimit) {
    this.absoluteLimit = absoluteLimit;
  }

  public Account isActive(Boolean isActive) {
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
    Account account = (Account) o;
    return Objects.equals(this.accountID, account.accountID) &&
        Objects.equals(this.accountType, account.accountType) &&
        Objects.equals(this.accountHolder, account.accountHolder) &&
        Objects.equals(this.absoluteLimit, account.absoluteLimit) &&
        Objects.equals(this.isActive, account.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountID, accountType, accountHolder, absoluteLimit, isActive);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    accountID: ").append(toIndentedString(accountID)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    accountHolder: ").append(toIndentedString(accountHolder)).append("\n");
    sb.append("    absoluteLimit: ").append(toIndentedString(absoluteLimit)).append("\n");
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
