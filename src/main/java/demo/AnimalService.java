package demo;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Service to handle {@link Animal} objects
 *
 * @author Created by tom on 17.04.2017.
 */
@Service
public class AnimalService {
    private final Collection<Animal> animals = Arrays.asList(new Animal() {
        public void talk() {
            System.out.println("Meow");
        }

        public String getName() {
            return "Molly";
        }
    }, new Animal() {
        public void talk() {
            System.out.println("Woof");
        }

        public String getName() {
            return "Molly";
        }
    }, new Animal() {
        public void talk() {
            System.out.println("Woof");
        }

        public String getName() {
            return "Poppy";
        }
    });

    public Collection<Animal> getAllAnimals() {
        return this.animals;
    }

    public Optional<Animal> getAnimalByName(String name) {
        return Optional.ofNullable(name)
                .flatMap(n -> this.animals.stream()
                        .filter(a -> n.equalsIgnoreCase(a.getName()))
                        .findFirst());
    }

    public boolean addAnimal(Animal a) {
        return this.animals.add(a);
    }

    public boolean removeAnimal(Animal a) {
        return this.animals.remove(a);
    }

}
