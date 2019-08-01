package kr.entree.chbentobox.event;

import com.laytonsmith.abstraction.StaticLayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chbentobox.event.adapter.IslandEventAdapter;
import world.bentobox.bentobox.api.events.island.IslandEvent;
import world.bentobox.bentobox.database.objects.Island;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by JunHyeong Lim on 7/30/2019
 */
@api
public class CHBentoBoxIslandEvent extends CHBentoBoxEvent {
    public static final String NAME = "bentobox_island";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String docs() {
        return "{player: <macro>, reason: <string>}Fired when island updates";
    }

    @Override
    public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
        if (e instanceof IslandEventAdapter) {
            IslandEventAdapter adapter = (IslandEventAdapter) e;
            IslandEvent event = adapter.getEvent();
            Prefilters.match(prefilter, "player", event.getReason().name(), Prefilters.PrefilterType.MACRO);
            Prefilters.match(prefilter, "reason", event.getReason().name(), Prefilters.PrefilterType.STRING_MATCH);
            return true;
        }
        return false;
    }

    @Override
    public BindableEvent convert(CArray manualObject, Target t) {
        return null;
    }

    @Override
    public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
        Target t = Target.UNKNOWN;
        if (e instanceof IslandEventAdapter) {
            Map<String, Mixed> map = new HashMap<>();
            IslandEventAdapter adapter = ((IslandEventAdapter) e);
            IslandEvent event = adapter.getEvent();
            UUID uuid = event.getPlayerUUID();
            Island island = event.getIsland();
            map.put("type", new CString(event.getReason().name(), t));
            map.put("player", uuid != null
                    ? new CString(uuid.toString(), t) : CNull.NULL);
            map.put("island", island != null && island.getUniqueId() != null
                    ? new CString(island.getUniqueId(), t) : CNull.NULL);
            return map;
        }
        throw new EventException();
    }

    @Override
    public boolean modifyEvent(String key, Mixed value, BindableEvent event) {
        return false;
    }
}
