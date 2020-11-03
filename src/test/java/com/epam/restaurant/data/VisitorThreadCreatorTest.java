package com.epam.restaurant.data;

import com.epam.restaurant.logic.Restaurant;
import com.epam.restaurant.model.Visitor;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class VisitorThreadCreatorTest {

    private final static Visitor FIRST_VISITOR = new Visitor(2, "Viktor", 10);
    private final static Visitor SECOND_VISITOR = new Visitor(11, "Alexander", 20);

    private final static List<Visitor> VISITORS = Arrays.asList(FIRST_VISITOR, SECOND_VISITOR);

    private final static Restaurant RESTAURANT = Restaurant.getInstance();

    @Test
    public void testCreateShouldCreateListSizeSameJsonCreatorCreateListSize() throws DataException {
        //given
        JsonVisitorCreator jsonVisitorCreator = Mockito.mock(JsonVisitorCreator.class);
        when(jsonVisitorCreator.create(anyString())).thenReturn(VISITORS);
        VisitorThreadCreator visitorThreadCreator = new VisitorThreadCreator(jsonVisitorCreator, RESTAURANT);
        //when
        List<Thread> actual = visitorThreadCreator.create("");
        int actualListSize = actual.size();
        //then
        Assert.assertEquals(2, actualListSize);
    }

}