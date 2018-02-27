/*
 * Project Ren @ 2018
 * Rinkako, Ariana, Gordan. SYSU SDCS.
 */
package org.sysu.renResourcing.interfaceService;

import org.sysu.renResourcing.GlobalContext;
import org.sysu.renResourcing.basic.enums.InitializationByType;
import org.sysu.renResourcing.basic.enums.WorkQueueType;
import org.sysu.renResourcing.basic.enums.WorkitemResourcingStatusType;
import org.sysu.renResourcing.context.*;
import org.sysu.renCommon.utility.AuthDomainHelper;
import org.sysu.renResourcing.utility.LogUtil;
import org.sysu.renCommon.utility.SerializationUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Rinkako
 * Date  : 2018/2/21
 * Usage : Implementation of Interface W of Resource Service.
 *         Interface W is responsible for providing services for outside clients.
 *         User sub-systems use this interface for manage work queues.
 *         Usually methods in the interface will return result immediately.
 */
public class InterfaceW {

    /**
     * Accept offer a workitem.
     * @param ctx rs context
     * @return true for a successful workitem accept
     */
    public static boolean AcceptOffer(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Accept offer but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.AcceptOfferedWorkitem(participant, workitem, InitializationByType.USER_INITIATED);
    }

    /**
     * Deallocate a workitem.
     * @param ctx rs context
     * @return true for a successful workitem deallocate
     */
    public static boolean Deallocate(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Deallocate but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.DeallocateWorkitem(participant, workitem);
    }

    /**
     * Start a workitem.
     * @param ctx rs context
     * @return true for a successful workitem start
     */
    public static boolean Start(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Start but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.StartWorkitem(participant, workitem);
    }

    /**
     * Reallocate a workitem.
     * @param ctx rs context
     * @return true for a successful workitem reallocate
     */
    public static boolean Reallocate(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Reallocate but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.ReallocateWorkitem(participant, workitem);
    }

    /**
     * Accept and start a workitem.
     * @param ctx rs context
     * @return true for a successful workitem accept and start
     */
    public static boolean AcceptAndStart(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Accept and start but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.AcceptOfferedWorkitem(participant, workitem, InitializationByType.SYSTEM_INITIATED);
    }

    /**
     * Skip a workitem.
     * @param ctx rs context
     * @return true for a successful workitem skip
     */
    public static boolean Skip(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Skip but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.SkipWorkitem(participant, workitem);
    }

    /**
     * Suspend a workitem.
     * @param ctx rs context
     * @return true for a successful workitem suspend
     */
    public static boolean Suspend(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Suspend but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.SuspendWorkitem(participant, workitem);
    }

    /**
     * Unsuspend a workitem.
     * @param ctx rs context
     * @return true for a successful workitem unsuspend
     */
    public static boolean Unsuspend(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Unsuspend but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.UnsuspendWorkitem(participant, workitem);
    }

    /**
     * Complete a workitem.
     * @param ctx rs context
     * @return true for a successful workitem complete
     */
    public static boolean Complete(ResourcingContext ctx) {
        String workitemId = (String) ctx.getArgs().get("workitemId");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkitemContext workitem = WorkitemContext.GetContext(workitemId, ctx.getRtid());
        ParticipantContext participant = ParticipantContext.GetContext(ctx.getRtid(), workerId);
        if (workitem == null) {
            LogUtil.Log("Accept offer but workitem not exist, rstid: " + ctx.getRstid(),
                    InterfaceW.class.getName(), LogUtil.LogLevelType.ERROR, ctx.getRtid());
            return false;
        }
        if (participant == null) {
            if (InterfaceO.SenseParticipantDataChanged(ctx.getRtid())) {
                InterfaceX.HandleFastFail(ctx.getRtid());
            }
            else {
                InterfaceX.FailedRedirectToLauncherAuthPool(workitem);
            }
            return false;
        }
        return InterfaceB.CompleteWorkitem(participant, workitem);
    }

