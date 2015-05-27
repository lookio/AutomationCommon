package com.agent;

import com.config.base.ConfigItemsRouter;
import com.config.data.le.LeConfigData;
import com.liveperson.Rep;
import com.liveperson.http.requests.RequestHelper;

import com.util.genutil.GeneralUtils;
import com.util.properties.PropertiesHandlerImpl;
import com.config.data.le.LeConfigData.Site.UsersData;
import com.config.data.le.LeConfigData.Site.UsersData.CreateUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by asih on 06/04/2015.
 */

@SuppressWarnings("DefaultFileTemplate")
public class AgentInitializer {

    private Properties prop;

    private List<LeConfigData.Site.UsersData> usersData;
    private RequestHelper helper;
    private Constants constants = new Constants();

    private Rep mobileRep = null;


    public List<Rep> initAgentData(String testPath) {
        List<Rep> agents = new ArrayList<Rep>();
        try {
            initFiles(testPath);
            helper = new RequestHelper(prop.getProperty(constants.propsAppKeyKey));
        } catch (IOException ioe) {
            GeneralUtils.handleError("Error parsing conf files", ioe);
        }
        return initReps(agents);
    }

    private List<Rep> initReps(List<Rep> agents){
        CreateUser create;
        String skill;
        for(UsersData userData : usersData) {
            create = userData.getCreateUser();
            skill = create.getSkill().get(0);
            Rep rep = new Rep(
                    prop.getProperty(constants.propsSiteIdKey),
                    create.getUser(),
                    create.getPassword(),
                    skill,
                    prop.getProperty(constants.propsHostKey) ,
                    helper
            );
            agents.add(rep);
            if(skill.equalsIgnoreCase(constants.mobileSkill)){
                mobileRep = rep;
            }
        }
        return agents;
    }

    private void initFiles(String testPath) throws IOException {
        prop = PropertiesHandlerImpl.getInstance().parse(testPath + constants.propsFileName);
        usersData = ConfigItemsRouter.getInstance().initService(
                testPath + constants.confFileName,
                LeConfigData.class).
                getSite().getUsersData();
    }

    public Rep getMobileRep() {
        return mobileRep;
    }

    private class Constants{

        private final String propsFileName = "agent.properties";
        private final String confFileName = "LE_config_data.xml";
        private final String propsAppKeyKey = "site.appKey";
        private final String propsSiteIdKey = "site.id";
        private final String propsHostKey = "host";
        private final String mobileSkill = "mobile";

    }


}
