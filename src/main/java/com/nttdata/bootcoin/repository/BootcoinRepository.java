package com.nttdata.bootcoin.repository;

import com.nttdata.bootcoin.model.Bootcoin;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Bootcoin repository.
 */
@Repository
public interface BootcoinRepository extends ReactiveMongoRepository<Bootcoin, ObjectId> {
  Mono<Bootcoin> findByDocumentNumber(String documentNumber);
}
