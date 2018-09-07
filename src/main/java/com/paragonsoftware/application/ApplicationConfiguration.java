package com.paragonsoftware.application;

import com.paragonsoftware.db.Author;
import com.paragonsoftware.db.Book;
import com.paragonsoftware.db.History;
import com.paragonsoftware.db.Stack;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Author.class, Book.class, History.class, Stack.class);
    }


}
