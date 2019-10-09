package com.nodedynamics.userservices.common;

public class EValidation {
	
	public static enum Repos
	{
		USER("UserRepository"),
		COMPANY("CompanyRepository");
		
		public final String key;
		private Repos(final String key) {
			this.key=key;
		}
	}

}
