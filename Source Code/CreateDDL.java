public abstract class CreateDDL {

    // createDDL values
    protected String[] _strDataType;
    protected String _createDatabase;
    protected String _endLine;
    protected String _newLine;
    protected String _use;
    protected String _createTable;
    protected String _tab;
    protected String _openGroup;
    protected String _closeGroup;
    protected String _negation;
    protected String _nullIndicator;
    protected String _defaultIndicator;
    protected String _constraint;
    protected String _primaryKeyNameAmendment;
    protected String _primaryKey;
    protected String _delimiter;
    protected String _keyNameAmendment;
    protected String _foreignKeyIndicator;
    protected String _referenceIndicator;

    public CreateDDL(String[] strDataType, String createDatabase, String endLine, String newLine, String use, String createTable, String tab, String openGroup, String closeGroup, String negation, String nullIndicator, String defaultIndicator, String constraint, String primaryKeyNameAmendment, String primaryKey, String delimiter, String keyNameAmendment, String foreignKeyIndicator, String referenceIndicator) {
        this._strDataType = strDataType;
        this._createDatabase = createDatabase;
        this._endLine = endLine;
        this._newLine = newLine;
        this._use = use;
        this._createTable = createTable;
        this._tab = tab;
        this._openGroup = openGroup;
        this._closeGroup = closeGroup;
        this._negation = negation;
        this._nullIndicator = nullIndicator;
        this._defaultIndicator = defaultIndicator;
        this._constraint = constraint;
        this._primaryKeyNameAmendment = primaryKeyNameAmendment;
        this._primaryKey = primaryKey;
        this._delimiter = delimiter;
        this._keyNameAmendment = keyNameAmendment;
        this._foreignKeyIndicator = foreignKeyIndicator;
        this._referenceIndicator = referenceIndicator;
    }

    public CreateDDL() { }

}