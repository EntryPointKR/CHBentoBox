package kr.entree.chbentobox.processor;

import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import kr.entree.chbentobox.event.CHBentoBoxIslandEvent;
import kr.entree.chbentobox.event.adapter.IslandEventAdapter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import world.bentobox.bentobox.api.events.island.IslandEvent;

/**
 * Created by JunHyeong Lim on 7/30/2019
 */
public class BentoBoxEventNotifier implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onIslandEvent(IslandEvent e) {
        IslandEventAdapter event = new IslandEventAdapter(e);
        EventUtils.TriggerListener(Driver.EXTENSION, CHBentoBoxIslandEvent.NAME, event);
    }
}
