package models.impls;

import exceptions.Notfound;
import models.*;
import models.daos.AirpodsDoaImpl;
import models.daos.LaptopDoaImpl;
import models.daos.TelephoneDoaImpl;
import models.daos.TvDoaImpl;
import models.generics.GenericChecks;
import models.generics.GenericInterFace;

import java.sql.SQLException;
import java.util.*;

public class AirpodsImpl implements GenericInterFace<Airpods> {
    TelephoneDoaImpl telephoneDoa = new TelephoneDoaImpl();
    TvDoaImpl tvDoa = new TvDoaImpl();
    AirpodsDoaImpl airpodsDoa = new AirpodsDoaImpl();
    LaptopDoaImpl laptop = new LaptopDoaImpl();

    public AirpodsImpl() throws SQLException {
    }

    public List<Electronics> sortByPriceAll(String ascOrDesc) throws SQLException {
        List<Electronics> allProductInDateBase = new ArrayList<>();
        List<Telephones> getall3 = telephoneDoa.getall();
        List<Tvs> getall2 = tvDoa.getall();
        List<Laptops> getall1 = laptop.getall();
        List<Airpods> getall = airpodsDoa.getall();
        allProductInDateBase.addAll(getall3);
        allProductInDateBase.addAll(getall2);
        allProductInDateBase.addAll(getall1);
        allProductInDateBase.addAll(getall);
        Comparator<Electronics> sortByPrice = new Comparator<Electronics>() {
            @Override
            public int compare(Electronics o1, Electronics o2) {
                return o1.getPrice() - o2.getPrice();
            }
        };
        if (ascOrDesc.equalsIgnoreCase("Asc")){
            allProductInDateBase.sort(sortByPrice);
            return allProductInDateBase;
        } else if (ascOrDesc.equalsIgnoreCase("Desc")) {
            allProductInDateBase.sort(sortByPrice.reversed());
            return allProductInDateBase;
        }
        throw  new Notfound("Write correct!");
    }

    @Override
    public String create(Airpods airpods) throws SQLException {
        while (true) {
            System.out.println("Write model :");
            String brand = new Scanner(System.in).nextLine();
            if (GenericChecks.check(brand)) {
                airpods.setModel(brand);
                break;
            }
        }
        while (true) {
            try {
                System.out.println("Write price :");
                int brand = new Scanner(System.in).nextInt();
                if (brand > 0) {
                    airpods.setPrice(brand);
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
                airpods.setStatus(true);
                break;
            } else if (brand.equalsIgnoreCase("old")) {
                airpods.setStatus(false);
                break;
            }
        }
        airpods.setCategories(Categorie.AIRPODS);
        airpodsDoa.add(airpods);
        return "Success";
    }

    @Override
    public Airpods findById(int id) throws SQLException {
        return airpodsDoa.getall().stream().filter(airpods -> airpods.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
    }

    @Override
    public Airpods findByName(String name) throws SQLException {
        return airpodsDoa.getall().stream().filter(airpods -> airpods.getModel().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new Notfound("Not found"));
    }

    @Override
    public String remove(int id) throws SQLException {
        airpodsDoa.getall().stream().filter(airpods -> airpods.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
        airpodsDoa.delete(id);
        return "Success";
    }

    @Override
    public List<Airpods> getAllByIdCategory() throws SQLException {
        return airpodsDoa.getall();
    }

    @Override
    public String update(int id) throws SQLException {
        getAllByIdCategory().stream().filter(airpods -> airpods.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
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
                            Airpods airpods = new Airpods();
                            int price = new Scanner(System.in).nextInt();
                            if (price > 0) {
                                airpods.setPrice(price);
                                return airpodsDoa.update(id, airpods, 1, 1);
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write price");
                        }
                    }
                }case 2->{
                    while (true) {
                        System.out.println("Write category id");
                        try {
                            Airpods airpods = new Airpods();
                            int categoryId = new Scanner(System.in).nextInt();
                            if (categoryId>0&&categoryId<5) {
                                return airpodsDoa.update(id, airpods, 2, categoryId);
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
                            Airpods airpods = new Airpods();
                            airpods.setStatus(true);
                            return airpodsDoa.update(id,airpods,3,1);

                        } else if (brand.equalsIgnoreCase("old")) {
                            Airpods airpods = new Airpods();
                            airpods.setStatus(false);
                            return airpodsDoa.update(id,airpods,3,1);

                        }
                    }
                }case 4->{
                    while (true) {
                        System.out.println("Write model :");
                        String brand = new Scanner(System.in).nextLine();
                        if (GenericChecks.check(brand)) {
                            Airpods airpods = new Airpods();
                            airpods.setModel(brand);
                            return airpodsDoa.update(id,airpods,4,1);

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
    public List<Airpods> sortByPrice(String ascOrDesc) throws SQLException {
        List<Airpods> allByIdCategory = getAllByIdCategory();
        Comparator<Airpods> comparator = new Comparator<Airpods>() {
            @Override
            public int compare(Airpods o1, Airpods o2) {
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
