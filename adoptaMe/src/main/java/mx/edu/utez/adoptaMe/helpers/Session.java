package mx.edu.utez.adoptaMe.helpers;

import mx.edu.utez.adoptaMe.entity.User;
//import mx.edu.utez.adoptaMe.entity.UserHasRole;

public class Session {
	
		private static User user;
		private static String url = "";
		

		public static User getSession(){			
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
