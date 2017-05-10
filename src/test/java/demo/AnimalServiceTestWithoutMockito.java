package demo;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Created by ergouser on 02.05.17.
 */
public class AnimalServiceTestWithoutMockito {

    private AnimalService animalService;
    private Cat catMike;
    private Cat catMax;

    @Before
    public void setup() {
        catMax = new Cat("Max");
        catMike = new Cat("Mike");
        animalService = new AnimalService(new AnimalRepository() {
            @Override
            public Collection<Animal> getAllAnimals() {
                return Arrays.asList(catMike, catMax);
            }

            @Override
            public boolean removeAnimal(Animal a) {
                return true;
            }

            @Override
            public boolean addAnimal(Animal a) {
                return true;
            }
        });
    }

    @Test
    public void test_getAnimalByName_not_existing() {
        Optional<Animal> found = animalService.getAnimalByName("Mark");
        Assert.assertThat(found.isPresent(), CoreMatchers.is(false));
    }

    @Test
    public void test_getAnimalByName_existing() {
        Optional<Animal> found = animalService.getAnimalByName("Mike");
        Assert.assertThat(found.isPresent(), CoreMatchers.is(true));
    }

    @Test
    public void test_getAnimalByName_empty() {
        Optional<Animal> found = new AnimalService(new AnimalRepository() {
            @Override
            public Collection<Animal> getAllAnimals() {
                return Collections.emptyList();
            }
        }).getAnimalByName("Mike");
        Assert.assertThat(found.isPresent(), CoreMatchers.is(false));
    }

}
