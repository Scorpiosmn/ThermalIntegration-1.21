package cofh.thermal.integration.init.data;

import cofh.thermal.integration.init.data.providers.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class TIntDataGen {

    public static void gatherData(final GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        TIntTagsProvider.Block blockTags = new TIntTagsProvider.Block(output, event.getLookupProvider(), exFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new TIntTagsProvider.Item(output, event.getLookupProvider(), blockTags.contentsGetter(), exFileHelper));

        gen.addProvider(event.includeServer(), new TIntLootTableProvider(output, event.getLookupProvider()));
        gen.addProvider(event.includeServer(), new TIntRecipeProvider(output, event.getLookupProvider()));

        gen.addProvider(event.includeClient(), new TIntBlockStateProvider(output, exFileHelper));
        gen.addProvider(event.includeClient(), new TIntItemModelProvider(output, exFileHelper));
    }

}
