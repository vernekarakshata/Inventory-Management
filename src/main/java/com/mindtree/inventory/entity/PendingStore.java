package com.mindtree.inventory.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "storeapproval")
public class PendingStore implements Comparable<PendingStore>{

	@Id
	private int productid;
	private String productname;
	private String vendor;
	private double mrp;
	private int batchnumber;
	private Date batchdate;
	private int quantity;
	private String status;
	
	public PendingStore(){}
	
	public PendingStore(Store p)
	{
		this.productid = p.getProductid();
		this.productname = p.getProductname();
		this.vendor = p.getVendor();
		this.mrp = p.getMrp();
		this.batchnumber = p.getBatchnumber();
		this.batchdate = p.getBatchdate();
		this.quantity = p.getQuantity();
		this.status = p.getStatus();
	}
	
	public int getProductid() {
		return productid;
	}
	
	public void setProductid(int productid) {
		this.productid = productid;
	}
	
	public String getProductname() {
		return productname;
	}
	
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public String getVendor() {
		return vendor;
	}
	
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public double getMrp() {
		return mrp;
	}
	
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	
	public int getBatchnumber() {
		return batchnumber;
	}
	
	public void setBatchnumber(int batchnumber) {
		this.batchnumber = batchnumber;
	}
	
	public Date getBatchdate() {
		return batchdate;
	}
	
	public void setBatchdate(Date batchdate) {
		this.batchdate = batchdate;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Store [productid=" + productid + ", productname=" + productname + ", vendor=" + vendor + ", mrp=" + mrp
				+ ", batchnumber=" + batchnumber + ", batchdate=" + batchdate + ", quantity=" + quantity + ", status="
				+ status + "]";
	}

	@Override
	public int compareTo(PendingStore p) {
		return this.productid - p.getProductid();
	}
	
}
