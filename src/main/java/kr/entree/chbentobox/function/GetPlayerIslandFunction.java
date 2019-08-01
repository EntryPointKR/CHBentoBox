package kr.entree.chbentobox.function;

import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRELengthException;
import com.laytonsmith.core.exceptions.CRE.CREPlayerOfflineException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chbentobox.CHBentoBox;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;

import java.util.UUID;

/**
 * Created by JunHyeong Lim on 7/30/2019
 */
@api
public class GetPlayerIslandFunction extends CHBentoBoxFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CREPlayerOfflineException.class,
                CRELengthException.class
        };
    }

    @Override
    public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
        UUID id;
        if (args.length >= 1) {
            String specifier = args[0].val();
            if (specifier.length() == 32 || specifier.length() == 36) {
                id = Static.GetUUID(specifier, t);
            } else {
                id = Static.GetPlayer(specifier, t).getUniqueId();
            }
        } else {
            id = Static.getPlayer(env, t).getUniqueId();
        }
        User user = User.getInstance(id);
        Island island = BentoBox.getInstance().getIslands().getIsland(user.getWorld(), user);
        if (island != null) {
            return CHBentoBox.parseIsland(island, t);
        }
        return CNull.NULL;
    }

    @Override
    public String getName() {
        return "pget_island";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{0, 1};
    }

    @Override
    public String docs() {
        return "array {[puuid]} Returns island data.";
    }
}
