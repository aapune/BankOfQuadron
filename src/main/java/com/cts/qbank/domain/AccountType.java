package com.cts.qbank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="MASTER_QBANK_ACCOUNT_TYPE")
public class AccountType {
	
@Id
@Column(name = "ACC_TYPE_ID")
private String accTypeId;


@Column(name = "ACC_TYPE_DESCRIPTION")
private String accDesc;


public String getAccTypeId() {
	return accTypeId;
}


public void setAccTypeId(String accTypeId) {
	this.accTypeId = accTypeId;
}


public String getAccDesk() {
	return accDesc;
}


public void setAccDesk(String accDesc) {
	this.accDesc = accDesc;
}




}
