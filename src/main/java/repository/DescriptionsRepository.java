package repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

@Repository
@Scope("singleton")
public class DescriptionsRepository {

    public static List<String> getAll(String descriptionName) {
        try {
            List<String> values = new ArrayList<>();
            Connection connection = DataSource.getComboPooledDataSource().getConnection();
            ResultSet rs = connection.createStatement().executeQuery(String.valueOf(new Formatter().format("SELECT DISTINCT value FROM descriptions WHERE name = '%s'", descriptionName)));
            while (rs.next()) {
                String value = rs.getString("value");
                values.add(value);
            }
            connection.close();
            return values;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllCategories() {
        try {
            List<String> values = new ArrayList<>();
            Connection connection = DataSource.getComboPooledDataSource().getConnection();
            ResultSet rs = connection.createStatement().executeQuery("SELECT DISTINCT name FROM descriptions");
            while (rs.next()) {
                String value = rs.getString("name");
                values.add(value);
            }
            connection.close();
            return values;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
