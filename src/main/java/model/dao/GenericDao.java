package model.dao;

import java.util.Optional;

/**
 * Created by troll on 16.08.2017.
 */
public interface GenericDao<T> {

    void delete(int id);
    void update(T t);
    void insert(T t);
    Optional<T> select(int id);
}
