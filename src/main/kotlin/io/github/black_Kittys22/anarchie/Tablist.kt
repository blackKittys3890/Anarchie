package io.github.black_Kittys22.anarchie

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import org.bukkit.plugin.java.JavaPlugin

class Tablist(plugin: JavaPlugin, private val protocolManager: ProtocolManager) {

    companion object {
        const val PERMISSION = "kittys.anarchie.tablist"
    }

    init {
        protocolManager.addPacketListener(object: PacketAdapter(
            plugin, PacketType.Play.Server.PLAYER_INFO
        ) {
            override fun onPacketSending(event: PacketEvent) {
                val player = event.player

                if (!player.isOp && !player.hasPermission(PERMISSION)) {
                    event.isCancelled = true
                }
            }
        })
    }
}