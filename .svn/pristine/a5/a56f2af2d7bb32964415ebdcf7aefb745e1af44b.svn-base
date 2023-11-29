package view;

import com.view.utils.ADFApproval;
import com.view.utils.ADFUtils;

import com.view.utils.JSFUtils;

import java.sql.SQLException;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.ViewObject;

public class SearchPayroll {
    public SearchPayroll() {
    }

    public void onClickEdit(ActionEvent actionEvent) {
//        Object obj =  ADFContext.getCurrent().getPageFlowScope().get("id");
//                 System.err.println("Object Name"+obj);
//                        ViewObject leaseHdrVO = ADFUtils.findIterator("SearchLeaveResumption_RoVo1Iterator").getViewObject();
//                        ViewCriteria hdrVC = leaseHdrVO.createViewCriteria();
//                        ViewCriteriaRow hdrVcr = hdrVC.createViewCriteriaRow();
//                        hdrVcr.setAttribute("LeaveReqId", obj);
//                        hdrVC.addRow(hdrVcr);
//                        leaseHdrVO.applyViewCriteria(hdrVC);
//                        leaseHdrVO.executeQuery();
    }

    public void onClickSync(ActionEvent actionEvent) throws SQLException {
        String flag = ADFApproval.syncLrProc("sync_leave_resumption");
        JSFUtils.addFacesInformationMessage("Sync ===== "+flag);
    }
}
