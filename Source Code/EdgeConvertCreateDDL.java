import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;

public abstract class EdgeConvertCreateDDL extends CreateDDL {
   protected String databaseName;
   static String[] products = {"MySQL"};
   protected EdgeTable[] tables; //master copy of EdgeTable objects
   protected EdgeField[] fields; //master copy of EdgeField objects
   protected int[] numBoundTables;
   protected int maxBound;
   protected StringBuffer sb;
   protected StringBuffer sbDDL;
   protected int selected;
   
   public EdgeConvertCreateDDL(EdgeTable[] tables, EdgeField[] fields, String[] strDataType, String createDatabase, String endLine, String newLine, String use, String createTable, String tab, String openGroup, String closeGroup, String negation, String nullIndicator, String defaultIndicator, String constraint, String primaryKeyNameAmendment, String primaryKey, String delimiter, String keyNameAmendment, String foreignKeyIndicator, String referenceIndicator) {
      super(strDataType, createDatabase, endLine, newLine, use, createTable, tab, openGroup, closeGroup, negation, nullIndicator, defaultIndicator, constraint, primaryKeyNameAmendment, primaryKey, delimiter, keyNameAmendment, foreignKeyIndicator, referenceIndicator);
      this.tables = tables;
      this.fields = fields;
      initialize();
   } //EdgeConvertCreateDDL(EdgeTable[], EdgeField[])
   
   public EdgeConvertCreateDDL() { //default constructor with empty arg list for to allow output dir to be set before there are table and field objects
      super();
   } //EdgeConvertCreateDDL()

   public void initialize() {
      numBoundTables = new int[tables.length];
      maxBound = 0;
      sb = new StringBuffer();

      for (int i = 0; i < tables.length; i++) { //step through list of tables
         int numBound = 0; //initialize counter for number of bound tables
         int[] relatedFields = tables[i].getRelatedFieldsArray();
         for (int j = 0; j < relatedFields.length; j++) { //step through related fields list
            if (relatedFields[j] != 0) {
               numBound++; //count the number of non-zero related fields
            }
         }
         numBoundTables[i] = numBound;
         if (numBound > maxBound) {
            maxBound = numBound;
         }
      }
   }

   protected int convertStrBooleanToInt(String input) { //MySQL uses '1' and '0' for boolean types
      if (input.equals("true")) {
         return 1;
      } else {
         return 0;
      }
   }
   
   protected EdgeTable getTable(int numFigure) {
      for (int tIndex = 0; tIndex < tables.length; tIndex++) {
         if (numFigure == tables[tIndex].getNumFigure()) {
            return tables[tIndex];
         }
      }
      return null;
   }
   
   protected EdgeField getField(int numFigure) {
      for (int fIndex = 0; fIndex < fields.length; fIndex++) {
         if (numFigure == fields[fIndex].getNumFigure()) {
            return fields[fIndex];
         }
      }
      return null;
   }

