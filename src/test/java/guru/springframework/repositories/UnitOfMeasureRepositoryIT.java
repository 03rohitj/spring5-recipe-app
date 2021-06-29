package guru.springframework.repositories;

import guru.springframework.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

/* Integration testing with in-memort database */

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByUom() {
        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByUom("Teaspoon");
        assertEquals("Teaspoon",teaspoonOptional.get().getUom());
    }

    @Test
    public void findByUomOunce() {
        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByUom("Ounce");
        assertEquals("Ounce",ounceOptional.get().getUom());
    }
}