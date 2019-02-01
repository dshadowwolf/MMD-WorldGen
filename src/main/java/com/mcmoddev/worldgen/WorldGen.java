package com.mcmoddev.worldgen;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mcmoddev.worldgen.proxy.Proxy;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 */
@Mod(
	modid = WorldGen.MODID,
	name = WorldGen.NAME,
	version = WorldGen.VERSION,
	dependencies = "required-after:forge@[14.23.4.2705,)",
	useMetadata = false,
	clientSideOnly = false,
	serverSideOnly = false,
	acceptedMinecraftVersions = "[1.12.2]",
	acceptableRemoteVersions = WorldGen.VERSION,
	acceptableSaveVersions = "",
	certificateFingerprint = "@FINGERPRINT@",
	modLanguage = "java",
	modLanguageAdapter = "",
	canBeDeactivated = false,
	guiFactory = "",
	updateJSON = "https://github.com/MinecraftModDevelopmentMods/WorldGen/master/update.json",
	customProperties = {})
public final class WorldGen {

	/* The Mod's Instance. */
	//@Mod.Instance
	//private static ExampleMod instance = null

	/** ID of this Mod. */
	public static final String MODID = "mmdworldgen";

	/** Display name of this Mod. */
	public static final String NAME = "MMD World Gen";

	/** Version of this Mod. */
	public static final String VERSION = "1.0.0-alpha1";
//	public static final String VERSION = "@MOD_VERSION@"

	/**
	 *
	 */
	public static final Logger LOGGER = LogManager.getLogger(WorldGen.MODID);

	/**
	 *
	 */
	private static final class InstanceHolder {

		/**
		 * The Instance.
		 */
		private static final WorldGen INSTANCE = new WorldGen();
	}

	private static final ScriptEngine engine = new ScriptEngineManager(ClassLoader.getSystemClassLoader()).getEngineByName("nashorn");

	public ScriptEngine scriptEngine() {
		return engine;
	}
	
	/**
	 *
	 * @return The Mod's Instance.
	 */
	@Mod.InstanceFactory
	public static WorldGen instance() {
		return InstanceHolder.INSTANCE;
	}

	/**
	 *
 	 */
	@SidedProxy(
				clientSide = "com.mcmoddev.worldgen.proxy.ClientProxy",
				serverSide = "com.mcmoddev.worldgen.proxy.ServerProxy")
	private static Proxy proxy = null;

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void onFingerprintViolation(final FMLFingerprintViolationEvent event) {
		// This complains if jar not signed, even if certificateFingerprint is blank
		LOGGER.warn("Invalid Fingerprint");
	}

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void preInit(final FMLPreInitializationEvent event) {
		// Instead of setting the logger above, you can set it like this instead.
		// logger = event.getModLog()
		proxy.preInit(event);
	}

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void init(final FMLInitializationEvent event) {
		proxy.init(event);
	}

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void receiveIMC(final IMCEvent event) {
		proxy.receiveIMC(event);
	}

	/**
	 *
 	 * @param event The Event.
	 */
	@Mod.EventHandler
	public static void postInit(final FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static World getWorld() {
		return proxy.getWorld();
	}

}
