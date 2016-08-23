package com.razorthink.pmo.controller;


import com.razorthink.pmo.commons.config.Constants;
import com.razorthink.pmo.commons.controller.AbstractController;
import com.razorthink.pmo.commons.exceptions.WebappException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

public class AbstractWebappController extends AbstractController {

	protected String getCurrentUser() throws WebappException
	{
		try
		{
			Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if( principal == null || principal.getName()==null || principal.getName().trim().isEmpty() )
			{
				throw new WebappException(Constants.Webapp.ERROR_FETCHING_CURRENT_USER, HttpStatus.FORBIDDEN);
			}
			return principal.getName();
		}
		catch( WebappException we )
		{
			throw we;
		}
		catch( Exception e )
		{
			throw new WebappException(e);
		}

	}

}
