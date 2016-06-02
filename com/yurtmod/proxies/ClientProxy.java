package com.yurtmod.proxies;

import com.yurtmod.block.BlockTepeeWall;
import com.yurtmod.init.Content;
import com.yurtmod.init.NomadicTents;
import com.yurtmod.structure.StructureType;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy 
{
	@Override
	public void preInitRenders() 
	{
		int len = StructureType.values().length;
		ResourceLocation[] namesTentItem = new ResourceLocation[len];
		ResourceLocation[] namesTepeeWall = new ResourceLocation[BlockTepeeWall.NUM_TEXTURES];
		for(int i = 0; i < len; i++)
		{
			String modelName = NomadicTents.MODID + ":" + StructureType.getName(i);
			namesTentItem[i] = new ResourceLocation(modelName);
			ModelLoader.setCustomModelResourceLocation(Content.ITEM_TENT, i, new ModelResourceLocation(modelName, "inventory"));
			// debug:
			//System.out.println("registering variant with name '" + modelName + "'");
		}
		for(int j = 0; j < BlockTepeeWall.NUM_TEXTURES; j++)
		{
			String modelName = Content.IB_TEPEE_WALL.getRegistryName() + "_" + j;
			namesTepeeWall[j] = new ResourceLocation(modelName);
			ModelLoader.setCustomModelResourceLocation(Content.IB_TEPEE_WALL, j, new ModelResourceLocation(modelName));
			// debug:
			//System.out.println("registering wall variant with name '" + modelName + "'");
		}
		ModelBakery.registerItemVariants(Content.ITEM_TENT, namesTentItem);
		ModelBakery.registerItemVariants(Content.IB_TEPEE_WALL, namesTepeeWall);
	}

	@Override
	public void registerRenders()
	{
		// register items		
		register(Content.ITEM_TENT_CANVAS);
		register(Content.ITEM_YURT_WALL);
		register(Content.ITEM_TEPEE_WALL);
		register(Content.ITEM_BEDOUIN_WALL);
		register(Content.ITEM_MALLET);
		register(Content.ITEM_SUPER_MALLET);
		//// register tent item
		for(StructureType type : StructureType.values())
		{
			String modelName = NomadicTents.MODID + ":" + StructureType.getName(type.ordinal());
			register(Content.ITEM_TENT, type.ordinal(), modelName);
		}
		// register blocks
		register(Content.TENT_BARRIER);
		register(Content.SUPER_DIRT);
		//// yurt blocks
		register(Content.YURT_WALL_OUTER);
		register(Content.YURT_WALL_INNER);
		register(Content.YURT_ROOF);
		//// tepee blocks
		for(int j = 0; j < BlockTepeeWall.NUM_TEXTURES; j++)
		{
			String name = Content.IB_TEPEE_WALL.getRegistryName().toString() + "_" + j;
			register(Content.IB_TEPEE_WALL, j, name);
		}
		//// bedouin blocks
		register(Content.BEDOUIN_WALL);
		register(Content.BEDOUIN_ROOF);
		//// door blocks		
		register(Content.YURT_DOOR_SMALL);
		register(Content.YURT_DOOR_MEDIUM);
		register(Content.YURT_DOOR_LARGE);	
		register(Content.TEPEE_DOOR_SMALL);
		register(Content.TEPEE_DOOR_MEDIUM);
		register(Content.TEPEE_DOOR_LARGE);
		register(Content.BEDOUIN_DOOR_SMALL);
		register(Content.BEDOUIN_DOOR_MEDIUM);
		register(Content.BEDOUIN_DOOR_LARGE);
		//// frame blocks
		register(Content.FRAME_YURT_WALL);
		register(Content.FRAME_YURT_ROOF);
		register(Content.FRAME_TEPEE_WALL);
		register(Content.FRAME_BEDOUIN_WALL);
		register(Content.FRAME_BEDOUIN_ROOF);
	}
	
	private void register(Item i)
	{
		register(i, 0);
	}
	
	private void register(Item i, int meta)
	{
		register(i, meta, i.getRegistryName().toString());
	}
	
	private void register(Item i, int meta, String name)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, meta, new ModelResourceLocation(name, "inventory"));
	}
	
	private Item getItemBlock(Block b)
	{
		return b != null ? Item.getItemFromBlock(b) : null;
	}
	
	private void register(Block b)
	{
		Item i = Item.getItemFromBlock(b);
		if(i != null)
		{
			register(i);
		} else System.out.println("Tried to register render for a null ItemBlock. Skipping.");
	}
}
