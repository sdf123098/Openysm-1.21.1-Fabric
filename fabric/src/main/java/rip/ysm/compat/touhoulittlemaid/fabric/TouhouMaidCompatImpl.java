package rip.ysm.compat.touhoulittlemaid.fabric;

import com.elfmcys.yesstevemodel.network.message.FeedbackData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;

public final class TouhouMaidCompatImpl {

    private TouhouMaidCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static void init() {
    }

    public static boolean isMaidEntity(Entity entity) {
        return false;
    }

    public static void handleProjectileOwner(Projectile projectile, Entity entity) {
    }

    public static void registerAnimationRoulette(Entity entity, String str, int i) {
    }

    public static void applyFeedback(Entity entity, FeedbackData message) {
    }

    public static void playMaidAnimation(Entity entity, String str) {
    }
}
