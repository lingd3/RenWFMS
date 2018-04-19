package org.sysu.renFeign.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sysu.renCommon.dto.ReturnModel;
import org.sysu.renFeign.service.BOEngine;

import javax.transaction.Transactional;

/**
 * Author: Gordan
 * Date  : 2018/3/14
 * Usage : Declarative service invocation of BOEngine.
 */
@RestController
public class BOEngineController {

    @Autowired
    BOEngine boEngine;

    @RequestMapping(value = "/gateway/callback", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel Callback(@RequestParam(value="rtid", required = false)String rtid,
                                @RequestParam(value="bo", required = false)String bo,
                                @RequestParam(value="on", required = false)String on,
                                @RequestParam(value="id", required = false)String id,
                                @RequestParam(value="event", required = false)String event,
                                @RequestParam(value="payload", required = false)String payload) {
        return boEngine.Callback(rtid, bo, on, id, event, payload);
    }

    @RequestMapping(value = "/gateway/serializeBO", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel SerializeBO(@RequestParam(value = "boidlist", required = false) String boidlist) {
        return boEngine.SerializeBO(boidlist);
    }

    @RequestMapping(value = "/gateway/launchProcess", produces = {"application/json", "application/xml"}, method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public ReturnModel LaunchProcess(@RequestParam(value = "rtid", required = false) String rtid) {
        return boEngine.LaunchProcess(rtid);
    }
}
