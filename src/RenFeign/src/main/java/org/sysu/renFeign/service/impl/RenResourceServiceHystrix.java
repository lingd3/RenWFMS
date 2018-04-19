package org.sysu.renFeign.service.impl;

import org.springframework.stereotype.Component;
import org.sysu.renCommon.dto.ReturnElement;
import org.sysu.renCommon.dto.ReturnModel;
import org.sysu.renCommon.dto.StatusCode;
import org.sysu.renFeign.service.RenResourceService;

/**
 * Author: Gordan
 * Date  : 2018/3/14
 * Usage : RenResourceService hystrix fallback class.
 */
@Component
public class RenResourceServiceHystrix implements RenResourceService {
	
    private ReturnModel setReturnModel() {
        ReturnModel returnModel = new ReturnModel();
        returnModel.setCode(StatusCode.Fail);
        returnModel.setServiceId("RenResourceService");
        ReturnElement returnElement = new ReturnElement();
        returnElement.setData("Fallback");
        returnModel.setReturnElement(returnElement);
        return returnModel;
    }

	@Override
	public ReturnModel SubmitTask(String rtid, String boname, String nodeId, String taskname, String args) {
		return setReturnModel(); 
	}

	@Override
	public ReturnModel FinRtid(String rtid, String successFlag) {
		return setReturnModel(); 
	}

	@Override
	public ReturnModel StartWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel AcceptWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel AcceptAndStartWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel CompleteWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel SuspendWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel UnsuspendWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel SkipWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel ReallocateWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel DeallocateWorkitem(String workerId, String workitemId) {
		return setReturnModel();
	}

	@Override
	public ReturnModel GetAll(String rtid) {
		return setReturnModel();
	}

	@Override
	public ReturnModel GetWorkQueue(String rtid, String workerId, String type) {
		return setReturnModel();
	}

	@Override
	public ReturnModel GetWorkQueueList(String rtid, String workerIdList, String type) {
		return setReturnModel();
	}

}
