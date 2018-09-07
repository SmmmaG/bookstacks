package com.paragonsoftware.db.repo;

import com.paragonsoftware.db.Stack;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author IvIsmakaev@RepositoryRestResource(path = "stack", collectionResourceRel = "stack")
 */
public interface StackRepository extends PagingAndSortingRepository<Stack, Long> {
}
