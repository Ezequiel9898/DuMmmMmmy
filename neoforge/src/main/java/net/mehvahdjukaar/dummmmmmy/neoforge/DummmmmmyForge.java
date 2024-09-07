package net.mehvahdjukaar.dummmmmmy.neoforge;

import net.mehvahdjukaar.dummmmmmy.Dummmmmmy;
import net.mehvahdjukaar.dummmmmmy.common.ModEvents;
import net.minecraft.world.entity.MobSpawnType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.player.CriticalHitEvent;

/**
 * Author: MehVahdJukaar
 */
@Mod(Dummmmmmy.MOD_ID)
public class DummmmmmyForge {

    public DummmmmmyForge() {
        Dummmmmmy.init();
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityCriticalHit(CriticalHitEvent event) {
        float mod = event.getDamageModifier();
        if (mod > 1) {
            ModEvents.onEntityCriticalHit(event.getEntity(), event.getTarget(), event.getDamageModifier());
        }
    }

    //prevents them from spawning
    @SubscribeEvent
    public void onCheckSpawn(FinalizeSpawnEvent event) {
        if (event.getSpawnType() == MobSpawnType.NATURAL && ModEvents.onCheckSpawn(event.getEntity(), event.getLevel())) {
            event.setSpawnCancelled(true);
        }
    }

    //add goal
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinLevelEvent event) {
        ModEvents.onEntityJoinWorld(event.getEntity());
    }
}
