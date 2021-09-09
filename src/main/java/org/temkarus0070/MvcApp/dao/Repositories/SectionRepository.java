package org.temkarus0070.MvcApp.dao.Repositories;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section,Integer> {
    @Override
            @Cacheable(key = "section", value = "#s.id")
    <S extends Section> S save(S s);

    @Override
    @CacheEvict(key = "section",value = "#s.id")
    void deleteById(Integer integer);
}
