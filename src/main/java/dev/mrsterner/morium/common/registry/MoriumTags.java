package dev.mrsterner.morium.common.registry;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class MoriumTags {
    public static final Tag<Item> JAR = TagFactory.ITEM.create(new Identifier("c", "jar"));
}
