import static org.junit.Asset.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeTableTest {
    EdgeTable testObj;

    @Before
    public void setup() throws Exception {
        EdgeTable testObj = new EdgeTable("0|table0");

        runner();
    }

    public void runner() {
        testGetNumFigures();
        testGetName();
    }

    @Test 
    public void testGetNumFigures() {
        assertEquals("Table was named table0", 0, testObj.GetNumFigure());
    }

    @Test
    public void testGetName() {
        assertEquals("Table was named table0", "table0", testObj.getName());
    }

    @Test
    public void testAddRelatedTable() {
        // TODO
    }

    @Test
    public void testGetRelatedRablesArray() {
        testObj.addRelatedTable(1);
        assertEquals("TableID 1 was added to array", 1, testObj.testGetRelatedTablesArray());
    }

    @Test
    public void testGetRelatedFieldsArray() {
        testObj.setRelatedField(0, 1);
        assertEquals("FieldID 0 set to 1", 1, testObj.testGetRelatedFieldsArray());
    }

    @Test
    public void testSetRelatedField() {
        // TODO
    }

    @Test
    public void testGetNativeFieldsArray() {
        testObj.addNativeField(1);
        assertEquals("NativeField set to 1", 1, testObj.getNativeFieldsArray());
    }

    @Test
    public void test addNativeField() {
        // TODO
    }

    @Test
    public void testMoveFieldUp() {
        // TODO
    }

    @Test
    public void testMoveFieldDown() {
        // TODO
    }

    @Test
    public void testMakeArrays() {
        // TODO
    }

    @Test
    public void testToString() {
        // Do I even test this one?
    }
}