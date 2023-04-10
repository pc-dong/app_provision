package cn.dpc.provision.persistence.repository;

import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeatureFlagDBRepository extends ReactiveSortingRepository<FeatureFlagDB, String> {
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND description.status != 'DELETED' offset $1 limit $2 ")
    Flux<FeatureFlagDB> findAllWithPage(long offset, int limit);

    @Query("SELECT COUNT(1) from #{#n1ql.bucket} WHERE #{#n1ql.filter} AND description.status != 'DELETED' ")
    Mono<Long> countNotDeleted();
}
