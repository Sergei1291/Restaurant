package com.epam.restaurant.data;

import com.epam.restaurant.model.Visitor;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class JsonVisitorCreatorTest {

    private final static String FIRST_LINE = "{\"id\":2, \"name\":\"Viktor\", \"orderSum\":10}";
    private final static String SECOND_LINE = "{\"id\":11, \"name\":\"Alexander\", \"orderSum\":20}";

    private final static List<String> LINES = Arrays.asList(FIRST_LINE, SECOND_LINE);

    private final static Visitor FIRST_VISITOR = new Visitor(2, "Viktor", 10);
    private final static Visitor SECOND_VISITOR = new Visitor(11, "Alexander", 20);

    private final static List<Visitor> VISITORS = Arrays.asList(FIRST_VISITOR, SECOND_VISITOR);

    private final static String INVALID_LINE = "{\"id\":11, \"nameName\":\"Alexander\", \"orderSum\":20}";

    private final static List<String> INVALID_LINES = Arrays.asList(FIRST_LINE, INVALID_LINE);

    @Test
    public void testCreateShouldReturnListVisitorsWhenFile() throws DataException {
        //given
        DataReader dataReader = Mockito.mock(FileDataReader.class);
        when(dataReader.readLines(anyString())).thenReturn(LINES);
        JsonVisitorCreator jsonVisitorCreator = new JsonVisitorCreator(dataReader);
        //when
        List<Visitor> actual = jsonVisitorCreator.create("");
        //then
        Assert.assertEquals(VISITORS, actual);
    }

    @Test(expected = DataException.class)//then
    public void testCreateShouldThrowDataExceptionWhenLineInvalid() throws DataException {
        //given
        DataReader dataReader = Mockito.mock(FileDataReader.class);
        when(dataReader.readLines(anyString())).thenReturn(INVALID_LINES);
        JsonVisitorCreator jsonVisitorCreator = new JsonVisitorCreator(dataReader);
        //when
        jsonVisitorCreator.create("");
    }

}