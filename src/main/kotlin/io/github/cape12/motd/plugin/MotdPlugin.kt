package io.github.cape12.motd.plugin

import io.github.cape12.motd.plugin.command.MotdCommand
import io.github.cape12.motd.plugin.listener.MotdListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.CachedServerIcon
import java.io.File
import javax.imageio.ImageIO

class MotdPlugin : JavaPlugin() {

    var serverIcon: CachedServerIcon? = null
        private set

    override fun onEnable() {
        saveDefaultConfig()
        logger.info("MOTD 플러그인이 활성화 되었습니다!")

        setupIconFile()
        if (config.getBoolean("use-server-icon")) {
            loadServerIcon()
        }

        server.pluginManager.registerEvents(MotdListener(this), this)
        getCommand("motd")?.setExecutor(MotdCommand(this))
    }

    override fun onDisable() {
        logger.info("MOTD 플러그인이 비활성화 되었습니다!")
    }

    // 기본 서버 아이콘 복사 (딱 한 번만)
    fun setupIconFile() {
        val iconDir = File(dataFolder, "server-icon")
        val iconFile = File(iconDir, "server-icon.png")
        val copiedFlag = File(iconDir, ".icon_copied")

        if (copiedFlag.exists()) {
            logger.info("기본 아이콘은 이미 복사되었습니다.")
            return
        }

        if (!iconFile.exists()) {
            val input = getResource("server-icon/server-icon.png")
            if (input != null) {
                iconDir.mkdirs()
                iconFile.outputStream().use { output -> input.copyTo(output) }
                copiedFlag.createNewFile()
                logger.info("기본 서버 아이콘을 plugins 폴더에 복사했습니다.")
            } else {
                logger.warning("리소스에서 기본 서버 아이콘을 찾을 수 없습니다.")
            }
        } else {
            logger.info("기본 서버 아이콘이 이미 존재하므로 복사하지 않습니다.")
            copiedFlag.createNewFile()
        }
    }

    // config의 server-icon-path 값을 기준으로 서버 아이콘 로드
    fun loadServerIcon() {
        val iconPath = config.getString("server-icon-path") ?: "server-icon.png"
        val iconFile = File(dataFolder, "server-icon/$iconPath")

        if (!config.getBoolean("use-server-icon")) {
            serverIcon = null
            logger.info("서버 아이콘 비활성화 설정이므로 사용하지 않습니다.")
            return
        }

        if (iconFile.exists()) {
            try {
                val image = ImageIO.read(iconFile)
                serverIcon = Bukkit.loadServerIcon(image)
                logger.info("서버 아이콘 로드 성공: server-icon/$iconPath")
            } catch (e: Exception) {
                logger.warning("서버 아이콘 로드 실패: ${e.message}")
            }
        } else {
            logger.warning("서버 아이콘 파일을 찾을 수 없습니다: server-icon/$iconPath")
        }
    }
}
