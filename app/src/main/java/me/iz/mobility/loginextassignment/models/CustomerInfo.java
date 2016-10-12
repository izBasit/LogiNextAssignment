package me.iz.mobility.loginextassignment.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ibasit on 10/10/2016.
 */

public class CustomerInfo extends RealmObject {

    private String customerName;

    private String custAddress;

    private String custPhoneNumber;

    @PrimaryKey
    private long custId;

    public CustomerInfo() {
    }

    public CustomerInfo(long custId, String customerName, String custAddress, String custPhoneNumber) {
        this.customerName = customerName;
        this.custAddress = custAddress;
        this.custPhoneNumber = custPhoneNumber;
        this.custId = custId;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhoneNumber() {
        return custPhoneNumber;
    }

    public void setCustPhoneNumber(String custPhoneNumber) {
        this.custPhoneNumber = custPhoneNumber;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "customerName='" + customerName + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhoneNumber='" + custPhoneNumber + '\'' +
                ", custId=" + custId +
                '}';
    }
}
