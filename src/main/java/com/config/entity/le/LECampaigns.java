//package com.config.entity.le;
//
//import com.config.base.BaseLEConfigItems;
//import com.config.data.le.LeConfigData.Site.CampaignData;
//
//import java.util.List;
//
///**
// * Created by asih on 09/02/2015.
// */
//public class LECampaigns extends BaseLEConfigItems<LECampaigns, CampaignData> {
//
//    private List<CampaignData> campaignData;
//
//    public LECampaigns(){
//        campaignData = super.parseObjects(LECampaigns.class);
//    }
//
//    public void create() {
//        create(campaignData);
//    }
//
//    public void modify() {
//        modify(campaignData);
//    }
//
//    public void delete() {
//        delete(campaignData);
//    }
//
//    @Override
//    public <E> void create(List<E> ConfigItem) {
//        CampaignData.CreateCampaign createCampaign;
//        for(CampaignData campaign : campaignData){
//            createCampaign = campaign.getCreateCampaign();
//        }
//    }
//
//    @Override
//    public <E> void modify(List<E> ConfigItem) {
//        CampaignData.ModifyCampaign modifyCampaign;
//        for(CampaignData campaign : campaignData){
//            modifyCampaign = campaign.getModifyCampaign();
//        }
//    }
//
//    @Override
//    public <E> void delete(List<E> ConfigItem) {
//        CampaignData.DeleteCampaign deleteCampaign;
//        for(CampaignData campaign : campaignData){
//            deleteCampaign = campaign.getDeleteCampaign();
//        }
//    }
//
//    @Override
//    public boolean isAllreadyContains() {
//        return false;
//    }
//
//
//}
