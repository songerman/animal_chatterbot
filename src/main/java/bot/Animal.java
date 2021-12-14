package bot;

public class Animal {

    public String name;
    public String color;
    public String area;
    public String size;

    public Animal(String name, String color, String area, String size) {
        this.name = name;
        this.color = color;
        this.area = area;
        this.size = size;
    }

    public boolean isSimilar(Animal animal2) {
        if (animal2 != null) {
            return this.color.equals(animal2.color) && this.area.equals(animal2.area) && this.size.equals(animal2.size);
        }
        return false;
    }
}
