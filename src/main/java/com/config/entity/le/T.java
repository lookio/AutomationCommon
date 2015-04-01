package com.config.entity.le;

import com.config.entity.le.lpadk.ConfigInitializer;
import com.liveperson.automation.datamodel.liveengage.user.PermissionType;
import com.liveperson.automation.datamodel.liveengage.user.User;

/**
 * Created by asih on 22/03/2015.
 */
public class T {

    static ConfigInitializer initializer = ConfigInitializer.getInstance();

    public static final void main(String[] args) throws Exception {
//        initializer.createSite();

        initializer.createNewSite(89497843);

//        iterate over jaxb

//        User adminUser = new User();

//        adminUser.setName(initializer.getUSERNAME());
//        adminUser.setPassword(initializer.getPASSWORD());
//        adminUser.setEmail(initializer.getEMAIL());

        initializer.createUser("asi" ,"asihliveperson" , ConfigInitializer.PermissionType.AGENT);


//        User agent = new User();
//
//        agent.setName("asi");
//        agent.setPassword("12345678");
//        agent.setEmail("asih@liveperson.com");
//        agent.setPermission(PermissionType.AGENT);
//
//        initializer.addUserToSite(agent);

        initializer.createSkill("mobile");
        initializer.createSkill("tech");
        initializer.createSkill("sales");
    }
}
