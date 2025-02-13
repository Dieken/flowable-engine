/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.eventsubscription.service.impl.persistence.entity;

import java.util.List;

import org.flowable.common.engine.impl.persistence.entity.EntityManager;
import org.flowable.eventsubscription.api.EventSubscription;
import org.flowable.eventsubscription.api.EventSubscriptionBuilder;
import org.flowable.eventsubscription.service.impl.EventSubscriptionQueryImpl;

/**
 * @author Joram Barrez
 */
public interface EventSubscriptionEntityManager extends EntityManager<EventSubscriptionEntity> {

    /* Create entity */

    MessageEventSubscriptionEntity createMessageEventSubscription();

    SignalEventSubscriptionEntity createSignalEventSubscription();

    CompensateEventSubscriptionEntity createCompensateEventSubscription();

    GenericEventSubscriptionEntity createGenericEventSubscription();

    /* Create and insert */

    EventSubscription createEventSubscription(EventSubscriptionBuilder eventSubscriptionBuilder);

    /* Update */

    void updateEventSubscriptionTenantId(String oldTenantId, String newTenantId);

    void updateEventSubscriptionProcessDefinitionId(String oldProcessDefinitionId, String newProcessDefinitionId, String eventType, String activityId, String scopeDefinitionKey, String configuration);

    boolean lockEventSubscription(String eventSubscriptionId);

    void unlockEventSubscription(String eventSubscriptionId);

    /* Delete */

    void deleteEventSubscriptionsForProcessDefinition(String processDefinitionId);
    
    void deleteEventSubscriptionsByExecutionId(String executionId);
    
    void deleteEventSubscriptionsForScopeIdAndType(String scopeId, String scopeType);

    void deleteEventSubscriptionsForScopeDefinitionIdAndType(String scopeDefinitionId, String scopeType);

    void deleteEventSubscriptionsForScopeDefinitionIdAndTypeAndNullScopeId(String scopeDefinitionId, String scopeType);

    void deleteEventSubscriptionsForProcessDefinitionAndProcessStartEvent(String processDefinitionId, String eventType, String activityId, String configuration);

    /* Find (generic) */

    List<EventSubscriptionEntity> findEventSubscriptionsByName(String type, String eventName, String tenantId);

    List<EventSubscriptionEntity> findEventSubscriptionsByNameAndExecution(String type, String eventName, String executionId);

    List<EventSubscriptionEntity> findEventSubscriptionsByExecution(String executionId);

    List<EventSubscriptionEntity> findEventSubscriptionsByExecutionAndType(String executionId, String type);
    
    List<EventSubscriptionEntity> findEventSubscriptionsByProcessInstanceAndType(String processInstanceId, String type);
    
    List<EventSubscriptionEntity> findEventSubscriptionsBySubScopeId(String subScopeId);

    List<EventSubscriptionEntity> findEventSubscriptionsByProcessInstanceAndActivityId(String processInstanceId, String activityId, String type);

    List<EventSubscriptionEntity> findEventSubscriptionsByTypeAndProcessDefinitionId(String type, String processDefinitionId, String tenantId);

    List<EventSubscriptionEntity> findEventSubscriptionsByScopeIdAndType(String scopeId, String type);
    
    List<EventSubscription> findEventSubscriptionsByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQueryImpl);

    long findEventSubscriptionCountByQueryCriteria(EventSubscriptionQueryImpl eventSubscriptionQueryImpl);

    /* Find (signal) */

    List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByEventName(String eventName, String tenantId);

    List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByProcessInstanceAndEventName(String processInstanceId, String eventName);
    
    List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByScopeAndEventName(String scopeId, String scopeType, String eventName);

    List<SignalEventSubscriptionEntity> findSignalEventSubscriptionsByNameAndExecution(String name, String executionId);

    /* Find (message) */

    MessageEventSubscriptionEntity findMessageStartEventSubscriptionByName(String messageName, String tenantId);

    List<MessageEventSubscriptionEntity> findMessageEventSubscriptionsByProcessInstanceAndEventName(String processInstanceId, String eventName);

    /* Find (compensation) */

    List<CompensateEventSubscriptionEntity> findCompensateEventSubscriptionsByExecutionId(String executionId);

    List<CompensateEventSubscriptionEntity> findCompensateEventSubscriptionsByExecutionIdAndActivityId(String executionId, String activityId);

    List<CompensateEventSubscriptionEntity> findCompensateEventSubscriptionsByProcessInstanceIdAndActivityId(String processInstanceId, String activityId);

}