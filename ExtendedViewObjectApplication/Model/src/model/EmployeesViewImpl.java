package model;

import model.common.EmployeesView;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Dec 02 16:36:38 IST 2024
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EmployeesViewImpl extends ViewObjectImpl implements EmployeesView {
    /**
     * This is the default constructor (do not remove).
     */
    public EmployeesViewImpl() {
    }
    
    public String getEmployeeFullName(int employeeId){
        System.out.println("Enteing into getEmployeeFullName-------------------");
        
        Key key = new Key(new Object[]{employeeId});
        System.out.println("Key"+key);
        Row r = findByKey(key, 1)[0];
        System.out.println(r);
        String fullName = r.getAttribute("FirstName")+" "+r.getAttribute("LastName");
        System.out.println("Employee fullName-------------------"+fullName);
        System.out.println("Exit into getEmployeeFullName-------------------");
        return fullName;
    }
}

