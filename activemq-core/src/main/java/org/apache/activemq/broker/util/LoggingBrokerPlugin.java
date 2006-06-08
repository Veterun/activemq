/**
 *
 * Copyright 2005-2006 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.broker.util;

import org.apache.activemq.broker.BrokerPluginSupport;
import org.apache.activemq.broker.ConnectionContext;
import org.apache.activemq.command.Message;
import org.apache.activemq.command.MessageAck;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A simple Broker interceptor which allows you to enable/disable logging.
 * 
 * @org.apache.xbean.XBean
 * 
 * @version $Revision$
 */
public class LoggingBrokerPlugin extends BrokerPluginSupport {

    private Log log = LogFactory.getLog(LoggingBrokerPlugin.class);
    private Log sendLog;
    private Log ackLog;

    public void send(ConnectionContext context, Message messageSend) throws Exception {
        if (sendLog == null) {
            sendLog = createLog("Send");
        }
        if (sendLog.isInfoEnabled()) {
            sendLog.info("Sending: " + messageSend);
        }
        super.send(context, messageSend);
    }

    public void acknowledge(ConnectionContext context, MessageAck ack) throws Exception {
        if (ackLog == null) {
            ackLog = createLog("Ack");
        }
        if (ackLog.isInfoEnabled()) {
            ackLog.info("Acknowledge: " + ack);
        }
        super.acknowledge(context, ack);
    }

    // Properties
    // -------------------------------------------------------------------------
    public Log getAckLog() {
        return ackLog;
    }

    public void setAckLog(Log ackLog) {
        this.ackLog = ackLog;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Log getSendLog() {
        return sendLog;
    }

    public void setSendLog(Log sendLog) {
        this.sendLog = sendLog;
    }

    // Implementation methods
    // -------------------------------------------------------------------------

    /**
     * Lazily creates a new child log
     */
    protected Log createLog(String name) {
        return LogFactory.getLog(log.toString() + "." + name);
    }

}
