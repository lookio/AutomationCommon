package com.agent;

import com.liveperson.AgentState;
import com.liveperson.Rep;
import com.liveperson.utils.RestAPI.AgentAndVisitorUtils;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;
import java.util.List;

/**
 * Created by asih on 06/04/2015.
 */

@SuppressWarnings("DefaultFileTemplate")
public class AgentService {

    public final static AgentService INSTANCE = new AgentService();
    private static final Logger logger = Logger.getLogger(AgentService.class);


    private AgentService() {
    }

    public static AgentService getInstance() {
        return INSTANCE;
    }

    public final void setup(String testPath, List<Rep> agents) {
        new AgentInitializer().initAgentData(testPath, agents);
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
        if (agent != null) {
            return waitForRingingCount(
                    expectedRingCount,
                    agent.getRingingCount(),
                    timeOutInMilisec
            );
        } else {
            logger.error("Agent is null");
            return false;
        }
    }

    private boolean waitForRingingCount(int expectedRingCount, int actualCount, long timeOutInMil) {
        while (expectedRingCount != actualCount) {
            try {
                long waitInterval = 500;
                Thread.sleep(waitInterval);
                timeOutInMil -= waitInterval;
                if (timeOutInMil <= 0) {
                    logger.warn(
                            "Time out finished, Number of chats must be equal to expected. expected : " +
                                    expectedRingCount + " actual : " +
                                    actualCount
                    );
                    return false;
                }
            } catch (InterruptedException e) {
                GeneralUtils.handleError("Error in wait for time out", e);
            }
        }
        logger.info("There are exactly " + expectedRingCount + " chats");
        return true;
    }

    public final boolean startChat(Rep agent) {
        AgentAndVisitorUtils.agentTakeChat(agent);
        logger.info("Agent taking chat (should be 200), result- " + agent.getLatestResponseCode());
        return (agent.getLatestResponseCode()) == 200;
    }

    public final void addChatLines(Rep agent, String msg) {
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

    public final boolean endChat(Rep rep) {
        rep.endChat();
        logger.info("Agent closing chat (should be 201), result- " + rep.getLatestResponseCode());
        return (rep.getLatestResponseCode()) == 201;
    }

    public final void tearDown(List<Rep> reps) {
        logger.info("Rep logout");
        for (Rep r : reps) {
            if (r != null) {
                try {
                    r.logout();
                }
                catch (Exception e) {
                    GeneralUtils.handleError("Rep Logout Error", e);
                }
            }
        }
    }

}

