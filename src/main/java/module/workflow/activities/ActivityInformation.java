/*
 * @(#)ActivityInformation.java
 *
 * Copyright 2009 Instituto Superior Tecnico
 * Founding Authors: Luis Cruz, Paulo Abrantes
 * 
 *      https://fenix-ashes.ist.utl.pt/
 * 
 *   This file is part of the Case Handleing Based Workflow Module.
 *
 *   The Case Handleing Based Workflow Module is free software: you can
 *   redistribute it and/or modify it under the terms of the GNU Lesser General
 *   Public License as published by the Free Software Foundation, either version 
 *   3 of the License, or (at your option) any later version.
 *
 *   The Workflow Module is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with the Workflow Module. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package module.workflow.activities;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.workflow.domain.WorkflowProcess;

/**
 * 
 * @author Daniel Ribeiro
 * @author Pedro Santos
 * @author Paulo Abrantes
 * @author Ricardo Almeida
 * 
 */
public class ActivityInformation<P extends WorkflowProcess> implements Serializable {

    private P process;
    private Class<? extends WorkflowActivity> activityClass;
    private String activityName;
    private String localizedName;
    private boolean forwardFromInput;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public boolean isForwardedFromInput() {
        return forwardFromInput;
    }

    public void markHasForwardedFromInput() {
        this.forwardFromInput = true;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivity(WorkflowActivity<? extends WorkflowProcess, ? extends ActivityInformation> activity) {
        this.activityName = activity.getName();
        this.activityClass = activity.getClass();
        this.localizedName = activity.getLocalizedName();
    }

    public ActivityInformation(P process, WorkflowActivity<? extends WorkflowProcess, ? extends ActivityInformation> activity) {
        setProcess(process);
        setActivity(activity);
        forwardFromInput = false;
    }

    public P getProcess() {
        return process;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public void setProcess(P process) {
        this.process = process;
    }

    public boolean hasAllneededInfo() {
        return true;
    }

    public void execute() {
        getActivity().execute(this);
    }

    public WorkflowActivity<P, ActivityInformation<P>> getActivity() {
        return getProcess().getActivity(getActivityName());
    }

    public Class getActivityClass() {
        return this.activityClass;
    }

    public String getUsedSchema() {
        return "activityInformation." + getActivityClass().getSimpleName();
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
