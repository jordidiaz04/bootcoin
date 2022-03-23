package com.nttdata.bootcoin.utilities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  /**
   * User types.
   */
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static final class UserType {
    public static final int BUYER = 1;
    public static final int SELLER = 2;
  }
}
