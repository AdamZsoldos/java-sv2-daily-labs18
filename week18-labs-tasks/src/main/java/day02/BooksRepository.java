package day02;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BooksRepository {

    private final JdbcTemplate jdbcTemplate;

    public BooksRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertBook(String title, String author, long price, long numberInStock) {
        jdbcTemplate.update("insert into books(title, author, price, number_in_stock) values (?, ?, ?, ?);",
                title, author, price, numberInStock);
    }

    public List<Book> fetchBooksByAuthorPrefix(String prefix) {
        return jdbcTemplate.query("select * from books where author like ?",
                this::createBook, prefix + "%");
    }

    public void updateIncrementNumberInStock(long id, long number) {
        jdbcTemplate.update("update books set number_in_stock = (number_in_stock + ?) where id = ?",
                number, id);
    }

    private Book createBook(ResultSet rs, int rowNum) throws SQLException {
        return new Book(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getLong("price"),
                rs.getLong("number_in_stock")
        );
    }
}
