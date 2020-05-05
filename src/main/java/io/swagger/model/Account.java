package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T11:29:14.407Z[GMT]")
public class Account   {
  @JsonProperty("AccountID")
  private String accountID = null;

  /**
   * Gets or Sets accountType
   */
  public enum AccountTypeEnum {
    SAVING("Saving"),
    
    DEPOSIT("Deposit");

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
  @JsonProperty("AccountType")
  private AccountTypeEnum accountType = null;

  @JsonProperty("AccountHolder")
  private Long accountHolder = null;

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
  @ApiModelProperty(example = "Saving", required = true, value = "")
      @NotNull

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
  @ApiModelProperty(example = "1234567890", required = true, value = "")
      @NotNull

    public Long getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(Long accountHolder) {
    this.accountHolder = accountHolder;
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
        Objects.equals(this.accountHolder, account.accountHolder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountID, accountType, accountHolder);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    accountID: ").append(toIndentedString(accountID)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    accountHolder: ").append(toIndentedString(accountHolder)).append("\n");
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
