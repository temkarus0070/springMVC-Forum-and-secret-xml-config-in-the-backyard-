package org.temkarus0070.MvcApp.dao.Repositories;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.Comment;
import org.temkarus0070.MvcApp.models.Section;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    @CacheEvict(key = "#s.post.id", value = {"post", "posts"}, allEntries = true)
    <S extends Comment> S save(S s);

    @Override
    @CacheEvict(value = {"posts", "post"}, allEntries = true)
    void deleteById(Long aLong);

    @Override
    @CacheEvict(value = {"post"}, allEntries = true)
    void delete(Comment comment);
}
