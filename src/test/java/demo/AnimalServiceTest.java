package demo;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/**
 * @author Created by ergouser on 26.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

//    @Before
//    public void initMocks() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void test_getAnimalByName_not_existing() {
        Mockito.when(animalRepository.getAllAnimals()).thenReturn(Arrays.asList(new Cat("Mike"), new Cat("Peter")));
        Optional<Animal> found = animalService.getAnimalByName("Mark");
        Assert.assertThat(found.isPresent(), CoreMatchers.is(false));
    }

    @Test
    public void test_getAnimalByName_existing() {
        Mockito.when(animalRepository.getAllAnimals()).thenReturn(Arrays.asList(new Cat("Mike"), new Cat("Peter")));
        Optional<Animal> found = animalService.getAnimalByName("Mike");
        Assert.assertThat(found.isPresent(), CoreMatchers.is(true));
    }

    @Test
    public void test_getAnimalByName_empty() {
        Mockito.when(animalRepository.getAllAnimals()).thenReturn(Collections.emptyList());
        Assert.assertThat(animalService.getAllAnimals(), Matchers.empty());
    }

    @Test
    public void test_addIfNotExisting_already_existing_V1() {
        Mockito.when(animalRepository.getAllAnimals()).thenReturn(Arrays.asList(new Dog("Mike")));
        Cat catToAdd = new Cat("Mike");
        animalService.addIfNotExisting(catToAdd);
        Mockito.verify(animalRepository, Mockito.never()).addAnimal(catToAdd);
    }

    @Test
    public void test_addIfNotExisting_already_existing_V2() {
        Mockito.when(animalRepository.getAllAnimals()).thenReturn(Arrays.asList(new Dog("Mike")));
        Cat catToAdd = new Cat("Mike");
        animalService.addIfNotExisting(catToAdd);
        Mockito.verify(animalRepository, Mockito.never()).addAnimal(ArgumentMatchers.any());
    }
}
