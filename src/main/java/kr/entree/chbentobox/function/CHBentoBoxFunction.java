package kr.entree.chbentobox.function;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.functions.AbstractFunction;

/**
 * Created by JunHyeong Lim on 7/30/2019
 */
public abstract class CHBentoBoxFunction extends AbstractFunction {
    @Override
    public boolean isRestricted() {
        return true;
    }

    @Override
    public Boolean runAsync() {
        return Boolean.FALSE;
    }

    @Override
    public Version since() {
        return MSVersion.LATEST;
    }
}
