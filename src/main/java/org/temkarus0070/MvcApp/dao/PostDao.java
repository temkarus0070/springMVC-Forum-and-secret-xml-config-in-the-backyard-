package org.temkarus0070.MvcApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.temkarus0070.MvcApp.dao.Mappers.PostMapper;
import org.temkarus0070.MvcApp.models.Post;


import java.util.List;
import java.util.TreeMap;

@Component
public class PostDao implements Repository<Post>{
    private PostMapper postMapper;
    private final String INDEX_SQL="SELECT * FROM Posts";
    private final String FIND_SQL="SELECT * FROM Posts WHERE post_id=:id";
    private final String DELETE_SQL="delete from Posts WHERE post_id=:id";
    private final String UPDATE_SQL="UPDATE Posts SET text=:text,section_id=:sectionId";
    private final String ADD_SQL="INSERT INTO Posts(text,author_id,section_id) VALUES(:text,:authorId,:sectionId)";
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<Post> index() {
        return jdbcTemplate.query(INDEX_SQL,postMapper );
    }

    @Override
    public Post find(int id){
        TreeMap<String,Integer> paramsMap=new TreeMap<>();
        paramsMap.put("id",id);
        return jdbcTemplate.query(FIND_SQL,paramsMap,postMapper).stream().findAny().orElse(null);
    }

    @Override
    public void delete(int id) {
        TreeMap<String,Integer> params=new TreeMap<>();
        params.put("id",id);
        jdbcTemplate.update(DELETE_SQL,params);
    }

    @Override
    public void update(Post entity) {
        TreeMap<String,Object> params=new TreeMap<>();
        params.put("text",entity.getText());
        params.put("sectionId",entity.getSectionId());
        jdbcTemplate.update(UPDATE_SQL,params);
    }

    @Override
    public void add(Post entity) {
        TreeMap<String,Object> params=new TreeMap<>();
        params.put("authorId",entity.getAuthorId());
        params.put("sectionId",entity.getSectionId());
        params.put("text",entity.getText());
        jdbcTemplate.update(ADD_SQL,params);
    }


}
