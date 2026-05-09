package com.elfmcys.yesstevemodel.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.lwjgl.glfw.GLFW;

import java.text.DecimalFormat;

public class RangedSliderWidget extends AbstractSliderButton {

    private static final ResourceLocation SLIDER_TEXTURE = new ResourceLocation("textures/gui/slider.png");

    protected Component prefix;
    protected Component suffix;

    protected double minValue;
    protected double maxValue;
    protected double stepSize;
    protected boolean drawString;

    private final DecimalFormat format;

    public RangedSliderWidget(int x, int y, int width, int height, Component prefix, Component suffix, double minValue, double maxValue, double currentValue, double stepSize, int precision, boolean drawString) {
        super(x, y, width, height, Component.empty(), 0D);
        this.prefix = prefix;
        this.suffix = suffix;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.stepSize = Math.abs(stepSize);
        this.value = this.snapToNearest((currentValue - minValue) / (maxValue - minValue));
        this.drawString = drawString;
        if (stepSize == 0D) {
            int p = Math.min(precision, 4);
            StringBuilder builder = new StringBuilder("0");
            if (p > 0) builder.append('.');
            while (p-- > 0) builder.append('0');
            this.format = new DecimalFormat(builder.toString());
        } else if (Mth.equal(this.stepSize, Math.floor(this.stepSize))) {
            this.format = new DecimalFormat("0");
        } else {
            this.format = new DecimalFormat(Double.toString(this.stepSize).replaceAll("\\d", "0"));
        }
        this.updateMessage();
    }

    public double getValue() {
        return this.value * (maxValue - minValue) + minValue;
    }

    public long getValueLong() {
        return Math.round(this.getValue());
    }

    public int getValueInt() {
        return (int) this.getValueLong();
    }

    public void setValue(double newValue) {
        this.value = this.snapToNearest((newValue - this.minValue) / (this.maxValue - this.minValue));
        this.updateMessage();
    }

    public String getValueString() {
        return this.format.format(this.getValue());
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.setValueFromMouse(mouseX);
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        super.onDrag(mouseX, mouseY, dragX, dragY);
        this.setValueFromMouse(mouseX);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean leftDir = keyCode == GLFW.GLFW_KEY_LEFT;
        if (leftDir || keyCode == GLFW.GLFW_KEY_RIGHT) {
            if (this.minValue > this.maxValue) leftDir = !leftDir;
            float dir = leftDir ? -1F : 1F;
            if (stepSize <= 0D) {
                this.setSliderValue(this.value + (dir / (this.width - 8)));
            } else {
                this.setValue(this.getValue() + dir * this.stepSize);
            }
        }
        return false;
    }

    private void setValueFromMouse(double mouseX) {
        this.setSliderValue((mouseX - (this.getX() + 4)) / (this.width - 8));
    }

    private void setSliderValue(double percent) {
        double oldValue = this.value;
        this.value = this.snapToNearest(percent);
        if (!Mth.equal(oldValue, this.value)) {
            this.applyValue();
        }
        this.updateMessage();
    }

    private double snapToNearest(double percent) {
        if (stepSize <= 0D) {
            return Mth.clamp(percent, 0D, 1D);
        }
        double snapped = Mth.lerp(Mth.clamp(percent, 0D, 1D), this.minValue, this.maxValue);
        snapped = stepSize * Math.round(snapped / stepSize);
        if (this.minValue > this.maxValue) {
            snapped = Mth.clamp(snapped, this.maxValue, this.minValue);
        } else {
            snapped = Mth.clamp(snapped, this.minValue, this.maxValue);
        }
        return Mth.inverseLerp(snapped, this.minValue, this.maxValue);
    }

    @Override
    protected void updateMessage() {
        if (this.drawString) {
            this.setMessage(Component.literal("").append(prefix).append(this.getValueString()).append(suffix));
        } else {
            this.setMessage(Component.empty());
        }
    }

    @Override
    protected void applyValue() {
    }

    protected int textureBaseY() {
        return 46 + (this.isHovered() ? 20 : 0);
    }

    protected int handleTextureBaseY() {
        return 46 + 20 + (this.isHovered() ? 20 : 0);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft mc = Minecraft.getInstance();
        guiGraphics.blit(SLIDER_TEXTURE, this.getX(), this.getY(), 0, textureBaseY(), this.width, this.height, 200, 20);
        guiGraphics.blit(SLIDER_TEXTURE, this.getX() + (int) (this.value * (double) (this.width - 8)), this.getY(), 0, handleTextureBaseY(), 8, this.height, 200, 20);
        int color = 16777215 | Mth.ceil(this.alpha * 255.0F) << 24;
        guiGraphics.drawCenteredString(mc.font, this.getMessage(), this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, color);
    }
}
