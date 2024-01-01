package models.daos;

import exceptions.Notfound;
import models.Categorie;
import models.Telephones;
import models.Tvs;
import models.generics.GenericDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TvDoaImpl implements GenericDaoInterface<Tvs> {
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nurlan21");

    public TvDoaImpl() throws SQLException {
    }

    @Override
    public boolean add(Tvs tvs) throws SQLException {
        String addnewTv = "insert into tv(price, created_at, category_id,status,model) VALUES (?,current_date,1,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addnewTv);
        preparedStatement.setInt(1, tvs.getPrice());
        preparedStatement.setBoolean(2, tvs.isStatus());
        preparedStatement.setString(3, tvs.getModel());
        int i = preparedStatement.executeUpdate();
        return i > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String delete = "delete from tv where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, id);
        int i = preparedStatement.executeUpdate();
        return i > 0;
    }

    @Override
    public List<Tvs> getall() throws SQLException {
        String getall = "select * from tv";
        List<Tvs> tvsList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(getall);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Tvs tvs = new Tvs();
            tvs.setPrice(resultSet.getInt("price"));
            tvs.setId(resultSet.getInt("id"));
            tvs.setModel(resultSet.getString("model"));
            tvs.setCreatedAt(resultSet.getDate("created_at"));
            tvs.setCategories(Categorie.TV);
            tvsList.add(tvs);
        }
        return tvsList;
    }

    @Override
    public String update(int id, Tvs tvs, int choose, int category) throws SQLException {
        if (choose == 1) {
            String update = "update tv set price = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, tvs.getPrice());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        if (choose == 2) {
            String update = "update tv set category_id = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1,category);
            preparedStatement.setInt(2,id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        if (choose == 3) {
            String update = "update tv set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setBoolean(1, tvs.isStatus());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        } if (choose == 4) {
            String update = "update tv set model = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, tvs.getModel());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        throw  new Notfound("Not found");
    }
}
