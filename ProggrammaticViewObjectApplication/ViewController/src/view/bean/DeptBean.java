package view.bean;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.BindingContainer;

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
    public void insertButton(ActionEvent actionEvent) {
        // Add event code here...
        Object idcomponent = this.getDeptIdComponent();
        Object deptName = this.getDeptNameComponent();
        DCIteratorBinding iter = (DCIteratorBinding) this.getBindings().get("ProggrammaticDepartmentsView1Iterator");
        ViewObject vo = iter.getViewObject();
        Row r = vo.createRow();
        r.setAttribute("DeptId", idcomponent);
        r.setAttribute("DepartmentName", deptName);
        vo.insertRow(r);
        this.getDeptIdComponent().setValue(null);
        this.deptNameComponent.setValue(null);

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
}
