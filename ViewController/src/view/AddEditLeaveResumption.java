package view;

import com.view.utils.ADFApproval;
import com.view.utils.ADFUtils;
import com.view.utils.JSFUtils;
import com.view.utils.RTFUtils;

import java.io.IOException;
import java.io.OutputStream;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.OperationBinding;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;

public class AddEditLeaveResumption {
    private RichPopup p1;
    private RichPopup p2;

    public AddEditLeaveResumption() {
        super();
    }
    
    ViewObject lookupROVO = ADFUtils.findIterator("Lookup_ROVO1Iterator").getViewObject();
    
    public void onClickSave(ActionEvent actionEvent) throws SQLException {   
        ViewObject HdrVO = ADFUtils.findIterator("LeaveResumptionHdr_VO1Iterator").getViewObject();
        String lrId=HdrVO.getCurrentRow().getAttribute("LeaveReqId")==null?"0":
                    HdrVO.getCurrentRow().getAttribute("LeaveReqId").toString();
        if(HdrVO.getCurrentRow().getAttribute("Status")==null){
            HdrVO.getCurrentRow().setAttribute("Status", "Draft");
        }
        String flag = ADFApproval.updateLrProc("UPDATE_LEAVE_RESUMPTION",lrId);
        ADFUtils.findOperation("Commit").execute();
        HdrVO.executeQuery();
        JSFUtils.addFacesInformationMessage("Information Saved Successfully");
    }

    public void onClickCancel(ActionEvent actionEvent) {
        OperationBinding binding = (OperationBinding) ADFUtils.findOperation("Rollback").execute();
    }

