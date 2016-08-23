package com.razorthink.pmo.controller.reports;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.razorthink.pmo.service.AdvancedLoginService;
import com.razorthink.pmo.service.LoginService;
import com.razorthink.pmo.service.SprintReportMinimalService;
import net.rcarz.jiraclient.JiraClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping( "/sprintReport" )
public class SprintReportController {

	@Autowired
	SprintReportMinimalService sprintReportMinimalService;

	@Autowired
	LoginService loginService;

	@Autowired
	AdvancedLoginService advancedLoginService;

	@RequestMapping( value = "/getMinimalSprintReport", method = RequestMethod.POST )
	public String getMinimalSprintReport( @RequestBody Map<String, String> params )
	{
		try
		{
			JiraRestClient restClient = loginService.getRestClient();
			JiraClient jiraClient = advancedLoginService.getJiraClient();
			return sprintReportMinimalService.getMininmalSprintReport(params, restClient, jiraClient);
		}
		catch( Exception e )
		{
			return e.getMessage();
		}
	}
}
