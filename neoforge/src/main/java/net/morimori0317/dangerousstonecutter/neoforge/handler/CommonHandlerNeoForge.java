package net.morimori0317.dangerousstonecutter.neoforge.handler;

import net.morimori0317.dangerousstonecutter.DangerousStoneCutter;
import net.morimori0317.dangerousstonecutter.neoforge.networking.DSCPacketsNeoForge;
import net.morimori0317.dangerousstonecutter.networking.DSCPackets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = DangerousStoneCutter.MODID)
public class CommonHandlerNeoForge {
    @SubscribeEvent
    public static void registerPayload(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(DSCPacketsNeoForge.PROTOCOL_VERSION);
        registrar.playBidirectional(DSCPackets.BLOOD_PARTICLE_TYPE, DSCPackets.BLOOD_PARTICLE_CODEC,
                new DirectionalPayloadHandler<>(DSCPacketsNeoForge::handleBloodParticleDataOnClient, DSCPacketsNeoForge::handleBloodParticleDataOnServer));
    }
}
