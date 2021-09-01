package org.temkarus0070.MvcApp.dao.Mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.temkarus0070.MvcApp.models.Post;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class PostMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Post(resultSet.getInt("id"),resultSet.getString("author_id"),resultSet.getString("text"),resultSet.getInt("section_id"));
    }
}
