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
	public static String[] disablePartTypeCreationList;
	
	public static boolean outputToConsole;
	
	public static void load() {
		Configuration config = new Configuration(new File("config/TinkersExtras.cfg"));
		config.load();
		materialListRemoval = config.getStringList("Remove Materials/Parts List", Configuration.CATEGORY_GENERAL, new String[0], "Use material name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: paper");
		
		disableReplacingList = config.getStringList("List of Materials That Cant Be Used As Replacements", Configuration.CATEGORY_GENERAL, new String[0], "Use material name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: stone");
		disableReplacing = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Replace All Parts", false, "Will stop all Parts from being replaceable").getBoolean(false);

		disableModifyingList = config.getStringList("List of Modifiers to Disable", Configuration.CATEGORY_GENERAL, new String[0], "Use modifier name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: mending_moss");
		disableModifying = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Apply All Modifiers", false, "Will disable all possible Modifiers").getBoolean(false);
		
		disablePartCreationList = config.getStringList("List of Part That Cant Be Created", Configuration.CATEGORY_GENERAL, new String[0], "Use material name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: stone");
		disablePartCreation = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Parts", false, "Will only disable the creation not the existing of the parts").getBoolean(false);

		disableToolCreationList = config.getStringList("List of Tool Types That Cant Be Created", Configuration.CATEGORY_GENERAL, new String[0], "Use tool type name (Use F3 + H to show unlocalized names on parts to find there names, will be the part after the : ) Example: pickaxe");
		disableToolCreation = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Tools", false, "Will disable the creation of all tools via a tool station/forge").getBoolean(false);
		
		disablePartTypeCreationList = config.getStringList("List of Part Types That Cant Be Created", Configuration.CATEGORY_GENERAL, new String[0], "Use part type name (Use F3 + H to show unlocalized names on parts to find there names, will be the part after the : ) Example: pick_head");
		
		outputToConsole = config.get(Configuration.CATEGORY_GENERAL, "Output Materials/Modifier names to the Log/Console", false, "Will output to the Log/Console during start up").getBoolean(false);
		
		config.save();
	}
}
