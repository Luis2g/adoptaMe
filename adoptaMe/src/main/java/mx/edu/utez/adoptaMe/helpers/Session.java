package mx.edu.utez.adoptaMe.helpers;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utez.adoptaMe.entity.Role;
import mx.edu.utez.adoptaMe.entity.User;
//import mx.edu.utez.adoptaMe.entity.UserHasRole;

public class Session {
	
		private static User user;
		private static String url;
		

		public static User getSession(){
			
//			if(user == null) {
//				user = new User();
//				//Creating role
//				Role role = new Role();
//				role.setId(0l);
//				//Creating userHasRole
//				UserHasRole userHasRole = new UserHasRole();
//				userHasRole.setRole(role);
//				//Creating the list
//				List<UserHasRole> list = new ArrayList<>();
//				list.add(userHasRole);
//				user.setUserHasRoles(list);				
//			}
			
			return user;
		}
		
		public static void setSession(User userIn) {
			user = userIn;
		}

		public static String getUrl() {
			return url;
		}

		public static void setUrl(String url) {
			Session.url = url;
		}

		
	
	
}
