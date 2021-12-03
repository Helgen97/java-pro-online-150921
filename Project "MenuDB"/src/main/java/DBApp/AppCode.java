package DBApp;

import Entity.Dish;
import jakarta.persistence.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AppCode {
    private static final String[] DISHES = new String[]{"Pasta", "Burger", "Salad", "Steak", "Waffles"};

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private static final Scanner scanner = new Scanner(System.in);

    public static void start() {
        try {
            //create connection to DB
            entityManagerFactory = Persistence.createEntityManagerFactory("MenuJPA");
            entityManager = entityManagerFactory.createEntityManager();

            try {
                while (true) {
                    System.out.println("1: Add new dish");
                    System.out.println("2: Add random dishes");
                    System.out.println("3: Delete dish");
                    System.out.println("4: Change the dish");
                    System.out.println("5: Print all dishes");
                    System.out.println("6: Choose dishes by price");
                    System.out.println("7: Choose dishes by discount");
                    System.out.println("8: Choose dishes by weight(sum weight <= 1 kg)");
                    System.out.print("->");
                    String var = scanner.nextLine();
                    variation(var);
                }
            } finally {
                entityManager.close();
                entityManagerFactory.close();
                scanner.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void variation(String var) {
        switch (var) {
            case "1": {
                addDish();
                break;
            }
            case "2": {
                addRandomDishes();
                break;
            }
            case "3": {
                deleteDish();
                break;
            }
            case "4": {
                whatChange();
                break;
            }
            case "5": {
                getAllDishes();
                break;
            }
            case "6": {
                getDishesByPrice();
                break;
            }
            case "7": {
                getDishesByDiscount();
                break;
            }
            case "8": {
                getDishesByWeight();
                break;
            }
        }
    }

    private static void addDish() {
        System.out.println("Enter dish name: ");
        String dishName = scanner.nextLine();
        System.out.println("Enter the price of the dish: ");
        int price = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Enter the weight of the dish: ");
        int weight = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Does this dish have a discount? ");
        boolean discount = "yes".equals(scanner.nextLine());

        Dish dish = new Dish(dishName, price, weight, discount);
        addDishAction(dish);
    }

    private static void addDishAction(Dish dish) {
        entityManager.getTransaction().begin(); //begin transaction
        try {
            entityManager.persist(dish); //add dish do context
            entityManager.getTransaction().commit(); //add changes to DB
            System.out.println(dish.getId());
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    private static void addRandomDishes() {
        System.out.println("Enter the number of random dishes: ");
        int var = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < var; i++) {
            addDishAction(makeRandomDish());
        }
    }

    private static Dish makeRandomDish() {
        Random random = new Random();
        String dishName = DISHES[random.nextInt(DISHES.length)] + " " + random.nextInt(10, 1000);
        int price = random.nextInt(100, 400);
        int weight = random.nextInt(80, 300);
        boolean discount = random.nextBoolean();

        return new Dish(dishName, price, weight, discount);
    }

    private static void deleteDish() {
        System.out.println("Enter dish ID: ");
        long id = Long.parseLong(scanner.nextLine().trim());

        Dish dish = entityManager.getReference(Dish.class, id); //get dish by his ID, only ID value exist
        if (dish == null) {
            System.out.println("No dish with this ID");
            return;
        }
        deleteDishAction(dish);
    }

    private static void deleteDishAction(Dish dish) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(dish); //remove dish in context
            entityManager.getTransaction().commit(); //remove dish from DB
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    private static void whatChange() {
        System.out.println("What will we change?");
        System.out.println("1: Dish name");
        System.out.println("2: Dish price");
        System.out.println("3: Dish weight");
        System.out.println("4: Dish discount");
        System.out.print("->");
        String var = scanner.nextLine();

        switch (var) {
            case "1": {
                changeDishName();
                break;
            }
            case "2": {
                changeDishPrice();
                break;
            }
            case "3": {
                changeDishWeight();
                break;
            }
            case "4": {
                changeDishDiscount();
                break;
            }
        }
    }

    private static void changeDishName() {
        System.out.println("Enter the dish name to change: ");
        String oldName = scanner.nextLine();
        System.out.println("Enter the new dish name: ");
        String newName = scanner.nextLine();

        Dish dish = null;

        try {
            Query query = entityManager.createQuery( //Create query for selecting dish by his name
                    "SELECT dish FROM Dish dish WHERE dish.dishName = :name", Dish.class
            );
            query.setParameter("name", oldName);
            dish = (Dish) query.getSingleResult(); //getting single dish, or get an exception
        } catch (NoResultException ex) {
            System.out.println("Dish not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Not unique dish!");
            return;
        }
        validateChangeName(dish, newName);
    }

    private static void validateChangeName(Dish dish, String newName) {
        entityManager.getTransaction().begin();
        try {
            dish.setDishName(newName); //changing dish name
            entityManager.getTransaction().commit(); //commit change to DB
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    private static void changeDishPrice() {
        System.out.println("Enter the name of dish to change price");
        String name = scanner.nextLine();
        System.out.println("Enter new dish price: ");
        int price = Integer.parseInt(scanner.nextLine().trim());

        Dish dish = null;

        try {
            Query query = entityManager.createQuery(
                    "SELECT dish FROM Dish dish WHERE dish.dishName = :name", Dish.class
            );
            query.setParameter("name", name);
            dish = (Dish) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("No such dish!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique dish!");
            return;
        }
        validateChangePrice(dish, price);
    }

    private static void validateChangePrice(Dish dish, int price) {
        entityManager.getTransaction().begin();
        try {
            dish.setPrice(price);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    private static void changeDishWeight() {
        System.out.println("Enter the name of dish to change price");
        String name = scanner.nextLine();
        System.out.println("Enter new dish weight: ");
        int weight = Integer.parseInt(scanner.nextLine().trim());

        Dish dish = null;

        try {
            Query query = entityManager.createQuery(
                    "SELECT dish FROM Dish dish WHERE dish.dishName = :name", Dish.class
            );
            query.setParameter("name", name);
            dish = (Dish) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("No such dish!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique dish!");
            return;
        }
        validateChangeWeight(dish, weight);
    }

    private static void validateChangeWeight(Dish dish, int weight) {
        entityManager.getTransaction().begin();
        try {
            dish.setWeight(weight);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    private static void changeDishDiscount() {
        System.out.println("Enter the name of the dish to change discount");
        String name = scanner.nextLine();
        System.out.println("Does this dish have discount? (yes/no)");
        String var = scanner.nextLine();
        boolean discount = "yes".equals(var);

        Dish dish = null;

        try {
            Query query = entityManager.createQuery(
                    "SELECT dish FROM Dish dish WHERE dish.dishName = :name", Dish.class
            );
            query.setParameter("name", name);
            dish = (Dish) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("No such dish!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique dish");
            return;
        }
        validateChangeDiscount(dish, discount);
    }

    private static void validateChangeDiscount(Dish dish, boolean discount) {
        entityManager.getTransaction().begin();
        try {
            dish.setDiscount(discount);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
        }
    }

    private static void getAllDishes() {
        Query query = entityManager.createQuery( //create query and select all list of dishes
                "SELECT dish FROM Dish dish", Dish.class
        );
        List<Dish> dishList = (List<Dish>) query.getResultList(); //getting list of dishes as List

        System.out.println("------------------------");
        for (Dish dish : dishList) {
            System.out.println(dish);
        }
        System.out.println("------------------------");
    }

    private static void getDishesByPrice() {
        System.out.println("Enter the price: ");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Choose task: ");
        System.out.println("1: Bigger than that price");
        System.out.println("2: Smaller than that price");
        System.out.print("->");
        String var = scanner.nextLine();
        switch (var) {
            case "1": {
                String query = "SELECT dish FROM Dish dish WHERE dish.price >= :price";
                getDishesByPriceAction(query, price);
                break;
            }
            case "2": {
                String query = "SELECT dish FROM Dish dish WHERE dish.price <= :price";
                getDishesByPriceAction(query, price);
                break;
            }
        }
    }

    private static void getDishesByPriceAction(String querry, int price) {
        Query query = entityManager.createQuery(querry, Dish.class);
        query.setParameter("price", price);

        List<Dish> dishList = (List<Dish>) query.getResultList();

        System.out.println("------------------------");
        for (Dish dish : dishList) {
            System.out.println(dish);
        }
        System.out.println("------------------------");
    }

    private static void getDishesByDiscount() {
        String query = "SELECT dish FROM Dish dish WHERE dish.discount = :discount";
        System.out.println("Choose task: ");
        System.out.println("1. Get dishes that have discount");
        System.out.println("2. Get dishes that haven't discount ");
        System.out.print("->");
        String var = scanner.nextLine();
        switch (var) {
            case "1": {
                getDishesByDiscountAction(query, true);
                break;
            }
            case "2": {
                getDishesByDiscountAction(query, false);
                break;
            }
        }
    }

    private static void getDishesByDiscountAction(String querry, boolean isDiscount) {
        Query query = entityManager.createQuery(querry, Dish.class);
        query.setParameter("discount", isDiscount);

        List<Dish> dishList = (List<Dish>) query.getResultList();

        System.out.println("------------------------");
        for (Dish dish : dishList) {
            System.out.println(dish);
        }
        System.out.println("------------------------");
    }

    private static void getDishesByWeight() {
        Query query = entityManager.createQuery( //create query and select all list of dishes
                "SELECT dish FROM Dish dish", Dish.class
        );
        List<Dish> dishList = (List<Dish>) query.getResultList(); //getting list of dishes as List

        int sumWeight = 0;
        System.out.println("------------------------");
        for (Dish dish : dishList) {
            if (sumWeight > 1000) {
                break;
            }
            sumWeight += dish.getWeight();
            System.out.println(dish);
        }
        System.out.println("------------------------");
    }
}
