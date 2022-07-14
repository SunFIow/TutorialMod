package com.sunflow.tutorialmod.entity.centaur;

import com.sunflow.tutorialmod.TutorialMod;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CentaurModel extends QuadrupedModel<CentaurEntity> {
	public static final String MAIN = "main";
	public static final ModelLayerLocation CENTAUR_LAYER = new ModelLayerLocation(new ResourceLocation(TutorialMod.MODID, "centaur"), MAIN);

	public CentaurModel(ModelPart root) {
		super(root, false, 10.0f, 4.0f, 2.0f, 2.0f, 24);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create()
				.texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F)
				.texOffs(22, 0).addBox("right_horn", -5.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F)
				.texOffs(22, 0).addBox("left_horn", 4.0F, -5.0F, -4.0F, 1.0F, 3.0F, 1.0F),
				PartPose.offset(0.0F, 4.0F, -8.0F));
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create()
				.texOffs(18, 4).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F)
				.texOffs(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F),
				PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float) Math.PI / 2F), 0.0F, 0.0F));
		CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
		partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-4.0F, 12.0F, 7.0F));
		partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(4.0F, 12.0F, 7.0F));
		partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder, PartPose.offset(-4.0F, 12.0F, -6.0F));
		partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(4.0F, 12.0F, -6.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
