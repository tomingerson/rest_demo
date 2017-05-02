package demo;

/**
 * @author Created by ergouser on 02.05.17.
 */
public class Cat implements Animal {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public void talk() {
        System.out.println("meow");
    }

    @Override
    public String getName() {
        return name;
    }
}
