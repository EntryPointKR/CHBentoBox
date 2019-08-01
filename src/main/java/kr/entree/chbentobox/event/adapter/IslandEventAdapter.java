package kr.entree.chbentobox.event.adapter;

import com.laytonsmith.core.events.BindableEvent;
import world.bentobox.bentobox.api.events.island.IslandEvent;

/**
 * Created by JunHyeong Lim on 7/30/2019
 */
public class IslandEventAdapter implements BindableEvent {
    private final IslandEvent handle;

    public IslandEventAdapter(IslandEvent handle) {
        this.handle = handle;
    }

    public IslandEvent getEvent() {
        return handle;
    }

    @Override
    public Object _GetObject() {
        return handle;
    }
}
