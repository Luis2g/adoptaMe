package mx.edu.utez.adoptaMe.helpers;

import mx.edu.utez.adoptaMe.entity.User;

public class Session {
	
		private static User user;
		

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
	
	
}
