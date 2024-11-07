package Bank;

import java.util.Date;

public class PaymentRecord {

    private Date salaryPaymentDate;
    private double amountPayed;

    public PaymentRecord(Date salaryPaymentDate, double amountPayed) {
        this.salaryPaymentDate = salaryPaymentDate;
        this.amountPayed = amountPayed;
    }

    public double getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(double amountPayed) {
        this.amountPayed = amountPayed;
    }

    public Date getSalaryPaymentDate() {
        return salaryPaymentDate;
    }

    public void setSalaryPaymentDate(Date salaryPaymentDate) {
        this.salaryPaymentDate = salaryPaymentDate;
    }

    @Override
    public String toString() {
        return "PaymentRecord{" +
                "salaryPaymentDate=" + salaryPaymentDate +
                ", amountPayed=" + amountPayed +
                '}';
    }
}