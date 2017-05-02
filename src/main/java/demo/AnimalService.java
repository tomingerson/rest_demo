package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Service to handle {@link Animal} objects
 *
 * @author Created by tom on 17.04.2017.
 */
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(final AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    public Collection<Animal> getAllAnimals() {
        return animalRepository.getAllAnimals();
    }

    public Optional<Animal> getAnimalByName(String name) {
        return Optional.ofNullable(name)
                .flatMap(n -> animalRepository.getAllAnimals().stream()
                        .filter(a -> n.equalsIgnoreCase(a.getName()))
                        .findFirst());
    }

    public void addIfNotExisting(Animal animal) {
        if (!getAnimalByName(animal.getName()).isPresent()) {
            addAnimal(animal);
        }
    }

    public boolean addAnimal(Animal a) {
        return animalRepository.addAnimal(a);
    }

    public boolean removeAnimal(Animal a) {
        return animalRepository.removeAnimal(a);
    }

}
