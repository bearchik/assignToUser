package ru.bearchik.plugin.jira.workflow;

import java.util.List;
import java.util.Map;

import com.atlassian.jira.bc.user.search.UserSearchParams;
import com.atlassian.jira.bc.user.search.UserSearchService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.workflow.function.issue.AbstractJiraFunctionProvider;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.WorkflowException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the post-function class that gets executed at the end of the transition.
 * Any parameters that were saved in your factory class will be available in the transientVars Map.
 */
@Scanned
public class assignToUserPostFunction extends AbstractJiraFunctionProvider
{
    private static final Logger log = LoggerFactory.getLogger(assignToUserPostFunction.class);
    public static final String FIELD_MESSAGE = "assignee_to_user_field";

    public void execute(Map transientVars, Map args, PropertySet ps) throws WorkflowException
    {
        UserSearchService userSearchService = ComponentAccessor.getComponent(UserSearchService.class);

        MutableIssue issue = getIssue(transientVars);
        String assigneeuser = (String)args.get(FIELD_MESSAGE);

        List<ApplicationUser> users = userSearchService.findUsers(assigneeuser, new UserSearchParams(false, true,false));

        if (null != assigneeuser && issue.getAssigneeId() == null && users.size() != 0) {
                issue.setAssignee(users.get(0));
        }
    }
}