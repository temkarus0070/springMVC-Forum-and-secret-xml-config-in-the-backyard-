package org.temkarus0070.MvcApp.dao.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temkarus0070.MvcApp.models.Comment;
import org.temkarus0070.MvcApp.models.Section;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
