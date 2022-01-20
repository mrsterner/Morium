package dev.mrsterner.alchrimea.client.renderer;

import dev.mrsterner.alchrimea.client.model.DwarfInAFlaskItemModel;
import dev.mrsterner.alchrimea.common.block.AlchrimeaBlockItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DwarfInAFlaskItemRenderer extends GeoItemRenderer<AlchrimeaBlockItem> {
    public DwarfInAFlaskItemRenderer() {
        super(new DwarfInAFlaskItemModel());
    }
}
