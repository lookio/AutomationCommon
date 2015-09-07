package com.util.poller;

import com.util.genutil.GeneralUtils;

/**
 * Created by asih on 16/08/2015.
 */
public abstract class PollService implements Pollable{

    public void waitUntil (long timeOutInMilisec, long interval, String timeoutMessage)
            throws Exception {

        while (!this.until()){
            try {
                Thread.sleep(interval);
                timeOutInMilisec -= interval;
                if (timeOutInMilisec <= 0) {
                    GeneralUtils.handleError(timeoutMessage, new Exception(timeoutMessage));
                }
            } catch (InterruptedException e) {
                GeneralUtils.handleError("Error in wait for time out", e);

            }
        }


    }


}
