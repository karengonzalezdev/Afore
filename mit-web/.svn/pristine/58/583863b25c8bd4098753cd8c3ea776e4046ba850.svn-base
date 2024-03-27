package mx.profuturo.nci.web.beans;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class AppRolBean implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
    private String name;
 
    private List<AppPrivilegeBean> privileges;
    
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AppPrivilegeBean> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<AppPrivilegeBean> privileges) {
		this.privileges = privileges;
	}


	public String getAuthority() {
		return this.name;
	}

}
