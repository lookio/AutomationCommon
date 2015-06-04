package com.agent;

import com.liveperson.Rep;
import com.util.genutil.GeneralUtils;
import org.apache.log4j.Logger;

/**
 * Created by asih on 04/06/2015.
 */
public class AgentPole {

    private static final Logger logger = Logger.getLogger(AgentPole.class);

    public final boolean isRingingCountAsExpected(Rep agent, int expectedRingCount, long timeOutInMilisec) {
        if (agent != null) {
            return waitForRingingCount(
                    expectedRingCount,
                    agent,
                    timeOutInMilisec
            );
        } else {
            logger.error("Agent is null");
            return false;
        }
    }

    private boolean waitForRingingCount(int expectedRingCount, Rep agent, long timeOutInMil) {
        while (expectedRingCount != agent.getRingingCount()) {
            try {
                long waitInterval = 500;
                Thread.sleep(waitInterval);
                timeOutInMil -= waitInterval;
                if (timeOutInMil <= 0) {
                    logger.warn(
                            "Time out finished, Number of chats must be equal to expected. expected : " +
                                    expectedRingCount + " actual : " +
                                    agent.getRingingCount()
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

    public final boolean isRingingCountPositive(Rep agent, long timeOutInMilisec) {
        if (agent != null) {
            return waitForRingingCountPositive(
                    agent,
                    timeOutInMilisec
            );
        } else {
            logger.error("Agent is null");
            return false;
        }
    }

    private boolean waitForRingingCountPositive(Rep agent, long timeOutInMil) {
        while (agent.getRingingCount() <= 0) {
            try {
                long waitInterval = 500;
                Thread.sleep(waitInterval);
                timeOutInMil -= waitInterval;
                if (timeOutInMil <= 0) {
                    logger.warn(
                            "Time out finished, Number of chats must be positive" +
                                    agent.getRingingCount()
                    );
                    return false;
                }
            } catch (InterruptedException e) {
                GeneralUtils.handleError("Error in wait for time out", e);
            }
        }
        logger.info("There are positive chats");
        return true;
    }
}
