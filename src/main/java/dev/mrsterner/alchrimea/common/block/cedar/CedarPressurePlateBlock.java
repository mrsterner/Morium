package dev.mrsterner.alchrimea.common.block.cedar;

import net.minecraft.block.PressurePlateBlock;

public class CedarPressurePlateBlock extends PressurePlateBlock {
    public CedarPressurePlateBlock(Settings settings) {
        super(ActivationRule.EVERYTHING, settings);
    }

    public CedarPressurePlateBlock(ActivationRule rule, Settings settings) {
        super(rule, settings);
    }
}
