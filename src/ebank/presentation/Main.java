package ebank.presentation;

import java.util.Scanner;

import ebank.business.Ebank;

/**
 * Entry point of the Ebank login system.
 * Shows the language menu then runs the login flow
 * in the selected language.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ebank ebank = new Ebank();

        while (true) {
            printMenu(ebank);
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    ebank.setLocate("Vi");
                    login(ebank, scanner);
                    break;
                case "2":
                    ebank.setLocate("En");
                    login(ebank, scanner);
                    break;
                case "3":
                    System.out.println(ebank.getMessage("menu.exit"));
                    scanner.close();
                    return;
                default:
                    System.out.println(ebank.getMessage("menu.invalidOption"));
            }
        }
    }

    private static void printMenu(Ebank ebank) {
        System.out.println();
        System.out.println("========================================");
        System.out.println(ebank.getMessage("menu.title"));
        System.out.println("========================================");
        System.out.println(ebank.getMessage("menu.option1"));
        System.out.println(ebank.getMessage("menu.option2"));
        System.out.println(ebank.getMessage("menu.option3"));
        System.out.print(ebank.getMessage("menu.select") + " ");
    }

    /**
     * Function 6: login.
     * Each step repeats until the input is valid, error messages
     * are shown in the language the user selected.
     */
    private static void login(Ebank ebank, Scanner scanner) {
        // step 1: account number
        while (true) {
            System.out.print(ebank.getMessage("input.account") + " ");
            String account = scanner.nextLine().trim();
            String result = ebank.checkAccountNumber(account);
            System.out.println(result);
            if (result.equals(ebank.getMessage("account.valid"))) {
                break;
            }
        }

        // step 2: password
        while (true) {
            System.out.print(ebank.getMessage("input.password") + " ");
            String password = scanner.nextLine().trim();
            String result = ebank.checkPassword(password);
            System.out.println(result);
            if (result.equals(ebank.getMessage("password.valid"))) {
                break;
            }
        }

        // step 3: captcha
        while (true) {
            String captcha = ebank.generateCaptcha();
            System.out.println(ebank.getMessage("input.captcha", captcha));
            System.out.print(ebank.getMessage("input.captcha.enter") + " ");
            String input = scanner.nextLine().trim();
            String result = ebank.checkCaptcha(input, captcha);
            System.out.println(result);
            if (result.equals(ebank.getMessage("captcha.valid"))) {
                break;
            }
        }

        System.out.println(ebank.getMessage("login.success"));
    }
}
