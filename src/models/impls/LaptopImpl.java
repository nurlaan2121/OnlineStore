package models.impls;

import exceptions.Notfound;
import models.Airpods;
import models.Categorie;
import models.Laptops;
import models.daos.LaptopDoaImpl;
import models.generics.GenericChecks;
import models.generics.GenericInterFace;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LaptopImpl implements GenericInterFace<Laptops> {
    LaptopDoaImpl laptopDao = new LaptopDoaImpl();

    public LaptopImpl() throws SQLException {
    }

    @Override
    public String create(Laptops laptops) throws SQLException {
        while (true) {
            System.out.println("Write model :");
            String brand = new Scanner(System.in).nextLine();
            if (GenericChecks.check(brand)) {
                laptops.setModel(brand);
                break;
            }
        }
        while (true) {
            try {
                System.out.println("Write price :");
                int brand = new Scanner(System.in).nextInt();
                if (brand > 0) {
                    laptops.setPrice(brand);
                    break;
                }
            } catch (InputMismatchException w) {
                System.out.println("Write price");
            }

        }
        while (true) {
            System.out.println("Write status new || old:");
            String brand = new Scanner(System.in).nextLine();
            if (brand.equalsIgnoreCase("new")) {
                laptops.setStatus(true);
                break;
            } else if (brand.equalsIgnoreCase("old")) {
                laptops.setStatus(false);
                break;
            }
        }
        laptops.setCategories(Categorie.LAPTOP);
        laptopDao.add(laptops);
        return "Success";
    }

    @Override
    public Laptops findById(int id) throws SQLException {
        return laptopDao.getall().stream().filter(laptops -> laptops.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
    }

    @Override
    public Laptops findByName(String name) throws SQLException {
        return laptopDao.getall().stream().filter(laptops -> laptops.getModel().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new Notfound("Not found"));

    }

    @Override
    public String remove(int id) throws SQLException {
        laptopDao.getall().stream().filter(laptops -> laptops.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
        laptopDao.delete(id);
        return "Success";
    }

    @Override
    public List<Laptops> getAllByIdCategory() throws SQLException {
        return laptopDao.getall();
    }

    @Override
    public String update(int id) throws SQLException {
        getAllByIdCategory().stream().filter(laptops -> laptops.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
        System.out.println("""
                Choose command
                1.Price
                2.Category
                3.Status
                4.Model
                """);
        try {
            int action = new Scanner(System.in).nextInt();
            switch (action) {
                case 1 -> {
                    while (true) {
                        System.out.println("Write price");
                        try {
                            Laptops laptops = new Laptops();
                            int price = new Scanner(System.in).nextInt();
                            if (price > 0) {
                                laptops.setPrice(price);
                                return laptopDao.update(id, laptops, 1, 1);
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write price");
                        }
                    }
                }case 2->{
                    while (true) {
                        System.out.println("Write category id");
                        try {
                            Laptops laptops = new Laptops();
                            int categoryId = new Scanner(System.in).nextInt();
                            if (categoryId>0&&categoryId<5) {
                                return laptopDao.update(id, laptops, 2, categoryId);
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write category id");
                        }
                    }
                }case 3->{
                    while (true) {
                        System.out.println("Write status new || old:");
                        String brand = new Scanner(System.in).nextLine();
                        if (brand.equalsIgnoreCase("new")) {
                            Laptops laptops = new Laptops();
                            laptops.setStatus(true);
                            return laptopDao.update(id,laptops,3,1);

                        } else if (brand.equalsIgnoreCase("old")) {
                            Laptops laptops = new Laptops();
                            laptops.setStatus(false);
                            return laptopDao.update(id,laptops,3,1);

                        }
                    }
                }case 4->{
                    while (true) {
                        System.out.println("Write model :");
                        String brand = new Scanner(System.in).nextLine();
                        if (GenericChecks.check(brand)) {
                            Laptops laptops = new Laptops();
                            laptops.setModel(brand);
                            return laptopDao.update(id,laptops,4,1);

                        }
                    }
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Write number");
        }
        throw  new Notfound("Not found");
    }

    @Override
    public List<Laptops> sortByPrice(String ascOrDesc) throws SQLException {
        List<Laptops> allByIdCategory = getAllByIdCategory();
        Comparator<Laptops> comparator = new Comparator<Laptops>() {
            @Override
            public int compare(Laptops o1, Laptops o2) {
                return o1.getPrice() - o2.getPrice();
            }
        };
        if (ascOrDesc.equalsIgnoreCase("Asc")){
            allByIdCategory.sort(comparator);
            return allByIdCategory;
        }else if (ascOrDesc.equalsIgnoreCase("desc")){
            allByIdCategory.sort(comparator.reversed());
            return allByIdCategory;
        }
        throw  new  Notfound("Write correct!");
    }
}
