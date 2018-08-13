/*
 * Project Ren @ 2018
 * Rinkako, Ariana, Gordan. SYSU SDCS.
 */
package org.sysu.renCommon.interactionRouter;

/**
 * Author: Rinkako
 * Date  : 2018/3/5
 * Usage : Location of interaction.
 */
public class LocationContext {

    /**
     * Service URL for RS submit task.
     */
    public static final String URL_RS_SUBMITTASK = "http://renresourceservice:10233/internal/submitTask";

    /**
     * Service URL for RS finish life cycle of BO.
     */
    public static final String URL_RS_FINISH = "http://renresourceservice:10233/internal/finRtid";

    /**
     * Service URL for BO Engine Serialization BO.
     */
    public static final String URL_BOENGINE_SERIALIZEBO = "http://boengine:10232/gateway/serializeBO";

    /**
     * Service URL for BO Engine Serialization BO.
     */
    public static final String URL_BOENGINE_START = "http://boengine:10232/gateway/launchProcess";

    /**
     * Service URL for BO Engine event callback.
     */
    public static final String URL_BOENGINE_SPANTREE = "http://boengine:10232/gateway/getSpanTree";

    /**
     * Service URL for BO Engine event callback.
     */
    public static final String URL_BOENGINE_CALLBACK = "http://boengine:10232/gateway/callback";

    /**
     * Service URL gateway for RS workitem actions.
     */
    public static final String GATEWAY_RS_WORKITEM = "http://renresourceservice:10233/workitem/";

    /**
     * Service URL gateway for RS workqueue actions.
     */
    public static final String GATEWAY_RS_QUEUE = "http://renresourceservice:10233/queue/";
}
