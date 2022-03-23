package com.nttdata.bootcoin.service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.nttdata.bootcoin.dto.response.AccountResponse;
import com.nttdata.bootcoin.exceptions.customs.CustomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Account service implementation.
 */
@Service
public class AccountServiceImpl implements AccountService {
  @Value("${backend.service.account}")
  private String urlAccount;

  @Autowired
  @Qualifier("wcLoadBalanced")
  private WebClient.Builder webClient;

  @Override
  public Mono<AccountResponse> findByNumberAndClientDocumentNumber(String number,
                                                                   String documentNumber) {
    return webClient
        .build()
        .get()
        .uri(urlAccount + "/get/number/{number}/client/documentNumber/{documentNumber}",
            number, documentNumber)
        .retrieve()
        .onStatus(NOT_FOUND::equals, response ->
            Mono.error(new CustomNotFoundException("Account " + number + " not found")))
        .bodyToMono(AccountResponse.class);
  }
}
