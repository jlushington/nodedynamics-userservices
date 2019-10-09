package com.nodedynamics.userservices.models.company;


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
@Document(collection="OrganizationContactInfo")
public class ContactInfoModel extends CoreModel{
	
    private Long id;
	private Long CompanyID;
	private String ContactType;
	private String Name;
	private String EmailAddress;
	private String Tel;
	private String MobileCell;
}
