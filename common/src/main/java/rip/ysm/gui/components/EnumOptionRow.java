package rip.ysm.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import rip.ysm.gui.Option;
import rip.ysm.gui.OptionRow;

public class EnumOptionRow<E extends Enum<E>> extends OptionRow<E> {
    private final E[] values;

    public EnumOptionRow(int x, int y, int width, int height, Option<E> option, E[] values) {
        super(x, y, width, height, option);
        this.values = values;
    }

    @Override
    protected int controlWidth() {
        return 140;
    }

    @Override
    protected void renderControl(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        int cx = controlX();
        int cy = controlY();
        int cw = controlWidth();
        int ch = controlHeight();
        boolean hover = isMouseOverControl(mouseX, mouseY);

        g.fill(cx, cy, cx + cw, cy + ch, blendBg(hover, 0x3EC8C8C8));

        Component text = Component.literal(option.get().name());
        int tw = Minecraft.getInstance().font.width(text);
        g.drawString(Minecraft.getInstance().font, text, cx + (cw - tw) / 2, cy + (ch - 8) / 2, 0xFFFFFFFF, false);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (!isMouseOverControl(mouseX, mouseY)) return;
        E current = option.get();
        int idx = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == current) {
                idx = i;
                break;
            }
        }
        option.setPending(values[(idx + 1) % values.length]);
    }
}
