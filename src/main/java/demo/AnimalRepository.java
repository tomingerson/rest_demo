package demo;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Created by ergouser on 26.04.17.
 */
@Repository
public class AnimalRepository {

    private final Collection<Animal> animals = Arrays.asList(new Cat("Molly"), new Dog("Bobby"), new Cat("Garfield"));

    public Collection<Animal> getAllAnimals() {
        return animals;
    }

    public boolean removeAnimal(Animal a) {
        return animals.remove(a);
    }

    public boolean addAnimal(Animal a) {
        return animals.add(a);
    }
}
