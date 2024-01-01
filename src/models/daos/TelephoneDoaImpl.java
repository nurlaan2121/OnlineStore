package models.daos;

import exceptions.Notfound;
import models.Categorie;
import models.Laptops;
import models.Telephones;
import models.generics.GenericDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelephoneDoaImpl implements GenericDaoInterface<Telephones> {
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nurlan21");

    public TelephoneDoaImpl() throws SQLException {
    }


    @Override
    public boolean add(Telephones telephones) throws SQLException {
        String addnewTelephone = "insert into telephone(price, created_at, category_id,status,model) VALUES (?,current_date,1,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addnewTelephone);
        preparedStatement.setInt(1, telephones.getPrice());
        preparedStatement.setBoolean(2, telephones.isStatus());
        preparedStatement.setString(3, telephones.getModel());
        int i = preparedStatement.executeUpdate();
        return i > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String delete = "delete from telephone where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1,id);
        int i = preparedStatement.executeUpdate();
        return i > 0;
    }

    @Override
    public List<Telephones> getall() throws SQLException {
        String getall = "select * from telephone";
        List<Telephones> telephoneslist = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(getall);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Telephones telephones = new Telephones();
            telephones.setPrice(resultSet.getInt("price"));
            telephones.setId(resultSet.getInt("id"));
            telephones.setModel(resultSet.getString("model"));
            telephones.setCreatedAt(resultSet.getDate("created_at"));
            telephones.setCategories(Categorie.TELEPHONE);
            telephoneslist.add(telephones);
        }
        return telephoneslist;
    }

    @Override
    public String update(int id, Telephones telephones, int choose, int category) throws SQLException {
        if (choose == 1) {
            String update = "update telephone set price = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, telephones.getPrice());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        if (choose == 2) {
            String update = "update telephone set category_id = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1,category);
            preparedStatement.setInt(2,id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        if (choose == 3) {
            String update = "update telephone set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setBoolean(1, telephones.isStatus());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        } if (choose == 4) {
            String update = "update telephone set model = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, telephones.getModel());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        throw  new Notfound("Not found");
    }
}
