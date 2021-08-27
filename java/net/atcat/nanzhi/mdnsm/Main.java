package net.atcat.nanzhi.mdnsm;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin ;
import net.atcat.nanzhi.mdnsm.event.onMobSpawn ;

public class Main extends JavaPlugin {
  
  @Override
  public void onEnable ( ) {

    getLogger().info( "插件已启动，现在矿洞不会刷出怪物了。" );
    Bukkit.getPluginManager( ).registerEvents( new onMobSpawn( ), this );

  } ;
  
}
