package labs4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

class Customer {
    private final String surname;
    private final String address;
    private final long creditCardNumber;
    private final long bankAccountNumber;

    public Customer(String surname, String address, long creditCardNumber, long bankAccountNumber) {
        this.surname = surname;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getSurname() {
        return surname;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void printRow() {
        System.out.printf("| %-15s | %-20s | %-18d | %-20d |\n",
                surname, address, creditCardNumber, bankAccountNumber);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfCustomers = 5;
        Customer[] customers = new Customer[numberOfCustomers];

        System.out.println("=== Введення даних покупців ===");
        for (int i = 0; i < numberOfCustomers; i++) {
            customers[i] = createCustomerSafe(scanner, i + 1);
        }
        System.out.println("\n=== Початковий список покупців ===");
        printTable(customers);

        System.out.println("\n=== Список покупців у алфавітному порядку ===");
        Customer[] sortedCustomers = Arrays.copyOf(customers, customers.length);
        Arrays.sort(sortedCustomers, Comparator.comparing(Customer::getSurname));
        printTable(sortedCustomers);
        System.out.println("\n=== Пошук за діапазоном кредитної картки ===");
        long minRange = 0, maxRange = 0;
        boolean validRange = false;
        while (!validRange) {
            try {
                System.out.print("Введіть мінімальне значення номера картки: ");
                minRange = scanner.nextLong();
                System.out.print("Введіть максимальне значення номера картки: ");
                maxRange = scanner.nextLong();

                if (minRange > maxRange) {
                    System.out.println("Помилка: мінімальне значення не може бути більшим за максимальне. Спробуйте ще раз.");
                    continue;
                }
                validRange = true;
            } catch (InputMismatchException e) {
                System.out.println("Помилка: Введено некоректний тип даних. Будь ласка, введіть число.");
                scanner.nextLine();
            }
        }

        findAndPrintByCreditCardRange(customers, minRange, maxRange);
        scanner.close();
    }

    private static Customer createCustomerSafe(Scanner scanner, int index) {
        String surname = "";
        String address = "";
        long creditCardNumber = 0;
        long bankAccountNumber = 0;
        boolean isValid = false;

        System.out.println("\nВведення даних для покупця #" + index + ":");

        while (!isValid) {
            try {
                System.out.print("Прізвище: ");
                surname = scanner.next();

                scanner.nextLine();

                System.out.print("Адреса: ");
                address = scanner.nextLine();

                System.out.print("Номер кредитної картки (тільки цифри): ");
                creditCardNumber = scanner.nextLong();

                System.out.print("Номер банківського рахунку (тільки цифри): ");
                bankAccountNumber = scanner.nextLong();

                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Помилка введення! Номери картки та рахунку повинні містити лише цифри. Спробуйте ввести дані цього покупця знову.");
                scanner.nextLine();
            }
        }
        return new Customer(surname, address, creditCardNumber, bankAccountNumber);
    }

    private static void printTable(Customer[] customers) {
        printTableHeader();
        for (Customer customer : customers) {
            customer.printRow();
        }
        printTableFooter();
    }
    private static void findAndPrintByCreditCardRange(Customer[] customers, long min, long max) {
        boolean found = false;

        System.out.println("\nРезультат пошуку:");
        printTableHeader();

        for (Customer customer : customers) {
            if (customer.getCreditCardNumber() >= min && customer.getCreditCardNumber() <= max) {
                customer.printRow();
                found = true;
            }
        }
        printTableFooter();

        if (!found) {
            System.out.println("За вказаним критерієм пошуку покупців не знайдено.");
        }
    }

    private static void printTableHeader() {
        System.out.println(new String(new char[83]).replace("\0", "-"));
        System.out.printf("| %-15s | %-20s | %-18s | %-20s |\n", "Прізвище", "Адреса", "Номер кр. картки", "Номер банк. рахунку");
        System.out.println(new String(new char[83]).replace("\0", "-"));
    }

    private static void printTableFooter() {
        System.out.println(new String(new char[83]).replace("\0", "-"));
    }
}