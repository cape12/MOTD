package io.github.cape12.motd.plugin.listener

import io.github.cape12.motd.plugin.MotdPlugin
import com.destroystokyo.paper.event.server.PaperServerListPingEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class MotdListener(private val plugin: MotdPlugin) : Listener {

    private val miniMessage = MiniMessage.miniMessage()

    @EventHandler
    fun onPing(e: PaperServerListPingEvent) {

        if (plugin.config.getBoolean("use-server-icon")) {
            plugin.serverIcon?.let {
                e.serverIcon = it
            }
        } else {
            e.serverIcon = null // 아이콘 사용 안 하도록 설정
        }

        if (plugin.config.getBoolean("use-motd")) {
            val motdLines = plugin.config.getStringList("motd").takeIf { it.isNotEmpty() }
                ?: listOf("<gray>기본 MOTD입니다.")
            val component = Component.join(
                Component.newline(),
                motdLines.map { miniMessage.deserialize(it) }
            )
            e.motd(component)
        }



        val customMaxPlayers = plugin.config.getInt("max-players", e.maxPlayers)
        e.maxPlayers = customMaxPlayers
    }
}
