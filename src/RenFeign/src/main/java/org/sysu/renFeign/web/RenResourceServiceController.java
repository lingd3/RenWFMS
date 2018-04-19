package org.sysu.renFeign.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.sysu.renCommon.dto.ReturnModel;
import org.sysu.renFeign.service.RenResourceService;

/**
 * Author: Gordan
 * Date  : 2018/3/14
 * Usage : Declarative service invocation of RenResourceService.
 */
@RestController
public class RenResourceServiceController {

    @Autowired
    RenResourceService renResourceService;

    @RequestMapping(value = "/internal/submitTask", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel SubmitTask(@RequestParam(value="rtid", required = false)String rtid,
                                  @RequestParam(value="boname", required = false)String boname,
                                  @RequestParam(value="nodeId", required = false)String nodeId,
                                  @RequestParam(value="taskname", required = false)String taskname,
                                  @RequestParam(value="args", required = false)String args) {
        return renResourceService.SubmitTask(rtid, boname, nodeId, taskname, args);
    }

    @RequestMapping(value = "/internal/finRtid", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel FinRtid(@RequestParam(value="rtid", required = false)String rtid,
                               @RequestParam(value="successFlag", required = false)String successFlag) {
        return renResourceService.FinRtid(rtid, successFlag);
    }

    @RequestMapping(value = "/workitem/start", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel StartWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                     @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.StartWorkitem(workerId, workitemId);
    }

    @RequestMapping(value = "/workitem/accept", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel AcceptWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                      @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.AcceptWorkitem(workerId, workitemId);
    }

    @RequestMapping(value = "/workitem/acceptStart", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel AcceptAndStartWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                              @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.AcceptAndStartWorkitem(workerId, workitemId);
    }


    @RequestMapping(value = "/workitem/complete", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel CompleteWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                        @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.CompleteWorkitem(workerId, workitemId);
    }

    @RequestMapping(value = "/workitem/suspend", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel SuspendWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                       @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.SuspendWorkitem(workerId, workitemId);
    }


    @RequestMapping(value = "/workitem/unsuspend", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel UnsuspendWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                         @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.UnsuspendWorkitem(workerId, workitemId);
    }

    @RequestMapping(value = "/workitem/skip", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel SkipWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                    @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.SkipWorkitem(workerId, workitemId);
    }

    @RequestMapping(value = "/workitem/reallocate", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel ReallocateWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                          @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.ReallocateWorkitem(workerId, workitemId);
    }

    @RequestMapping(value = "/workitem/deallocate", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel DeallocateWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                          @RequestParam(value = "workitemId", required = false) String workitemId) {
        return renResourceService.DeallocateWorkitem(workerId, workitemId);
    }

    @RequestMapping(value = "/workitem/getAll", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel GetAll(@RequestParam(value = "rtid", required = false) String rtid) {
        return renResourceService.GetAll(rtid);
    }

    @RequestMapping(value = "/queue/get", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel GetWorkQueue(@RequestParam(value="rtid", required = false)String rtid,
                                    @RequestParam(value="workerId", required = false)String workerId,
                                    @RequestParam(value="type", required = false)String type) {
        return renResourceService.GetWorkQueue(rtid, workerId, type);
    }

    @RequestMapping(value = "/queue/getlist", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel GetWorkQueueList(@RequestParam(value="rtid", required = false)String rtid,
                                        @RequestParam(value="workerIdList", required = false)String workerIdList,
                                        @RequestParam(value="type", required = false)String type) {
        return renResourceService.GetWorkQueueList(rtid, workerIdList, type);
    }

}
