package com.agent;

import com.liveperson.AgentState;
import com.liveperson.Rep;
import scala.actors.threadpool.Arrays;

import java.lang.reflect.Array;
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
