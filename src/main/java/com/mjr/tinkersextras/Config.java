package com.mjr.tinkersextras;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {
	public static boolean disableReplacing;
	public static boolean disableModifying;
	public static boolean disablePartCreationPB;
	public static boolean disablePartCreationSM;
	public static boolean disableToolCreation;
	public static boolean disableTraits;

	public static String[] materialListRemoval;
	public static String[] disableReplacingList;
	public static String[] disableReplacingMaterialListPB;
	public static String[] disableModifyingList;
	public static String[] disablePartCreationList;

	public static String[] disablePartTypeCreationListPB;
	public static String[] disablePartTypeonMaterialListPB;
	public static String[] disablePartTypeCreationListSM;
	public static String[] disablePartTypeonMaterialListSM;;

	public static String[] disableToolCreationList;
	public static String[] disableToolonMaterialList;

	public static String[] disableTraitsList;

	public static boolean outputToConsole;

	public static final String CONFIG_CATEGORY_GLOBAL = "global settings";
	public static final String CONFIG_CATEGORY_TOOLS = "tools settings";
	public static final String CONFIG_CATEGORY_PARTS = "parts settings";
	public static final String CONFIG_CATEGORY_PARTS_GENERAL = "general parts settings";
	public static final String CONFIG_CATEGORY_MATERIALS = "materials settings";
	public static final String CONFIG_CATEGORY_EXTRAS = "extras settings";

	public static void load() {
		Configuration config = new Configuration(new File("config/TinkersExtras.cfg"));
		config.load();

		config.renameProperty(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Parts", "Disable Ability to Create All Parts in the Part Builder");
		config.renameProperty(Configuration.CATEGORY_GENERAL, "List of Materials which parts cant be created for", "List of Materials which parts cant be created for using a Part Builder/Smeltery Casting table");
		config.renameProperty(Configuration.CATEGORY_GENERAL, "List of Parts that cant be created", "List of Parts that cant be created in the Part Builder");
		config.renameProperty(Configuration.CATEGORY_GENERAL, "List of Parts based on certain Materials that cant be created", "List of Parts that cant be created in the Part Builder (based on certain Material type)");
		config.renameProperty(Configuration.CATEGORY_GENERAL, "List of Tools based on certain Materials that cant be created", "List of Tools that cant be created (based on certain Material type)");

		config.moveProperty(Configuration.CATEGORY_GENERAL, "Disable Ability to Replace All Parts", CONFIG_CATEGORY_GLOBAL);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "Disable Ability to Apply All Modifiers", CONFIG_CATEGORY_GLOBAL);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Parts in the Part Builder", CONFIG_CATEGORY_GLOBAL);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Parts in the Smeltery Casting table", CONFIG_CATEGORY_GLOBAL);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "Disable Ability to Create All Tools", CONFIG_CATEGORY_GLOBAL);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "Disable Ability to Apply All Traits", CONFIG_CATEGORY_GLOBAL);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Materials to be disabled", CONFIG_CATEGORY_MATERIALS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Materials which cant be used as replacements on tools", CONFIG_CATEGORY_EXTRAS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Modifiers to be disabled", CONFIG_CATEGORY_EXTRAS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Materials which parts cant be created for using a Part Builder/Smeltery Casting table", CONFIG_CATEGORY_PARTS_GENERAL);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Parts that cant be created in the Part Builder", CONFIG_CATEGORY_PARTS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Parts that cant be created in the Part Builder (based on certain Material type)", CONFIG_CATEGORY_PARTS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Parts that cant be created in the Smeltery Casting table", CONFIG_CATEGORY_PARTS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Parts that cant be created in the Smeltery Casting table (based on certain Material type)", CONFIG_CATEGORY_PARTS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Tools that cant be created", CONFIG_CATEGORY_TOOLS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Tools that cant be created (based on certain Material type)", CONFIG_CATEGORY_TOOLS);
		config.moveProperty(Configuration.CATEGORY_GENERAL, "List of Traits to be disabled", CONFIG_CATEGORY_EXTRAS);

		disableReplacing = config.get(CONFIG_CATEGORY_GLOBAL, "Disable Ability to Replace All Parts", false, "Will stop all Parts from being replaceable").getBoolean(false);
		disableModifying = config.get(CONFIG_CATEGORY_GLOBAL, "Disable Ability to Apply All Modifiers", false, "Will disable all possible Modifiers").getBoolean(false);
		disablePartCreationPB = config.get(CONFIG_CATEGORY_GLOBAL, "Disable Ability to Create All Parts in the Part Builder", false, "Will only disable the creation not the existing of the parts").getBoolean(false);
		disablePartCreationSM = config.get(CONFIG_CATEGORY_GLOBAL, "Disable Ability to Create All Parts in the Smeltery Casting table", false, "Will only disable the creation not the existing of the parts").getBoolean(false);
		disableToolCreation = config.get(CONFIG_CATEGORY_GLOBAL, "Disable Ability to Create All Tools", false, "Will disable the creation of all tools via a tool station/forge").getBoolean(false);
		disableTraits = config.get(CONFIG_CATEGORY_GLOBAL, "Disable Ability to Apply All Traits", false, "Will disable all possible Traits").getBoolean(false);

		materialListRemoval = config.getStringList("List of Materials to be disabled", CONFIG_CATEGORY_MATERIALS, new String[0],
				"Use material name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: paper");

		disableReplacingList = config.getStringList("List of Materials which cant be used as replacements on tools", CONFIG_CATEGORY_EXTRAS, new String[0],
				"Use material name ('Use Output Materials names to the Log/Console' option to show all materials) Example: stone");
		disableReplacingMaterialListPB = config.getStringList("List of parts which cant be used as replacements on tools (based on certain Material type)", CONFIG_CATEGORY_EXTRAS, new String[0],
				"Use part name and material name in the Format: part_name:material_name Example: pick_head:stone");
		
		
		disableModifyingList = config.getStringList("List of Modifiers to be disabled", CONFIG_CATEGORY_EXTRAS, new String[0],
				"Use modifier name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: mending_moss");

		disablePartCreationList = config.getStringList("List of Materials which parts cant be created for using a Part Builder/Smeltery Casting table", CONFIG_CATEGORY_PARTS_GENERAL, new String[0],
				"Use material name ('Use Output Materials/Modifier names to the Log/Console' option to show all materials) Example: stone");

		disablePartTypeCreationListPB = config.getStringList("List of Parts that cant be created in the Part Builder", CONFIG_CATEGORY_PARTS, new String[0],
				"Use part type name (Use F3 + H to show unlocalized names on parts to find there names, will be the name after the : ) Example: pick_head");
		disablePartTypeonMaterialListPB = config.getStringList("List of Parts that cant be created in the Part Builder (based on certain Material type)", CONFIG_CATEGORY_PARTS, new String[0],
				"Use part name and material name in the Format: part_name:material_name Example: pick_head:stone");
		disablePartTypeCreationListSM = config.getStringList("List of Parts that cant be created in the Smeltery Casting table", CONFIG_CATEGORY_PARTS, new String[0],
				"Use part type name (Use F3 + H to show unlocalized names on parts to find there names, will be the name after the : ) Example: pick_head");
		disablePartTypeonMaterialListSM = config.getStringList("List of Parts that cant be created in the Smeltery Casting table (based on certain Material type)", CONFIG_CATEGORY_PARTS, new String[0],
				"Use part name and material name in the Format: part_name:material_name Example: pick_head:stone");

		disableToolCreationList = config.getStringList("List of Tools that cant be created", CONFIG_CATEGORY_TOOLS, new String[0],
				"Use tool type name (Use F3 + H to show unlocalized names on tools to find there names, will be the name after the : ) Example: pickaxe");
		disableToolonMaterialList = config.getStringList("List of Tools that cant be created (based on certain Material type)", CONFIG_CATEGORY_TOOLS, new String[0],
				"Use tool name and material name in the Format: tool_name:material_name Example: pickaxe:stone");

		disableTraitsList = config.getStringList("List of Traits to be disabled", CONFIG_CATEGORY_EXTRAS, new String[0], "Use Traits name Example: ecological");

		outputToConsole = config.get(Configuration.CATEGORY_GENERAL, "Output Materials/Modifier names to the Log/Console", false, "Will output to the Log/Console during start up").getBoolean(false);

		config.save();
	}
}
