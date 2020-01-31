package com.sunflow.tutorialmod.world.gen.feature.tree;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.LogBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class CustomTreeFeature extends AbstractTreeFeature<TreeFeatureConfig> {
//	protected final int minTreeHeight;
//	private int randomTreeHeight;
//	private final boolean vinesGrow;
//	private final BlockState trunk;
//	private final BlockState leaf;

	public CustomTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> configFactory) {// , boolean doBlockNotifyOnPlace, boolean vinesGrow, int minTreeHeight, int randomTreeHeight, IPlantable sapling, BlockState trunkState, BlockState leafState) {
		super(configFactory);
//		this.minTreeHeight = minTreeHeight;
//		this.randomTreeHeight = randomTreeHeight;
//		this.trunk = trunkState;
//		this.leaf = leafState;
//		this.vinesGrow = vinesGrow;
//		this.sapling = sapling;
	}

	private void func_227233_a_(IWorldGenerationReader p_227233_1_, Random p_227233_2_, BlockPos p_227233_3_, float p_227233_4_, Set<BlockPos> p_227233_5_, MutableBoundingBox p_227233_6_, TreeFeatureConfig p_227233_7_) {
		int i = (int) (p_227233_4_ + 0.618D);

		for (int j = -i; j <= i; ++j) {
			for (int k = -i; k <= i; ++k) {
				if (Math.pow(Math.abs(j) + 0.5D, 2.0D) + Math.pow(Math.abs(k) + 0.5D, 2.0D) <= p_227233_4_ * p_227233_4_) {
					this.func_227219_b_(p_227233_1_, p_227233_2_, p_227233_3_.add(j, 0, k), p_227233_5_, p_227233_6_, p_227233_7_);
				}
			}
		}
	}

	private float func_227231_a_(int p_227231_1_, int p_227231_2_) {
		if (p_227231_2_ < p_227231_1_ * 0.3F) {
			return -1.0F;
		} else {
			float f = p_227231_1_ / 2.0F;
			float f1 = f - p_227231_2_;
			float f2 = MathHelper.sqrt(f * f - f1 * f1);
			if (f1 == 0.0F) {
				f2 = f;
			} else if (Math.abs(f1) >= f) {
				return 0.0F;
			}

			return f2 * 0.5F;
		}
	}

	private float func_227230_a_(int p_227230_1_) {
		if (p_227230_1_ >= 0 && p_227230_1_ < 5) {
			return p_227230_1_ != 0 && p_227230_1_ != 4 ? 3.0F : 2.0F;
		} else {
			return -1.0F;
		}
	}

	private void func_227236_a_(IWorldGenerationReader generationReader, Random random, BlockPos pos, Set<BlockPos> posSet1, MutableBoundingBox boundingBox, TreeFeatureConfig treeFeature) {
		for (int i = 0; i < 5; ++i) {
			this.func_227233_a_(generationReader, random, pos.up(i), this.func_227230_a_(i), posSet1, boundingBox, treeFeature);
		}
	}

	private int func_227235_a_(IWorldGenerationReader generationReader, Random random, BlockPos pos1, BlockPos pos2, boolean b0, Set<BlockPos> blockSet1, MutableBoundingBox boundingBox, TreeFeatureConfig treeFeature) {
		if (!b0 && Objects.equals(pos1, pos2)) {
			return -1;
		} else {
			BlockPos blockpos = pos2.add(-pos1.getX(), -pos1.getY(), -pos1.getZ());
			int i = this.func_227237_a_(blockpos);
			float f = (float) blockpos.getX() / (float) i;
			float f1 = (float) blockpos.getY() / (float) i;
			float f2 = (float) blockpos.getZ() / (float) i;

			for (int j = 0; j <= i; ++j) {
				BlockPos blockpos1 = pos1.add(0.5F + j * f, 0.5F + j * f1, 0.5F + j * f2);
				if (b0) {
					this.func_227217_a_(generationReader, blockpos1, treeFeature.trunkProvider.func_225574_a_(random, blockpos1).with(LogBlock.AXIS, this.func_227238_a_(pos1, blockpos1)), boundingBox);
					blockSet1.add(blockpos1);
				} else if (!func_214587_a(generationReader, blockpos1)) {
					return j;
				}
			}

			return -1;
		}
	}

	private int func_227237_a_(BlockPos pos) {
		int i = MathHelper.abs(pos.getX());
		int j = MathHelper.abs(pos.getY());
		int k = MathHelper.abs(pos.getZ());
		if (k > i && k > j) {
			return k;
		} else {
			return j > i ? j : i;
		}
	}

	private Direction.Axis func_227238_a_(BlockPos p_227238_1_, BlockPos p_227238_2_) {
		Direction.Axis direction$axis = Direction.Axis.Y;
		int i = Math.abs(p_227238_2_.getX() - p_227238_1_.getX());
		int j = Math.abs(p_227238_2_.getZ() - p_227238_1_.getZ());
		int k = Math.max(i, j);
		if (k > 0) {
			if (i == k) {
				direction$axis = Direction.Axis.X;
			} else if (j == k) {
				direction$axis = Direction.Axis.Z;
			}
		}

		return direction$axis;
	}

	private void func_227232_a_(IWorldGenerationReader p_227232_1_, Random p_227232_2_, int p_227232_3_, BlockPos p_227232_4_, List<ExtendedPos> p_227232_5_, Set<BlockPos> p_227232_6_, MutableBoundingBox p_227232_7_, TreeFeatureConfig p_227232_8_) {
		for (ExtendedPos fancytreefeature$extendedpos : p_227232_5_) {
			if (this.func_227239_b_(p_227232_3_, fancytreefeature$extendedpos.func_227243_r_() - p_227232_4_.getY())) {
				this.func_227236_a_(p_227232_1_, p_227232_2_, fancytreefeature$extendedpos, p_227232_6_, p_227232_7_, p_227232_8_);
			}
		}

	}

	private boolean func_227239_b_(int p_227239_1_, int p_227239_2_) {
		return p_227239_2_ >= p_227239_1_ * 0.2D;
	}

	private void func_227234_a_(IWorldGenerationReader p_227234_1_, Random p_227234_2_, BlockPos p_227234_3_, int p_227234_4_, Set<BlockPos> p_227234_5_, MutableBoundingBox p_227234_6_, TreeFeatureConfig p_227234_7_) {
		this.func_227235_a_(p_227234_1_, p_227234_2_, p_227234_3_, p_227234_3_.up(p_227234_4_), true, p_227234_5_, p_227234_6_, p_227234_7_);
	}

	private void func_227240_b_(IWorldGenerationReader p_227240_1_, Random p_227240_2_, int p_227240_3_, BlockPos p_227240_4_, List<ExtendedPos> p_227240_5_, Set<BlockPos> p_227240_6_, MutableBoundingBox p_227240_7_, TreeFeatureConfig p_227240_8_) {
		for (ExtendedPos fancytreefeature$extendedpos : p_227240_5_) {
			int i = fancytreefeature$extendedpos.func_227243_r_();
			BlockPos blockpos = new BlockPos(p_227240_4_.getX(), i, p_227240_4_.getZ());
			if (!blockpos.equals(fancytreefeature$extendedpos) && this.func_227239_b_(p_227240_3_, i - p_227240_4_.getY())) {
				this.func_227235_a_(p_227240_1_, p_227240_2_, blockpos, fancytreefeature$extendedpos, true, p_227240_6_, p_227240_7_, p_227240_8_);
			}
		}

	}

	@Override
	public boolean func_225557_a_(IWorldGenerationReader p_225557_1_, Random p_225557_2_, BlockPos p_225557_3_, Set<BlockPos> p_225557_4_, Set<BlockPos> p_225557_5_, MutableBoundingBox p_225557_6_, TreeFeatureConfig p_225557_7_) {
		Random random = new Random(p_225557_2_.nextLong());
		int i = this.func_227241_b_(p_225557_1_, p_225557_2_, p_225557_3_, 5 + random.nextInt(12), p_225557_4_, p_225557_6_, p_225557_7_);
		if (i == -1) {
			return false;
		} else {
			this.func_214584_a(p_225557_1_, p_225557_3_.down());
			int j = (int) (i * 0.618D);
			if (j >= i) {
				j = i - 1;
			}

			double d0 = 1.0D;
			int k = (int) (1.382D + Math.pow(1.0D * i / 13.0D, 2.0D));
			if (k < 1) {
				k = 1;
			}

			int l = p_225557_3_.getY() + j;
			int i1 = i - 5;
			List<ExtendedPos> list = Lists.newArrayList();
			list.add(new ExtendedPos(p_225557_3_.up(i1), l));

			for (; i1 >= 0; --i1) {
				float f = this.func_227231_a_(i, i1);
				if (!(f < 0.0F)) {
					for (int j1 = 0; j1 < k; ++j1) {
						double d1 = 1.0D;
						double d2 = 1.0D * f * (random.nextFloat() + 0.328D);
						double d3 = random.nextFloat() * 2.0F * Math.PI;
						double d4 = d2 * Math.sin(d3) + 0.5D;
						double d5 = d2 * Math.cos(d3) + 0.5D;
						BlockPos blockpos = p_225557_3_.add(d4, i1 - 1, d5);
						BlockPos blockpos1 = blockpos.up(5);
						if (this.func_227235_a_(p_225557_1_, p_225557_2_, blockpos, blockpos1, false, p_225557_4_, p_225557_6_, p_225557_7_) == -1) {
							int k1 = p_225557_3_.getX() - blockpos.getX();
							int l1 = p_225557_3_.getZ() - blockpos.getZ();
							double d6 = blockpos.getY() - Math.sqrt(k1 * k1 + l1 * l1) * 0.381D;
							int i2 = d6 > l ? l : (int) d6;
							BlockPos blockpos2 = new BlockPos(p_225557_3_.getX(), i2, p_225557_3_.getZ());
							if (this.func_227235_a_(p_225557_1_, p_225557_2_, blockpos2, blockpos, false, p_225557_4_, p_225557_6_, p_225557_7_) == -1) {
								list.add(new ExtendedPos(blockpos, blockpos2.getY()));
							}
						}
					}
				}
			}

			this.func_227232_a_(p_225557_1_, p_225557_2_, i, p_225557_3_, list, p_225557_5_, p_225557_6_, p_225557_7_);
			this.func_227234_a_(p_225557_1_, p_225557_2_, p_225557_3_, j, p_225557_4_, p_225557_6_, p_225557_7_);
			this.func_227240_b_(p_225557_1_, p_225557_2_, i, p_225557_3_, list, p_225557_4_, p_225557_6_, p_225557_7_);
			return true;
		}
	}

	private int func_227241_b_(IWorldGenerationReader p_227241_1_, Random p_227241_2_, BlockPos p_227241_3_, int p_227241_4_, Set<BlockPos> p_227241_5_, MutableBoundingBox p_227241_6_, TreeFeatureConfig p_227241_7_) {
		if (!isDirtOrGrassBlockOrFarmland(p_227241_1_, p_227241_3_.down())) {
			return -1;
		} else {
			int i = this.func_227235_a_(p_227241_1_, p_227241_2_, p_227241_3_, p_227241_3_.up(p_227241_4_ - 1), false, p_227241_5_, p_227241_6_, p_227241_7_);
			if (i == -1) {
				return p_227241_4_;
			} else {
				return i < 6 ? -1 : i;
			}
		}
	}

	static class ExtendedPos extends BlockPos {
		private final int field_227242_b_;

		public ExtendedPos(BlockPos p_i225804_1_, int p_i225804_2_) {
			super(p_i225804_1_.getX(), p_i225804_1_.getY(), p_i225804_1_.getZ());
			this.field_227242_b_ = p_i225804_2_;
		}

		public int func_227243_r_() {
			return this.field_227242_b_;
		}
	}
}