package icsd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ICSDCheckAuth {
public static  HttpSession ICSDCheckAuthofuser(HttpServletRequest request) throws ICSDAuthException
{
	HttpSession session=request.getSession(false);
	if(session==null)
	{
		throw new ICSDAuthException("please login with correct id" );

	}
	return session;
	
}
}

