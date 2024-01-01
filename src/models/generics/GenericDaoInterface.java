package models.generics;

import java.io.LineNumberInputStream;
import java.sql.SQLException;
import java.util.List;

public interface GenericDaoInterface<T> {
    public boolean add(T t) throws SQLException;
    public boolean delete(int id) throws SQLException;
    public List<T> getall() throws SQLException;
    String update(int id,T t,int choose,int category) throws SQLException;
}
