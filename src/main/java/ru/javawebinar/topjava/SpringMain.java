package ru.javawebinar.topjava;

public class SpringMain {
    public static void main(String[] args) {
        System.out.printf("%-5s%-11s%-25s%-11s%n", "Код", "За единиц", "Валюты", "Рублей РФ");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-5s%-11d%-25s%-11.4f%n", "AUD", 1, "Австралийский доллар", 44.9883);
        System.out.printf("%-5s%-11d%-25s%-11.4f%n", "GBP", 1, "Фунт стерлингов", 86.8429);
        System.out.printf("%-5s%-11d%-25s%-11.4f%n", "BYR", 10000, "Белорусский рубль", 39.7716);
        System.out.printf("%-5s%-11d%-25s%-11.4f%n", "DKK", 10, "Датская крона", 84.9192);
        System.out.printf("%-5s%-11d%-25s%-11.4f%n", "USD", 1, "Доллар США", 58.4643);
        System.out.printf("%-5s%-11d%-25s%-11.4f%n", "EUR", 1, "Евро", 63.3695);
        System.out.printf("%-5s%-11d%-25s%-11.4f%n", "KZT", 100, "Казахский тенге", 31.4654);
    }
}
