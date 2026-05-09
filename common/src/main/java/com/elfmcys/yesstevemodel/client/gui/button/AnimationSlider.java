package com.elfmcys.yesstevemodel.client.gui.button;

import com.elfmcys.yesstevemodel.YesSteveModel;
import com.elfmcys.yesstevemodel.client.gui.ISpecialWidget;
import com.elfmcys.yesstevemodel.config.ServerConfig;
import com.elfmcys.yesstevemodel.geckolib3.core.AnimatableEntity;
import com.elfmcys.yesstevemodel.geckolib3.resource.GeckoLibCache;
import com.elfmcys.yesstevemodel.molang.parser.ParseException;
import com.elfmcys.yesstevemodel.network.NetworkHandler;
import com.elfmcys.yesstevemodel.network.message.C2SRequestExecuteMolangPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.text.DecimalFormat;

public class AnimationSlider extends RangedSliderWidget implements ISpecialWidget {

    private static final ResourceLocation ROULETTE_TEXTURE = new ResourceLocation(YesSteveModel.MOD_ID, "texture/roulette.png");

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    private final AnimatableEntity<?> model;

    private final String controllerName;

    public AnimationSlider(int x, int y, Component component, double currentValue, AnimatableEntity<?> animatableEntity, String controllerName, double stepSize, double minValue, double maxValue) {
        super(x, y, 115, 15, component, Component.empty(), minValue, maxValue, currentValue, stepSize, 0, true);
        this.model = animatableEntity;
        this.controllerName = controllerName;
    }

    public void applyValue() {
        try {
            String str = this.controllerName + "=" + getValue();
            this.model.executeExpression(GeckoLibCache.parseSimpleExpression(str), true, false, null);
            if (!GeckoLibCache.isRoamingVariableAssignment(str) && NetworkHandler.isClientConnected() && !ServerConfig.LOW_BANDWIDTH_USAGE.get().booleanValue()) {
                NetworkHandler.sendToServer(new C2SRequestExecuteMolangPacket(str, this.model.getEntity().getId()));
            }
        } catch (ParseException e) {
            YesSteveModel.LOGGER.error(e);
        }
    }

    public String getValueString() {
        return DECIMAL_FORMAT.format(getValue());
    }

    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        guiGraphics.blit(ROULETTE_TEXTURE, getX(), getY(), 0, textureBaseY() + 24, this.width, this.height, 200, 15);
        guiGraphics.blit(ROULETTE_TEXTURE, getX() + ((int) (this.value * (this.width - 8))), getY(), 0, handleTextureBaseY() + 24, 8, this.height, 200, 15);
        int color = 16777215 | (Mth.ceil(this.alpha * 255.0f) << 24);
        guiGraphics.drawCenteredString(minecraft.font, this.getMessage(), getX() + this.width / 2, getY() + (this.height - 8) / 2, color);
    }
}