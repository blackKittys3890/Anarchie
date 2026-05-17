package io.github.black_Kittys22.anarchie

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener{

    override fun onEnable() {
        logger.info("[ANARCHIE] I'm awake :)")
    }

    override fun onDisable() {
        logger.info("[ANARCHIE] I'm dead :(")
    }

}