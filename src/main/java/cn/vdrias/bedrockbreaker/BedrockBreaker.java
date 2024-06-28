package cn.vdrias.bedrockbreaker;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BedrockBreaker extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("bedrockbreaker").setExecutor(new BreakCommand());
    }

    @Override
    public void onDisable() {
        // 插件禁用时执行的代码
    }

    public class BreakCommand implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("bedrockbreaker.break")) {
                    Block targetBlock = player.getTargetBlock(null, 5); // 获取玩家准星所指向的方块（范围5格）
                    if (targetBlock != null && targetBlock.getType() == Material.BEDROCK) {
                        targetBlock.setType(Material.AIR); // 将基岩替换为空气
                        player.sendMessage("基岩已破坏!");
                        return true;
                    } else {
                        player.sendMessage("你没有指向基岩!");
                        return false;
                    }
                } else {
                    player.sendMessage("你没有权限使用此命令!");
                    return false;
                }
            } else {
                sender.sendMessage("该命令只能由玩家执行!");
                return false;
            }
        }
    }
}
