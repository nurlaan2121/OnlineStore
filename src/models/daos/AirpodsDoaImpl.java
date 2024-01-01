package models.daos;

import exceptions.Notfound;
import models.Airpods;
import models.Categorie;
import models.generics.GenericDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirpodsDoaImpl implements GenericDaoInterface<Airpods> {
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nurlan21");

    public AirpodsDoaImpl() throws SQLException {
    }

    @Override
    public boolean add(Airpods airpods) throws SQLException {
        String addnewAirpods = "insert into airpods(price, created_at, category_id,status,model) VALUES (?,current_date,2,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addnewAirpods);
        preparedStatement.setInt(1, airpods.getPrice());
        preparedStatement.setBoolean(2, airpods.isStatus());
        preparedStatement.setString(3, airpods.getModel());
        int i = preparedStatement.executeUpdate();
        return i > 0;

    }

    @Override
    public boolean delete(int id) throws SQLException {
        String delete = "delete from airpods where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1, id);
        int i = preparedStatement.executeUpdate();
        return i > 0;
    }

    @Override
    public List<Airpods> getall() throws SQLException {
        String getall = "select * from airpods";
        List<Airpods> airpodsList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(getall);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Airpods airpods = new Airpods();
            airpods.setPrice(resultSet.getInt("price"));
            airpods.setId(resultSet.getInt("id"));
            airpods.setModel(resultSet.getString("model"));
            airpods.setCreatedAt(resultSet.getDate("created_at"));
            airpods.setCategories(Categorie.AIRPODS);
            airpodsList.add(airpods);
        }
        return airpodsList;
    }

    @Override
    public String update(int id, Airpods airpods, int choose,int categoria) throws SQLException {
        if (choose == 1) {
            String update = "update airpods set price = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1, airpods.getPrice());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        if (choose == 2) {
            String update = "update airpods set category_id = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setInt(1,categoria);
            preparedStatement.setInt(2,id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        if (choose == 3) {
            String update = "update airpods set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setBoolean(1, airpods.isStatus());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        } if (choose == 4) {
            String update = "update airpods set model = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, airpods.getModel());
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                return "Success";
            }
        }
        throw  new Notfound("Not found");
    }
}
