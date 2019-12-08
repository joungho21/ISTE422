import java.awt.*;
import java.awt.event.*;
import javax.swing.*;   
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public class CreateDDLMySQL extends EdgeConvertCreateDDL implements MySQLConfig {
   protected StringBuffer sb;
   
   public CreateDDLMySQL(EdgeTable[] inputTables, EdgeField[] inputFields) {
      super(inputTables, inputFields, strDataType, createDatabase, endLine, newLine, use, createTable, tab, openGroup, closeGroup, negation, nullIndicator, defaultIndicator, constraint, primaryKeyNameAmendment, primaryKey, delimiter, keyNameAmendment, foreignKeyIndicator, referenceIndicator);
      sb = new StringBuffer();
   } 
   
   public CreateDDLMySQL() { //default constructor with empty arg list for to allow output dir to be set before there are table and field objects
      
   }
   
   public String getDatabaseName() {
      return databaseName;
   }
   
   public String getProductName() {
      return "MySQL";
   }

   public String getSQLString() {
      super.createDDL();
      return sbDDL.toString();
   }
   
}//EdgeConvertCreateDDL
