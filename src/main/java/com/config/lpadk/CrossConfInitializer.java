package com.config.lpadk;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asih on 07/04/2015.
 */
public class CrossConfInitializer {

    private String cassandraHosts;
    private Set<Integer> privileges;
    private Set<String> acFeatures;
    private Set<String> acPackages;

    CrossConfInitializer(){
    }

    void setPrivilages() {
        privileges = new HashSet<Integer>();
        privileges.add(111);
        privileges.add(112);
        privileges.add(1501); // user management module privilege
    }

    void setCassandraHosts() {
        cassandraHosts = "dev-int-unix2,dev-int-unix3,dev-int-unix5";
    }

    Set<String> initFeatures() {
        acFeatures = new HashSet<String>();
        acFeatures.add("LEUI.ConnectionBar_Display");
        acFeatures.add("LEUI.WebAnalytics");
        acFeatures.add("Common.Billing_CPI2");
        acFeatures.add("Common.LiveEngage_2");
        acFeatures.add("Common.LiveEngage_2_Unified_window");
        return acFeatures;
    }

    Set<String> initAcFeatures(String[] features) {
        acFeatures = new HashSet<String>();
        for (String feature : features) {
            acFeatures.add(feature);
        }
        return acFeatures;
    }

    Set<String> initAcPackages() {
        acPackages = new HashSet<String>();
        acPackages.add("LE_Platform");
        acPackages.add("LP_Chat");
        acPackages.add("LIVE_ENGAGEv2");
        return acPackages;
    }

    Set<String> initAcPackages(String[] packages) {
        acPackages = new HashSet<String>();
        for (String acPackage : packages) {
            acPackages.add(acPackage);
        }
        return acPackages;
    }

    Set<String> getAcPackages() {
        return acPackages;
    }

    String getCassandraHosts() {
        return cassandraHosts;
    }

    Set<Integer> getPrivileges() {
        return privileges;
    }

    Set<String> getAcFeatures() {
        return acFeatures;
    }

}
