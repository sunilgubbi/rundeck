/*
 * Copyright 2018 Rundeck, Inc. (http://rundeck.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.rundeck.core.tasks;

/**
 * Trigger  handler
 */
public interface TaskTriggerHandler<T> {
    /**
     * @return true if this handler needs registration info at system startup
     */
    default boolean onStartup() {
        return false;
    }

    /**
     * @param taskTrigger
     * @param contextInfo
     * @return true if the given taskTrigger should be handled
     */
    boolean handlesTrigger(TaskTrigger taskTrigger, T contextInfo);

    /**
     * Register taskTrigger checks for the trigger, will be called when a trigger is created or modified, so if the
     * registration has already occurred for the given trigger, the taskTrigger data should be checked for changes
     *
     * @param triggerId
     * @param contextInfo
     * @param taskTrigger
     * @param action
     * @param service
     */
    void registerTriggerForAction(
            String triggerId,
            T contextInfo,
            TaskTrigger taskTrigger,
            TaskAction action,
            TaskActionInvoker<T> service
    );

    /**
     * Remove taskTrigger check registration for the action
     *
     * @param triggerId
     * @param contextInfo
     * @param taskTrigger
     * @param action
     * @param service
     */
    void deregisterTriggerForAction(
            String triggerId,
            T contextInfo,
            TaskTrigger taskTrigger,
            TaskAction action,
            TaskActionInvoker<T> service
    );
}