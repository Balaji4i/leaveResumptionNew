package com.view.utils;

import java.sql.Date;
import java.sql.SQLException;

import oracle.adf.share.ADFContext;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;
import oracle.jbo.server.ApplicationModuleImpl;

import oracle.jdbc.OracleTypes;

public class ADFApproval {
    public ADFApproval() {
        super();
    }
    private static Object[][] dobProcArgs = null;


    public static String invokeSubmitPkg(String submitPkg, Object func_id,
                                         Object ref_id, Object level_no,
                                         String tableName,
                                         String app_status_column,
                                         String pk_column, Object OrgId,
                                         Object ProjectId) throws SQLException {

        String flag = null;
        String pkgMethod =
            "call " + submitPkg + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        oracle.jbo.domain.Number p_func_id =
            new oracle.jbo.domain.Number(func_id.toString());
        oracle.jbo.domain.Number p_ref_id =
            new oracle.jbo.domain.Number(ref_id.toString());
        oracle.jbo.domain.Number p_level_no =
            new oracle.jbo.domain.Number(level_no.toString());
        String p_table_name = tableName;
        String p_app_status_column = app_status_column;
        String p_pk_column = pk_column;
        String p_flow_with = null;
        String p_err_code = null;
        String p_err_msg = null;
        String p_attribute1 = OrgId.toString();
        String p_attribute2 = null;
        String p_attribute3 = null;
        String p_attribute4 = null;
        String p_attribute5 = null;

        System.err.println("submitPkg==>" + submitPkg + "p_func_id==>" +
                           p_func_id + "p_ref_id==>" + p_ref_id +
                           "p_level_no==>" + p_level_no + "p_table_name==>" +
                           p_table_name + "p_app_status_column==>" +
                           p_app_status_column + "p_pk_column==>" +
                           p_pk_column + "ORGID==>" + OrgId + "PROID==>" +
                           ProjectId);        
System.out.println("1111");
      
System.out.println("22222222222");
        dobProcArgs =
                new Object[][] { { "IN", p_func_id, OracleTypes.NUMBER }, //0
                    { "IN", p_ref_id, OracleTypes.NUMBER }, //1
                    { "IN", p_level_no, OracleTypes.NUMBER }, //2
                    { "IN", p_table_name, OracleTypes.VARCHAR }, //3
                    { "IN", p_app_status_column, OracleTypes.VARCHAR }, //4
                    { "IN", p_pk_column, OracleTypes.VARCHAR }, //5
                    { "IN", p_attribute1, OracleTypes.VARCHAR }, //6 --ORG ID
                    { "IN", p_attribute2, OracleTypes.VARCHAR },
                    //7 --Project ID
                    { "IN", p_attribute3, OracleTypes.VARCHAR }, //8
                    { "IN", p_attribute4, OracleTypes.VARCHAR }, //9
                    { "IN", p_attribute5, OracleTypes.VARCHAR }, //10
                    { "OUT", p_flow_with, OracleTypes.VARCHAR }, //11
                    { "OUT", p_err_code, OracleTypes.VARCHAR }, //12
                    { "OUT", p_err_msg, OracleTypes.VARCHAR } //13
                    } ;
        System.out.println("333333333333");
        System.out.println(p_flow_with +"------------p_err_code");

        try {
            ApplicationModuleImpl am =(ApplicationModuleImpl)ADFUtils.getApplicationModuleForDataControl(null);

            DBUtils.callDBStoredProcedure(am.getDBTransaction(), pkgMethod,
                                          dobProcArgs);
            flag = (String)dobProcArgs[13][1];
            System.out.println("44444444444444444");
            p_flow_with = (String)dobProcArgs[11][1];
            ADFContext.getCurrent().getSessionScope().put("flow_with",
                                                          p_flow_with);
            System.out.println(p_err_code +"------------p_err_code");
                    System.out.println(p_err_msg +"------------p_err_msg");
                    System.out.println("55555");

        } catch (SQLException e) {
            flag = (String)dobProcArgs[13][1];
        }
        return flag;
    }

