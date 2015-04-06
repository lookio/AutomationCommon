package com.config.entity.le;

import com.config.lpadk.ConfigInitializer;
import org.json.JSONArray;

/**
 * Created by asih on 22/03/2015.
 */
public class T {

    static ConfigInitializer initializer = ConfigInitializer.getInstance();

    public static final void main(String[] args) throws Exception {

        initializer.createNewSite(34236547, true);

//        initializer.updateConfigurationInSite(null, true);

//        iterate over jaxb

        initializer.createSkill("mobile");
//        initializer.updateConfigurationInSite(null, true);
        initializer.createSkill("TechSupport");
//        initializer.updateConfigurationInSite(null, true);
        initializer.createSkill("sales");
//        initializer.updateConfigurationInSite(null, true);

//        String id = initializer.getSkillId("mobile");




//        ConfigInitializer.createUser(initializer.testAccount , "asih", "asih@liveperson.com" ,ConfigInitializer.PermissionType.AGENT , new JSONArray(Arrays.asList("mobile", "techSupport")));


//        JSONObject jo = new JSONObject();
//        jo.put("skills", "mobile");
//        jo.put("skills", "techSupport");

        JSONArray ja = new JSONArray();
        ja.put("mobile");
        ja.put("techSupport");

        initializer.createAgent("asihhh@liveperson.com", "12345678", ja);

//        initializer.createAgent("asih", "asih@liveperson.com", new JSONArray(Arrays.asList("mobile", "techSupport")));
//        initializer.createAgent("asih", "asih@liveperson.com", ja);
//        initializer.updateConfigurationInSite(null, true);
//        initializer.createAgent("asihh", "asihh@liveperson.com", new JSONArray(Arrays.asList("mobile")));
//        initializer.updateConfigurationInSite(null, true);



//        initializer.createAgent("asihhh", "asihhh@liveperson.com");
//        initializer.deleteSite();


//        initializer.updateConfigurationInSite(null, true);


//        initializer.deleteSite();
    }
}
