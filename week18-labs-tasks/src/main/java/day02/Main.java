package day02;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        dataSource.setUrl("jdbc:mariadb://localhost:3306/book_store?useUnicode=true");
        dataSource.setUserName("root");
        dataSource.setPassword("root");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        BooksRepository booksRepository = new BooksRepository(dataSource);

        booksRepository.insertBook("Vuk", "Fekete István", 3400, 10);
        booksRepository.insertBook("Téli berek", "Fekete István", 3600, 8);

        System.out.println(booksRepository.fetchBooksByAuthorPrefix("f"));

        booksRepository.updateIncrementNumberInStock(1, 30);

        System.out.println(booksRepository.fetchBooksByAuthorPrefix("f"));
    }
}
