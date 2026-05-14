package rip.ysm.gui.components.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import rip.ysm.gui.OptionGroup;

import java.util.function.Consumer;

public class TabButton extends AbstractWidget {

    private final OptionGroup group;
    private final Consumer<OptionGroup> onSelect;
    private boolean selected;

    public TabButton(int x, int y, int width, int height, OptionGroup group, Consumer<OptionGroup> onSelect) {
        super(x, y, width, height, group.getTitle());
        this.group = group;
        this.onSelect = onSelect;
    }

    public OptionGroup getGroup() {
        return group;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    protected void renderWidget(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        int bg = selected ? 0x90171717 : (isHovered() ? 0x900B0B0B : 0x90000000);
        g.fill(getX(), getY(), getX() + width, getY() + height, bg);
        if (selected) {
            g.fill(getX(), getY(), getX() + 1, getY() + height, -1);
        }
        int textY = getY() + (height - 8) / 2;
        g.drawString(Minecraft.getInstance().font, getMessage(), getX() + 10, textY, 0xFFFFFFFF, false);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        onSelect.accept(group);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput out) {
        defaultButtonNarrationText(out);
    }
}