   public void createDDL() {
      EdgeConvertGUI.setReadSuccess(true);
      this.databaseName = generateDatabaseName();
      sbDDL = new StringBuffer();
      sbDDL.append(_createDatabase + " " + databaseName + _endLine + _newLine);
      sbDDL.append(_use + " " + databaseName + _endLine + _newLine);
      for (int boundCount = 0; boundCount <= maxBound; boundCount++) { //process tables in order from least dependent (least number of bound tables) to most dependent
         for (int tableCount = 0; tableCount < numBoundTables.length; tableCount++) { //step through list of tables
            if (numBoundTables[tableCount] == boundCount) { //
               sbDDL.append(_createTable + " " + tables[tableCount].getName() + " " + _openGroup + _newLine);
               int[] nativeFields = tables[tableCount].getNativeFieldsArray();
               int[] relatedFields = tables[tableCount].getRelatedFieldsArray();
               boolean[] primaryKey = new boolean[nativeFields.length];
               int numPrimaryKey = 0;
               int numForeignKey = 0;
               for (int nativeFieldCount = 0; nativeFieldCount < nativeFields.length; nativeFieldCount++) { //print out the fields
                  EdgeField currentField = getField(nativeFields[nativeFieldCount]);
                  sbDDL.append(_tab + currentField.getName() + " " + _strDataType[currentField.getDataType()]);
                  if (currentField.getDataType() == 0) { //varchar
                     sbDDL.append(_openGroup + currentField.getVarcharValue() + _closeGroup); //append varchar length in () if data type is varchar
                  }
                  if (currentField.getDisallowNull()) {
                     sbDDL.append(" " + _negation + _nullIndicator);
                  }
                  if (!currentField.getDefaultValue().equals("")) {
                     if (currentField.getDataType() == 1) { //boolean data type
                        sbDDL.append(" " + _defaultIndicator + " " + convertStrBooleanToInt(currentField.getDefaultValue()));
                     } else { //any other data type
                        sbDDL.append(" " + _defaultIndicator + " " + currentField.getDefaultValue());
                     }
                  }
                  if (currentField.getIsPrimaryKey()) {
                     primaryKey[nativeFieldCount] = true;
                     numPrimaryKey++;
                  } else {
                     primaryKey[nativeFieldCount] = false;
                  }
                  if (currentField.getFieldBound() != 0) {
                     numForeignKey++;
                  }
                  sbDDL.append(_delimiter + _newLine); //end of field
               }
               if (numPrimaryKey > 0) { //table has primary key(s)
                  sbDDL.append(_constraint + " " + tables[tableCount].getName() + _primaryKeyNameAmendment + " " + _primaryKey + " " + _openGroup);
                  for (int i = 0; i < primaryKey.length; i++) {
                     if (primaryKey[i]) {
                        sbDDL.append(getField(nativeFields[i]).getName());
                        numPrimaryKey--;
                        if (numPrimaryKey > 0) {
                           sbDDL.append(_delimiter + " ");
                        }
                     }
                  }
                  sbDDL.append(_closeGroup);
                  if (numForeignKey > 0) {
                     sbDDL.append(_delimiter);
                  }
                  sbDDL.append(_newLine);
               }
               if (numForeignKey > 0) { //table has foreign keys
                  int currentFK = 1;
                  for (int i = 0; i < relatedFields.length; i++) {
                     if (relatedFields[i] != 0) {
                        sbDDL.append(_constraint + " " + tables[tableCount].getName() + _keyNameAmendment + currentFK +
                                  " " + _foreignKeyIndicator + _openGroup + getField(nativeFields[i]).getName() + _closeGroup + _referenceIndicator +
                                  getTable(getField(nativeFields[i]).getTableBound()).getName() + _openGroup + getField(relatedFields[i]).getName() + _closeGroup);
                        if (currentFK < numForeignKey) {
                           sbDDL.append(_delimiter + _newLine);
                        }
                        currentFK++;
                     }
                  }
                  sbDDL.append(_newLine);
               }
               sbDDL.append(_closeGroup + _endLine + _newLine + _newLine); //end of table
            }
         }
      }
   }

   public String generateDatabaseName() { //prompts user for database name
      String dbNameDefault = "DefaultDB";
      do {
         databaseName = (String)JOptionPane.showInputDialog(
                       null,
                       "Enter the database name:",
                       "Database Name",
                       JOptionPane.PLAIN_MESSAGE,
                       null,
                       null,
                       dbNameDefault);
         if (databaseName == null) {
            EdgeConvertGUI.setReadSuccess(false);
            return "";
         }
         if (databaseName.equals("")) {
            JOptionPane.showMessageDialog(null, "You must select a name for your database.");
         }
      } while (databaseName.equals(""));
      return databaseName;
   }

   public abstract String getDatabaseName();

   public abstract String getProductName();
   
   public abstract String getSQLString();
   
}//EdgeConvertCreateDDL
