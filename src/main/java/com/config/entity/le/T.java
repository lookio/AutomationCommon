package com.config.entity.le;

import com.config.entity.le.lpadk.ConfigInitializer;
import com.liveperson.automation.datamodel.liveengage.user.PermissionType;
import com.liveperson.automation.datamodel.liveengage.user.User;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by asih on 22/03/2015.
 */
public class T {

    static ConfigInitializer initializer = ConfigInitializer.getInstance();

    public static final void main(String[] args) throws Exception {

        initializer.createNewSite();

        initializer.updateConfigurationInSite(null, true);

//        iterate over jaxb

        initializer.createSkill("mobile");
        initializer.updateConfigurationInSite(null, true);
        initializer.createSkill("TechSupport");
        initializer.updateConfigurationInSite(null, true);
        initializer.createSkill("sales");
        initializer.updateConfigurationInSite(null, true);

//        initializer.createAgent("asi", "asih@liveperson.com", ConfigInitializer.PermissionType.AGENT, new JSONArray(Arrays.asList("mobile", "techSupport")));

//        initializer.createAgent("asi", "asih@liveperson.com", ConfigInitializer.PermissionType.AGENT, new JSONArray(Arrays.asList("mobile")));
        initializer.createAgent("asi", "asih@liveperson.com");


        initializer.updateConfigurationInSite(null, true);

//        initializer.deleteSite();
    }
}
