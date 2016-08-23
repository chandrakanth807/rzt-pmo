package com.razorthink.pmo.controller.reports;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.razorthink.pmo.service.AdvancedLoginService;
import com.razorthink.pmo.service.AggregateProjectReportService;
import com.razorthink.pmo.service.LoginService;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.greenhopper.GreenHopperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping( "/projectReport" )
public class AggregateProjectReportController {

	@Autowired
	AggregateProjectReportService aggregateProjectReportService;

	@Autowired
	LoginService loginService;

	@Autowired
	AdvancedLoginService advancedLoginService;

	@RequestMapping( value = "/getAggregateProjectReport", method = RequestMethod.POST )
	public String getSprintReport( @RequestBody Map<String, String> params )
	{
		try
		{
			JiraRestClient restClient = loginService.getRestClient();
			JiraClient jiraClient = advancedLoginService.getJiraClient();
			GreenHopperClient gh = advancedLoginService.getGreenHopperClient();
			return aggregateProjectReportService.getAggregateProjectReport(params, restClient,jiraClient, gh);
		}
		catch( Exception e )
		{
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
