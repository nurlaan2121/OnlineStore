import models.Airpods;
import models.Laptops;
import models.Telephones;
import models.Tvs;
import models.impls.AirpodsImpl;
import models.impls.LaptopImpl;
import models.impls.TelephoneImpl;
import models.impls.TvImpl;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        AirpodsImpl airpods = new AirpodsImpl();
        LaptopImpl laptop = new LaptopImpl();
        TelephoneImpl telephone = new TelephoneImpl();
        TvImpl tv = new TvImpl();
        LOOOPMAIN:
        while (true) {
            System.out.println("""
                    Choose command
                    1.Create sale
                    2.Find product By Id  in category
                    3.Find product By Name in category
                    4.Remove product
                    5.Get All By Id Category
                    6.Update product
                    7.Sort By Price
                    8.All sort by price
                    0.Exit
                    """);
            try {
                int action = new Scanner(System.in).nextInt();
                switch (action) {
                    case 1 -> {
                        System.out.println("Choose category");
                        System.out.println("""
                                1-( LAPTOP )
                                2-( AIRPODS )
                                3-( TV )
                                4-( TELEPHONE )
                                """);
                        try {
                            int category = new Scanner(System.in).nextInt();
                            switch (category) {
                                case 1 -> {
                                    System.out.println(laptop.create(new Laptops()));
                                }
                                case 2 -> {
                                    System.out.println(airpods.create(new Airpods()));
                                }
                                case 3 -> {
                                    System.out.println(tv.create(new Tvs()));
                                }
                                case 4 -> {
                                    System.out.println(telephone.create(new Telephones()));
                                }
                                default -> System.out.println("Write correct category");
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write correct!");
                        }
                    }
                    case 2 -> {
                        System.out.println("Choose category");
                        System.out.println("""
                                1-( LAPTOP )
                                2-( AIRPODS )
                                3-( TV )
                                4-( TELEPHONE )
                                """);
                        try {
                            int category = new Scanner(System.in).nextInt();
                            switch (category) {
                                case 1 -> {
                                    System.out.println("Write id: ");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(laptop.findById(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 2 -> {
                                    System.out.println("Write id: ");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(airpods.findById(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Write id: ");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(tv.findById(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 4 -> {
                                    System.out.println("Write id: ");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(telephone.findById(id));
                                    } catch (SQLException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                default -> System.out.println("Write correct category");
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write correct!");
                        }
                    }
                    case 3 -> {
                        System.out.println("Choose category");
                        System.out.println("""
                                1-( LAPTOP )
                                2-( AIRPODS )
                                3-( TV )
                                4-( TELEPHONE )
                                """);
                        try {
                            int category = new Scanner(System.in).nextInt();
                            switch (category) {
                                case 1 -> {
                                    System.out.println("Write name: ");
                                    String id = new Scanner(System.in).nextLine();
                                    try {
                                        System.out.println(laptop.findByName(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 2 -> {
                                    System.out.println("Write name: ");
                                    String id = new Scanner(System.in).nextLine();
                                    try {
                                        System.out.println(airpods.findByName(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Write name: ");
                                    String id = new Scanner(System.in).nextLine();
                                    try {
                                        System.out.println(tv.findByName(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 4 -> {
                                    System.out.println("Write id: ");
                                    String id = new Scanner(System.in).nextLine();
                                    try {
                                        System.out.println(telephone.findByName(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                default -> System.out.println("Write correct category");
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write correct!");
                        }
                    }
                    case 4 -> {
                        System.out.println("Choose category");
                        System.out.println("""
                                1-( LAPTOP )
                                2-( AIRPODS )
                                3-( TV )
                                4-( TELEPHONE )
                                """);
                        try {
                            int category = new Scanner(System.in).nextInt();
                            switch (category) {
                                case 1 -> {
                                    System.out.println("Write id for remove: ");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(laptop.remove(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 2 -> {
                                    System.out.println("Write id: ");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(airpods.remove(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Write id: ");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(tv.remove(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 4 -> {
                                    System.out.println("Write id: ");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(telephone.remove(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                default -> System.out.println("Write correct category");
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write correct!");
                        }
                    }
                    case 5 -> {
                        System.out.println("Choose category");
                        System.out.println("""
                                1-( LAPTOP )
                                2-( AIRPODS )
                                3-( TV )
                                4-( TELEPHONE )
                                """);
                        try {
                            int category = new Scanner(System.in).nextInt();
                            switch (category) {
                                case 1 -> {
                                    System.out.println(laptop.getAllByIdCategory());
                                }
                                case 2 -> {
                                    System.out.println(airpods.getAllByIdCategory());
                                }
                                case 3 -> {
                                    System.out.println(tv.getAllByIdCategory());
                                }
                                case 4 -> {
                                    System.out.println(telephone.getAllByIdCategory());
                                }
                                default -> System.out.println("Write correct category");
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write correct!");
                        }
                    }
                    case 6 -> {
                        System.out.println("Choose category");
                        System.out.println("""
                                1-( LAPTOP )
                                2-( AIRPODS )
                                3-( TV )
                                4-( TELEPHONE )
                                """);
                        try {
                            int category = new Scanner(System.in).nextInt();
                            switch (category) {
                                case 1 -> {
                                    System.out.println("Write id");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(laptop.update(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 2 -> {
                                    System.out.println("Write id");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(airpods.update(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Write id");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(tv.update(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 4 -> {
                                    System.out.println("Write id");
                                    int id = new Scanner(System.in).nextInt();
                                    try {
                                        System.out.println(telephone.update(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                default -> System.out.println("Write correct category");
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write correct!");
                        }
                    }
                    case 7 -> {
                        System.out.println("Choose category");
                        System.out.println("""
                                1-( LAPTOP )
                                2-( AIRPODS )
                                3-( TV )
                                4-( TELEPHONE )
                                """);
                        try {
                            int category = new Scanner(System.in).nextInt();
                            switch (category) {
                                case 1 -> {
                                    System.out.println("Write asc or desc");
                                    String id = new Scanner(System.in).nextLine();
                                    try {
                                        System.out.println(laptop.sortByPrice(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 2 -> {
                                    System.out.println("Write asc or desc");
                                    String id = new Scanner(System.in).nextLine();
                                    try {
                                        System.out.println(airpods.sortByPrice(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 3 -> {
                                    System.out.println("Write asc or desc");
                                    String id = new Scanner(System.in).nextLine();
                                    try {
                                        System.out.println(tv.sortByPrice(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                case 4 -> {
                                    System.out.println("Write asc or desc");
                                    String id = new Scanner(System.in).nextLine();
                                    try {
                                        System.out.println(telephone.sortByPrice(id));
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                default -> System.out.println("Write correct category");
                            }
                        } catch (InputMismatchException exception) {
                            System.out.println("Write correct!");
                        }
                    }
                    case 8 -> {
                        System.out.println("Write asc pr desc");
                        try {
                            System.out.println(airpods.sortByPriceAll(new Scanner(System.in).nextLine()));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }case 0->{break LOOOPMAIN;
                    }

                }
            } catch (InputMismatchException exception) {
                System.out.println("Write number");

            }
        }
    }
}