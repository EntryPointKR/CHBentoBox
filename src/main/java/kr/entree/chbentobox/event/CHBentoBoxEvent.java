package kr.entree.chbentobox.event;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.Driver;

/**
 * Created by JunHyeong Lim on 7/30/2019
 */
public abstract class CHBentoBoxEvent extends AbstractEvent {
    @Override
    public Version since() {
        return MSVersion.LATEST;
    }

    @Override
    public Driver driver() {
        return Driver.EXTENSION;
    }
}
