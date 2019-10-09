package com.nodedynamics.userservices.models.company;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.nodedynamics.userservices.models.CoreModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
@Document(collection="Organization")
public class CompanyModel extends CoreModel{
	

	@Id
	private String id;
	private String ParentCompanyID;
	private String CompanyName;
	private String Address1;
	private String Address2;
	private String City;
	private String Province;
	private String PostalCode;
	private String Country;
	private Set<ContactInfoModel>Contacts;
	
}
