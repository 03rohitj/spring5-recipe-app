package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.model.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private final static Long UOM_ID = 1L;
    private final static String UOM_VAL = "SOME_VAL";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    //To test that if command object is null then the domain object should also be null
    @Test
    public void testNullParameter() throws Exception{
        assertNull(converter.convert(null));
    }

    //To test that if command object is empty then the domain object should also be empty
    @Test
    public void testEmptyObject() throws Exception{
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    //To test whether the command object's values matches the domain object or not
    @Test
    public void convert() {

        //given
        UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
        uomCommand.setId(UOM_ID);
        uomCommand.setUom(UOM_VAL);

        //when
        UnitOfMeasure uom = converter.convert(uomCommand);

        //then
        assertNotNull(uom);
        assertEquals(UOM_ID, uom.getId());
        assertEquals(UOM_VAL, uom.getUom());

    }
}