package kr.entree.chbentobox.function;

import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREFormatException;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import com.laytonsmith.core.exceptions.CRE.CREInvalidWorldException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import kr.entree.chbentobox.CHBentoBox;
import org.bukkit.Location;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.IslandsManager;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by JunHyeong Lim on 7/30/2019
 */
@api
public class GetIslandFunction extends CHBentoBoxFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
                CREFormatException.class,
                CREInvalidWorldException.class
        };
    }

    @Override
    public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
        Mixed id = args[0];
        IslandsManager manager = BentoBox.getInstance().getIslands();
        Optional<Island> islandOpt;
        if (id instanceof CArray) {
            MCLocation location = ObjectGenerator.GetGenerator().location(id, null, t);
            islandOpt = manager.getIslandAt((Location) location.getHandle());
        } else {
            islandOpt = manager.getIslandById(id.val());
        }
        return islandOpt.<Mixed>map(is -> CHBentoBox.parseIsland(is, t))
                .orElse(CNull.NULL);
    }

    @Override
    public String getName() {
        return "get_island";
    }

    @Override
    public Integer[] numArgs() {
        return new Integer[]{1};
    }

    @Override
    public String docs() {
        return "array {[player]} Returns island data.";
    }
}
