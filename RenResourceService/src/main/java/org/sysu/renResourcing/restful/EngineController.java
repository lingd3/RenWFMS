/*
 * Project Ren @ 2018
 * Rinkako, Ariana, Gordan. SYSU SDCS.
 */
package org.sysu.renResourcing.restful;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.sysu.renResourcing.ResourcingEngine;
import org.sysu.renCommon.dto.ReturnModel;
import org.sysu.renResourcing.restful.ReturnModelHelper;
import org.sysu.renCommon.dto.StatusCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Rinkako
 * Date  : 2018/1/19
 * Usage : Handle requests from BO Engine and NS Engine.
 */

@RestController
@RequestMapping("/internal")
public class EngineController {
    /**
     * Submit a task resourcing request from BOEngine.
     * @param rtid process runtime record id (required)
     * @param boname bo name (required)
     * @param taskname task polymorphism name (required)
     * @param args argument
     * @return response package
     */
    @PostMapping(value = "/submitTask", produces = {"application/json", "application/xml"})
    @ResponseBody
    @Transactional
    public ReturnModel SubmitTask(@RequestParam(value="rtid", required = false)String rtid,
                                  @RequestParam(value="boname", required = false)String boname,
                                  @RequestParam(value="taskname", required = false)String taskname,
                                  @RequestParam(value="args", required = false)String args) {
        ReturnModel rnModel = new ReturnModel();
        try {
            // miss params
            List<String> missingParams = new ArrayList<>();
            if (rtid == null) missingParams.add("rtid");
            if (boname == null) missingParams.add("boname");
            if (taskname == null) missingParams.add("taskname");
            if (args == null) missingParams.add("args");
            if (missingParams.size() > 0) {
                return ReturnModelHelper.MissingParametersResponse(missingParams);
            }
            // logic
            String jsonifyResult = ResourcingEngine.EngineSubmitTask(rtid, boname, taskname, args);
            // return
            ReturnModelHelper.StandardResponse(rnModel, StatusCode.OK, jsonifyResult);
        } catch (Exception e) {
            ReturnModelHelper.ExceptionResponse(rnModel, e.getClass().getName());
        }
        return rnModel;
    }

    /**
     * Signal that a process runtime has already finished.
     * @param rtid process runtime record id (required)
     * @return response package
     */
    @PostMapping(value = "/finRtid", produces = {"application/json", "application/xml"})
    @ResponseBody
    @Transactional
    public ReturnModel FinRtid(@RequestParam(value="rtid", required = false)String rtid) {
        ReturnModel rnModel = new ReturnModel();
        try {
            // miss params
            List<String> missingParams = new ArrayList<>();
            if (rtid == null) missingParams.add("rtid");
            if (missingParams.size() > 0) {
                return ReturnModelHelper.MissingParametersResponse(missingParams);
            }
            // logic
            String jsonifyResult = "";  // todo
            // return
            ReturnModelHelper.StandardResponse(rnModel, StatusCode.OK, jsonifyResult);
        } catch (Exception e) {
            ReturnModelHelper.ExceptionResponse(rnModel, e.getClass().getName());
        }
        return rnModel;
    }
}