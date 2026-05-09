package rip.ysm.api.network.forge;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import rip.ysm.api.network.PacketContext;
import rip.ysm.api.network.PacketDirection;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public final class YSMChannelImpl {

    private static SimpleChannel channel;

    private YSMChannelImpl() {
    }

    public static void init(ResourceLocation channelId, String version) {
        channel = NetworkRegistry.newSimpleChannel(channelId, () -> version, str -> true, str -> true);
    }

    public static <T> void register(int discriminator, Class<T> type, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, PacketContext> handler, PacketDirection direction) {
        channel.registerMessage(discriminator, type, encoder, decoder,
                (msg, ctxSupplier) -> {
                    handler.accept(msg, new PacketContextImpl(ctxSupplier));
                    ctxSupplier.get().setPacketHandled(true);
                },
                Optional.of(toForge(direction))
        );
    }

    public static void sendToServer(Object packet) {
        channel.sendToServer(packet);
    }

    public static void sendToClientPlayer(Object packet, ServerPlayer player) {
        channel.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    public static void sendToAll(Object packet) {
        channel.send(PacketDistributor.ALL.noArg(), packet);
    }

    public static void sendToTrackingEntity(Object packet, Entity entity) {
        channel.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), packet);
    }

    public static void sendToTrackingEntityAndSelf(Object packet, Player player) {
        channel.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), packet);
    }

    public static Packet<?> toClientboundPacket(Object packet) {
        return channel.toVanillaPacket(packet, NetworkDirection.PLAY_TO_CLIENT);
    }

    public static Packet<?> toServerboundPacket(Object packet) {
        return channel.toVanillaPacket(packet, NetworkDirection.PLAY_TO_SERVER);
    }

    private static NetworkDirection toForge(PacketDirection direction) {
        return switch (direction) {
            case PLAY_TO_CLIENT -> NetworkDirection.PLAY_TO_CLIENT;
            case PLAY_TO_SERVER -> NetworkDirection.PLAY_TO_SERVER;
        };
    }
}
