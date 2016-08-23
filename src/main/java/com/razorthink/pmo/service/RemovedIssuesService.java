package com.razorthink.pmo.service;

import com.razorthink.pmo.bean.reports.RemovedIssues;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.RestClient;
import net.rcarz.jiraclient.greenhopper.GreenHopperField;
import net.rcarz.jiraclient.greenhopper.SprintIssue;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RemovedIssuesService {

	static final String RESOURCE_URI = "/rest/greenhopper/1.0/";

	@SuppressWarnings( "serial" )
	public RemovedIssues get(RestClient restclient, int rvId, int sprintId ) throws JiraException
	{

		JSON result = null;
		try
		{

			URI reporturi = restclient.buildURI(RESOURCE_URI + "rapid/charts/sprintreport",
					new HashMap<String, String>() {

						{
							put("rapidViewId", Integer.toString(rvId));
							put("sprintId", Integer.toString(sprintId));
						}
					});
			result = restclient.get(reporturi);
		}
		catch( Exception ex )
		{
			throw new JiraException("Failed to retrieve sprint report", ex);
		}

		if( !(result instanceof JSONObject) )
			throw new JiraException("JSON payload is malformed");

		JSONObject jo = (JSONObject) result;

		if( !jo.containsKey("contents") || !(jo.get("contents") instanceof JSONObject) )
			throw new JiraException("Sprint report content is malformed");

		return new RemovedIssues(restclient, (JSONObject) jo.get("contents"));
	}
}
