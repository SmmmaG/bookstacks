package com.paragonsoftware.db.repo;

import com.paragonsoftware.db.History;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author IvIsmakaev@RepositoryRestResource(path = "history", collectionResourceRel = "history")
 */
public interface HistoryRepository extends PagingAndSortingRepository<History, Long> {
}
