package com.agent;

import com.liveperson.AgentAPIFactory;
import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.liveperson.impl.ChatAPIClientObject;
import com.liveperson.utils.RestAPI.AgentAndVisitorUtils;
import com.ui.service.AppiumService;
import com.util.genutil.GeneralUtils;
import humanclick.logging.ContextLogger;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Info:
 * Check that the more available agent (according to active chats) get the call
 * User: ilanm
 * Date: 7/3/14
 * Time: 11:02 AM
 */
public class AgentService {

    public final static AgentService INSTANCE = new AgentService();

    public String chatRequest;
    private PreConfiguredSite siteEntity;
    private List<Rep> agents = new ArrayList<Rep>();
    private final long waitForPageSourceInterval = 500;

    private static final Logger logger = Logger.getLogger(AgentService.class);

    private AgentService(){}

    public static AgentService getInstance(){
        return INSTANCE;
    }

    public void setup(String testPath, List<Rep> agents) {
        this.agents = agents;
        AgentInitializer.initTest(testPath, agents, siteEntity);
    }

    public void logInAndSetState(List<Rep> agents, List<AgentState> agentsState){
        int index = 0;
        if(agents.size() != agentsState.size()){
            logger.warn(
                    "State array must be equal in size. agent : " + agents.size() + " states : " + agentsState.size()
            );
            return;
        }
        for(AgentState state : agentsState) {
            AgentAndVisitorUtils.agentLogInAndSetState(agents.get(index), state);
            index++;
        }
    }

    public boolean isRingingCountAsExpected(Rep agent, int expectedCount, long timeOutInMilisec) {
        if(agent != null) {
            try {
                return waitForRingingCount(expectedCount, agent.getRingingCount(), timeOutInMilisec);
            }
            catch (Throwable t) {
                GeneralUtils.handleError("Failed to get ringing count for rep", t);
                return false;
            }
        } else {
            logger.error("Agent is null");
            return false;
        }
    }

    private boolean waitForRingingCount(int expCount, int actualCount, long timeOutInMilisec){
        AppiumService.getInstance().implicitWait(1500);
        while(expCount != actualCount) {
            try {
                Thread.sleep(waitForPageSourceInterval);
                timeOutInMilisec -= waitForPageSourceInterval;
                if (timeOutInMilisec <= 0) {
                    logger.warn(
                            "Number of chats must be equal to expected. expected : " + expCount + " actual : " + actualCount
                    );
                    return false;
                }
            }
            catch (InterruptedException e) {
                GeneralUtils.handleError("Error in wait for time out", e);
            }
        }
        logger.info("There are exactly " + expCount + " chats");
        return true;
    }

    public boolean startChat(Rep agent) {
        AgentAndVisitorUtils.agentTakeChat(agent);
        logger.info("Agent taking chat (should be 200), result- " + agent.getLatestResponseCode());
        if ((agent.getLatestResponseCode()) != 200) {
            return false;
        }

        return true;
    }

    public void addChatLines(Rep agent, String msg){
        agent.addChatLines(msg);
    }

    public boolean verifyLatestChatLines(Rep agent, String visitorMsg){
        String latestMsg = null;
        try{
            latestMsg = agent.getLatestChatLine();
        } catch(Throwable t){
            GeneralUtils.handleError("Failed to get latest msg for rep", t);
            return false;
        }
        if(latestMsg.equalsIgnoreCase(visitorMsg)){
            logger.info("Latest chat line is as expected : " + visitorMsg);
            return true;
        }else {
            logger.info("Latest chat line is not as expected, expected : " + visitorMsg + " actual : " + latestMsg);
            return false;
        }
    }

    public boolean endChat(Rep rep) {
        rep.endChat();
        logger.info("Agent closing chat (should be 201), result- " + rep.getLatestResponseCode());
        if ((rep.getLatestResponseCode()) != 201) {
            return false;
        }
        return true;
    }

