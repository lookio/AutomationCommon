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

public class AgentService {

    public final static AgentService INSTANCE = new AgentService();
    private final long waitInterval = 500;

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

    public final boolean isRingingCountAsExpected(Rep agent, int expectedCount, long timeOutInMilisec) {
        if (agent != null) {
            try {
                return waitForRingingCount(expectedCount, agent.getRingingCount(), timeOutInMilisec);
            } catch (Throwable t) {
                GeneralUtils.handleError("Failed to get ringing count for rep", t);
                return false;
            }
        } else {
            logger.error("Agent is null");
            return false;
        }
    }

    private final boolean waitForRingingCount(int expCount, int actualCount, long timeOutInMili) {
        while (expCount != actualCount) {
            try {
                Thread.sleep(waitInterval);
                timeOutInMili -= waitInterval;
                if (timeOutInMili <= 0) {
                    logger.warn(
                            "Number of chats must be equal to expected. expected : " + expCount + " actual : " + actualCount
                    );
                    return false;
                }
            } catch (InterruptedException e) {
                GeneralUtils.handleError("Error in wait for time out", e);
            }
        }
        logger.info("There are exactly " + expCount + " chats");
        return true;
    }

    public final boolean startChat(Rep agent) {
        AgentAndVisitorUtils.agentTakeChat(agent);
        logger.info("Agent taking chat (should be 200), result- " + agent.getLatestResponseCode());
        if ((agent.getLatestResponseCode()) != 200) {
            return false;
        }
        return true;
    }

    public final void addChatLines(Rep agent, String msg) {
        agent.addChatLines(msg);
    }

    public final boolean verifyLatestChatLines(Rep agent, String msg) {
        String latestMsg = null;
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
        if ((rep.getLatestResponseCode()) != 201) {
            return false;
        }
        return true;
    }

    public final void tearDown(List<Rep> reps) {
        logger.info("Rep logout");
        for (Rep r : reps) {
            if (r != null) {
                try {
                    r.logout();
                }
                catch (Exception e) {
                    logger.error("Rep Logout");
                }
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Failed to wait 1 sec");
        }
    }

}

