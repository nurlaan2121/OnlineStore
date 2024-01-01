package models.daos;

import exceptions.Notfound;
import models.Airpods;
import models.Categorie;
import models.Laptops;
import models.generics.GenericDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaptopDoaImpl implements GenericDaoInterface<Laptops> {
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "nurlan21");

    public LaptopDoaImpl() throws SQLException {
    }


    @Override
    public boolean add(Laptops laptops) throws SQLException {
        String addnewLaptop = "insert into laptop(price, created_at, category_id,status,model) VALUES (?,current_date,1,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addnewLaptop);
        preparedStatement.setInt(1,laptops.getPrice());
        preparedStatement.setBoolean(2,laptops.isStatus());
        preparedStatement.setString(3,laptops.getModel());
        int i = preparedStatement.executeUpdate();
        return i > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        String delete = "delete from laptop where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setInt(1,id);
        int i = preparedStatement.executeUpdate();
        return i > 0;
    }

    @Override
    public List<Laptops> getall() throws SQLException {
        String getall = "select * from laptop";
        List<Laptops> laptopsList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(getall);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Laptops laptop = new Laptops();
            laptop.setPrice(resultSet.getInt("price"));
            laptop.setId(resultSet.getInt("id"));
            laptop.setModel(resultSet.getString("model"));
            laptop.setCreatedAt(resultSet.getDate("created_at"));
            laptop.setCategories(Categorie.LAPTOP);
            laptopsList.add(laptop);
        }
        return laptopsList;
    }

    @Override
    public String update(int id, Laptops laptops, int choose, int category) throws SQLException {
            if (choose == 1) {
                String update = "update laptop set price = ? where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(update);
                preparedStatement.setInt(1, laptops.getPrice());
                preparedStatement.setInt(2, id);
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    return "Success";
                }
            }
            if (choose == 2) {
                String update = "update laptop set category_id = ? where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(update);
                preparedStatement.setInt(1,category);
                preparedStatement.setInt(2,id);
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    return "Success";
                }
            }
            if (choose == 3) {
                String update = "update laptop set status = ? where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(update);
                preparedStatement.setBoolean(1, laptops.isStatus());
                preparedStatement.setInt(2, id);
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    return "Success";
                }
            } if (choose == 4) {
                String update = "update laptop set model = ? where id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(update);
                preparedStatement.setString(1, laptops.getModel());
                preparedStatement.setInt(2, id);
                int i = preparedStatement.executeUpdate();
                if (i > 0) {
                    return "Success";
                }
            }
            throw  new Notfound("Not found");

    }
}
