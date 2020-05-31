package com.group18.asdc.util;

import java.util.ArrayList;
import java.util.HashMap;

public class CommonUtil {

    private static CommonUtil commonUtilObj = null;

    public static final HashMap<String,String> roleVsLandingPage = new HashMap<String,String>();
    public static enum userRoles {
        ROLE_ADMIN,
        ROLE_GUEST
    };

    static{
        roleVsLandingPage.put(userRoles.ROLE_ADMIN.name(),"/admin");
        roleVsLandingPage.put(userRoles.ROLE_GUEST.name(),"/course");
    }

    private CommonUtil()
    {
    }

    /**
     * 
     * @return
     */
    public static CommonUtil getInstance()
    {
        if(commonUtilObj == null)
        {
            commonUtilObj = new CommonUtil();
        }
        //
        return commonUtilObj;
    }

    /**
     * 
     * @param objects
     * @return
     */
    public ArrayList<Object> convertQueryVariablesToArrayList(Object... objects)
    {
        ArrayList<Object> valueList = new ArrayList<Object>();
        for(Object eachValue: objects)
        {
            valueList.add(eachValue);
        }

        return valueList;
    }
    
}