package com.nttdata.bootcoin.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

/**
 * Account object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {
  private String id;
  private String debitCard;
  private String number;
  private int position;
  private ClientResponse client;
  private TypeAccountResponse typeAccount;
  private List<String> holders;
  private List<String> signatories;
  private BigDecimal balance;
  private Long totalTransactions;
  private BigDecimal availableBalance;
  private boolean status;
}
