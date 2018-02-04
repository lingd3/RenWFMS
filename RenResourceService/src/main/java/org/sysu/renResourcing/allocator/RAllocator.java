package org.sysu.renResourcing.allocator;

import org.sysu.renResourcing.basic.RSelector;
import org.sysu.renResourcing.context.ParticipantContext;
import org.sysu.renResourcing.context.WorkitemContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Author: Rinkako
 * Date  : 2017/11/15
 * Usage : Base allocator for all implemented allocators.
 *         Allocator is used to choose a participant to handle task from candidate set.
 */
public abstract class RAllocator extends RSelector implements Serializable {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a new allocator.
     * @param id unique id for selector fetching
     * @param type type name string
     * @param description selector description text
     * @param args parameter dictionary in HashMap
     */
    public RAllocator(String id, String type, String description, HashMap<String, String> args) {
        super(id, type, description, args);
    }

    /**
     * Perform allocation on the candidate set.
     * @return selected participant
     */
    public abstract ParticipantContext PerformAllocate(HashSet<ParticipantContext> candidateSet, WorkitemContext context);
}
