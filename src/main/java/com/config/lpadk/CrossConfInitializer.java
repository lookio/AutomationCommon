package com.config.lpadk;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asih on 07/04/2015.
 */
public class CrossConfInitializer {

    private ConfigInjector injector;

    CrossConfInitializer(){
        injector = ConfigInjector.getInstance();
    }

    void setPrivilages() {
        injector.privileges = new HashSet<Integer>();
        injector.privileges.add(111);
        injector.privileges.add(112);
        injector.privileges.add(1501); // user management module privilege
    }

    void setCassandraHosts() {
        injector.cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
    }

    Set<String> setAcFeatures() {
        injector.acFeatures = new HashSet<String>();
        injector.acFeatures.add("LEUI.ConnectionBar_Display");
        injector.acFeatures.add("LEUI.WebAnalytics");
        injector.acFeatures.add("Common.Billing_CPI2");
        injector.acFeatures.add("Common.LiveEngage_2");
        injector.acFeatures.add("Common.LiveEngage_2_Unified_window");
        return injector.acFeatures;
    }

    Set<String> setAcFeatures(String[] features) {
        injector.acFeatures = new HashSet<String>();
        for (String feature : features) {
            injector.acFeatures.add(feature);
        }
        return injector.acFeatures;
    }

    Set<String> setAcPackages() {
        injector.acPackages = new HashSet<String>();
        injector.acPackages.add("LE_Platform");
        injector.acPackages.add("LP_Chat");
        injector.acPackages.add("LIVE_ENGAGEv2");
        return injector.acPackages;
    }

    Set<String> setAcPackages(String[] packages) {
        injector.acPackages = new HashSet<String>();
        for (String acPackage : packages) {
            injector.acPackages.add(acPackage);
        }
        return injector.acPackages;
    }

}
