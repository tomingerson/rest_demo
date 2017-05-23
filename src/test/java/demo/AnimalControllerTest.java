package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Created by tom on 13.05.2017.
 */
@WebMvcTest(controllers = {AnimalController.class})
@RunWith(SpringRunner.class)
public class AnimalControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AnimalService animalService;

    @Test
    public void getAnimals() throws Exception {

        given(animalService.getAllAnimals())
                .willReturn(Collections.singletonList(new Dog("molly")));

        mvc.perform(get("/animals"))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(content().json("[{\"name\":\"molly\"}]"));
                .andExpect(jsonPath("$[0].name", is("molly")));
    }

    @Test
    public void getAnimalByName_notExisting() throws Exception {
        given(animalService.getAnimalByName("molly"))
                .willReturn(Optional.empty());

        mvc.perform(get("/animals/molly"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAnimalByName_existing() throws Exception {
        given(animalService.getAnimalByName("molly"))
                .willReturn(Optional.of(new Dog("molly")));

        mvc.perform(get("/animals/molly"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("molly")));
    }

    @Test
    public void deleteAnimalByName_notExisting() throws Exception {
        given(animalService.getAnimalByName("molly"))
                .willReturn(Optional.empty());

        mvc.perform(delete("/animals/molly"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void deleteAnimalByName_cannotRemove() throws Exception {

        final Dog molly = new Dog("molly");
        given(animalService.getAnimalByName("molly"))
                .willReturn(Optional.of(molly));
        given(animalService.removeAnimal(molly))
                .willReturn(false);

        mvc.perform(delete("/animals/molly"))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void deleteAnimalByName_ok() throws Exception {

        final Dog molly = new Dog("molly");
        given(animalService.getAnimalByName("molly"))
                .willReturn(Optional.of(molly));
        given(animalService.removeAnimal(molly))
                .willReturn(true);

        mvc.perform(delete("/animals/molly"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void upsertAnimalByName() throws Exception {
        mvc.perform(put("/animals/molly")).andDo(print()).andExpect(status()
                .isNotImplemented());
    }
}