    public void onClickRefresh(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void onClickExportExcel(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void onClickSubmit(ActionEvent actionEvent) {
        // Add event code here...
        try{
                ViewObject HdrVO = ADFUtils.findIterator("LeaveResumptionHdr_VO1Iterator").getViewObject();
                                   String submitPkg = "xxfnd_util_pkg.submit_for_appr_lr";
                                   String responsePkg = "xxfnd_util_pkg.update_response";
                                   String tableName = "XXLR_LEAVE_RESUMPTION_HEADER";
                                   String app_status_column = "STATUS";
                                   String pk_column = "LEAVE_REQ_ID";
                                   String fwd_to = "0";
                                   String func_id =
                                    HdrVO.getCurrentRow().getAttribute("FuncId") ==
                                    null ? "0" :
                                    HdrVO.getCurrentRow().getAttribute("FuncId").toString();
                                   String ref_id =
                                    HdrVO.getCurrentRow().getAttribute("LeaveReqId") ==
                                    null ? "0" :
                                    HdrVO.getCurrentRow().getAttribute("LeaveReqId").toString();
                                   String level_no =
                                    HdrVO.getCurrentRow().getAttribute("FlowLevel") ==
                                    null ? "0" :
                                    HdrVO.getCurrentRow().getAttribute("FlowLevel").toString();
                                   String attribute1 =  
                                    HdrVO.getCurrentRow().getAttribute("UserPersonType") ==
                                    null ? "0" :
                                    HdrVO.getCurrentRow().getAttribute("UserPersonType").toString();
                                   String attribute2 =null;
                                   String actualRtrnDate =
                                    HdrVO.getCurrentRow().getAttribute("ActualReturnDate") ==
                                    null ? "0" :
                                    HdrVO.getCurrentRow().getAttribute("ActualReturnDate").toString();
                  String approvalInfo=ADFApproval.approvalInfo(lookupROVO, "LEAVE_RESUMPTION_SUBMIT");
              if(actualRtrnDate.equals("0")){
                    JSFUtils.addFacesErrorMessage("Kindly enter the Actual return date !!!");
                }else{
              if(approvalInfo.equalsIgnoreCase("AUTO")){
                    HdrVO.getCurrentRow().setAttribute("Status", "APR");
                    ADFUtils.findOperation("Commit").execute();
                }else{
//                    String flag =
//                                   ADFApproval.invokeSubmitPkg(submitPkg, func_id, ref_id,
//                                                        level_no, tableName,
//                                                        app_status_column, pk_column,
//                                                        attribute1, attribute2);
                     String flag =
                                   ADFApproval.invokeLRSubmitPkg(ref_id,submitPkg);
                  ADFUtils.findOperation("Commit").execute();
                  JSFUtils.addFacesInformationMessage("Submitted ===== "+flag);
                                        }
                }
                }catch(Exception e){
                    System.out.println("Exception====="+e);
                }
    }
    
    public boolean getApprovalUser() {
        ViewObject HdrVO = ADFUtils.findIterator("LeaveResumptionHdr_VO1Iterator").getViewObject();
        boolean flag = false;
        System.out.println("---1------");
        String flowWith =
            HdrVO.getCurrentRow().getAttribute("FlowWith") ==
            null ? "" :
            HdrVO.getCurrentRow().getAttribute("FlowWith").toString();
        String LoginUser =
            ADFContext.getCurrent().getSessionScope().get("userName") == null ?
            "0" :
            ADFContext.getCurrent().getSessionScope().get("userName").toString();
        ViewObject userVO =
            ADFUtils.findIterator("UserAcess_ROVO1Iterator").getViewObject();
        ViewCriteria viewCriteria = userVO.createViewCriteria();
         ViewCriteriaRow viewCriteriaRow = viewCriteria.createViewCriteriaRow();
         viewCriteriaRow.setAttribute("UserName",LoginUser  );                                   
         viewCriteria.addRow(viewCriteriaRow);
         userVO.applyViewCriteria(viewCriteria);       
        userVO.executeQuery();
        System.out.println("----2----");
        if (userVO.getEstimatedRowCount() != 0) {
            String loginID =
                userVO.first().getAttribute("UserId") == null ? "0" :
                userVO.first().getAttribute("UserId").toString();
            System.err.println("flowWith-->>" + flowWith + "loginID-->" +
                               loginID);
            if (flowWith.equalsIgnoreCase(loginID)) {
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        System.err.println("Flag" + flag);
        return flag;
    }

    public void onClickLJoingReport(FacesContext facesContext, OutputStream outputStream) throws ParseException {
        try {
                  ViewObject HdrVO =
                             ADFUtils.findIterator("LeaveResumptionHdr_VO1Iterator").getViewObject();
        BigDecimal lvRqtId =(BigDecimal)HdrVO.getCurrentRow().getAttribute("LeaveReqId"); 
        JSFUtils.addFacesInformationMessage("lvRqtId======"+lvRqtId);
        Object result=RTFUtils.runReport("//reports//Duty_Joining_Report.rtf", lvRqtId, "DUTY_JOINING");
        outputStream.write((byte[])result);
        } 
    catch (IOException e) {
        System.out.println("Exception " + e);
        } 
        }

    public void setP1(RichPopup p1) {
        this.p1 = p1;
    }

    public RichPopup getP1() {
        return p1;
    }

    public void setP2(RichPopup p2) {
        this.p2 = p2;
    }

    public RichPopup getP2() {
        return p2;
    }

    public void onClickApprove(ActionEvent actionEvent) throws SQLException {
        ViewObject HdrVO = ADFUtils.findIterator("LeaveResumptionHdr_VO1Iterator").getViewObject();
        String responsePkg = "xxfnd_util_pkg.update_response";
        String tableName = "XXLR_LEAVE_RESUMPTION_HEADER";
        String app_status_column = "STATUS";
        String pk_column = "LEAVE_REQ_ID";
        String fwd_to = "0";
        String apprDesc ="Approved";
        
        String func_id =
            HdrVO.getCurrentRow().getAttribute("FuncId") ==
            null ? "" :
            HdrVO.getCurrentRow().getAttribute("FuncId").toString();
        String ref_id =
            HdrVO.getCurrentRow().getAttribute("LeaveReqId") ==
            null ? "" :
            HdrVO.getCurrentRow().getAttribute("LeaveReqId").toString();
        String level_no =
            HdrVO.getCurrentRow().getAttribute("FlowLevel") ==
            null ? "" :
            HdrVO.getCurrentRow().getAttribute("FlowLevel").toString();
        String appr_id =
            HdrVO.getCurrentRow().getAttribute("FlowWith") ==
            null ? "" :
            HdrVO.getCurrentRow().getAttribute("FlowWith").toString();
        String user_grp =
            HdrVO.getCurrentRow().getAttribute("UserGrpId") ==
            null ? "" :
            HdrVO.getCurrentRow().getAttribute("UserGrpId").toString();

        String flag =
            ADFApproval.invokeResponsePkg(responsePkg, func_id,
                                          user_grp, ref_id, level_no,
                                          appr_id, apprDesc, "A",
                                          fwd_to, tableName,
                                          app_status_column,
                                          pk_column);
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(ot25);
        
        if (flag.equalsIgnoreCase("Success")) {
            HdrVO.executeQuery();
             System.err.println(HdrVO.getCurrentRow().getAttribute("Status")+"---------Status");
            JSFUtils.addFacesInformationMessage("Approved Successfully !!!");
            if(HdrVO.getCurrentRow().getAttribute("Status").toString().equalsIgnoreCase("APR")){
//                      JSFUtils.addFacesInformationMessage("Status----"+HdrVO.getCurrentRow().getAttribute("Status"));
                      ADFUtils.findOperation("Commit").execute();
//                      HdrVO.applyViewCriteria(null);
//                      HdrVO.executeQuery();                      
                  }
            else {
//                System.err.println(HdrVO.getCurrentRow().getAttribute("Status")+"---------Status");
                JSFUtils.addFacesInformationMessage("Kindly check----"+HdrVO.getCurrentRow().getAttribute("Status"));
            }
        }
        this.getP1().cancel();
    }

    public void onClickApprCancel(ActionEvent actionEvent) {
        this.getP1().cancel();
    }

    public void onClickRejectSave(ActionEvent actionEvent) throws SQLException {
        ViewObject HdrVO = ADFUtils.findIterator("LeaveResumptionHdr_VO1Iterator").getViewObject();
        String submitPkg = "xxfnd_util_pkg.submit_for_approval";
        String responsePkg = "xxfnd_util_pkg.update_response";
        String tableName = "XXLR_LEAVE_RESUMPTION_HEADER";
        String app_status_column = "STATUS";
        String pk_column = "LEAVE_REQ_ID";
        String fwd_to = "0";
        String rejDesc ="Rejected";
                        
            String func_id =
                HdrVO.getCurrentRow().getAttribute("FuncId") ==
                null ? "" :
                HdrVO.getCurrentRow().getAttribute("FuncId").toString();
            String ref_id =
                HdrVO.getCurrentRow().getAttribute("LeaveReqId") ==
                null ? "" :
                HdrVO.getCurrentRow().getAttribute("LeaveReqId").toString();
            String level_no =
                HdrVO.getCurrentRow().getAttribute("FlowLevel") ==
                null ? "" :
                HdrVO.getCurrentRow().getAttribute("FlowLevel").toString();
            String appr_id =
                HdrVO.getCurrentRow().getAttribute("FlowWith") ==
                null ? "" :
                HdrVO.getCurrentRow().getAttribute("FlowWith").toString();
            String user_grp =
                HdrVO.getCurrentRow().getAttribute("UserGrpId") ==
                null ? "" :
                HdrVO.getCurrentRow().getAttribute("UserGrpId").toString();
            String flag =
                ADFApproval.invokeResponsePkg(responsePkg, func_id,
                                              user_grp, ref_id, level_no,
                                              appr_id,
                                              rejDesc,
                                              "R", fwd_to, tableName,
                                              app_status_column,
                                              pk_column);
        
        //                        AdfFacesContext.getCurrentInstance().addPartialTarget(ot25);
            
            if (flag.equalsIgnoreCase("Success")) {
                HdrVO.executeQuery();
            }
        
        this.getP2().cancel();
    }

    public void onClickRejectCancel(ActionEvent actionEvent) {
        this.getP2().cancel();
    }
    
    public Date getMinActualDate() {
        try {
            ViewObject HdrVO = ADFUtils.findIterator("LeaveResumptionHdr_VO1Iterator").getViewObject();
            Object endDate=HdrVO.getCurrentRow().getAttribute("EndDate");
            Date endDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate.toString());
            Calendar cal = Calendar.getInstance();
            cal.setTime(endDate1);
            cal.add(Calendar.DATE, 1);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String maxDate = formatter.format(cal.getTime());
//            Date maxDate1 = formatter.parse(maxDate);
                return formatter.parse(maxDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
             return null;
    }
    
    public Date getMaxActualDate() {
        try {
             Calendar cal = Calendar.getInstance();
             java.util.Date date = cal.getTime();
             DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
             String currentDate = formatter.format(date);
//                    Date maxDate = formatter.parse(currentDate);
             return formatter.parse(currentDate);
             } catch (Exception e) {
                e.printStackTrace();
             }
                return null;
    }
}
