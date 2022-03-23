package com.nttdata.bootcoin.service;

import com.nttdata.bootcoin.dto.request.BootcoinRequest;
import com.nttdata.bootcoin.model.Bootcoin;
import reactor.core.publisher.Mono;

/**
 * Bootcoin service interface.
 */
public interface BootcoinService {
  Mono<Bootcoin> findByDocumentNumber(String documentNumber);

  Mono<Bootcoin> create(BootcoinRequest request);
}
