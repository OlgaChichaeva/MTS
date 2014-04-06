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
import objects.LegalContr;
import dao.LegalContrDAO;
import filters.LegalContrFilter;
import objects.LegalEntity;
import oracle.conditions.ConditionCreator;
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
    private static final String SELECT_FOR_ALL = "SELECT con.contr_id, con.contr_doc, con.begin_date,"
                + " ent.*"
                + " FROM " + TABLE_NAME + " con"
                + " INNER JOIN legal_entity ent on con.company_id=ent.company_id";

    private final IntegerConditionCreator contrIDConditionCreator;
    private final IntegerConditionCreator companyIDConditionCreator;
    private final FilteredConditionCreator filteredConditionCreator;
    
    public OracleLegalContrDAO(DataSource dataSource) {
        super(dataSource);
        contrIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE " + CONTR_ID_COL + " = ?");
        companyIDConditionCreator = new IntegerConditionCreator(SELECT_FOR_ALL + " WHERE " + COMP_ID_COL + " = ?");
        filteredConditionCreator = new FilteredConditionCreator();
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
        return getAllObjectsByCustomQuery(SELECT_FOR_ALL);
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
    public List<LegalContr> getFilteredContracts(LegalContrFilter legalContr) {
        filteredConditionCreator.setFilter(legalContr);
        return getObjectsWithConditions(filteredConditionCreator);
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
        LegalEntity legalEntity = makeLegalEntity(rs);
        LegalContr newContr = makeLegalContr(rs, legalEntity);
        return newContr;
    }
    
    // Классы ConditionCreator
    private static class FilteredConditionCreator extends ConditionCreator {
        
        private LegalContrFilter filter;

        @Override
        public String createSelect() {
            final String SEP = " LIKE ?";
            final String SELECT = SELECT_FOR_ALL 
                    + " WHERE ent.name_company" + SEP
                    + " AND con." + DOC_COL + SEP
                    + " AND con." + DATE_COL + SEP;
            return SELECT;
        }

        @Override
        public void prepareSelectStatement(PreparedStatement ps) throws SQLException {
            final String SEP = "%";
            ps.setString(1, SEP + filter.getLegalEntity() + SEP);
            ps.setString(2, SEP + filter.getContrDoc() + SEP);
            ps.setString(3, SEP + filter.getBeginDate() + SEP);
        }

        /**
         * @param filter the filter to set
         */
        public void setFilter(LegalContrFilter filter) {
            this.filter = filter;
        }
        
    }
}
