package dev.mrsterner.alchrimea.common.block.cedar;

import net.minecraft.block.WallSignBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

public class CedarWallSignBlock extends WallSignBlock implements CedarSign {
    private final Identifier texture;

    public CedarWallSignBlock(Identifier texture, Settings settings) {
        super(settings, SignType.OAK);
        this.texture = texture;
    }

    @Override
    public Identifier getTexture() {
        return texture;
    }
}
