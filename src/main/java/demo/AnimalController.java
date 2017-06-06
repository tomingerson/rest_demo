package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Collection;
import java.util.Optional;

/**
 * Controller to serve {@link Animal Animals} as a ReST-Resource.
 *
 * @author Created by tom on 17.04.2017.
 */
@RestController
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animals")
    public ResponseEntity<Collection<Animal>> getAnimals() {
        return ResponseEntity.ok(this.animalService.getAllAnimals());
    }

    @GetMapping("/animals/*/{name}")
    public ResponseEntity<Animal> getAnimalByName(@PathVariable String name, @RequestHeader("funny") String funny) {
        return this.animalService.getAnimalByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping("/animals/{name}")
    public ResponseEntity<Animal> deleteAnimalByName(@PathVariable String name) {
        final Optional<Animal> animalCandidate = this.animalService.getAnimalByName(name);
        if (animalCandidate.isPresent()) {
            if (this.animalService.removeAnimal(animalCandidate.get())) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/animals/{name}")
    public ResponseEntity<Animal> upsertAnimal(@PathVariable String name) {
        throw new NotImplementedException();
    }

}
