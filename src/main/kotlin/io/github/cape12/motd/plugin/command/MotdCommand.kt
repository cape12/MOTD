package io.github.cape12.motd.plugin.command

import io.github.cape12.motd.plugin.MotdPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class MotdCommand(private val plugin: MotdPlugin) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isNotEmpty() && args[0].equals("reload", ignoreCase = true)) {
            plugin.reloadConfig()
            plugin.loadServerIcon()  // 아이콘 복사 다시 시도

            sender.sendMessage("§aMOTD 설정과 서버 아이콘이 리로드되었습니다!")
            return true
        }
        sender.sendMessage("§c잘못된 명령어입니다. /motd reload")
        return false
    }
}