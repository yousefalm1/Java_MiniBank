package Bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee {

    private String employeeId;
    private String name;
    private String position;
    private double basicSalary;
    private double overTimeHours;
    private double overTimeRate;
    private double bonuses;
    private List<PaymentRecord> paymentHistory;



//    private boolean isAdmin;

    public Employee(String employeeId, String name, String position, double basicSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.basicSalary = this.basicSalary;
        this.paymentHistory = new ArrayList<>();
    }

    public double calcSalary() {
        double overTimePay = overTimeHours * overTimeRate;
        return basicSalary + overTimePay + basicSalary;
    }

    public void generatePaymentRecord() {
        double finalSalary = calcSalary();
        PaymentRecord record = new PaymentRecord(new Date(), finalSalary);
        paymentHistory.add(record);
        System.out.println("Payment recorded");
    }


    public void displayPaymentHistory(){
        if(paymentHistory.isEmpty()){
            System.out.println("No payment histoy");
        }
        else{
            for(PaymentRecord record : paymentHistory) {
                System.out.println(record);
            }
        }
    }


    public double getOverTimeHours() {
        return overTimeHours;
    }

    public void setOverTimeHours(double overTimeHours) {
        this.overTimeHours = overTimeHours;
    }

    public double getOverTimeRate() {
        return overTimeRate;
    }

    public void setOverTimeRate(double overTimeRate) {
        this.overTimeRate = overTimeRate;
    }

    public double getBonuses() {
        return bonuses;
    }

    public void setBonuses(double bonuses) {
        this.bonuses = bonuses;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getBasicStrategy() {
        return basicSalary;
    }

    public void setBasicStrategy(double basicStrategy) {
        this.basicSalary = basicStrategy;
    }



}
