package com.paragonsoftware.db.repo;

import com.paragonsoftware.db.Author;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author IvIsmakaev@RepositoryRestResource(path = "author", collectionResourceRel = "author")
 */
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
}
