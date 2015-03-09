package com.config.entity.le;

import com.config.base.BaseLEConfigItems;
import com.config.data.le.LeConfigData.Site;
import com.config.entity.le.LECampaigns;
import com.config.entity.le.LEUsers;
import com.config.entity.le.LEVisitors;
import com.config.service.ISite;
import com.config.service.Parsable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asih on 09/02/2015.
 */
public class LESites implements ISite{

    private Site site;

    public LESites(){
        site = getSite(Site.class);
    }

    @Override
    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    public <T> T getSite(Class<T> site){
        BaseLEConfigItems.getConfData();
        return (T)BaseLEConfigItems.confData.getSite();
    }

    public void create() {
//        create(sites);
        String siteId = site.getCreateSite().getSiteId();
        new LEUsers().create();
        new LEVisitors().create();
        new LECampaigns().create();
    }

    public void modify() {
        // modify site;
        new LEUsers().modify();
        new LEVisitors().modify();
        new LECampaigns().modify();
    }

    public void delete() {
//        delete site;
        new LEUsers().delete();
        new LEVisitors().delete();
        new LECampaigns().delete();
    }



}