    /**
     * Get all workitems in all types of queue of a worker.
     * @param ctx rs context
     * @return a dictionary of (WorkQueueType, ListOfWorkitemDescriptors)
     */
    public static String GetWorkQueues(ResourcingContext ctx) {
        String domain = (String) ctx.getArgs().get("domain");
        String workerId = (String) ctx.getArgs().get("workerId");
        WorkQueueContainer container = WorkQueueContainer.GetContext(workerId);
        HashMap<String, HashSet<WorkitemContext>> retMap = new HashMap<>();
        HashSet<WorkitemContext> allocateSet = (HashSet<WorkitemContext>) container.GetQueuedWorkitem(WorkQueueType.ALLOCATED);
        retMap.put(WorkQueueType.ALLOCATED.name(), new HashSet<>());
        for (WorkitemContext workitem : allocateSet) {
            String authDomain = AuthDomainHelper.GetDomainByRTID(ctx.getRtid());
            if (authDomain.equals(domain)) {
                retMap.get(WorkQueueType.ALLOCATED.name()).add(workitem);
            }
        }
        HashSet<WorkitemContext> offeredSet = (HashSet<WorkitemContext>) container.GetQueuedWorkitem(WorkQueueType.OFFERED);
        retMap.put(WorkQueueType.OFFERED.name(), new HashSet<>());
        for (WorkitemContext workitem : offeredSet) {
            String authDomain = AuthDomainHelper.GetDomainByRTID(ctx.getRtid());
            if (authDomain.equals(domain)) {
                retMap.get(WorkQueueType.OFFERED.name()).add(workitem);
            }
        }
        HashSet<WorkitemContext> startedSet = (HashSet<WorkitemContext>) container.GetQueuedWorkitem(WorkQueueType.STARTED);
        retMap.put(WorkQueueType.STARTED.name(), new HashSet<>());
        for (WorkitemContext workitem : startedSet) {
            String authDomain = AuthDomainHelper.GetDomainByRTID(ctx.getRtid());
            if (authDomain.equals(domain)) {
                retMap.get(WorkQueueType.STARTED.name()).add(workitem);
            }
        }
        HashSet<WorkitemContext> suspendSet = (HashSet<WorkitemContext>) container.GetQueuedWorkitem(WorkQueueType.SUSPENDED);
        retMap.put(WorkQueueType.SUSPENDED.name(), new HashSet<>());
        for (WorkitemContext workitem : suspendSet) {
            String authDomain = AuthDomainHelper.GetDomainByRTID(ctx.getRtid());
            if (authDomain.equals(domain)) {
                retMap.get(WorkQueueType.SUSPENDED.name()).add(workitem);
            }
        }
        return SerializationUtil.JsonSerialization(retMap);
    }

    /**
     * Get all workitems in a specific type of queue of a worker.
     * @param ctx rs context
     * @return workitem descriptors string in list
     */
    public static String GetWorkQueue(ResourcingContext ctx) {
        String domain = (String) ctx.getArgs().get("domain");
        String workerId = (String) ctx.getArgs().get("workerId");
        String queueTypeName = (String) ctx.getArgs().get("queueTypeName");
        WorkQueueType wqType = WorkQueueType.valueOf(queueTypeName.toUpperCase());
        WorkQueueContainer container = WorkQueueContainer.GetContext(workerId);
        HashSet<WorkitemContext> allocateSet = (HashSet<WorkitemContext>) container.GetQueuedWorkitem(wqType);
        HashSet<WorkitemContext> retSet = new HashSet<>();
        for (WorkitemContext workitem : allocateSet) {
            String authDomain = AuthDomainHelper.GetDomainByRTID(ctx.getRtid());
            if (authDomain.equals(domain)) {
                retSet.add(workitem);
            }
        }
        return SerializationUtil.JsonSerialization(retSet);
    }