    public void tearDown(List<Rep> reps) {
        logger.info("Rep logout");
        for ( Rep r : reps ) {
            if (r != null)  {
                try {
                    r.logout();
                }
                catch (Exception e) {
                    System.out.println("Rep Logout");
                }
            }
        }
        TestHelper.wait(1);
    }

//
//    protected boolean chatIfCallExists(Rep agent, String visitorMsg, String agentMsg){
//        if(agent.getRingingCount() == 1){
//            AgentAndVisitorUtils.agentTakeChat(agent);
//            if(agent.getLatestChatLine().equalsIgnoreCase(visitorMsg)){
//                agent.addChatLines(agentMsg);
//                endChat(agent);
//                return true;
//            }else {
//                return false;
//            }
//        }return false;
//    }


//    protected boolean createChatRequest(ChatAPIClientObject visitor){
//        log.info("Create a chat request");
//        return AgentAndVisitorUtils.visitorRequestChat(visitor, chatRequest);
//    }


    /**
     * Get agent and visitor, create a chat request for the given visitor
     * and make the agent try and take this request
     * @param rep
     * @param visitor
     */
//    protected boolean CreateAndTake1Chat(Rep rep, ChatAPIClientObject visitor){
//        boolean retVal = false;
//        AgentAndVisitorUtils.agentLogInAndSetState(rep, AgentState.Online); //login agent
//
//        if(rep.getRingingCount() < 1){
//            //create a chat request from visitor side:
//            createChatRequest(visitor);
//        }
//
//        AgentAndVisitorUtils.agentTakeChat(rep);
//        log.info("Agent taking chat (should be 200), result- " + rep.getLatestResponseCode());
//        if ((rep.getLatestResponseCode()) != 200) {
//            return retVal;
//        }
//
//        rep.endChat();
//        log.info("Agent closing chat (should be 201), result- " + rep.getLatestResponseCode());
//        if ((rep.getLatestResponseCode()) != 201) {
//            return retVal;
//        }
//
//        clearQue(rep);
//        rep.logout();
//
//        return true;
//    }


    /**
     * this func clear the que for incoming chat requests and end current chat of the given agent (if exist)
     //     * @param rep
     */
//    private static void clearQue(Rep rep){
//        int takeChatResponseCode = -1;
//
//        while(rep.getRingingCount() > 0){
////            rep.addChatLines("ggg");
//            ;            takeChatResponseCode = rep.takeChat();
//            try {
//                Thread.sleep(2000); //wait 2 sec' to update server that chat was taken
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            if(takeChatResponseCode == 200 || takeChatResponseCode == 201){
//                rep.endChat();
//                Assert.assertEquals("Agent couldn't close chat", 201, rep.getLatestResponseCode());
//            }
//        }
//    }

//    public boolean handleChat(Rep rep) {
//        AgentAndVisitorUtils.agentTakeChat(rep);
//        log.info("Agent taking chat (should be 200), result- " + rep.getLatestResponseCode());
//        if ((rep.getLatestResponseCode()) != 200) {
//            return false;
//        }try {
//            log.info("rep.getAgentSessionId() - " + rep.getAgentSessionId());
//            log.info("rep.retrieveChatSessionID() - " + rep.retrieveChatSessionID());
//            log.info("rep.rep.getAgentInformation() - " + rep.getAgentInformation());
//        } catch (IOException e) {
//            log.error("ERROR in getting agent's info");
//        }
//        rep.endChat();
//        log.info("Agent closing chat (should be 201), result- " + rep.getLatestResponseCode());
//        if ((rep.getLatestResponseCode()) != 201) {
//            return false;
//        }
//        return true;
//    }








//    protected static void loadAgentsInRange(int fromIndex, int toIndex) {
//        agents.clear();
//
//        for(int i=(fromIndex-1) ; i < toIndex ; i++) {
//            Rep rep = AgentAPIFactory.createAgent(siteEntity.getSiteId(), siteEntity.getAppKey(), siteEntity.getAppSecret(), siteEntity.getTokenKey(), siteEntity.getTokenSecret(),
//                    DEFAULT_USER_NAME + String.valueOf(i + 1), DEFAULT_PASSWORD, prop.getProperty("skill.name"), siteEntity.getProtocol(), siteEntity.getDomain(), siteEntity.getSiteURL());
//            agents.add(rep);
//        }
//    }





//    public void closeAllPreviousChatRequests() {
//
//        while (agents.get(0).getRingingCount() >= 1) {
//            handleChat(agents.get(0));
//            TestHelper.wait(1);
//        }
//
//        agents.get(0).logout();
//    }
//
//
//    protected String getChatSessionId(Rep rep) {
//
//        try {
//            return rep.retrieveChatSessionID();
//        } catch (IOException e) {
//            log.error("Failure in getting chat session ID");
//        }
//
//        return "";
//    }




}
