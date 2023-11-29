package view;

import com.view.utils.ADFUtils;
import com.view.utils.JSFUtils;

import com.view.utils.RTFUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.OperationBinding;



import oracle.jbo.ViewObject;

public class AddEditPayroll {
    public AddEditPayroll() {
    }

    public void onClickSave(ActionEvent actionEvent) {
        ViewObject HdrVO = ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        String Status =
            HdrVO.getCurrentRow().getAttribute("ApprovalStatus") ==
            null ? "" :
            HdrVO.getCurrentRow().getAttribute("ApprovalStatus").toString();
        if(Status.equals("DRAFT")){
            HdrVO.getCurrentRow().setAttribute("ApprovalStatus","Draft");
        }
        
        ADFUtils.findOperation("Commit").execute();
                    JSFUtils.addFacesInformationMessage("Information Saved Successfully");
    }

    public void onClickCancel(ActionEvent actionEvent) {
        ViewObject HdrVO = ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        HdrVO.applyViewCriteria(null);
        HdrVO.executeQuery();
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
        ViewObject HdrVO =
                   ADFUtils.findIterator("PAYROLL_HDR_VOIterator").getViewObject();
        HdrVO.getCurrentRow().setAttribute("ApprovalStatus","PROCESSED");
        ADFUtils.findOperation("Commit").execute();
    }

}