    /**
     * Get all workers with any non-empty work queue in a domain.
     * @param ctx rs context
     * @return worker gid in a list
     */
    public static String GetNotEmptyQueueWorkers(ResourcingContext ctx) {
        String domain = (String) ctx.getArgs().get("domain");
        WorkQueueContainer adminContainer = WorkQueueContainer.GetContext(GlobalContext.WORKQUEUE_ADMIN_PREFIX + domain);
        Set<WorkitemContext> worklisted = adminContainer.GetQueue(WorkQueueType.WORKLISTED).GetQueueAsSet();
        HashSet<String> retParticipantIds = new HashSet<>();
        for (WorkitemContext workitem : worklisted) {
            retParticipantIds.add(workitem.getEntity().getWid());
        }
        return SerializationUtil.JsonSerialization(retParticipantIds);
    }

    /**
     * Get all workers with a non-empty offered work queue in a domain.
     * @param ctx rs context
     * @return worker gid in a list
     */
    public static String GetNotEmptyOfferedQueueWorkers(ResourcingContext ctx) {
        String domain = (String) ctx.getArgs().get("domain");
        WorkQueueContainer adminContainer = WorkQueueContainer.GetContext(GlobalContext.WORKQUEUE_ADMIN_PREFIX + domain);
        Set<WorkitemContext> worklisted = adminContainer.GetQueue(WorkQueueType.WORKLISTED).GetQueueAsSet();
        HashSet<String> retParticipantIds = new HashSet<>();
        for (WorkitemContext workitem : worklisted) {
            if (workitem.getEntity().getResourceStatus().equals(WorkitemResourcingStatusType.Offered.name())) {
                retParticipantIds.add(workitem.getEntity().getWid());
            }
        }
        return SerializationUtil.JsonSerialization(retParticipantIds);
    }

    /**
     * Get all workers with a non-empty allocated work queue in a domain.
     * @param ctx rs context
     * @return worker gid in a list
     */
    public static String GetNotEmptyAllocatedQueueWorkers(ResourcingContext ctx) {
        String domain = (String) ctx.getArgs().get("domain");
        WorkQueueContainer adminContainer = WorkQueueContainer.GetContext(GlobalContext.WORKQUEUE_ADMIN_PREFIX + domain);
        Set<WorkitemContext> worklisted = adminContainer.GetQueue(WorkQueueType.WORKLISTED).GetQueueAsSet();
        HashSet<String> retParticipantIds = new HashSet<>();
        for (WorkitemContext workitem : worklisted) {
            if (workitem.getEntity().getResourceStatus().equals(WorkitemResourcingStatusType.Allocated.name())) {
                retParticipantIds.add(workitem.getEntity().getWid());
            }
        }
        return SerializationUtil.JsonSerialization(retParticipantIds);
    }

    /**
     * Get all workers with a non-empty allocated or allocated work queue in a domain.
     * @param ctx rs context
     * @return worker gid in a list
     */
    public static String GetNotEmptyOfferedAllocatedQueueWorkers(ResourcingContext ctx) {
        String domain = (String) ctx.getArgs().get("domain");
        WorkQueueContainer adminContainer = WorkQueueContainer.GetContext(GlobalContext.WORKQUEUE_ADMIN_PREFIX + domain);
        Set<WorkitemContext> worklisted = adminContainer.GetQueue(WorkQueueType.WORKLISTED).GetQueueAsSet();
        HashSet<String> retParticipantIds = new HashSet<>();
        for (WorkitemContext workitem : worklisted) {
            if (workitem.getEntity().getResourceStatus().equals(WorkitemResourcingStatusType.Offered.name()) ||
                workitem.getEntity().getResourceStatus().equals(WorkitemResourcingStatusType.Allocated.name())) {
                retParticipantIds.add(workitem.getEntity().getWid());
            }
        }
        return SerializationUtil.JsonSerialization(retParticipantIds);
    }
}
