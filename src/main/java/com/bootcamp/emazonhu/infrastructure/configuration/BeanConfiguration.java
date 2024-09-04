package com.bootcamp.emazonhu.infrastructure.configuration;

import com.bootcamp.emazonhu.domain.api.ICategoryServicePort;
import com.bootcamp.emazonhu.domain.spi.ICategoryPersistencePort;
import com.bootcamp.emazonhu.domain.usecase.CategoryUseCase;
import com.bootcamp.emazonhu.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.bootcamp.emazonhu.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.bootcamp.emazonhu.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }

}
