package com.nodedynamics.userservices.models.user;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
@Document(collection="Users")
public class UserModel extends CoreModel{
	
    @Id
    private String id;
    
    @Indexed
    private String companyID;
    
    @Indexed
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    private Boolean isActive;
    

}
