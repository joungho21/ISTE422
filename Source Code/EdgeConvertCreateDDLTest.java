import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeConvertCreateDDLTest {
  EdgeConvertCreateDDL testObj;

  @Before
  public void setUp() throws Exception {
  EdgeTable table1 = new EdgeTable("1|table1");
  EdgeTable table2 = new EdgeTable("2|table2");
  EdgeField field1 = new EdgeField("1|field1");
  EdgeField field2 = new EdgeField("2|field2");
  EdgeField field3 = new EdgeField("3|field3");
  table2.setRelatedField(0, 1);
  EdgeTable[] tables = {table1, table2};
  EdgeField[] fields = {field1, field2, field3};
  CreateDDLMySQL testObj = new CreateDDLMySQL(tables, fields);
  
  runner();
  }
  
  // When MainTester is run, the number of tests
  // reported is the number of tests in this list.
  public void runner() {
  testNumTables();
  testNumBoundTables();
  testNumBoundTablesValue1();
  testNumBoundTablesValue2();
  testMaxBound();
  testGetTableByName();
  testGetTableByNumFigure();
  testGetFieldByName();
  testGetFieldByNumFigure();
  }

  @Test
  public void testNumTables() {
    assertEquals("The initial number of tables set was 2 so it should be 2",2,testObj.tables.length);
  }

  @Test
  public void testNumBoundTables() {
    assertEquals("The initial number of tables set was 2 so it should be 2",2,testObj.numBoundTables.length);
  }

  @Test
  public void testNumBoundTablesValue1() {
    assertEquals("The number of bound fields was 0 so it should be 0",0,testObj.numBoundTables[0]);
  }

  @Test
  public void testNumBoundTablesValue2() {
    assertEquals("The number of bound fields 1 so it should be 1",1,testObj.numBoundTables[1]);
  }

  @Test
  public void testMaxBound() {
    assertEquals("The number of bound fields in both tables was 0 and 1 so the max should be 1",1,testObj.maxBound);
  }

  @Test
  public void testGetTableByName() {
    assertEquals("First table was named table1 so it should return table1","table1",testObj.getTable(1).getName());
  }

  @Test
  public void testGetTableByNumFigure() {
    assertEquals("First table was named table1 so it should return table1",1,testObj.getTable(1).getNumFigure());
  }

  @Test
  public void testGetFieldByName() {
    assertEquals("First field was named field1 so it should return field1","field1",testObj.getField(1).getName());
  }

  @Test
  public void testGetFieldByNumFigure() {
    assertEquals("First field was named field1 so it should return field1",1,testObj.getField(1).getNumFigure());
  }

}
