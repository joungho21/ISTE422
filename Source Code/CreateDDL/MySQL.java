package CreateDDL;

interface MySQL {
    String databaseName = "";
    String[] strDataType = {"VARCHAR", "BOOL", "INT", "DOUBLE"};
    StringBuffer sb = new StringBuffer();
}