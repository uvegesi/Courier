package Courier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static Courier[] deliveries;

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("1. feladat");
        readFileIn("src/tavok.txt");
        for (Courier delivery : deliveries) {
            System.out.println(delivery.getDay() + " " + delivery.getDeliveryNum() + " " + delivery.getDistance());
        }
        System.out.println();

        System.out.println("2. feladat");
        System.out.println(firstDeliveryOfTheWeek());
        System.out.println();

        System.out.println("3. feladat");
        System.out.println(lastDeliveryOfTheWeek());
        System.out.println();

        System.out.println("4. feladat");
        offDays();
        System.out.println();

        System.out.println("5. feladat");
        System.out.println(mostDeliveriesOnADay());
        System.out.println();

        System.out.println("6. feladat");
        kmPerDay();
        System.out.println();

        System.out.println("7. feladat");
        System.out.println(salaryPerKm(26));
        System.out.println();

        System.out.println(deliveriesPerDay(5));
        System.out.println();

        System.out.println("8. feladat");
        salaryPerDeliveries();
        System.out.println();

        System.out.println("9. feladat");
        System.out.println(salaryCalculator() + " Ft-ot kap a futár a hétre.");

    }

    public static void readFileIn(String string) throws FileNotFoundException {

        Scanner sc = new Scanner(new File(string));

        int rows = 0;

        while (sc.hasNextLine()) {
            sc.nextLine();
            rows++;
        }

        deliveries = new Courier[rows];
        rows = 0;

        sc = new Scanner(new File(string));

        while (sc.hasNext()) {
            int day = sc.nextInt();
            int deliveryNum = sc.nextInt();
            int distance = sc.nextInt();
            deliveries[rows++] = new Courier(day, deliveryNum, distance);
        }
    }

    public static int firstDeliveryOfTheWeek() {
        int first = 0;
        int firstDay = 7;
        for (Courier delivery : deliveries) {
            if (delivery.getDay() < firstDay) {
                firstDay = delivery.getDay();
            }
        }
        for (Courier delivery : deliveries) {
            if (delivery.getDay() == firstDay && delivery.getDeliveryNum() == 1) {
                first = delivery.getDistance();
            }
        }
        return first;
    }

    public static int lastDeliveryOfTheWeek() {
        int last = 0;
        int lastDay = 1;
        int lastDelivery = 0;
        for (Courier delivery : deliveries) {
            if (delivery.getDay() > lastDay && delivery.getDeliveryNum() > lastDelivery) {
                lastDay = delivery.getDay();
                lastDelivery = delivery.getDeliveryNum();
            }
        }
        for (Courier delivery : deliveries) {
            if (delivery.getDay() == lastDay && delivery.getDeliveryNum() == lastDelivery) {
                last = delivery.getDistance();
            }
        }
        return last;
    }

    public static void offDays() {
        for (int i = 1; i <= 7; i++) {
            boolean offDay = true;
            for (Courier delivery : deliveries) {
                if (delivery.getDay() == i) {
                    offDay = false;
                    break;
                }
            }
            if (offDay) {
                System.out.println("A hét " + i + ". napján a futár nem dolgozott.");
            }
        }
    }

    public static int mostDeliveriesOnADay() {
        int max = 0;
        int maxDay = 0;
        for (int i = 1; i <= 7; i++) {
            int day = 0;
            int count = 0;
            for (Courier delivery : deliveries) {
                if (delivery.getDay() == i) {
                    count++;
                    day = delivery.getDay();
                }
            }
            if (count > max) {
                max = count;
                maxDay = day;
            }
        }
        return maxDay;
    }

    public static void kmPerDay() {
        for (int i = 1; i <= 7; i++) {
            int sum = 0;
            int count = 0;
            for (Courier delivery : deliveries) {
                if (delivery.getDay() == i) {
                    count++;
                    sum += delivery.getDistance();
                }
            }
            if (count > 0) {
                System.out.println(i + ". nap: " + sum + " km");
            }
        }
    }

    public static int salaryPerKm(int km) {
        int salary = 0;
        if (km <= 2) {
            salary = km * 500;
        } else if (km <= 5) {
            salary = km * 700;
        } else if (km <= 10) {
            salary = km * 900;
        } else if (km <= 20) {
            salary = km * 1400;
        } else if (km <= 30) {
            salary = km * 2000;
        }
        return salary;
    }

    public static int deliveriesPerDay(int day) {
        int count = 0;
        for (Courier delivery : deliveries) {
            if (delivery.getDay() == day) {
                count++;
            }
        }
        return count;
    }

    public static void salaryPerDeliveries() {
        for (int i = 1; i <= 7; i++) {
            int numberOfDels = deliveriesPerDay(i);
            if (numberOfDels > 0) {
                for (int j = 1; j <= numberOfDels; j++) {
                    for (Courier delivery : deliveries) {
                        if (delivery.getDay() == i && delivery.getDeliveryNum() == j) {
                            System.out.println(delivery.getDay() + ". nap " + delivery.getDeliveryNum() + ". út: " +
                                    salaryPerKm(delivery.getDistance()) + " Ft.");
                            break;
                        }
                    }
                }
                System.out.println();
            }
        }
    }

    public static int salaryCalculator() {
        int salary = 0;
        for (Courier delivery : deliveries) {
            salary += salaryPerKm(delivery.getDistance());
        }
        return salary;
    }

}
