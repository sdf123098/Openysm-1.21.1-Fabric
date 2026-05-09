package rip.ysm.compat.touhoulittlemaid.fabric;

import com.elfmcys.yesstevemodel.client.animation.molang.TLMBinding;
import com.elfmcys.yesstevemodel.client.entity.LivingAnimatable;
import com.elfmcys.yesstevemodel.client.model.PlayerModelBundle;
import com.elfmcys.yesstevemodel.geckolib3.core.event.predicate.AnimationEvent;
import com.elfmcys.yesstevemodel.geckolib3.core.enums.PlayState;
import com.elfmcys.yesstevemodel.client.model.ModelResourceBundle;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

public final class TouhouLittleMaidCompatImpl {

    private TouhouLittleMaidCompatImpl() {
    }

    public static boolean isLoaded() {
        return false;
    }

    public static boolean isMaidEntity(Entity entity) {
        return false;
    }

    public static boolean isMaidRideable(Entity entity) {
        return false;
    }

    public static boolean isSimplePlanesEntity(Entity entity) {
        return false;
    }

    public static boolean isImmersiveAircraftEntity(Entity entity) {
        return false;
    }

    public static boolean isMaidItem(Item item) {
        return false;
    }

    public static String getMaidEntityId(Entity entity) {
        return "";
    }

    public static boolean isMaidSitting(LivingEntity livingEntity) {
        return false;
    }

    public static void registerMaidAnimStates(TLMBinding tlmBinding) {
    }

    public static PlayState handleMaidInteraction(AnimationEvent<LivingAnimatable<?>> event, LivingEntity livingEntity, Entity entity) {
        return null;
    }

    public static boolean isMaidChatAvailable() {
        return false;
    }

    public static void openMaidChat() {
    }

    public static Object buildControllers(PlayerModelBundle modelBundle, ModelResourceBundle resourceBundle) {
        return null;
    }
}
