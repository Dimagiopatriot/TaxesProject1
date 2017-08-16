package model.dao;

/**
 * Created by troll on 16.08.2017.
 */
public interface GenericDao<T> {

    void delete(int id);
    void update(T t);
    void insert(T t);
    T select(int id);
}
