package com.nttdata.bootcoin.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

/**
 * Bootcoin object.
 */
@Data
public class BootcoinRequest {
  @NotBlank(message = "Field documentNumber must be required")
  private String documentNumber;
  @NotBlank(message = "Field documentNumber must be required")
  private String phone;
  @NotBlank(message = "Field documentNumber must be required")
  private String email;
  @Range(min = 1, max = 2, message = "Field profile must be 1 or 2")
  private int profile;
  private String accountNumber;
}
