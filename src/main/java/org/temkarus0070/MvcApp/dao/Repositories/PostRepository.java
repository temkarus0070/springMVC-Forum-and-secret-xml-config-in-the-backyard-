package org.temkarus0070.MvcApp.dao.Repositories;

import liquibase.pro.packaged.S;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.Post;
import org.temkarus0070.MvcApp.models.Section;
import org.temkarus0070.MvcApp.models.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    @Cacheable(value = "postsBySection")
    List<Post> findAllBySectionId(int sectionId);

    @Override
    @Cacheable(value = "post", key = "#id")
    Optional<Post> findById(Integer id);

    @Override
    @Cacheable(value = "posts")
    List<Post> findAll();

    @Override
    @CacheEvict(value = {"post","posts","postsBySection"},key = "#id", allEntries = true)
    void deleteById(Integer id);

    @Cacheable(value = "posts", key = "#header")
    List<Post> getPostsByHeaderContainingIgnoreCase(String header);

   Long countPostsByUserUsername(String username);



    @CacheEvict(value = {"posts","postsBySection","post"},allEntries = true)
    @CachePut(value = "post",key = "#s.id")
    @Override
    <S extends Post> S save(S s);
}
