package com.mjr.tinkersextras;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.MaterialEvent.IntegrationEvent;
import slimeknights.tconstruct.library.events.MaterialEvent.MaterialRegisterEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolModifyEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolPartCraftingEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolPartReplaceEvent;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.utils.TinkerUtil;

@Mod(modid = TinkersExtras.MODID, version = TinkersExtras.VERSION, dependencies = "before:tconstruct")
public class TinkersExtras {
	public static final String MODID = "tinkersextras";
	public static final String VERSION = "1.12.2-1.0.0";

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		Config.load();
	}

	@SubscribeEvent
	public void onMaterialRegister(MaterialRegisterEvent event) {
		for (String temp : Config.materialListRemoval)
			if (event.material.identifier.equalsIgnoreCase(temp))
				event.setCanceled(true);

	}

	@SubscribeEvent
	public void onMaterialIntegration(IntegrationEvent event) {
		if (event.material == null)
			return;
		for (String temp : Config.materialListRemoval)
			if (event.material.identifier.equalsIgnoreCase(temp))
				event.setCanceled(true);
	}

	@SubscribeEvent
	public void onToolPartReplace(ToolPartReplaceEvent event) {
		if (Config.disableReplacing) {
			event.setCanceled("Replacing of Parts has been disabled!");
			return;
		} else {
			for (ItemStack temp : event.getToolParts()) {
				if (!temp.isEmpty()) {
					final Material material = TinkerUtil.getMaterialFromStack(temp);
					for (String temp2 : Config.disableReplacingList) {
						if (material.identifier.equalsIgnoreCase(temp2)) {
							event.setCanceled("You can not use " + temp.getDisplayName() + " as a replacement part!");
							return;
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void ToolModify(ToolModifyEvent event) {
		if (Config.disableModifying) {
			event.setCanceled("The use of Modifiers has been disabled!");
			return;
		} else {
			for (IModifier temp : event.getModifiers()) {
				for (String temp2 : Config.disableModifyingList) {
					if (temp.getIdentifier().equalsIgnoreCase(temp2)) {
						event.setCanceled("You can not use " + temp.getLocalizedName() + " as a Modifier!");
						return;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onToolPartCrafting(ToolPartCraftingEvent event) {
		Material material = TinkerUtil.getMaterialFromStack(event.getItemStack());
		if (Config.disablePartCreation) {
			event.setCanceled("Creation of parts has been disabled!");
			return;
		} else {
			for (String temp : Config.disablePartCreationList) {
				if (material.getIdentifier().equalsIgnoreCase(temp)) {
					event.setCanceled("You can not use " + material.getLocalizedName() + " for parts creation as its been disabled!");
					return;
				}
			}
		}
	}
}
