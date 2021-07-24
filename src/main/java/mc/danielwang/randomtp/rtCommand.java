package mc.danielwang.randomtp;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class rtCommand implements CommandExecutor {

    public static Material[] blocked_Material = {
            Material.LAVA,
            Material.FIRE,
            Material.TNT,
            Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
            Material.ACACIA_PRESSURE_PLATE,
            Material.BIRCH_PRESSURE_PLATE,
            Material.CRIMSON_PRESSURE_PLATE,
            Material.DARK_OAK_PRESSURE_PLATE,
            Material.HEAVY_WEIGHTED_PRESSURE_PLATE,
            Material.JUNGLE_PRESSURE_PLATE,
            Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
            Material.OAK_PRESSURE_PLATE,
            Material.SPRUCE_PRESSURE_PLATE,
            Material.STONE_PRESSURE_PLATE,
            Material.WARPED_PRESSURE_PLATE,
            Material.WATER,
            Material.ACACIA_LEAVES,
            Material.BIRCH_LEAVES,
            Material.DARK_OAK_LEAVES,
            Material.JUNGLE_LEAVES,
            Material.OAK_LEAVES,
            Material.SPRUCE_LEAVES
    };

    public static int world_border = 100000;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            Location randomLocation = findSafeLocation(player);

            player.teleport(randomLocation);

            player.sendTitle(ChatColor.GOLD + "隨 機 傳 送 ！",ChatColor.WHITE + "X:" + ChatColor.YELLOW + randomLocation.getX() + " " +
                    ChatColor.WHITE + "Y:" + ChatColor.YELLOW + randomLocation.getY() + " " +
                    ChatColor.WHITE + "Z:" + ChatColor.YELLOW + randomLocation.getZ(),10,70,20);

        }else{
            sender.sendMessage(ChatColor.RED + "Console can't use this command!");
        }


        return true;
    }
    public static Location generateLocation(Player player){
        Random random = new Random();
        int x = random.nextInt(world_border);
        int y = 150;
        int z = random.nextInt(world_border);
        Location randomLocation = new Location(player.getWorld(), x, y, z);
        randomLocation.setY(randomLocation.getWorld().getHighestBlockYAt(randomLocation));

        return randomLocation;
    }
    public static Location findSafeLocation(Player player){

        Location randomLocation = generateLocation(player);

        while (!isLocationSafe(randomLocation)){
            //Keep looking for a safe location
            randomLocation = generateLocation(player);
        }
        randomLocation.setY(randomLocation.getY() + 1);
        return randomLocation;
    }
    public static boolean isLocationSafe(Location location){

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //Get instances of the blocks around where the player would spawn
        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        //Check to see if the surroundings are safe or not
        if (((Arrays.asList(blocked_Material).contains(block.getType()))) || ((Arrays.asList(blocked_Material).contains(block.getType()))) || ((Arrays.asList(blocked_Material).contains(block.getType())))){
            return false;
        }else{
            return true;
        }
    }
}
