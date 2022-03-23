package com.nttdata.bootcoin.service;

import static com.nttdata.bootcoin.utilities.Constants.UserType.SELLER;

import com.nttdata.bootcoin.dto.request.BootcoinRequest;
import com.nttdata.bootcoin.exceptions.customs.CustomInformationException;
import com.nttdata.bootcoin.exceptions.customs.CustomNotFoundException;
import com.nttdata.bootcoin.model.Bootcoin;
import com.nttdata.bootcoin.repository.BootcoinRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Bootcoin service implementation.
 */
@Service
@RequiredArgsConstructor
public class BootcoinServiceImpl implements BootcoinService {
  private static final Logger logger = LogManager.getLogger(BootcoinServiceImpl.class);

  private final BootcoinRepository bootcoinRepository;
  private final AccountService accountService;

  @Override
  public Mono<Bootcoin> findByDocumentNumber(String documentNumber) {
    return bootcoinRepository.findByDocumentNumber(documentNumber)
        .switchIfEmpty(Mono.error(new CustomNotFoundException("Data not found")));
  }

  @Override
  public Mono<Bootcoin> create(BootcoinRequest request) {
    return bootcoinRepository.findByDocumentNumber(request.getDocumentNumber())
        .doOnNext(res -> Mono
            .error(new CustomInformationException("A bootcoin account with "
                + "that document number already exists")))
        .switchIfEmpty(validateInformation(request)
            .flatMap(req -> setBootcoin(req)
                .flatMap(bootcoin -> bootcoinRepository.save(bootcoin)
                    .map(x -> {
                      logger.info("Created a new bootcoin id = {}", bootcoin.getId());
                      return x;
                    }))
            ));
  }

  private Mono<BootcoinRequest> validateInformation(BootcoinRequest request) {
    if (request.getProfile() == SELLER && StringUtils.isBlank(request.getAccountNumber())) {
      return Mono.error(new CustomInformationException("The seller profile "
          + "requires an account number"));
    }

    return Mono.just(request);
  }

  private Mono<Bootcoin> setBootcoin(BootcoinRequest request) {
    Mono<Bootcoin> monoBootcoin = Mono.just(new Bootcoin(request));

    if (request.getProfile() == SELLER) {
      return monoBootcoin.
          flatMap(bootcoin -> accountService
              .findByNumberAndClientDocumentNumber(request.getAccountNumber(), request.getDocumentNumber())
              .doOnNext(ac -> bootcoin.setIdAccount(new ObjectId(ac.getId()))))
          .then(monoBootcoin);
    } else {
      return monoBootcoin;
    }
  }
}
