package me.fredthedoggy.boxedeyes;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EnderSignal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;

public class EyeListener implements Listener {

    private final BoxedEyes boxedEyes;

    public EyeListener(BoxedEyes boxedEyes) {
        this.boxedEyes = boxedEyes;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return;
        if (event.getItem() == null)
            return;
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.END_PORTAL_FRAME)))
            return;
        if (event.getItem() != null && !event.getItem().getType().equals(Material.ENDER_EYE)) return;
        Player player = event.getPlayer();
        if (!BentoBox.getInstance().getIslands().getIslandAt(player.getLocation()).isPresent()) return;
        Island island = BentoBox.getInstance().getIslands().getIslandAt(player.getLocation()).get();
        if (island.getCenter() == null) return;
        if (player.getGameMode() == GameMode.SPECTATOR) return;
        if (player.getGameMode() != GameMode.CREATIVE) {
            if (event.getItem().getAmount() > 1) {
                event.getItem().setAmount(event.getItem().getAmount() - 1);
            } else {
                event.getItem().setAmount(0);
            }
        }
        EnderSignal enderEye = (EnderSignal) player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_SIGNAL);
        enderEye.setTargetLocation(new Location(player.getWorld(), island.getX() + boxedEyes.config.getDouble("EyeLocation.X-Offset"), boxedEyes.config.getDouble("EyeLocation.Y-Location"), island.getZ() + boxedEyes.config.getDouble("EyeLocation.Z-Offset")));
    }
}