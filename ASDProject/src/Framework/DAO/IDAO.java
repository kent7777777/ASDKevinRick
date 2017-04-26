/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework.DAO;

import Framework.IData;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yeerick
 */
public interface IDAO {

    public List<IData> findALL(Connection cn) throws SQLException;
    public IData findUnique(Connection cn, String identifier) throws SQLException;
    public void AddData(Connection cn, IData data) throws SQLException;
    public void deleteData(Connection cn, String identifier) throws SQLException;
    public void updateData(Connection cn, IData data) throws SQLException;
    
}
