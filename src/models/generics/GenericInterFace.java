package models.generics;

import java.sql.SQLException;
import java.util.List;

public interface GenericInterFace<T> {
    String create(T t) throws SQLException;
    T findById(int id) throws SQLException;
    T findByName(String name) throws SQLException;
    String remove (int id) throws SQLException;
    List<T> getAllByIdCategory() throws SQLException;
    String update(int id) throws SQLException;
    List<T> sortByPrice(String ascOrDesc) throws SQLException;


}
