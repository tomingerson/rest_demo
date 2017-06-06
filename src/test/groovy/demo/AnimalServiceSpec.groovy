package demo

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

/**
 * @author Created by ergouser on 03.05.17.
 */
class AnimalServiceSpec extends Specification {

    private AnimalRepository animalRepository = Mock(AnimalRepository)

    @Subject
    private AnimalService animalService = new AnimalService(animalRepository)

//    @Test
//    public void test_getAnimalByName_not_existing() {
//        Mockito.when(animalRepository.getAllAnimals()).thenReturn(Arrays.asList(new Cat("Mike"), new Cat("Peter")));
//        Optional<Animal> found = animalService.getAnimalByName("Mark");
//        Assert.assertThat(found.isPresent(), CoreMatchers.is(false));
//    }

    def "getAnimalByName - Test case no.1: The animal that we are looking for doesn't exist"() {
        when:
        1 * animalRepository.getAllAnimals() >> [new Cat("Mike"), new Cat("Peter")]

        def found = animalService.getAnimalByName("Mark")

        then:
        !found.isPresent()

    }

    def "Test the method addIfNotExisting"() {
        given:
        def dog = new Dog("Mark")

        when:
        1 * animalRepository.getAllAnimals() >> [new Cat("Mike"), new Cat("Peter")]

        animalService.addIfNotExisting(dog)

        then:
        1 * animalRepository.addAnimal(dog)
    }


    @Unroll
    def "getAnimalByName(#name)"() {
        given:
        _ * animalRepository.getAllAnimals() >> [new Cat("Mike"), new Cat("Peter")]

        expect:
        def found = animalService.getAnimalByName(name)
        found.isPresent() == foundBool

        where:
        name    || foundBool
        "Mike"  || true
        "Mark"  || false
        null    || false
        "Peter" || true

    }
}
