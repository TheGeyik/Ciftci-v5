package xyz.geik.farmer.listeners.backend;

import net.brcdev.shopgui.ShopGuiPlusApi;
import net.brcdev.shopgui.event.ShopsPostLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xyz.geik.farmer.Main;
import xyz.geik.farmer.api.managers.FarmerManager;
import xyz.geik.farmer.model.inventory.FarmerItem;

public class ShopGuiListener implements Listener {

	@EventHandler
	public void onShopsPostLoadEvent(ShopsPostLoadEvent event) {
		Main.getInstance().getLogger().info("ShopGui+ support added. Farmer will update prices according to ShopGui+ if available.");
		FarmerManager.getFarmers().forEach((id, farmer) -> {
			for (FarmerItem farmerItem : farmer.getInv().getItems()) {
				double shopGuiPrice = ShopGuiPlusApi.getItemStackPriceSell(farmerItem.getMaterial().parseItem());
				if (shopGuiPrice != -1) {
					farmerItem.setPrice(shopGuiPrice);
				}
			}
		});
	}
}
