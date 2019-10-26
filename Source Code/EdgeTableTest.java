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
        testGetRelatedTablesArray();
        testGetRelatedFieldsArray();
        testGetNativeFieldsArray();
        testMoveFieldUp();
        testMoveFieldDown();
        testToString();
    }

    @Test 
    public void testGetNumFigures() {
        assertEquals("Table was named table0", 0, testObj.getNumFigure());
    }

    @Test
    public void testGetName() {
        assertEquals("Table was named table0", "table0", testObj.getName());
    }

    @Test
    public void testGetRelatedTablesArray() {
        testObj.addRelatedTable(1);
        testObj.makeArrays();
        assertEquals("TableID 1 was added to array", 1, testObj.getRelatedTablesArray());
    }

    @Test
    public void testGetRelatedFieldsArray() {
        testObj.setRelatedField(0, 1);
        testObj.makeArrays();
        assertEquals("FieldID 0 set to 1", 1, testObj.getRelatedFieldsArray());
    }

    @Test
    public void testGetNativeFieldsArray() {
        testObj.addNativeField(1);
        testObj.makeArrays();
        assertEquals("NativeField set to 1", 1, testObj.getNativeFieldsArray());
    }

    @Test
    public void testMoveFieldUp() {
        testObj.addNativeField(0);
        testObj.addNativeField(1);
        testObj.moveFieldUp(1);
        if(testObj.getNativeFieldsArray()[0] == 1 && testObj.getNativeFieldsArray()[1] == 0) {
            assertTrue("Forward array swap value 1 to index 0", true);
        }
        assertTrue("Forward array swap value 1 to index 0", false);
    }

    @Test
    public void testMoveFieldDown() {
        testObj.addNativeField(0);
        testObj.addNativeField(1);
        testObj.moveFieldDown(0);
        if(testObj.getNativeFieldsArray()[0] == 1 && testObj.getNativeFieldsArray()[1] == 0) {
            assertTrue("Backwards array swap value 0 to index 1", true);
        }
        assertTrue("Backwards array swap value 0 to index 1", false);
    }

    @Test
    public void testToString() {
        assertEquals("Outputs valid toString for table0 value", "Table: 0\r\n{\r\nTableName: table0\r\nNativeFields: \r\nRelatedTables: \r\nRelatedFields: \r\n}\r\n", testObj.toString());
    }
}