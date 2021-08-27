package net.atcat.nanzhi.mdnsm.event;

import net.minecraft.server.v1_16_R3.Blocks;
import net.minecraft.server.v1_16_R3.FluidTypes;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class onMobSpawn implements Listener {

  @EventHandler
  public void onMobSpawnEvent( EntitySpawnEvent event ) {

    Location location = event.getLocation( ).add( 0, 1, 0 ) ;
    int xPos = location.getBlockX( ) ;
    int yPos = location.getBlockY( ) ;
    int zPos = location.getBlockZ( ) ;
    Entity entity = event.getEntity( ) ;

    if ( yPos > 0 && yPos < 256 && entity instanceof Monster && entity.getWorld().getEnvironment( ) == World.Environment.NORMAL ) { // 坐标合理，且是怪物，且在主世界
      if ( location.getBlock( ).getLightFromSky( ) < 13 && !location.getBlock( ).isLiquid( ) ) { // 非露天且非流体
        int count = 0 ;
        for ( int x = xPos -1 ; x <= xPos +1 ; x++ ) {
          for ( int y = Math.max( yPos -1, 0 ) ; y <= Math.min( yPos +1, 255 ) ; y++ ) {
            for ( int z = zPos -1 ; z <= zPos +1 ; z++ ) {
              if ( !entity.getWorld( ).getBlockAt( x, y, z ).isEmpty( ) ) {
                count++ ;
              }
            } ;
          } ;
        } ;
        if ( count > 9 || location.getBlock( ).getLightLevel( ) != 0 ) { // 周围拥挤或者光照不等于0都不再生成
          event.setCancelled( true ) ;
        } ;
      } ;
    } ;

  } ;

}
