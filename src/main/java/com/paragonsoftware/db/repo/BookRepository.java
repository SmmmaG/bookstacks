package com.paragonsoftware.db.repo;

import com.paragonsoftware.db.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author IvIsmakaev@RepositoryRestResource(path = "book", collectionResourceRel = "book")
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
}
