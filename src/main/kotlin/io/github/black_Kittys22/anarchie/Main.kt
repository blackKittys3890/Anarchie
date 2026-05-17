package io.github.black_Kittys22.anarchie

import org.bukkit.event.Listener
import com.comphenix.protocol.ProtocolLibrary
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener{

    override fun onEnable() {
        val protocolManager = ProtocolLibrary.getProtocolManager()
        Tablist(this, protocolManager)

        logger.info("[ANARCHIE] I'm awake :)")
    }

    override fun onDisable() {
        logger.info("[ANARCHIE] I'm dead :(")
    }

}