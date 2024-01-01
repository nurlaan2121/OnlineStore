package models.impls;

import exceptions.Notfound;
import models.Categorie;
import models.Laptops;
import models.Telephones;
import models.daos.TelephoneDoaImpl;
import models.generics.GenericChecks;
import models.generics.GenericInterFace;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TelephoneImpl implements GenericInterFace<Telephones> {
    TelephoneDoaImpl telephoneDoa = new TelephoneDoaImpl();

    public TelephoneImpl() throws SQLException {
    }

    @Override
    public String create(Telephones telephones) throws SQLException {
        while (true) {
            System.out.println("Write model :");
            String brand = new Scanner(System.in).nextLine();
            if (GenericChecks.check(brand)) {
                telephones.setModel(brand);
                break;
            }
        }
        while (true) {
            try {
                System.out.println("Write price :");
                int brand = new Scanner(System.in).nextInt();
                if (brand > 0) {
                    telephones.setPrice(brand);
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
                telephones.setStatus(true);
                break;
            } else if (brand.equalsIgnoreCase("old")) {
                telephones.setStatus(false);
                break;
            }
        }
        telephones.setCategories(Categorie.TELEPHONE);
        telephoneDoa.add(telephones);
        return "Success";
    }

    @Override
    public Telephones findById(int id) throws SQLException {
        return telephoneDoa.getall().stream().filter(telephones -> telephones.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
    }

    @Override
    public Telephones findByName(String name) throws SQLException {
        return telephoneDoa.getall().stream().filter(laptops -> laptops.getModel().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new Notfound("Not found"));

    }

    @Override
    public String remove(int id) throws SQLException {
        telephoneDoa.getall().stream().filter(laptops -> laptops.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
        telephoneDoa.delete(id);
        return "Success";
    }

    @Override
    public List<Telephones> getAllByIdCategory() throws SQLException {
        return telephoneDoa.getall();
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
                            Telephones laptops = new Telephones();
                            int price = new Scanner(System.in).nextInt();
                            if (price > 0) {
                                laptops.setPrice(price);
                                return telephoneDoa.update(id, laptops, 1, 1);
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write price");
                        }
                    }
                }case 2->{
                    while (true) {
                        System.out.println("Write category id");
                        try {
                            Telephones laptops = new Telephones();
                            int categoryId = new Scanner(System.in).nextInt();
                            if (categoryId>0&&categoryId<5) {
                                return telephoneDoa.update(id, laptops, 2, categoryId);
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
                            Telephones laptops = new Telephones();
                            laptops.setStatus(true);
                            return telephoneDoa.update(id,laptops,3,1);

                        } else if (brand.equalsIgnoreCase("old")) {
                            Telephones laptops = new Telephones();
                            laptops.setStatus(false);
                            return telephoneDoa.update(id,laptops,3,1);

                        }
                    }
                }case 4->{
                    while (true) {
                        System.out.println("Write model :");
                        String brand = new Scanner(System.in).nextLine();
                        if (GenericChecks.check(brand)) {
                            Telephones laptops = new Telephones();
                            laptops.setModel(brand);
                            return telephoneDoa.update(id,laptops,4,1);

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
    public List<Telephones> sortByPrice(String ascOrDesc) throws SQLException {
        List<Telephones> allByIdCategory = getAllByIdCategory();
        Comparator<Telephones> comparator = new Comparator<Telephones>() {
            @Override
            public int compare(Telephones o1, Telephones o2) {
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
