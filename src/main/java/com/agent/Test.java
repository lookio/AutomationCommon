package com.agent;

import com.liveperson.AgentState;
import com.liveperson.Rep;
import java.util.ArrayList;
import java.util.List;

import static com.liveperson.AgentState.*;

/**
 * Created by asih on 29/04/2015.
 */
public class Test {

    protected static String propsFilePath = "./src/main/resources/agent/"; // get from test the test path

    private static List<Rep> agents = new ArrayList<Rep>();
    private static List<Rep> repsState = new ArrayList<Rep>();
    private static List<AgentState> agentStates = new ArrayList<AgentState>();
    static AgentService service = AgentService.getInstance();

    public static void main(String[] args){
        init();
        service.logInAndSetState(repsState, agentStates);
        if(service.isRingingCountAsExpected(agents.get(0), 1, 5000)){
            service.startChat(agents.get(0));
            if(service.verifyLatestChatLines(agents.get(0), "aaaa")) {
                service.addChatLines(agents.get(0), "bbbb");
                service.endChat(agents.get(0));
            }
        }
        service.tearDown(agents);
    }

    private static void init(){
        service.setup(propsFilePath, agents);
        initLoginState();
    }

    private static void initLoginState(){
        repsState.add(agents.get(0));
        repsState.add(agents.get(1));
        agentStates.add(Online);
        agentStates.add(Online);
    }

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




//}