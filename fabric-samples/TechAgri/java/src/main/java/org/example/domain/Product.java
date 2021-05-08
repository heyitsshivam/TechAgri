package com.example.demo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {
    private String dataprice,dataid,dataimage,dataname,dataquantity;
    private String dataprice1,dataid1,dataimage1,dataname1,dataquantity1;
    private String quantity;
    private String total_amount,tot_amount;
    public Product(){}

    public String getDataprice() {
        return dataprice;
    }

    public void setDataprice(String dataprice) {
        this.dataprice = dataprice;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getDataimage() {
        return dataimage;
    }

    public void setDataimage(String dataimage) {
        this.dataimage = dataimage;
    }

    public String getDataname() {
        return dataname;
    }

    public void setDataname(String dataname) {
        this.dataname = dataname;
    }

    public String getDataquantity() {
        return dataquantity;
    }

    public void setDataquantity(String dataquantity) {
        this.dataquantity = dataquantity;
    }

    public String getDataprice1() {
        return dataprice1;
    }

    public void setDataprice1(String dataprice1) {
        this.dataprice1 = dataprice1;
    }

    public String getDataid1() {
        return dataid1;
    }

    public void setDataid1(String dataid1) {
        this.dataid1 = dataid1;
    }

    public String getDataimage1() {
        return dataimage1;
    }

    public void setDataimage1(String dataimage1) {
        this.dataimage1 = dataimage1;
    }

    public String getDataname1() {
        return dataname1;
    }

    public void setDataname1(String dataname1) {
        this.dataname1 = dataname1;
    }

    public String getDataquantity1() {
        return dataquantity1;
    }

    public void setDataquantity1(String dataquantity1) {
        this.dataquantity1 = dataquantity1;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTot_amount() {
        return tot_amount;
    }

    public void setTot_amount(String tot_amount) {
        this.tot_amount = tot_amount;
    }
}
