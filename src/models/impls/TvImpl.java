package models.impls;

import exceptions.Notfound;
import models.Categorie;
import models.Telephones;
import models.Tvs;
import models.daos.TvDoaImpl;
import models.generics.GenericChecks;
import models.generics.GenericInterFace;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TvImpl implements GenericInterFace<Tvs> {
    TvDoaImpl tvDoa = new TvDoaImpl();

    public TvImpl() throws SQLException {
    }

    @Override
    public String create(Tvs tvs) throws SQLException {
        while (true) {
            System.out.println("Write model :");
            String brand = new Scanner(System.in).nextLine();
            if (GenericChecks.check(brand)) {
                tvs.setModel(brand);
                break;
            }
        }
        while (true) {
            try {
                System.out.println("Write price :");
                int brand = new Scanner(System.in).nextInt();
                if (brand > 0) {
                    tvs.setPrice(brand);
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
                tvs.setStatus(true);
                break;
            } else if (brand.equalsIgnoreCase("old")) {
                tvs.setStatus(false);
                break;
            }
        }
        tvs.setCategories(Categorie.TV);
        tvDoa.add(tvs);
        return "Success";
    }

    @Override
    public Tvs findById(int id) throws SQLException {
        return tvDoa.getall().stream().filter(telephones -> telephones.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));

    }

    @Override
    public Tvs findByName(String name) throws SQLException {
        return tvDoa.getall().stream().filter(telephones -> telephones.getModel().equalsIgnoreCase(name)).findFirst().orElseThrow(() -> new Notfound("Not found"));

    }

    @Override
    public String remove(int id) throws SQLException {
        tvDoa.getall().stream().filter(laptops -> laptops.getId() == id).findFirst().orElseThrow(() -> new Notfound("Not found"));
        tvDoa.delete(id);
        return "Success";
    }

    @Override
    public List<Tvs> getAllByIdCategory() throws SQLException {
        return tvDoa.getall();

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
                            Tvs laptops = new Tvs();
                            int price = new Scanner(System.in).nextInt();
                            if (price > 0) {
                                laptops.setPrice(price);
                                return tvDoa.update(id, laptops, 1, 1);
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write price");
                        }
                    }
                }case 2->{
                    while (true) {
                        System.out.println("Write category id");
                        try {
                            Tvs laptops = new Tvs();
                            int categoryId = new Scanner(System.in).nextInt();
                            if (categoryId>0&&categoryId<5) {
                                return tvDoa.update(id, laptops, 2, categoryId);
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
                            Tvs laptops = new Tvs();
                            laptops.setStatus(true);
                            return tvDoa.update(id,laptops,3,1);

                        } else if (brand.equalsIgnoreCase("old")) {
                            Tvs laptops = new Tvs();
                            laptops.setStatus(false);
                            return tvDoa.update(id,laptops,3,1);

                        }
                    }
                }case 4->{
                    while (true) {
                        System.out.println("Write model :");
                        String brand = new Scanner(System.in).nextLine();
                        if (GenericChecks.check(brand)) {
                            Tvs laptops = new Tvs();
                            laptops.setModel(brand);
                            return tvDoa.update(id,laptops,4,1);

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
    public List<Tvs> sortByPrice(String ascOrDesc) {
        return null;
    }
}
