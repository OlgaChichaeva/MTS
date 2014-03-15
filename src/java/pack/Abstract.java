/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;



/**
 *
 * @author Ольга
 */
public abstract class Abstract {

    private DataSource sour;

   public Abstract(DataSource sour) {
        this.sour = sour;
    }

    protected Connection getConn() throws SQLException {
        return sour.getConnection();
    }
}
