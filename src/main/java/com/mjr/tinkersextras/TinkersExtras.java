package com.mjr.tinkersextras;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TinkersExtras.MODID, version = TinkersExtras.VERSION, dependencies = "before:tconstruct")
public class TinkersExtras {
	public static final String MODID = "tinkersextras";
	public static final String VERSION = "1.12.2-1.0.2";

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EventHandlerMain());
		Config.load();
	}
}
