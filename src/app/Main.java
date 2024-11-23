package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0)
        );

        // Групування по категоріям
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("\nПродукти мають такі категорії: ");
        groupedByCategory.keySet().forEach(System.out::println);

        // Знаходимо середнє арифметичне вартості в кожній категорії
        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));

        System.out.println("\nСередня ціна продуктів за категоріями: ");
        averagePriceByCategory.forEach((category, averagePrice) -> {
            System.out.printf("%s: %.2f%n", category, averagePrice);
        });

        // Знаходимо та виводимо максимальне середня значення вартості за категоріями
        Optional<Map.Entry<String, Double>> maxAvgPriceCategory = averagePriceByCategory
                .entrySet().stream().max(Map.Entry.comparingByValue());

        System.out.println("\nКатегорія з найвищою середньою ціною: ");
        maxAvgPriceCategory.ifPresent(entry -> {
            System.out.printf("%s: %.2f", entry.getKey(), entry.getValue());
        });
    }
}