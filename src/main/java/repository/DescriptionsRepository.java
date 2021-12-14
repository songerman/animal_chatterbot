package repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import repository.entities.Description;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;

@Repository
@Scope("singleton")
public class DescriptionsRepository {

    public List<String> getAll(String descriptionName) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/animals_chatterbot","postgres", "12345");

            List<String> values = new ArrayList<>();
            ResultSet rs = connection.createStatement().executeQuery(String.valueOf(new Formatter().format("SELECT value FROM descriptions WHERE name = '%s'", descriptionName)));
            while (rs.next()) {
                String value = rs.getString("value");
                values.add(value);
            }
            connection.close();
            return values;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
