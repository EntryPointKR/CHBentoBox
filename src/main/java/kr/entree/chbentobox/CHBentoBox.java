package kr.entree.chbentobox;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import com.mongodb.internal.connection.CommandHelper;
import kr.entree.chbentobox.processor.BentoBoxEventNotifier;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import world.bentobox.bentobox.database.objects.Island;

import java.util.Map;
import java.util.UUID;

/**
 * Created by JunHyeong Lim on 7/30/2019
 */
@MSExtension("CHBentoBox")
public class CHBentoBox extends AbstractExtension {
    private final BentoBoxEventNotifier notifier = new BentoBoxEventNotifier();

    public static CArray parseIsland(Island island, Target t) {
        CArray array = CArray.GetAssociativeArray(t);
        CArray members = new CArray(t);
        CArray bannedMembers = new CArray(t);
        for (Map.Entry<UUID, Integer> entry : island.getMembers().entrySet()) {
            members.push(new CString(entry.getKey().toString(), t), t);
        }
        for (UUID banned : island.getBanned()) {
            bannedMembers.push(new CString(banned.toString(), t), t);
        }
        array.set("owner", island.getOwner() != null
                ? new CString(island.getOwner().toString(), t) : CNull.NULL, t);
        array.set("name", island.getName(), t);
        array.set("members", members, t);
        array.set("banned", bannedMembers, t);
        array.set("created", new CInt(island.getCreatedDate(), t), t);
        return array;
    }

    @Override
    public Version getVersion() {
        return new SimpleVersion(1, 0, 0);
    }

    @Override
    public void onStartup() {
        HandlerList.unregisterAll(notifier);
        Bukkit.getPluginManager().registerEvents(notifier, CommandHelperPlugin.self);
    }

    @Override
    public void onShutdown() {
        HandlerList.unregisterAll(notifier);
    }
}
