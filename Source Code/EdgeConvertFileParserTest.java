import java.beans.Transient;
import java.io.File;

import static org.junit.Asset.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeConvertFileParserTest {
    EdgeConvertFileParser testObj;

    @Before
    public void setup() throws Exception {
        File file = new File("Courses.edg");
        testObj = new EdgeConvertFileParser(file);

        runner();
    }

    public void runner() {
        testParseSaveFile();
        testGetEdgeTables();
        testGetEdgeFields();
        testOpenFile();
    }

    @Test
    public void testParseSaveFile() {
        testObj.parseSaveFile();
        assertTrue("Class detects and parses save files", (testObj.getEdgeTables().length > 0));
    }

    @Test
    public void testGetEdgeTables() {
        assertEquals("Edge tables is length 0 array at object initialization", null, testObj.getEdgeTables());
    }

    @Test
    public void testGetEdgeFields() {
        assertEquals("Edge fields is length 0 array at object initialization", null, testObj.getEdgeFields());
    }

    @Test
    public void testOpenFile() {
        File file = new File("Courses.edg");
        testObj.openFile(file);
        assertTrue("EdgeTables is not null when test file is opened", testObj.getEdgeTables() != null);
    }
}