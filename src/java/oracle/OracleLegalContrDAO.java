/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import legalcontr.LegalContr;
import legalcontr.LegalContrDAO;
import oracle.conditions.IntegerConditionCreator;

/**
 *
 * @author Ivan
 */
class OracleLegalContrDAO extends OracleUniversalDAO<LegalContr> implements LegalContrDAO {
    
    private static final String TABLE_NAME = "legal_contr";
    private static final String CONTR_ID_COL = "contr_id";
    private static final String COMP_ID_COL = "company_id";
    private static final String DOC_COL = "contr_doc";
    private static final String DATE_COL = "begin_date";

    private final IntegerConditionCreator contrIDConditionCreator;
    private final IntegerConditionCreator companyIDConditionCreator;
    
    public OracleLegalContrDAO(DataSource dataSource) {
        super(dataSource);
        contrIDConditionCreator = new IntegerConditionCreator(TABLE_NAME, CONTR_ID_COL);
        companyIDConditionCreator = new IntegerConditionCreator(TABLE_NAME, COMP_ID_COL);
    }
    
    @Override
    public boolean addLegalContr(LegalContr contr) {
        return addObject(contr);
    }

    @Override
    public boolean deleteLegalContr(int contrID) {
        return deleteObjectByID(contrID);
    }

    @Override
    public boolean updateLegalContr(LegalContr contr) {
        return updateObject(contr);
    }

    @Override
    public List<LegalContr> getAllContracts() {
        return getAllObjects(TABLE_NAME);
    }

    @Override
    public LegalContr getContrByID(int contrID) {
        contrIDConditionCreator.setValue(contrID);
        return getUniqueObject(contrIDConditionCreator);
    }

    @Override
    public List<LegalContr> getContractsByCompanyID(int companyID) {
        companyIDConditionCreator.setValue(companyID);
        return getObjectsWithConditions(companyIDConditionCreator);
    }

    @Override
    protected String makeInsertStatement() {
        final String INSERT = "INSERT INTO " + TABLE_NAME
                    + "(" + COMP_ID_COL + "," + DOC_COL + "," + DATE_COL + ")"
                    + " VALUES(?,?,?)";
        return INSERT;
    }

    @Override
    protected String makeDeleteStatement() {
        final String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + CONTR_ID_COL + " = ?";
        return DELETE;
    }

    @Override
    protected String makeUpdateStatement() {
        final String SEP = "=?,";
        final String UPDATE = "UPDATE " + TABLE_NAME + " SET "
                    + COMP_ID_COL + SEP + DOC_COL + SEP + DATE_COL + "=?"
                    + " WHERE " + CONTR_ID_COL + " = ?";
        return UPDATE;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement ps, LegalContr contr) throws SQLException {
        ps.setInt(1, contr.getCompanyID());
        ps.setString(2, contr.getContrDoc());
        ps.setDate(3, new java.sql.Date(contr.getBeginDate().getTime()));
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement ps, LegalContr contr) throws SQLException {
        prepareInsertStatement(ps, contr);
        ps.setInt(4, contr.getCompanyID());
    }

    @Override
    protected LegalContr makeObject(ResultSet rs) throws SQLException {
        LegalContr newContr = new LegalContr();
        newContr.setCompanyID(rs.getInt(COMP_ID_COL));
        newContr.setContrID(rs.getInt(CONTR_ID_COL));
        newContr.setContrDoc(rs.getString(DOC_COL));
        newContr.setBeginDate(rs.getDate(DATE_COL));
        return newContr;
    }
}
