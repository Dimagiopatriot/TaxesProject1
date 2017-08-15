package model.dao;

/**
 * Created by troll on 16.08.2017.
 */
public interface GenericDao<T> {

    void delete(String query);
    void update(String query);
    void insert(String query);
    T select(String query);
}
