/*
 * @(#)CurrentProcessQueues.java
 *
 * Copyright 2011 Instituto Superior Tecnico
 * Founding Authors: Paulo Abrantes
 * 
 *      https://fenix-ashes.ist.utl.pt/
 * 
 *   This file is part of the Meta-Workflow Module.
 *
 *   The Meta-Workflow Module is free software: you can
 *   redistribute it and/or modify it under the terms of the GNU Lesser General
 *   Public License as published by the Free Software Foundation, either version 
 *   3 of the License, or (at your option) any later version.
 *
 *   The Meta-Workflow Module is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with the Meta-Workflow Module. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package module.metaWorkflow.presentationTier.renderers.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import module.metaWorkflow.domain.WorkflowMetaProcess;
import module.workflow.activities.ChangeQueueInformation;
import module.workflow.domain.WorkflowQueue;
import pt.ist.fenixWebFramework.renderers.DataProvider;
import pt.ist.fenixWebFramework.renderers.components.converters.Converter;

/**
 * 
 * @author João Neves
 * 
 */
public class CurrentProcessQueues implements DataProvider {

    @Override
    public Converter getConverter() {
	return null;
    }

    @Override
    public Object provide(Object source, Object value) {
	ChangeQueueInformation<WorkflowMetaProcess> information = (ChangeQueueInformation<WorkflowMetaProcess>) source;
	return provide(information.getProcess());
    }

    public Object provide(WorkflowMetaProcess process) {
	List<WorkflowQueue> queues = new ArrayList<WorkflowQueue>(process.getCurrentQueues());
	Collections.sort(queues, WorkflowQueue.COMPARATOR_BY_NAME);
	return queues;
    }
}
