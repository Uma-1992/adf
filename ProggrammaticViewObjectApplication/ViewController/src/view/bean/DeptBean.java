package view.bean;


import java.awt.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

public class DeptBean {
    private RichInputText deptIdComponent;
    private RichInputText deptNameComponent;

    public DeptBean() {
    }
    private BindingContainer getBindings(){
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
        public void insertButton(javax.faces.event.ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("we are in insertButton programmtic vo");
        Object idcomponent = this.getDeptIdComponent().getValue();
        System.out.println("idcomponent :- "+idcomponent);
        Object deptName = this.getDeptNameComponent().getValue();
        System.out.println("deptName :- "+deptName);
        
        DCIteratorBinding iter = (DCIteratorBinding) this.getBindings().get("ProggrammaticDepartmentsView1Iterator");
        System.out.println("ProggrammaticDepartmentsView1Iterator-------"+iter);
        ViewObject vo = iter.getViewObject();
        System.out.println("ProggrammaticDepartmentsView======="+vo);
        Row r = vo.createRow();
        System.out.println("Create Row:- "+r);
        r.setAttribute("DeptId", idcomponent);
        r.setAttribute("DepartmentName", deptName);
        vo.insertRow(r);
        
        this.getDeptIdComponent().setValue(null);
        this.deptNameComponent.setValue(null);
        System.out.println("exit from insertButton proggrammatic vo--------------------------------------------");

    }
    
   
    
    private void callMethodBinding(Object deptId, Object deptName){
        System.out.println("we are in callMethodBinding++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        OperationBinding ob = getBindings().getOperationBinding("insertDeptToDatabase");
        ob.getParamsMap().put("deptId", Integer.parseInt(deptId.toString()));
        ob.getParamsMap().put("deptName", String.valueOf(deptName));
        ob.execute();
        System.out.println("We are exit from callMethodBinding---------------------------------------------------");
    }

    public void setDeptIdComponent(RichInputText deptIdComponent) {
        this.deptIdComponent = deptIdComponent;
    }

    public RichInputText getDeptIdComponent() {
        return deptIdComponent;
    }

    public void setDeptNameComponent(RichInputText deptNameComponent) {
        this.deptNameComponent = deptNameComponent;
    }

    public RichInputText getDeptNameComponent() {
        return deptNameComponent;
    }

    public void saveDeptActionListener(javax.faces.event.ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("we are in saveDeptActionListener++++++++++++++++++++++++++++++++++++++++++++++++++++");
        DCIteratorBinding iter = (DCIteratorBinding) this.getBindings().get("ProggrammaticDepartmentsView1Iterator");
        
        for(int i = 0; i<iter.getViewObject().getEstimatedRowCount(); i++){
            Row r = iter.getRowAtRangeIndex(i);
            callMethodBinding(r.getAttribute("DeptId"), r.getAttribute("DepartmentName"));            
        }
        System.out.println("before commit ===============================");
        OperationBinding ob = getBindings().getOperationBinding("Commit");
        
        ob.execute();
        System.out.println("exicute commit================================");
        iter.getViewObject().executeQuery();
        System.out.println("execute programmatic vo============================================");
        System.out.println("We are exit from saveDeptActionListener---------------------------------------------------");
    }

    
}
