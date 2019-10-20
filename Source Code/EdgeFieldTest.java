import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeFieldTest {
	EdgeField testObj;

	@Before

	public void setUp() throws Exception {
		testObj = new EdgeField("123 test");

		runner();
	}

	public void runner() {
		/* 
		 * Following test with Set method are tested with the Get counterparts together
		 * to test whether the value has been set and whether the value returns
		 * what it was set previously
		 * */
		testGetNumFigure();
		testGetName();
		testTableID();
		testTableBound();
		testFieldBound();
		testDisallowNull();
		testIsPrimaryKey();
		testDefaultValue();
		testVarcharValue();
		testDataType();
		testGetStrDataType();
		testToString();
	}

	@Test
	public void testGetNumFigure() {
		assertEquals("NumFigure was set to 123",123,testObj.getNumFigure());
	}

	public void testGetName() {
		assertEquals("Name was set to test","test",testObj.getName());
	}

	public void testTableID() {
		testObj.setTableID(5);
		assertEquals("TableID set to 5",5,testObj.getTableID());
	}

	public void testTableBound() {
		testObj.setTableBound(8);
		assertEquals("TableBound set to 8",8,testObj.getTableBound());
	}

	public void testFieldBound() {
	        testObj.setFieldBound(1);
	 	assertEquals("FieldBound set to 1",1,testObj.getFieldBound());
	}

	public void testDisallowNull() {
		testObj.setDisallowNull(false);
		assertEquals("DisallowNull set to false",false,testObj.getDisallowNull());
	}

	public void testIsPrimaryKey() {
		testObj.setIsPrimaryKey(true);
		assertEquals("IsPrimaryKey set to true",true,testObj.getIsPrimaryKey());
	}

	public void testDefaultValue() {
		testObj.setDefaultValue("testV2");
		assertEquals("Default Value set to testV2","testV2",testObj.getDefaultValue());
	}

	public void testVarcharValue() {
		testObj.setVarcharValue(314);
		assertEquals("VarcharValue set to 314",314,testObj.getVarcharValue());
	}

	public void testDataType() {
		testObj.setDataType(1);
		assertEquals("DataType set to 1 (Boolean)",1,testObj.getDataType());
	}

	public void testGetStrDataType() {
		String[] testArry = {"Varchar", "Boolean", "Integer", "Double"};
		assertEquals("GetStrDataType should return what it was initailized in the beginning= {varchar,boolean,int,double}",testArry,testObj.getStrDataType());
	}

	public void testToString() {
		System.out.println(testObj.toString());
	}
}

