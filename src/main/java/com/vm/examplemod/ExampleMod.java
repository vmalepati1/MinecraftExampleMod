package com.vm.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod
{

    static final class ClientConfig {

        final ForgeConfigSpec.BooleanValue clientBoolean;
        final ForgeConfigSpec.ConfigValue<List<String>> clientStringList;
        final ForgeConfigSpec.EnumValue<DyeColor> clientDyeColorEnum;

        final ForgeConfigSpec.BooleanValue modelTranslucency;
        final ForgeConfigSpec.DoubleValue modelScale;

        ClientConfig(final ForgeConfigSpec.Builder builder) {
            builder.push("general");
            clientBoolean = builder
                    .comment("An example boolean in the client config")
                    .translation(ExampleMod.MODID + ".config.clientBoolean")
                    .define("clientBoolean", true);
            clientStringList = builder
                    .comment("An example list of Strings in the client config")
                    .translation(ExampleMod.MODID + ".config.clientStringList")
                    .define("clientStringList", new ArrayList<>());
            clientDyeColorEnum = builder
                    .comment("An example DyeColor enum in the client config")
                    .translation(ExampleMod.MODID + ".config.clientDyeColorEnum")
                    .defineEnum("clientDyeColorEnum", DyeColor.WHITE);

            modelTranslucency = builder
                    .comment("If the model should be rendered translucent")
                    .translation(ExampleMod.MODID + ".config.modelTranslucency")
                    .define("modelTranslucency", true);
            modelScale = builder
                    .comment("The scale to render the model at")
                    .translation(ExampleMod.MODID + ".config.modelScale")
                    .defineInRange("modelScale", 0.0625F, 0.0001F, 100F);
            builder.pop();
        }

    }

    public static final String MODID = "examplemod";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ExampleMod.MODID);
    public static final RegistryObject<Block> CUSTOM_BLOCK = BLOCKS.register("custom_block", () -> new Block(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)));

    public static final ForgeConfigSpec CLIENT_SPEC;
    static final ClientConfig CLIENT;
    static {
        {
            final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
            CLIENT = specPair.getLeft();
            CLIENT_SPEC = specPair.getRight();
        }
    }

    public ExampleMod() {
        LOGGER.debug("Hello from Example Mod!");

        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register Deferred Registers (Does not need to be before Configs)
        BLOCKS.register(modEventBus);
        // Register Configs (Does not need to be after Deferred Registers)
        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
    }
}
