package com.sunflow.tutorialmod.world.gen.feature.tree;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.HugeTreesFeature;

public class CustomBigTreeFeature extends HugeTreesFeature<HugeTreeFeatureConfig> {

	public CustomBigTreeFeature(Function<Dynamic<?>, ? extends HugeTreeFeatureConfig> configFactoryIn) {// , boolean doBlockNotifyIn, BlockState log, BlockState leaves) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorldGenerationReader generationReader, Random random, BlockPos pos, Set<BlockPos> posSet1, Set<BlockPos> posSet2, MutableBoundingBox boundingBox, HugeTreeFeatureConfig hugeTreeConfig) {
		int i = random.nextInt(3) + random.nextInt(2) + hugeTreeConfig.baseHeight;
		int j = pos.getX();
		int k = pos.getY();
		int l = pos.getZ();
		if (k >= 1 && k + i + 1 < generationReader.getMaxHeight()) {
			BlockPos blockpos = pos.down();
			if (!isSoil(generationReader, blockpos, hugeTreeConfig.getSapling())) {
				return false;
			} else if (!this.func_214615_a(generationReader, pos, i)) {
				return false;
			} else {
				this.setDirtAt(generationReader, blockpos, pos);
				this.setDirtAt(generationReader, blockpos.east(), pos);
				this.setDirtAt(generationReader, blockpos.south(), pos);
				this.setDirtAt(generationReader, blockpos.south().east(), pos);
				Direction direction = Direction.Plane.HORIZONTAL.random(random);
				int i1 = i - random.nextInt(4);
				int j1 = 2 - random.nextInt(3);
				int k1 = j;
				int l1 = l;
				int i2 = k + i - 1;

				for (int j2 = 0; j2 < i; ++j2) {
					if (j2 >= i1 && j1 > 0) {
						k1 += direction.getXOffset();
						l1 += direction.getZOffset();
						--j1;
					}

					int k2 = k + j2;
					BlockPos blockpos1 = new BlockPos(k1, k2, l1);
					if (isAirOrLeaves(generationReader, blockpos1)) {
						this.setLog(generationReader, random, blockpos1, posSet1, boundingBox, hugeTreeConfig);
						this.setLog(generationReader, random, blockpos1.east(), posSet1, boundingBox, hugeTreeConfig);
						this.setLog(generationReader, random, blockpos1.south(), posSet1, boundingBox, hugeTreeConfig);
						this.setLog(generationReader, random, blockpos1.east().south(), posSet1, boundingBox, hugeTreeConfig);
					}
				}

				for (int j3 = -2; j3 <= 0; ++j3) {
					for (int i4 = -2; i4 <= 0; ++i4) {
						int l4 = -1;
						this.setLeaf(generationReader, random, new BlockPos(k1 + j3, i2 + l4, l1 + i4), posSet2, boundingBox, hugeTreeConfig);
						this.setLeaf(generationReader, random, new BlockPos(1 + k1 - j3, i2 + l4, l1 + i4), posSet2, boundingBox, hugeTreeConfig);
						this.setLeaf(generationReader, random, new BlockPos(k1 + j3, i2 + l4, 1 + l1 - i4), posSet2, boundingBox, hugeTreeConfig);
						this.setLeaf(generationReader, random, new BlockPos(1 + k1 - j3, i2 + l4, 1 + l1 - i4), posSet2, boundingBox, hugeTreeConfig);
						if ((j3 > -2 || i4 > -1) && (j3 != -1 || i4 != -2)) {
							l4 = 1;
							this.setLeaf(generationReader, random, new BlockPos(k1 + j3, i2 + l4, l1 + i4), posSet2, boundingBox, hugeTreeConfig);
							this.setLeaf(generationReader, random, new BlockPos(1 + k1 - j3, i2 + l4, l1 + i4), posSet2, boundingBox, hugeTreeConfig);
							this.setLeaf(generationReader, random, new BlockPos(k1 + j3, i2 + l4, 1 + l1 - i4), posSet2, boundingBox, hugeTreeConfig);
							this.setLeaf(generationReader, random, new BlockPos(1 + k1 - j3, i2 + l4, 1 + l1 - i4), posSet2, boundingBox, hugeTreeConfig);
						}
					}
				}

				if (random.nextBoolean()) {
					this.setLeaf(generationReader, random, new BlockPos(k1, i2 + 2, l1), posSet2, boundingBox, hugeTreeConfig);
					this.setLeaf(generationReader, random, new BlockPos(k1 + 1, i2 + 2, l1), posSet2, boundingBox, hugeTreeConfig);
					this.setLeaf(generationReader, random, new BlockPos(k1 + 1, i2 + 2, l1 + 1), posSet2, boundingBox, hugeTreeConfig);
					this.setLeaf(generationReader, random, new BlockPos(k1, i2 + 2, l1 + 1), posSet2, boundingBox, hugeTreeConfig);
				}

				for (int k3 = -3; k3 <= 4; ++k3) {
					for (int j4 = -3; j4 <= 4; ++j4) {
						if ((k3 != -3 || j4 != -3) && (k3 != -3 || j4 != 4) && (k3 != 4 || j4 != -3) && (k3 != 4 || j4 != 4) && (Math.abs(k3) < 3 || Math.abs(j4) < 3)) {
							this.setLeaf(generationReader, random, new BlockPos(k1 + k3, i2, l1 + j4), posSet2, boundingBox, hugeTreeConfig);
						}
					}
				}

				for (int l3 = -1; l3 <= 2; ++l3) {
					for (int k4 = -1; k4 <= 2; ++k4) {
						if ((l3 < 0 || l3 > 1 || k4 < 0 || k4 > 1) && random.nextInt(3) <= 0) {
							int i5 = random.nextInt(3) + 2;

							for (int l2 = 0; l2 < i5; ++l2) {
								this.setLog(generationReader, random, new BlockPos(j + l3, i2 - l2 - 1, l + k4), posSet1, boundingBox, hugeTreeConfig);
							}

							for (int j5 = -1; j5 <= 1; ++j5) {
								for (int i3 = -1; i3 <= 1; ++i3) {
									this.setLeaf(generationReader, random, new BlockPos(k1 + l3 + j5, i2, l1 + k4 + i3), posSet2, boundingBox, hugeTreeConfig);
								}
							}

							for (int k5 = -2; k5 <= 2; ++k5) {
								for (int l5 = -2; l5 <= 2; ++l5) {
									if (Math.abs(k5) != 2 || Math.abs(l5) != 2) {
										this.setLeaf(generationReader, random, new BlockPos(k1 + l3 + k5, i2 - 1, l1 + k4 + l5), posSet2, boundingBox, hugeTreeConfig);
									}
								}
							}
						}
					}
				}

				return true;
			}
		} else {
			return false;
		}
	}

	private boolean func_214615_a(IWorldGenerationBaseReader p_214615_1_, BlockPos p_214615_2_, int p_214615_3_) {
		int i = p_214615_2_.getX();
		int j = p_214615_2_.getY();
		int k = p_214615_2_.getZ();
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int l = 0; l <= p_214615_3_ + 1; ++l) {
			int i1 = 1;
			if (l == 0) {
				i1 = 0;
			}

			if (l >= p_214615_3_ - 1) {
				i1 = 2;
			}

			for (int j1 = -i1; j1 <= i1; ++j1) {
				for (int k1 = -i1; k1 <= i1; ++k1) {
					if (!canBeReplacedByLogs(p_214615_1_, blockpos$mutable.setPos(i + j1, j + l, k + k1))) {
						return false;
					}
				}
			}
		}

		return true;
	}
}