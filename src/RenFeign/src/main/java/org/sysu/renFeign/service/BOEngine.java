package org.sysu.renFeign.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sysu.renCommon.dto.ReturnModel;
import org.sysu.renFeign.service.impl.BOEngineHystrix;

/**
 * Author: Gordan
 * Date  : 2018/3/14
 * Usage : Interface of BOEngine API.
 */
@FeignClient(value = "BOEngine", fallback = BOEngineHystrix.class)
public interface BOEngine {

    @RequestMapping(value = "/gateway/callback", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel Callback(@RequestParam(value="rtid", required = false)String rtid,
                                @RequestParam(value="bo", required = false)String bo,
                                @RequestParam(value="on", required = false)String on,
                                @RequestParam(value="id", required = false)String id,
                                @RequestParam(value="event", required = false)String event,
                                @RequestParam(value="payload", required = false)String payload);

    @RequestMapping(value = "/gateway/serializeBO", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel SerializeBO(@RequestParam(value = "boidlist", required = false) String boidlist);

    @RequestMapping(value = "/gateway/launchProcess", method = RequestMethod.POST)
    @ResponseBody
    public ReturnModel LaunchProcess(@RequestParam(value = "rtid", required = false) String rtid);
}
