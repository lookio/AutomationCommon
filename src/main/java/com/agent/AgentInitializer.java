package com.agent;

import com.liveperson.AgentAPIFactory;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.liveperson.impl.ChatAPIClientObject;;
import com.liveperson.utils.RestAPI.AgentAndVisitorUtils;
import com.util.properties.PropertiesHandlerImpl;
import humanclick.logging.ContextLogger;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class Info:
 * <p/>
 * User: ilanm
 * Date: 6/23/14
 * Time: 2:39 PM
 */
public class AgentInitializer {

    private static Properties prop;
    private static final Logger logger = Logger.getLogger(AgentInitializer.class);

    public static void initTest(String chatRequest, String propsFilePath, int numOfAgents, List<Rep> agents, PreConfiguredSite siteEntity) {
        prop = PropertiesHandlerImpl.getInstance().parse(propsFilePath);
        chatRequest = "<request><skill>" + prop.getProperty("skill.name")+"</skill><maxWaitTime>150</maxWaitTime></request>";
        try {
            siteEntity = new PreConfiguredSite(prop, (InetAddress.getLocalHost().getHostName()));
        } catch (UnknownHostException e) {
            logger.error("Unrecognized host");
        }
        initReps(numOfAgents, agents, siteEntity);
    }

    private static void initReps(int numOfAgents, List<Rep> agents, PreConfiguredSite siteEntity){
        numOfAgents = Integer.parseInt(prop.getProperty("numOfAgents"));
        agents.clear();
        for(int i=0 ; i < numOfAgents ; i++) { // take data from conf data file
            agents.add(AgentAPIFactory.createAgent(
                    siteEntity.getSiteId(), siteEntity.getAppKey(), siteEntity.getAppSecret(), siteEntity.getTokenKey(), siteEntity.getTokenSecret(),
                            prop.getProperty("user.email"), prop.getProperty("user.password"), prop.getProperty("skill.name"), siteEntity.getProtocol(), siteEntity.getDomain(), siteEntity.getSiteURL())
            );
        }
    }




}
