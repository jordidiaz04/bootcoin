package com.nttdata.bootcoin.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

/**
 * TypeAccount object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeAccountResponse {
  private int option;
  private BigDecimal maintenance;
  private Integer maxTransactions;
  private BigDecimal commission;
  private Integer day;
}
