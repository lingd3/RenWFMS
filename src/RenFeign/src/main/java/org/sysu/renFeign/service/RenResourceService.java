package org.sysu.renFeign.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sysu.renCommon.dto.ReturnModel;
import org.sysu.renFeign.service.impl.RenResourceServiceHystrix;

/**
 * Author: Gordan
 * Date  : 2018/3/14
 * Usage : Interface of RenResourceService API.
 */
@FeignClient(value = "RenResourceService", fallback = RenResourceServiceHystrix.class)
public interface RenResourceService {

    @RequestMapping(value = "/internal/submitTask", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel SubmitTask(@RequestParam(value="rtid", required = false)String rtid,
                                  @RequestParam(value="boname", required = false)String boname,
                                  @RequestParam(value="nodeId", required = false)String nodeId,
                                  @RequestParam(value="taskname", required = false)String taskname,
                                  @RequestParam(value="args", required = false)String args);

    @RequestMapping(value = "/internal/finRtid", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel FinRtid(@RequestParam(value="rtid", required = false)String rtid,
                               @RequestParam(value="successFlag", required = false)String successFlag);

    @RequestMapping(value = "/workitem/start", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel StartWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                     @RequestParam(value = "workitemId", required = false) String workitemId);

    @RequestMapping(value = "/workitem/accept", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel AcceptWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                      @RequestParam(value = "workitemId", required = false) String workitemId);

    @RequestMapping(value = "/workitem/acceptStart", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel AcceptAndStartWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                              @RequestParam(value = "workitemId", required = false) String workitemId);


    @RequestMapping(value = "/workitem/complete", method = RequestMethod.POST)
    @ResponseBody

    public ReturnModel CompleteWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                        @RequestParam(value = "workitemId", required = false) String workitemId);

    @RequestMapping(value = "/workitem/suspend", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel SuspendWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                       @RequestParam(value = "workitemId", required = false) String workitemId);


    @RequestMapping(value = "/workitem/unsuspend", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel UnsuspendWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                         @RequestParam(value = "workitemId", required = false) String workitemId);

    @RequestMapping(value = "/workitem/skip", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel SkipWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                    @RequestParam(value = "workitemId", required = false) String workitemId);

    @RequestMapping(value = "/workitem/reallocate", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel ReallocateWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                          @RequestParam(value = "workitemId", required = false) String workitemId);

    @RequestMapping(value = "/workitem/deallocate", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel DeallocateWorkitem(@RequestParam(value = "workerId", required = false) String workerId,
                                          @RequestParam(value = "workitemId", required = false) String workitemId);

    @Cacheable("allWorkitems")
    @RequestMapping(value = "/workitem/getAll", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel GetAll(@RequestParam(value = "rtid", required = false) String rtid);

    @Cacheable("workqueue")
    @RequestMapping(value = "/queue/get", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel GetWorkQueue(@RequestParam(value="rtid", required = false)String rtid,
                                    @RequestParam(value="workerId", required = false)String workerId,
                                    @RequestParam(value="type", required = false)String type);

    @Cacheable("list")
    @RequestMapping(value = "/queue/getlist", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel GetWorkQueueList(@RequestParam(value="rtid", required = false)String rtid,
                                        @RequestParam(value="workerIdList", required = false)String workerIdList,
                                        @RequestParam(value="type", required = false)String type);

}
