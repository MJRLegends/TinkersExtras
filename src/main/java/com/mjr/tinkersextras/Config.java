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
	
	public static String[] disablePartTypeBasedonMaterialCreationList;
	
	public static boolean outputToConsole;
	
	public static void load() {
		Configuration config = new Configuration(new File("config/TinkersExtras.cfg"));
		config.load();
		
		disableReplacing = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Replace All Parts", false, "Will stop all Parts from being replaceable").getBoolean(false);
		disableModifying = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Apply All Modifiers", false, "Will disable all possible Modifiers").getBoolean(false);
		disablePartCreation = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Parts", false, "Will only disable the creation not the existing of the parts").getBoolean(false);
		disableToolCreation = config.get(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Tools", false, "Will disable the creation of all tools via a tool station/forge").getBoolean(false);
		
		materialListRemoval = config.getStringList("List of Materials to be disabled", Configuration.CATEGORY_GENERAL, new String[0], "Use material name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: paper");
		disableReplacingList = config.getStringList("List of Materials which cant be used as replacements on tools", Configuration.CATEGORY_GENERAL, new String[0], "Use material name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: stone");
		disableModifyingList = config.getStringList("List of Modifiers to be disabled", Configuration.CATEGORY_GENERAL, new String[0], "Use modifier name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: mending_moss");
		disablePartCreationList = config.getStringList("List of Materials which parts cant be created for", Configuration.CATEGORY_GENERAL, new String[0], "Use material name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: stone");
		disablePartTypeCreationList = config.getStringList("List of Parts that cant be created", Configuration.CATEGORY_GENERAL, new String[0], "Use part type name (Use F3 + H to show unlocalized names on parts to find there names, will be the name after the : ) Example: pick_head");
		disableToolCreationList = config.getStringList("List of Tools that cant be created", Configuration.CATEGORY_GENERAL, new String[0], "Use tool type name (Use F3 + H to show unlocalized names on tools to find there names, will be the name after the : ) Example: pickaxe");
		disablePartTypeBasedonMaterialCreationList = config.getStringList("List of Parts based on certain Materials that cant be created", Configuration.CATEGORY_GENERAL, new String[0], "Use part type name and material name in the Format: part_name:material_name Example: pick_head:stone");
		
		outputToConsole = config.get(Configuration.CATEGORY_GENERAL, "Output Materials/Modifier names to the Log/Console", false, "Will output to the Log/Console during start up").getBoolean(false);
		
		config.save();
	}
}
