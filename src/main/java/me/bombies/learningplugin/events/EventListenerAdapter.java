package me.bombies.learningplugin.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.HopperInventorySearchEvent;
import org.bukkit.event.player.*;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public interface EventListenerAdapter extends Listener {

    // Player Events
    @EventHandler
    default void onPlayerChat(AsyncPlayerChatEvent event) {
    }

    @EventHandler
    default void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {
    }

    @EventHandler
    default void onPlayerAnimation(PlayerAnimationEvent event) {
    }

    @EventHandler
    default void onPlayerBedEnter(PlayerBedEnterEvent event) {
    }

    @EventHandler
    default void onPlayerBedLeave(PlayerBedLeaveEvent event) {
    }

    @EventHandler
    default void onPlayerBucketEntity(PlayerBucketEntityEvent event) {
    }

    @EventHandler
    default void onPlayerBucket(PlayerBucketEvent event) {
    }

    @EventHandler
    default void onPlayerChangeMainHand(PlayerChangedMainHandEvent event) {
    }

    @EventHandler
    default void onPlayerWorldChange(PlayerChangedWorldEvent event) {
    }

    @EventHandler
    default void onPlayerChannel(PlayerChannelEvent event) {
    }

    @EventHandler
    default void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    }

    @EventHandler
    default void onPlayerCommandSend(PlayerCommandSendEvent event) {
    }

    @EventHandler
    default void onPlayerItemDrop(PlayerDropItemEvent event) {
    }

    @EventHandler
    default void onPlayerEditBook(PlayerEditBookEvent event) {
    }

    @EventHandler
    default void onPlayerEggThrow(PlayerEggThrowEvent event) {
    }

    @EventHandler
    default void onPlayerExpChange(PlayerExpChangeEvent event) {
    }

    @EventHandler
    default void onPlayerFish(PlayerFishEvent event) {
    }

    @EventHandler
    default void onPlayerGameModeChange(PlayerGameModeChangeEvent event) {
    }

    @EventHandler
    default void onPlayerHarvest(PlayerHarvestBlockEvent event) {
    }

    @EventHandler
    default void onPlayerHideEntity(PlayerHideEntityEvent event) {
    }

    @EventHandler
    default void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    }

    @EventHandler
    default void onPlayerInteract(PlayerInteractEvent event) {
    }

    @EventHandler
    default void onPlayerItemBreak(PlayerItemBreakEvent event) {
    }

    @EventHandler
    default void onPlayerItemConsume(PlayerItemConsumeEvent event) {
    }

    @EventHandler
    default void onPlayerItemDamage(PlayerItemDamageEvent event) {
    }

    @EventHandler
    default void onPlayerItemHeld(PlayerItemHeldEvent event) {
    }

    @EventHandler
    default void onPlayerItemMend(PlayerItemMendEvent event) {
    }

    @EventHandler
    default void onPlayerJoin(PlayerJoinEvent event) {
    }

    @EventHandler
    default void onPlayerKick(PlayerKickEvent event) {
    }

    @EventHandler
    default void onPlayerLevelChange(PlayerLevelChangeEvent event) {
    }

    @EventHandler
    default void onPlayerLocaleChange(PlayerLocaleChangeEvent event) {
    }

    @EventHandler
    default void onPlayerLogin(PlayerLoginEvent event) {
    }

    @EventHandler
    default void onPlayerMove(PlayerMoveEvent event) {
    }

    @EventHandler
    default void onPlayerQuit(PlayerQuitEvent event) {
    }

    @EventHandler
    default void onPlayerRecipeBookClick(PlayerRecipeBookClickEvent event) {
    }

    @EventHandler
    default void onPlayerRecipeDiscover(PlayerRecipeDiscoverEvent event) {
    }

    @EventHandler
    default void onPlayerResourcePackStatus(PlayerResourcePackStatusEvent event) {
    }

    @EventHandler
    default void onPlayerRespawn(PlayerRespawnEvent event) {
    }

    @EventHandler
    default void onPlayerRiptide(PlayerRiptideEvent event) {
    }

    @EventHandler
    default void onPlayerShearEntity(PlayerShearEntityEvent event) {
    }

    @EventHandler
    default void onPlayerShowEntity(PlayerShowEntityEvent event) {
    }

    @EventHandler
    default void onPlayerSignOpenEvent(PlayerSignOpenEvent event) {
    }

    @EventHandler
    default void onPlayerSpawnChange(PlayerSpawnLocationEvent event) {
    }

    @EventHandler
    default void onPlayerStatisticIncrement(PlayerStatisticIncrementEvent event) {
    }

    @EventHandler
    default void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
    }

    @EventHandler
    default void onPlayerTakeLecternBook(PlayerTakeLecternBookEvent event) {
    }

    @EventHandler
    default void onPlayerFlightToggle(PlayerToggleFlightEvent event) {
    }

    @EventHandler
    default void onPlayerSneakToggle(PlayerToggleSneakEvent event) {
    }

    @EventHandler
    default void onPlayerSprintToggle(PlayerToggleSprintEvent event) {
    }

    @EventHandler
    default void onPlayerVelocity(PlayerVelocityEvent event) {
    }

    // AsyncPlayerPreLogin
    @EventHandler
    default void onPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
    }

    // Blocks
    @EventHandler
    default void onBellResonate(BellResonateEvent event) {
    }

    @EventHandler
    default void onBellRing(BellRingEvent event) {
    }

    @EventHandler
    default void onBlockBurn(BlockBurnEvent event) {
    }

    @EventHandler
    default void onBlockCanBuild(BlockCanBuildEvent event) {
    }

    @EventHandler
    default void onBlockCook(BlockCookEvent event) {
    }

    @EventHandler
    default void onBlockDamageAbort(BlockDamageAbortEvent event) {
    }

    @EventHandler
    default void onBlockDamage(BlockDamageEvent event) {
    }

    @EventHandler
    default void onBlockDispense(BlockDispenseEvent event) {
    }

    @EventHandler
    default void onBlockDropItem(BlockDropItemEvent event) {
    }

    @EventHandler
    default void onBlockExp(BlockExpEvent event) {
    }

    @EventHandler
    default void onBlockExplode(BlockExplodeEvent event) {
    }

    @EventHandler
    default void onBlockFade(BlockFadeEvent event) {
    }

    @EventHandler
    default void onBlockFertilize(BlockFertilizeEvent event) {
    }

    @EventHandler
    default void onBlockFromTo(BlockFromToEvent event) {
    }

    @EventHandler
    default void onBlockGrow(BlockGrowEvent event) {
    }

    @EventHandler
    default void onBlockIgnite(BlockIgniteEvent event) {
    }

    @EventHandler
    default void onBlockPhysics(BlockPhysicsEvent event) {
    }

    @EventHandler
    default void onBlockPiston(BlockPistonEvent event) {
    }

    @EventHandler
    default void onBlockPlace(BlockPlaceEvent event) {
    }

    @EventHandler
    default void onBlockReceiveGameEvent(BlockReceiveGameEvent event) {
    }

    @EventHandler
    default void onBlockRedstone(BlockRedstoneEvent event) {
    }

    @EventHandler
    default void onBlockShearEntity(BlockShearEntityEvent event) {
    }

    @EventHandler
    default void onBrew(BrewEvent event) {
    }

    @EventHandler
    default void onBrewingStandFuel(BrewingStandFuelEvent event) {
    }

    @EventHandler
    default void onCauldronLevelChange(CauldronLevelChangeEvent event) {
    }

    @EventHandler
    default void onFluidLevelChange(FluidLevelChangeEvent event) {
    }

    @EventHandler
    default void onFurnaceBurn(FurnaceBurnEvent event) {
    }

    @EventHandler
    default void onHopperInventorySearch(HopperInventorySearchEvent event) {
    }

    @EventHandler
    default void onInventoryBlockStart(InventoryBlockStartEvent event) {
    }

    @EventHandler
    default void onCampfireStart(CampfireStartEvent event) {
    }

    @EventHandler
    default void onLeavesDecay(LeavesDecayEvent event) {
    }

    @EventHandler
    default void onMoistureChange(MoistureChangeEvent event) {
    }

    @EventHandler
    default void onNotePlay(NotePlayEvent event) {
    }

    @EventHandler
    default void onSculkBloom(SculkBloomEvent event) {
    }

    @EventHandler
    default void onSignChange(SignChangeEvent event) {
    }

    @EventHandler
    default void onSpongeAbsorb(SpongeAbsorbEvent event) {
    }

    @EventHandler
    default void onTNTPrime(TNTPrimeEvent event) {
    }

}
