package com.mjr.tinkersextras;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TinkersExtras.MODID, version = TinkersExtras.VERSION, dependencies = "before:tconstruct", certificateFingerprint = "b02331787272ec3515ebe63ecdeea0d746653468")
public class TinkersExtras {
	public static final String MODID = "tinkersextras";
	public static final String VERSION = "1.12.2-1.1.1";
	public static Logger logger = LogManager.getLogger();

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.load();
		MinecraftForge.EVENT_BUS.register(new EventHandlerMain());
		Config.load();
	}

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		logger.fatal("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported!");
	}
}
