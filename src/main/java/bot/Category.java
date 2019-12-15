package bot;

import java.util.function.BiConsumer;

public enum Category {
    AREA("Среда обитания", (Animal animal, String feature) -> animal.area = feature),
    COLOR("Цвет", (Animal animal, String feature) -> animal.color = feature),
    SIZE("Размер", (Animal animal, String feature) -> animal.size = feature);

    private final String name;
    private final BiConsumer<Animal, String> setter;

    Category(String name, BiConsumer<Animal, String> setter) {
        this.name = name;
        this.setter = setter;
    }

    public String getName() {
        return name;
    }

    public BiConsumer<Animal, String> getSetter() {
        return setter;
    }
}