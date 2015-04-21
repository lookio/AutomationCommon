package com.config.entity.legacy;

import com.config.base.BaseLegacyConfigItems;
import com.config.data.legacy.LegacyConfigData;
import com.config.service.ISite;

import java.util.List;

/**
 * Created by asih on 09/02/2015.
 */
public class LegacySites implements ISite {

    private LegacyConfigData.Site site;
    List<LegacyConfigData.Site> sites;

    public LegacySites(){
        site = getSite(LegacyConfigData.Site.class);
    }

    @Override
    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    public <T> T getSite(Class<T> site){
        BaseLegacyConfigItems.getConfData();
        return (T) BaseLegacyConfigItems.confData.getSite();
    }

    public void create() {
//        create(sites);
        String siteId = site.getCreateSite().getSiteId();
        new LegacyUsers().create();
        new LegacyVisitors().create();
        new com.config.entity.legacy.LegacyRules().create();
    }

    public void modify() {
        // modify site;
        new LegacyUsers().modify();
        new LegacyVisitors().modify();
        new LegacyRules().modify();
    }

    public void delete() {
//        delete site;
        new LegacyUsers().delete();
        new LegacyVisitors().delete();
        new LegacyRules().delete();
    }

    @Override
    public boolean isAllreadyContains() {
        return false;
    }

}
