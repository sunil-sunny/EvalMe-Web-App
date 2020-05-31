package com.group18.asdc.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@Repository
public class SQLMethods {

    @Autowired
    private DataSource dataSource;
    private ResultSet rs;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void replaceValuesInPreparedStmt(PreparedStatement preparedStmt, ArrayList values) throws SQLException {
        int iterator = 1;
        for (Object eachValue : values) {
            preparedStmt.setObject(iterator,eachValue);
            iterator++;
        }   
    }

    private PreparedStatement constructPreparedStmt(Connection conn, String sqlQuery, ArrayList values) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);

        replaceValuesInPreparedStmt(preparedStatement,values);

        return preparedStatement;
    }


    private ArrayList<String> getColumnNames(ResultSetMetaData metaData) throws SQLException
    {
        int columnIter = 1, columnCount = metaData.getColumnCount();
        ArrayList<String> columnNameList = new ArrayList<>();
        for(columnIter = 1;columnIter <= columnCount; columnIter++)
        {
            columnNameList.add(metaData.getColumnName(columnIter));
        }

        return columnNameList;
    }

    private PreparedStatement constructBatchInsertQuery(PreparedStatement preparedStmt, ArrayList<ArrayList> valuesList)
            throws SQLException
    {
        for(ArrayList values : valuesList)
        {
            replaceValuesInPreparedStmt(preparedStmt,values);
            preparedStmt.addBatch();
        }
        return preparedStmt;
    }

    /**
     * 
     * @param sqlQuery
     * @param values
     * @return
     * @throws SQLException
     */
    public Object insertQuery(String sqlQuery, ArrayList<Object> values) throws SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement preparedStatement = constructPreparedStmt(conn, sqlQuery, values);
        int rowAffected = preparedStatement.executeUpdate();
        if (rowAffected == 1) {
            // get candidate id
            rs = preparedStatement.getGeneratedKeys();
            if (rs.next())
                return rs.getObject(1);

        }
        return null;
    }

    /**
     * 
     * @param sqlQuery
     * @param values
     * @return
     * @throws SQLException
     */
    public ArrayList<HashMap<String, Object>> selectQuery(String sqlQuery, ArrayList<Object> values)
            throws SQLException {
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = constructPreparedStmt(conn, sqlQuery, values);
        //
        rs = preparedStatement.executeQuery();
        //
        ResultSetMetaData metaData = rs.getMetaData();
        ArrayList<String> columnNamesList = getColumnNames(metaData);
        //
        ArrayList<HashMap<String,Object>> resultRowList = new ArrayList<HashMap<String,Object>>();
        HashMap<String,Object> rowObjectMap = null;
        int columnCount = columnNamesList.size();
        while(rs.next())
        {
            rowObjectMap = new HashMap<String,Object>();
            //
            int columnIter = 1;
            for(columnIter = 1;columnIter <= columnCount; columnIter++)
            {
                rowObjectMap.put(columnNamesList.get(columnIter-1), rs.getObject(columnIter));   
            }
            //
            resultRowList.add(rowObjectMap);   
        }
        //
        return resultRowList;
    }

    /**
     * 
     * @param sqlQuery
     * @param updateValueList
     * @param criteriaValueList
     * @return
     * @throws SQLException
     */
    public Integer updateQuery(String sqlQuery, ArrayList updateValueList, ArrayList criteriaValueList) throws SQLException{

        Connection conn = dataSource.getConnection();
        updateValueList.addAll(criteriaValueList);
        PreparedStatement preparedStatement = constructPreparedStmt(conn, sqlQuery, updateValueList);
        //
        return preparedStatement.executeUpdate();
    }

    /**
     * 
     * @param sqlQuery
     * @param criteriaList
     * @return
     * @throws SQLException
     */
    public Integer deleteQuery(String sqlQuery,ArrayList criteriaList)throws SQLException{
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = constructPreparedStmt(conn, sqlQuery, criteriaList);
        //
        return preparedStatement.executeUpdate();
    }


    /**
     * 
     * @param sqlQuery
     * @param valuesList
     * @return
     * @throws SQLException
     */
    public Integer multipleInsertQuery(String sqlQuery,ArrayList<ArrayList> valuesList)throws SQLException{
        Connection conn = dataSource.getConnection();
        PreparedStatement preparedStatement = constructPreparedStmt(conn, sqlQuery, new ArrayList<>());
        //
        preparedStatement = constructBatchInsertQuery(preparedStatement, valuesList);
        //
        return preparedStatement.executeBatch().length;
    }

}