    public static String invokeResponsePkg(String responsePkg, Object func_id,
                                           Object user_grp, Object ref_id,
                                           Object level_no, Object appr_id,
                                           String response, String ar_status,
                                           Object fwd_to, String tableName,
                                           String status_column,
                                           String pk_column) throws SQLException {
        String flag = null;
        
        System.out.println("111111111");
        String pkgMethod =
            "call " + responsePkg + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";


        oracle.jbo.domain.Number p_func_id =
            new oracle.jbo.domain.Number(func_id.toString());
        oracle.jbo.domain.Number p_ref_id =
            new oracle.jbo.domain.Number(ref_id.toString());
        System.out.println("222222222222");
        oracle.jbo.domain.Number p_user_grp_id =
            new oracle.jbo.domain.Number(user_grp.toString());
        System.out.println("333333333333");
        oracle.jbo.domain.Number p_level_no =
            new oracle.jbo.domain.Number(level_no.toString());
        oracle.jbo.domain.Number p_appr_id =
            new oracle.jbo.domain.Number(appr_id.toString());
        String p_response = response;
        String p_ar_status = ar_status;
        oracle.jbo.domain.Number p_fwd_to =
            new oracle.jbo.domain.Number(fwd_to.toString());
        String p_table_name = tableName;
        String p_status_column = status_column;
        String p_pk_column = pk_column;
        String p_err_code = null;
        String p_err_msg = null;
        System.out.println("444444444");

        ApplicationModuleImpl am =(ApplicationModuleImpl)ADFUtils.getApplicationModuleForDataControl(null);

        dobProcArgs =
                new Object[][] { { "IN", p_func_id, OracleTypes.NUMBER }, //0
                    { "IN", p_ref_id, OracleTypes.NUMBER }, //1
                    { "IN", p_user_grp_id, OracleTypes.NUMBER }, //2
                    { "IN", p_level_no, OracleTypes.NUMBER }, //3
                    { "IN", p_appr_id, OracleTypes.NUMBER }, //4
                    { "IN", p_response, OracleTypes.VARCHAR }, //5
                    { "IN", p_ar_status, OracleTypes.VARCHAR }, //6
                    { "IN", p_fwd_to, OracleTypes.NUMBER }, //7
                    { "IN", p_table_name, OracleTypes.VARCHAR }, //8
                    { "IN", p_status_column, OracleTypes.VARCHAR }, //9
                    { "IN", p_pk_column, OracleTypes.VARCHAR }, //10
                    { "OUT", p_err_code, OracleTypes.VARCHAR }, //11
                    { "OUT", p_err_msg,
                      OracleTypes.VARCHAR } //12
                    } ;
System.out.println("5555555555555");


        try {
            DBUtils.callDBStoredProcedure(am.getDBTransaction(), pkgMethod,
                                          dobProcArgs);

        } catch (SQLException e) {
        }
System.out.println("66666666666666");
        flag = (String)dobProcArgs[12][1];
        System.out.println(flag +"----flag");

        return flag;
    }

    //sync data from stg to Lr hdr table
    public static String syncLrProc(String procName) throws SQLException {

        String flag = null;
        String pkgMethod = "call " + procName + "(?)";
        String p_err_code = null;
                    
        dobProcArgs =
                new Object[][] { 
                    { "OUT", p_err_code, OracleTypes.VARCHAR }, //0
                    } ;
        try {
            ApplicationModuleImpl am =(ApplicationModuleImpl)ADFUtils.getApplicationModuleForDataControl(null);
            DBUtils.callDBStoredProcedure(am.getDBTransaction(), pkgMethod, dobProcArgs);
            flag = (String)dobProcArgs[0][1];

        } catch (SQLException e) {
            flag = (String)dobProcArgs[0][1];
        }
        return flag;
    }
    
    public static String approvalInfo(ViewObject VO, String ADD_VAL_CODE){
        String approvalInfo=null;
        ViewCriteria VC=VO.createViewCriteria();
        ViewCriteriaRow VCRow=VC.createViewCriteriaRow();
        VCRow.setAttribute("LookupValueName", ADD_VAL_CODE);
        VC.addRow(VCRow);
        VO.applyViewCriteria(VC);
        VO.executeQuery();
        if(VO.getEstimatedRowCount()==1){
        approvalInfo=VO.first().getAttribute("LookupAddlValue")==null?"a":VO.first().getAttribute("LookupAddlValue").toString();
        }else{
            approvalInfo="a"; 
        }
        return  approvalInfo;
    }
    
}


