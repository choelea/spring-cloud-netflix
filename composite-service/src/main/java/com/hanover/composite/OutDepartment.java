/*******************************************************************************
 * Licensed to the OKChem
 *
 * http://www.okchem.com
 *
 *******************************************************************************/
package com.hanover.composite;

import java.util.List;

/**
 * @author Joe
 *
 */
public class OutDepartment  {
	/**
	 * id
	 */
	private Long id;
	/**
	 * parent id
	 */
    private Long parentId ;
    /**
     * department Name
     */
	private String departmentName;
	
    /**
     * childrens
     */
	private List<OutDepartment> children;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the children
	 */
	public List<OutDepartment> getChildren() {
		return children;
	}
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<OutDepartment> children) {
		this.children = children;
	}
	
	
}


class BastDTOData {
	   private String msg;
	   private String msgCode;
	   private boolean success = true;

	   public String getMsg() {
	      return this.msg;// 18
	   }

	   public void setMsg(String msg) {
	      this.msg = msg;// 21
	   }// 22

	   public String getMsgCode() {
	      return this.msgCode;// 24
	   }

	   public void setMsgCode(String msgCode) {
	      this.msgCode = msgCode;// 27
	   }// 28

	   public boolean isSuccess() {
	      return this.success;// 30
	   }

	   public void setSuccess(boolean success) {
	      this.success = success;// 33
	   }// 34
	}

class CommonData<T> extends BastDTOData {
	   private T data;

	   public CommonData() {
	   }// 23

	   public CommonData(T data) {
	      this.data = data;// 27
	   }// 28

	   public T getData() {
	      return this.data;// 31
	   }

	   public void setData(T data) {
	      this.data = data;// 35
	   }// 36
	}

class SyncEmployeeToAuth {
	
	/**
	 * employeeNo
	 */
	private String employeeNo;
	/**
	 * userName
	 */
	private String userName;
	/**
	 * email
	 */
	private String  email;
	
	/**
	 * password
	 */
	private String password;
	
	/**
	 * productLines
	 */
	private String productLines;
	
	/**
	 * businessMarkets
	 */
	private String businessMarkets;
	
	/**
	 * enable
	 */
	private boolean enable;
	
	/**
	 * firstName
	 */
	private String firstName;
	
	/**
	 * lastName
	 */
	private String lastName;


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the employeeNo
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}

	/**
	 * @param employeeNo the employeeNo to set
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the productLines
	 */
	public String getProductLines() {
		return productLines;
	}

	/**
	 * @param productLines the productLines to set
	 */
	public void setProductLines(String productLines) {
		this.productLines = productLines;
	}

	/**
	 * @return the businessMarkets
	 */
	public String getBusinessMarkets() {
		return businessMarkets;
	}

	/**
	 * @param businessMarkets the businessMarkets to set
	 */
	public void setBusinessMarkets(String businessMarkets) {
		this.businessMarkets = businessMarkets;
	}

	/**
	 * @return the enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * @param enable the enable to set
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	

}