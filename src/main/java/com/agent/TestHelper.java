package com.agent;

import com.liveperson.http.requests.RequestHelper;
import com.liveperson.http.requests.RequestHelperSigned;
//import com.liveperson.restAPI.externalACD.CtiPreConfiguredSite;
import com.liveperson.utils.RestAPI.externalACD.request.Event;
import com.liveperson.utils.RestAPI.externalACD.request.ExternalEvents;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.lang.Object;

/**
 * This is a request helper for all the CTI rest API commands
 * User: guyk
 * Date: 10/2/13
 */
public class TestHelper {


    private static final Logger logger = LoggerFactory.getLogger(TestHelper.class);
    private static RequestHelper requestHelper;
    private static RequestHelperSigned.oAuthKeys keys;
    private PreConfiguredSite siteEntity;

    public TestHelper(PreConfiguredSite siteEntity) {
        Assert.notNull(siteEntity,"SiteEntity must not be null");
        this.siteEntity = siteEntity;
        keys = new RequestHelperSigned.oAuthKeys();
        keys.appKey = siteEntity.getAppKey();
        keys.appSecret = siteEntity.getAppSecret();
        keys.tokenKey = siteEntity.getTokenKey();
        keys.tokenSecret = siteEntity.getTokenSecret();
        requestHelper = new RequestHelperSigned(keys);
        requestHelper.setHeadersForPost(buildHeadersMap());
    }

    public enum COMMAND {
        SESSION_ASSIGN("agentAssign"), STATUS("status"), WAIT("events"), SESSION("session");

        private String command;

        private COMMAND(String command) {
            this.command = command;
        }
        public String getCommand() {
            return command;
        }
    }

    /**
     * Using {@link com.liveperson.utils.RestAPI.externalACD.request.Event }  for marshaling
     * @param command the command to send
     * @param id send by session , DNIS
     * @param agent to assign to
     * @param session
     * @param skill of the assigned agent
     * @return  xmk of the request
     */
    protected String buildEventsRequestBody(COMMAND command, String id, String agent, String session, String skill) {
        JAXBContext jaxbContext = null;
        Marshaller jaxbMarshaller = null;
        StringWriter writer = new StringWriter();
        try {
            jaxbContext = JAXBContext.newInstance(ExternalEvents.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            logger.info("Marshaller creation failed");
        }
        ExternalEvents externalEvents = setExternalEvents(command, id, agent, session, skill);
        try {
            jaxbMarshaller.marshal(externalEvents, writer);
        } catch (JAXBException e) {
           logger.error("Unable to marshall",e);
        }
        return writer.toString();
    }

    protected ExternalEvents unmarshalleResponse(String response) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ExternalEvents.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (ExternalEvents) unmarshaller.unmarshal(new StringReader(response));
    }

    private ExternalEvents setExternalEvents(COMMAND command, String id, String agent, String session, String skill) {
        ExternalEvents externalEvents = new ExternalEvents();
        ArrayList<Event> events =new ArrayList<Event>();
        Event event = new Event();
        event.setAgent(agent);
        event.setSession(session);
        event.setSkill(skill);
        event.setId(id);
        event.setType(command.getCommand());
        events.add(event);
        externalEvents.setEvents(events);
        return externalEvents;
    }

    /**
     *
     * @param id
     * @param product  the product type e.g voice,chat....
     * @param version  api version
     * @param command  from the command list
     * @return
     */
    protected String urlBuilder(String id, String product ,int version, COMMAND command, int index) {
         return urlBuilder(siteEntity.getDomain(),siteEntity.getSiteId(), id, product, version , command, index);
    }

    protected String urlBuilder(String domain,String siteID, String id, String product, int version ,COMMAND command, int index) {
        StringBuilder url = new StringBuilder().append("https://").append(domain).append("/api/account/")
                .append(siteID)
                .append("/routing/").append(product + "/");
        switch (command) {
            case SESSION_ASSIGN:
                url.append(command.getCommand()).append("/" + id) ;
                break;
            case STATUS :
                url.append(command.getCommand()).append("/" + id) ;
                break;
            case WAIT :
                url.append(command.getCommand()).append("/" + index);
                break;
            case SESSION:
                url.append(command.getCommand()).append("/" + id) ;
            default:
                url.append(StringUtils.EMPTY);

        }
        url.append("?v=").append(version);
        return url.toString();
    }

    protected String doGetRequest(String product ,int version, COMMAND command,int index) throws IOException {
        return requestHelper.doGetRequest(urlBuilder(StringUtils.EMPTY, product, version, command,index));
    }

    protected void doPostRequest(String id, String product, int version, COMMAND command, String body) throws IOException {
        byte[][] res = new byte[1][];
        requestHelper.doPostRequest(urlBuilder(id, product, version, command,0), body, res);
    }

    protected int doPostRequest(String req, String body, byte[][] res) throws IOException {
        return requestHelper.doPostRequest(req, body, res, null, null);
    }

    private Map<String, String> buildHeadersMap() {
        Map<String,String> headersForPost = new HashMap<String,String>();
        headersForPost.put("Accept", "application/xml");
        headersForPost.put("Content-type", "application/xml");
        headersForPost.put("X-HTTP-Method-Override", "PUT");
        return headersForPost;
    }

    protected String getWaitingEvents() {
        String response = null;
        try {
            response = doGetRequest("chat", 2, COMMAND.WAIT,0);
        } catch (IOException e) {
            logAndThrowException("Unable to fetch queue", e);
        }
        return response;
    }

    protected String getWaitingEventsFromIndex(int index) {
        String response = null;
        try {
            response = doGetRequest("chat", 2, COMMAND.WAIT ,index);
        } catch (IOException e) {
            logAndThrowException("Unable to fetch queue", e);
        }
        return response;
    }

    protected void setExternalAvailability(String skillId) {
        try {
            doPostRequest("https://" + siteEntity.getDomain() + "/api/account/" + siteEntity.getSiteId() + "/externalAvailability/skillAvailability?v=1",
                    "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><skillInformation><skill Throughput='5' SessionInQueue='0' QueueWaitTime='111.99' QueueOnline='true' mediaType='chat' id='" + skillId + "' AvailableSlots='5'/></skillInformation>", new byte[1][]);
        } catch (IOException e) {
            logAndThrowException("Unable to set external availability", e);
        }
    }

    private static RuntimeException logAndThrowException(String msg, Exception e) {
        logger.error(msg);
        return new RuntimeException(msg, e);
    }


    public static void wait(int timeInSeconds) {

        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }

    }


}
