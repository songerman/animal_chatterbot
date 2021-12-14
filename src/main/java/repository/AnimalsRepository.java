package repository;

import bot.Animal;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Repository
@Scope("singleton")
public class AnimalsRepository {

    public HashMap<String, HashMap<String, String>> findAll() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/animals_chatterbot","postgres", "12345");
            ResultSet animalsRS = connection.createStatement().executeQuery("SELECT * FROM animals");
            HashMap<String, HashMap<String, String>> animals = new HashMap<>();
            while (animalsRS.next()) {
                String animalName = animalsRS.getString("name");
                int id = animalsRS.getInt("id");

                ResultSet animalsDescriptionsRS = connection.createStatement().executeQuery("SELECT * FROM animals_descriptions WHERE animalid = " + id);
                HashMap<String, String> descriptions = new HashMap<>();
                while (animalsDescriptionsRS.next()) {
                    int descriptionId = animalsDescriptionsRS.getInt("descriptionid");

                    ResultSet descriptionsRS = connection.createStatement().executeQuery("SELECT * FROM descriptions WHERE id = " + descriptionId);
                    while (descriptionsRS.next()) {
                        String descriptionName = descriptionsRS.getString("name");
                        String descriptionValue = descriptionsRS.getString("value");

                        descriptions.put(descriptionName, descriptionValue);
                    }
                }
                animals.put(animalName, descriptions);
            }

            connection.close();
            return animals;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
