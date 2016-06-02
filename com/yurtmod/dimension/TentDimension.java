package com.yurtmod.dimension;

import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class TentDimension 
{
	public static final int DIMENSION_ID = DimensionManager.getNextFreeDimId();
	public static final String DIM_NAME = "Tent Dimension";
	public static final DimensionType TENT_DIMENSION = DimensionType.register("TENT", "_tent", DIMENSION_ID, WorldProviderTent.class, false);
			
	public static void mainRegistry()
	{
		DimensionManager.registerDimension(DIMENSION_ID, TentDimension.TENT_DIMENSION);
	}
	
	public static boolean isTentDimension(World world)
	{
		return isTentDimension(world.provider.getDimension());
	}
	
	public static boolean isTentDimension(int id)
	{
		return id == TentDimension.DIMENSION_ID;
	}
}
