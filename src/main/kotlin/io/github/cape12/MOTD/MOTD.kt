package io.github.cape12.MOTD

import io.github.cape12.MOTD.command.MOTDCommand
import io.github.cape12.MOTD.listener.MOTDListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.CachedServerIcon
import java.io.File
import javax.imageio.ImageIO

class MOTD : JavaPlugin() {

    var serverIcon: CachedServerIcon? = null
        private set

    override fun onEnable() {
        saveDefaultConfig()
        logger.info("MOTD 플러그인이 활성화 되었습니다!")

        setupIconFile()
        if (config.getBoolean("use-server-icon")) {
            loadServerIcon()
        }

        server.pluginManager.registerEvents(MOTDListener(this), this)
        getCommand("motd")?.setExecutor(MOTDCommand(this))
    }

    override fun onDisable() {
        logger.info("MOTD 플러그인이 비활성화 되었습니다!")
    }

    fun setupIconFile() {
        val iconDir = File(dataFolder, "server-icon")
        val iconFile = File(iconDir, "server-icon.png")
        val copiedFlag = File(iconDir, ".icon_copied")

        if (copiedFlag.exists()) {
            return
        }

        if (!iconFile.exists()) {
            val input = getResource("server-icon/server-icon.png")
            if (input != null) {
                iconDir.mkdirs()
                iconFile.outputStream().use { output -> input.copyTo(output) }
                copiedFlag.createNewFile()
            }
        } else {
            copiedFlag.createNewFile()
        }
    }

    fun loadServerIcon() {
        val iconPath = config.getString("server-icon-path") ?: "server-icon.png"
        val iconFile = File(dataFolder, "server-icon/$iconPath")

        if (!config.getBoolean("use-server-icon")) {
            serverIcon = null
            return
        }

        if (iconFile.exists()) {
            try {
                val image = ImageIO.read(iconFile)
                serverIcon = Bukkit.loadServerIcon(image)
            } catch (_: Exception) {

            }
        } else {
            logger.warning("서버 아이콘 파일을 찾을 수 없습니다: server-icon/$iconPath")
        }
    }
}