package org.sysu.renFeign.service.impl;

import org.springframework.stereotype.Component;
import org.sysu.renCommon.dto.ReturnElement;
import org.sysu.renCommon.dto.ReturnModel;
import org.sysu.renCommon.dto.StatusCode;
import org.sysu.renFeign.service.BOEngine;

/**
 * Author: Gordan
 * Date  : 2018/3/14
 * Usage : BOEngine hystrix fallback class.
 */
@Component
public class BOEngineHystrix implements BOEngine {
	
    private ReturnModel setReturnModel() {
        ReturnModel returnModel = new ReturnModel();
        returnModel.setCode(StatusCode.Fail);
        returnModel.setServiceId("BOEngine");
        ReturnElement returnElement = new ReturnElement();
        returnElement.setData("Fallback");
        returnModel.setReturnElement(returnElement);
        return returnModel;
    }

	@Override
	public ReturnModel Callback(String rtid, String bo, String on, String id, String event, String payload) {
		return setReturnModel();
	}

	@Override
	public ReturnModel SerializeBO(String boidlist) {
		return setReturnModel();
	}

	@Override
	public ReturnModel LaunchProcess(String rtid) {
		return setReturnModel();
	}

}
