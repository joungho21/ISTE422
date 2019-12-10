interface MySQLConfig {

    //this array is for determining how MySQL refers to datatypes
    public String[] strDataType = {"VARCHAR", "BOOL", "INT", "DOUBLE"};
    public String createDatabase = "CREATE DATABASE";
    public String endLine = ";";
    public String newLine = "\r\n";
    public String use = "USE";
    public String createTable = "CREATE TABLE";
    public String tab = "\t";
    public String openGroup = "(";
    public String closeGroup = ")";
    public String negation = "NOT";
    public String nullIndicator = "NULL";
    public String defaultIndicator = "DEFAULT";
    public String constraint = "CONSTRAINT";
    public String primaryKeyNameAmendment = "_PK";
    public String primaryKey = "PRIMARY KEY";
    public String delimiter = ",";
    public String keyNameAmendment = "_FK";
    public String foreignKeyIndicator = "FOREIGN KEY";
    public String referenceIndicator = "REFERENCES";

}