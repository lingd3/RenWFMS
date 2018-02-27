/*
 * Project Ren @ 2018
 * Rinkako, Ariana, Gordan. SYSU SDCS.
 */
package org.sysu.renResourcing.restful.dto;

import org.sysu.renResourcing.GlobalContext;
import org.sysu.renResourcing.utility.LogUtil;
import org.sysu.renCommon.utility.TimestampUtil;

import java.util.List;

/**
 * Author: Rinkako
 * Date  : 2018/1/26
 * Usage : Helper methods for ReturnModel construction.
 */
public class ReturnModelHelper {
    /**
     * Warp success response to a ReturnModel
     * @param rnModel return model package to be updated
     * @param code status code enum
     * @param retStr execution return data
     */
    public static void StandardResponse(ReturnModel rnModel, StatusCode code, String retStr) {
        rnModel.setCode(code);
        rnModel.setRs(String.format("%s %s", TimestampUtil.GetTimestampString(), GlobalContext.RESOURCE_SERVICE_GLOBAL_ID));
        ReturnElement returnElement = new ReturnElement();
        returnElement.setData(retStr);
        rnModel.setReturnElement(returnElement);
    }

    /**
     * Router exception handler.
     * @param exception exception descriptor
     */
    public static void ExceptionResponse(ReturnModel rnModel, String exception) {
        rnModel.setCode(StatusCode.Exception);
        rnModel.setRs(String.format("%s %s", TimestampUtil.GetTimestampString(), GlobalContext.RESOURCE_SERVICE_GLOBAL_ID));
        ReturnElement returnElement = new ReturnElement();
        returnElement.setMessage(exception);
        rnModel.setReturnElement(returnElement);
    }

    /**
     * Router unauthorized service request handler.
     * @param token unauthorized token
     * @return response package
     */
    public static ReturnModel UnauthorizedResponse(String token) {
        ReturnModel rnModel = new ReturnModel();
        rnModel.setCode(StatusCode.Unauthorized);
        rnModel.setRs(String.format("%s %s", TimestampUtil.GetTimestampString(), GlobalContext.RESOURCE_SERVICE_GLOBAL_ID));
        ReturnElement returnElement = new ReturnElement();
        returnElement.setMessage(token);
        rnModel.setReturnElement(returnElement);
        LogUtil.Log(String.format("Unauthorized service request (token:%s)", token),
                ReturnModelHelper.class.getName(), LogUtil.LogLevelType.UNAUTHORIZED, "");
        return rnModel;
    }

    /**
     * Router request parameter missing handler.
     * @param params missing parameter list
     * @return response package
     */
    public static ReturnModel MissingParametersResponse(List<String> params) {
        ReturnModel rnModel = new ReturnModel();
        rnModel.setCode(StatusCode.Fail);
        rnModel.setRs(String.format("%s %s", TimestampUtil.GetTimestampString(), GlobalContext.RESOURCE_SERVICE_GLOBAL_ID));
        ReturnElement returnElement = new ReturnElement();
        StringBuilder sb = new StringBuilder();
        sb.append("miss required parameters:");
        for (String s : params) {
            sb.append(s).append(" ");
        }
        returnElement.setMessage(sb.toString());
        rnModel.setReturnElement(returnElement);
        return rnModel;
    }
}
