package bot;

import java.util.HashMap;

public class Animal {

    public String name;
    public HashMap<String, String> description;

    public Animal(String name, HashMap<String, String> description) {
        this.name = name;
        this.description = description;
    }

    public boolean isSimilar(Animal animal2) {
        for (String _name : this.description.keySet()) {
            String _value = this.description.get(_name);
            if (!animal2.description.containsKey(_name) || !animal2.description.get(_name).equals(_value)) {
                return false;
            }
        }
        return true;
    }
}
