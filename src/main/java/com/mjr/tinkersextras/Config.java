package com.mjr.tinkersextras;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
	public static String[] materialListRemoval;
	public static String[] disableReplacingList;
	public static boolean disableReplacing;
	public static String[] disableModifyingList;
	public static boolean disableModifying;
	public static String[] disablePartCreationList;
	public static boolean disablePartCreation;
	public static String[] disableToolCreationList;
	public static boolean disableToolCreation;
	
	public static void load() {
		Configuration config = new Configuration(new File("config/TinkersExtras.cfg"));
		config.load();
		materialListRemoval = config.getStringList("Remove Materials/Parts List", Configuration.CATEGORY_GENERAL, new String[0], "Use material name. Example: paper");
		
		disableReplacingList = config.getStringList("List of Materials That Cant Be Used As Replacements", Configuration.CATEGORY_GENERAL, new String[0], "Use material name. Example: stone");
		disableReplacing = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Replace All Parts", false, "Will stop all Parts from being replaceable").getBoolean(false);

		disableModifyingList = config.getStringList("List of Modifiers to Disable", Configuration.CATEGORY_GENERAL, new String[0], "Use material name. Example: mending_moss");
		disableModifying = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Apply All Modifiers", false, "Will disable all possible Modifiers").getBoolean(false);
		
		disablePartCreationList = config.getStringList("List of Part That Cant Be Created", Configuration.CATEGORY_GENERAL, new String[0], "Use material name. Example: stone");
		disablePartCreation = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Parts", false, "Will only disable the creation not the existing of the parts").getBoolean(false);

		disableToolCreationList = config.getStringList("List of Tool Types That Cant Be Created", Configuration.CATEGORY_GENERAL, new String[0], "Use tool type. Example: pickaxe");
		disableToolCreation = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Tools", false, "Will disable the creation of all tools via a tool station/forge").getBoolean(false);
		
		config.save();
	}
}
