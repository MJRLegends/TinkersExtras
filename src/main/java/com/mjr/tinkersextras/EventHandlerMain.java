package com.mjr.tinkersextras;

import org.apache.logging.log4j.LogManager;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.events.MaterialEvent.IntegrationEvent;
import slimeknights.tconstruct.library.events.MaterialEvent.MaterialRegisterEvent;
import slimeknights.tconstruct.library.events.MaterialEvent.TraitRegisterEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolCraftingEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolModifyEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolPartCraftingEvent;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent.ToolPartReplaceEvent;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent.ModifierRegisterEvent;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.smeltery.CastingRecipe;
import slimeknights.tconstruct.library.tinkering.IMaterialItem;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.smeltery.events.TinkerCastingEvent;

public class EventHandlerMain {

	@SubscribeEvent
	public void onModifierRegister(ModifierRegisterEvent event) {
		if (Config.outputToConsole)
			LogManager.getLogger().info(TinkersExtras.MODID + ": Modifier: " + event.getRecipe().getIdentifier());
	}

	@SubscribeEvent
	public void onMaterialRegister(MaterialRegisterEvent event) {
		if (Config.outputToConsole)
			LogManager.getLogger().info(TinkersExtras.MODID + ": Material: " + event.material.identifier);
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
		if (Config.disablePartCreationPB) {
			event.setCanceled("Creation of parts has been disabled!");
			return;
		} else {
			for (String temp : Config.disablePartCreationList) {
				if (material.getIdentifier().equalsIgnoreCase(temp)) {
					event.setCanceled("You can not use " + material.getLocalizedName() + " for parts creation as its been disabled!");
					return;
				}
			}
			for (String temp : Config.disablePartTypeCreationListPB) {
				if (event.getItemStack().getUnlocalizedName().toLowerCase().contains(temp.toLowerCase())) {
					event.setCanceled("You can not build a " + event.getItemStack().getDisplayName() + " due to its been disabled!");
					return;
				}
			}
			for (String temp : Config.disablePartTypeonMaterialListPB) {
				String partName = temp.substring(0, temp.indexOf(':'));
				String materialName = temp.substring(temp.indexOf(':') + 1);
				if (event.getItemStack().getUnlocalizedName().toLowerCase().contains(partName.toLowerCase())
						&& ((IMaterialItem) event.getItemStack().getItem()).getMaterial(event.getItemStack()).getIdentifier().toLowerCase().equals(materialName.toLowerCase())) {
					event.setCanceled("You can not build a " + event.getItemStack().getDisplayName() + " due to its been disabled!");
					return;
				}
			}
		}
	}

	@SubscribeEvent
	public void onSmelteryPartCreation(TinkerCastingEvent.OnCasting event) {
		if (event.recipe instanceof CastingRecipe) {
			CastingRecipe recipe = ((CastingRecipe) event.recipe);
			if (recipe == null)
				return;
			ItemStack output = recipe.getResult();
			if (output == null)
				return;
			for (String temp : Config.disablePartTypeCreationListSM) {
				if (output.getUnlocalizedName().toLowerCase().contains(temp.toLowerCase())) {
					event.setCanceled(true);
					return;
				}
			}
			if (output.getItem() instanceof IToolPart) {
				if (Config.disablePartCreationSM) {
					event.setCanceled(true);
					return;
				}
				IMaterialItem item = ((IMaterialItem) output.getItem());
				for (String temp : Config.disablePartCreationList) {
					if (item.getMaterial(output).getIdentifier().equalsIgnoreCase(temp)) {
						event.setCanceled(true);
						return;
					}
				}
				for (String temp : Config.disablePartTypeonMaterialListSM) {
					String partName = temp.substring(0, temp.indexOf(':'));
					String materialName = temp.substring(temp.indexOf(':') + 1);
					if (output.getUnlocalizedName().toLowerCase().contains(partName.toLowerCase()) && item.getMaterial(output).getIdentifier().toLowerCase().equals(materialName.toLowerCase())) {
						event.setCanceled(true);
						return;
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onToolCrafting(ToolCraftingEvent event) {
		if (Config.disableToolCreation) {
			event.setCanceled("Creation of tools has been disabled!");
			return;
		} else {
			for (String temp : Config.disableToolCreationList) {
				if (event.getItemStack().getItem() instanceof TinkerToolCore) {
					if (((TinkerToolCore) event.getItemStack().getItem()).getIdentifier().toLowerCase().equals(temp.toLowerCase())) {
						event.setCanceled("You can not create a " + event.getItemStack().getDisplayName() + " due to its been disabled!");
						return;
					}
				}
			}
			for (String temp : Config.disableToolonMaterialList) {
				String toolName = temp.substring(0, temp.indexOf(':'));
				String materialName = temp.substring(temp.indexOf(':') + 1);
				if (event.getItemStack().getItem() instanceof TinkerToolCore) {
					if (((TinkerToolCore) event.getItemStack().getItem()).getIdentifier().toLowerCase().equals(toolName)
							&& ((IMaterialItem) event.getToolParts().get(1).getItem()).getMaterial(event.getToolParts().get(1)).getIdentifier().toLowerCase().equals(materialName.toLowerCase())) {
						event.setCanceled("You can not create a " + event.getItemStack().getDisplayName() + " due to its been disabled!");
						return;
					}
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@SubscribeEvent
	public void onTraitRegister(TraitRegisterEvent event) {
		if (Config.disableTraits) {
			event.setCanceled(true);
			return;
		} else {
			for (String temp : Config.disableTraitsList) {
				if (temp.equalsIgnoreCase(event.trait.getIdentifier())) {
					event.setCanceled(true);
					return;
				}
			}
		}
	}
}
