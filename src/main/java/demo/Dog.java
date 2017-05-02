package demo;

/**
 * @author Created by ergouser on 02.05.17.
 */
public class Dog implements Animal {

    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void talk() {
        System.out.println("woof");
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
