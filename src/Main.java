import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static List<Book> createBooks() {
        List<Book> list = new ArrayList<>();

        Author author1 = new Author("Casa Grande");
        Author author2 = new Author("Goku");
        Author author3 = new Author("Moises Caicedo");
        Author author4 = new Author("Harry Kane");
        Author author5 = new Author("Sadio Mane");

        list.add(new Book("Harry Potter 1", List.of(author1, author2), BookType.SCIENTIFIC_FICTION, 89.99));
        list.add(new Book("Harry Potter 2", List.of(author1, author2), BookType.SCIENTIFIC_FICTION, 89.99));
        list.add(new Book("Harry Potter 3", List.of(author1, author2, author3), BookType.SCIENTIFIC_FICTION, 79.00));
        list.add(new Book("Harry Potter 4", List.of(author1, author2, author3), BookType.SCIENTIFIC_FICTION, 79.00));
        list.add(new Book("Harry Potter 5", List.of(author1, author2, author3), BookType.SCIENTIFIC_FICTION, 79.00));
        list.add(new Book("Em busca da felicidade", List.of(author4, author5), BookType.DRAMA, 60.00));
        list.add(new Book("O terminal", List.of(author5, author1), BookType.DRAMA, 30.00));
        list.add(new Book("Tres vezes amor", List.of(author3), BookType.ROMANCE, 15.00));
        list.add(new Book("O pequenino", List.of(author3), BookType.COMEDY, 25.00));
        list.add(new Book("Terrifier", List.of(author4), BookType.TERROR, 120.00));

        return list;
    }

    public static void printaBook(Consumer<Book> consumer, Book book) {
        consumer.accept(book);
    }

    public static void main(String[] args) {
        var books = createBooks();

        // 1 - Metodos de DoubleStream todos os valores dos livros
        // maneira usando Stream<Book> -> Stream<Double> -> DoubleStream
        double sumBooksValue = books.stream()
                .map(Book::getValue)
                .mapToDouble(Double::doubleValue)
                .sum();
        double maxBooksValue = books.stream()
                .map(Book::getValue)
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0.0);
        double minBooksValue = books.stream()
                .map(Book::getValue)
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(0.0);
        double averageBooksValue = books.stream()
                .map(Book::getValue)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        var statistics = books.stream()
                .map(Book::getValue)
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();
        System.out.println("Soma dos valores dos livros : " + sumBooksValue);
        System.out.println("Valor max dos livros : " + maxBooksValue);
        System.out.println("Valor min dos livros : " + minBooksValue);
        System.out.println("Valor average dos livros : " + averageBooksValue);
        System.out.println("Estatisticas : " + statistics);

        // usando map and filter
        var listMap = books.stream()
                .map(item -> item.getValue() * 2)
                .filter(item -> item > 100.00)
                .collect(Collectors.toList());
        System.out.println(listMap);

        // usando reduce
        var listReduce = books.stream()
                .map(Book::getValue)
                .reduce(0.00, Double::sum);
        System.out.println(listReduce);

        // usando match
        System.out.println(books.stream().allMatch(item -> item.getValue() > 40));
        System.out.println(books.stream().noneMatch(item -> item.getValue() > 1000));
        System.out.println(books.stream().anyMatch(item -> item.getType().equals(BookType.TERROR)));

        // collectors exemplos
        var result = books.stream()
                .collect(Collectors.groupingBy(Book::getType));
        result.forEach((key, value) -> {
            System.out.print(key + " - ");
            System.out.println(value);
        });

        var result2 = books.stream()
                .collect(Collectors.groupingBy(Book::getType, Collectors.groupingBy(Book::getTitle)));

        result2.forEach((key, value) -> {
            System.out.print(key + " - ");
            System.out.println(value);
        });

        System.out.println(books.stream().map(Book::getTitle)
                .collect(Collectors.joining(" - ")));

        // consumer example
        Consumer<Book> printBook = System.out::println;
        printBook.accept(books.get(0));

        // function example
        Function<List<Book>, Double> calculateAverage =
                livros -> livros.stream().map(Book::getValue).mapToDouble(Double::doubleValue).sum();

        var average = calculateAverage.apply(books);
        System.out.println(average);

        // predicate examples
        Predicate<List<Book>> isAllBookTypesTerror =
                livros -> livros.stream().noneMatch(book -> book.getType().equals(BookType.TERROR));

        var isAllTypesTerror = isAllBookTypesTerror.test(books);
        System.out.println(isAllTypesTerror);

        // printaBook(System.out::println, books.get(0));
    }
}