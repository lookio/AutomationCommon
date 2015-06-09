package com.agent;

import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.liveperson.utils.RestAPI.AgentAndVisitorUtils;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.List;

/**
 * Created by asih on 06/04/2015.
 *
 */

public class AgentService {

    private final static AgentService INSTANCE = new AgentService();
    private static final Logger logger = Logger.getLogger(AgentService.class);
    private AgentInitializer initializer = new AgentInitializer();
    private AgentPole pole = new AgentPole();

    private AgentService() {
    }

    public static AgentService getInstance() {
        return INSTANCE;
    }

    public final List<Rep> setup(String testPath) {
        return initializer.initAgentData(testPath);
    }

    public final void logInAndSetState(List<Rep> agents, List<AgentState> agentsState) {
        int index = 0;
        if (agents.size() != agentsState.size()) {
            logger.warn(
                    "State array must be equal in size. agent : " + agents.size() + " states : " + agentsState.size()
            );
            return;
        }
        for (AgentState state : agentsState) {
            AgentAndVisitorUtils.agentLogInAndSetState(agents.get(index), state);
            index++;
        }
    }

    public final boolean isRingingCountAsExpected(Rep agent, int expectedRingCount, long timeOutInMilisec) {
        return pole.isRingingCountAsExpected(
                agent,
                expectedRingCount,
                timeOutInMilisec
        );
    }

    public final boolean isRingingCountPositive(Rep agent, long timeOutInMilisec) {
        return pole.isRingingCountPositive(
                agent,
                timeOutInMilisec
        );
    }

    public final boolean startChat(Rep agent) {
        AgentAndVisitorUtils.agentTakeChat(agent);
        logger.info("Agent taking chat (should be 200), result- " + agent.getLatestResponseCode());
        return (agent.getLatestResponseCode()) == 200;
    }

    public final void addChatLines(Rep agent, String msg) {
        try{
            Thread.sleep(3000);
        }catch (InterruptedException ie){
            GeneralUtils.handleError("Failed to wait before sending chat line", ie);
        }
        agent.addChatLines(msg);
    }

    public final boolean verifyLatestChatLines(Rep agent, String msg) {
        String latestMsg;
        try {
            latestMsg = agent.getLatestChatLine();
        } catch (Throwable t) {
            GeneralUtils.handleError("Failed to get latest msg for rep", t);
            return false;
        }
        if (latestMsg.equalsIgnoreCase(msg)) {
            logger.info("Latest chat line is as expected : " + msg);
            return true;
        } else {
            logger.info("Latest chat line is not as expected, expected : " + msg + " actual : " + latestMsg);
            return false;
        }
    }

    public void prepareAgentForChat(Rep agent){
        Assert.assertNotNull("There is no mobile agent", agent);
        Assert.assertTrue("Ringing count is not as expected", isRingingCountPositive(agent, 5000));
        Assert.assertTrue("Start chat encountered a problem", startChat(agent));
    }

    public final boolean endChat(Rep rep) {
        rep.endChat();
        logger.info("Agent closing chat (should be 201), result- " + rep.getLatestResponseCode());
        return (rep.getLatestResponseCode()) == 201;
    }

    public static final void tearDown(List<Rep> reps) {
        logger.info("Rep logout");
        try {
            for (Rep r : reps) {
                if (r != null) {
                    try {
                        r.logout();
                    } catch (Exception e) {
                        GeneralUtils.handleError("Rep Logout Error", e);
                    }
                }
            }
        }
        finally{
            reps.clear();
        }
    }


}

