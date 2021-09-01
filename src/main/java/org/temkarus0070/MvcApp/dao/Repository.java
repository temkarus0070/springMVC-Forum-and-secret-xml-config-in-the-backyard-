package org.temkarus0070.MvcApp.dao;

import java.util.List;

public interface Repository<T> {
    public List<T> index();
    public T find(int id);
    public void delete(int id);
    public void update(T entity);
    public void add(T entity);
}
