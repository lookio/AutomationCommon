package com.config.entity.le;

import com.config.base.BaseLEConfigItems;
import com.config.data.le.LeConfigData.Site;
import com.config.lpadk.ConfigInjector;
import com.config.service.ISite;

/**
 * Created by asih on 09/02/2015.
 */
public class LESites implements ISite{

    private Site site;
    private ConfigInjector.Creator confCreator = ConfigInjector.getInstance().getCreator();

    public LESites(){
        site = getSite(Site.class);
    }

    @Override
    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    public <T> T getSite(Class<T> site){
        BaseLEConfigItems.getConfData();
        return (T)BaseLEConfigItems.confData.getSite();
    }

    public void create() throws Exception {
        Site.CreateSite createSite = site.getCreateSite();
            confCreator.createNewSite(getSiteId(createSite),
                    createSite.isIsExtentExpiration(),
                            site.getUsersData().get(0).getCreateUser());
        String siteId = site.getCreateSite().getSiteId();
        new LEUsers().create();
//        new LEVisitors().create();
//        new LECampaigns().create();
    }

    private String getSiteId(Site.CreateSite createSite){
        if(createSite.getCreationType().equalsIgnoreCase("CreateNew")) {
            return createSite.getSiteId();
        }else{
            return null;
        }
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

    @Override
    public boolean isAllreadyContains() {
        return false;
    }

}
