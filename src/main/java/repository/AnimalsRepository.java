package repository;

import bot.Animal;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Repository
@Scope("singleton")
public class AnimalsRepository {

    public List<Animal> findAll() {
        try {
            Connection connection = DataSource.getComboPooledDataSource().getConnection();
            ResultSet animalsRS = connection.createStatement().executeQuery("SELECT * FROM animals");
            HashMap<String, HashMap<String, String>> animals = new HashMap<>();
            while (animalsRS.next()) {
                int id = animalsRS.getInt("id");
                String animalName = animalsRS.getString("name");

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

            List<Animal> animalsList = new ArrayList<>();

            animals.forEach((animalName, descriptionsMap) -> {
                    Animal animal = getAnimal(animalName, descriptionsMap);
                    animalsList.add(animal);
            });

            connection.close();
            return animalsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Animal getAnimal(String animalName, HashMap<String, String> descriptionsMap) {
        AtomicReference<String> animalColor = new AtomicReference<>("");
        AtomicReference<String> animalArea = new AtomicReference<>("");
        AtomicReference<String> animalSize = new AtomicReference<>("");
        descriptionsMap.forEach((_name, _value) -> {
            switch (_name) {
                case "color": {
                    animalColor.set(_value);
                    break;
                }
                case "area": {
                    animalArea.set(_value);
                    break;
                }
                case "size": {
                    animalSize.set(_value);
                    break;
                }
            }
        });
        Animal animal = new Animal(animalName, animalColor.get(), animalArea.get(), animalSize.get());
        return animal;
    }
}
