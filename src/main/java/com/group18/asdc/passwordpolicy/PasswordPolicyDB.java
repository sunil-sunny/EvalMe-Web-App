package com.group18.asdc.passwordpolicy;

import java.util.ArrayList;

import com.group18.asdc.database.SQLMethods;

public class PasswordPolicyDB implements IPasswordPolicyDB {

    @Override
    public ArrayList loadBasePoliciesFromDB() {
        
        SQLMethods sqlImplementation = null;
        ArrayList policiesList = new ArrayList<>();
        try {
            sqlImplementation = new SQLMethods();
            policiesList = sqlImplementation.selectQuery("SQL_QUERY", new ArrayList<>());    
        } catch (Exception e) {
            //TODO: handle exception
        }
        finally{
            if( sqlImplementation != null )
            {
                //sqlImplementation.cleanup();
            }
        }
        

        return policiesList;
    }

    @Override
    public ArrayList loadPoliciesFromDB() {
        
        SQLMethods sqlImplementation = null;
        ArrayList policiesList = new ArrayList<>();
        try {
            sqlImplementation = new SQLMethods();
            policiesList = sqlImplementation.selectQuery("SQL_QUERY", new ArrayList<>());    
        } catch (Exception e) {
            //TODO: handle exception
        }
        finally{
            if( sqlImplementation != null )
            {
                //sqlImplementation.cleanup();
            }
        }
        

        return policiesList;
    }
    
}