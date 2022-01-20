package dev.mrsterner.alchrimea.common.block.cedar;

import net.minecraft.block.SignBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

public class CedarSignBlock extends SignBlock implements CedarSign {
    private final Identifier texture;

    public CedarSignBlock(Identifier texture, Settings settings) {
        super(settings, SignType.OAK);
        this.texture = texture;
    }

    @Override
    public Identifier getTexture() {
        return texture;
    }
}
