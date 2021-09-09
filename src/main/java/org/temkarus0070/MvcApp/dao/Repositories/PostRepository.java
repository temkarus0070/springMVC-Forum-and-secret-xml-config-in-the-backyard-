package org.temkarus0070.MvcApp.dao.Repositories;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.Post;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    @Cacheable("appCache")
    public List<Post> findAllBySectionId(int sectionId);

    @Override
    @Cacheable("appCache")
    Optional<Post> findById(Integer integer);

    @Override
    @Cacheable("appCache")
    List<Post> findAll();

    @Override
    @CacheEvict(value = "post")
    void deleteById(Integer integer);
}
