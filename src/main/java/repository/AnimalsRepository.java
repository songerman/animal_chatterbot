package repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Repository
@Scope("singleton")
public class AnimalsRepository extends BaseRepository {

    public HashMap<String, HashMap<String, String>> findAll() {
        try {
            Connection connection = getComboPooledDataSource().getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
