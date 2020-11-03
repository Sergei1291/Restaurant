package com.epam.restaurant.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FileDataReaderTest {

    private final static String DATA = "src/test/resources/dataDataReaderTest.txt";
    private final static String FILE_NOT_EXIST = "src/test/resources/doesNotExist.txt";

    private final static String FIRST_LINE = "1st line";
    private final static String SECOND_LINE = "2nd line!!! f sd";
    private final static String THIRD_LINE = "3rd line";
    private final static List<String> LINES = Arrays.asList(FIRST_LINE, SECOND_LINE, THIRD_LINE);

    private final DataReader dataReader = new FileDataReader();

    @Test
    public void testReadLinesShouldReadFile() throws DataException {
        //when
        List<String> actual = dataReader.readLines(DATA);
        //then
        Assert.assertEquals(LINES, actual);
    }

    @Test(expected = DataException.class)//then
    public void testReadLinesShouldThrowDataExceptionWhenFileNotExist() throws DataException {
        //when
        dataReader.readLines(FILE_NOT_EXIST);
    }

}