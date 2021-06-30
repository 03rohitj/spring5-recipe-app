package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
    private static final Long UNIT_ID = 1L;
    private static final String UNIT_VAL = "Unit X";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() throws Exception{
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }
    @Test
    public void convert() {

        //given
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(UNIT_ID);
        unitOfMeasure.setUom(UNIT_VAL);

        //when
        UnitOfMeasureCommand command = converter.convert(unitOfMeasure);

        //then
        assertNotNull(command);
        assertEquals(UNIT_ID, command.getId());
        assertEquals(UNIT_VAL, command.getUom());
    }
}