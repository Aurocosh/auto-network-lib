package aurocosh.divinefavor.common.requirements.cost.costs;

import aurocosh.divinefavor.common.lib.IInitiatable;
import aurocosh.divinefavor.common.spell.base.SpellContext;

public abstract class Cost implements IInitiatable {
    @Override
    public void init() {}

    public abstract boolean canClaim(SpellContext context);
    public abstract boolean claim(SpellContext context);
    public abstract String getUsageInfo(SpellContext context);
    public abstract int getUseCount(SpellContext context);
}