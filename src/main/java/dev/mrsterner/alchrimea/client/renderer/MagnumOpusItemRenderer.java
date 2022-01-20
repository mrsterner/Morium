package dev.mrsterner.alchrimea.client.renderer;

import dev.mrsterner.alchrimea.client.model.MagnumOpusItemModel;
import dev.mrsterner.alchrimea.common.item.MagnumOpusItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class MagnumOpusItemRenderer extends GeoItemRenderer<MagnumOpusItem> {
    public MagnumOpusItemRenderer() {
        super(new MagnumOpusItemModel());
    }
}
