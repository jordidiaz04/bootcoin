package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.dto.response.AccountResponse;
import reactor.core.publisher.Mono;

/**
 * Account service interface.
 */
public interface AccountService {
  Mono<AccountResponse> findByNumberAndClientDocumentNumber(String number, String documentNumber);
}
