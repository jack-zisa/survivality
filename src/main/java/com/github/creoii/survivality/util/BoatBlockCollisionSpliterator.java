package com.github.creoii.survivality.util;

import com.google.common.collect.AbstractIterator;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.CuboidBlockIterator;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.*;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.CollisionView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BoatBlockCollisionSpliterator extends AbstractIterator<VoxelShape> {
    private final Box box;
    private final ShapeContext context;
    private final CuboidBlockIterator blockIterator;
    private final BlockPos.Mutable pos;
    private final VoxelShape boxShape;
    private final CollisionView world;
    @Nullable
    private BlockView chunk;
    private long chunkPos;

    public BoatBlockCollisionSpliterator(CollisionView world, @Nullable Entity entity, Box box) {
        this.context = entity == null ? ShapeContext.absent() : ShapeContext.of(entity);
        this.pos = new BlockPos.Mutable();
        this.boxShape = VoxelShapes.cuboid(box);
        this.world = world;
        this.box = box;
        int i = MathHelper.floor(box.minX - 1.0E-7) - 1;
        int j = MathHelper.floor(box.maxX + 1.0E-7) + 1;
        int k = MathHelper.floor(box.minY - 1.0E-7) - 1;
        int l = MathHelper.floor(box.maxY + 1.0E-7) + 1;
        int m = MathHelper.floor(box.minZ - 1.0E-7) - 1;
        int n = MathHelper.floor(box.maxZ + 1.0E-7) + 1;
        this.blockIterator = new CuboidBlockIterator(i, k, m, j, l, n);
    }

    @Nullable
    private BlockView getChunk(int x, int z) {
        int i = ChunkSectionPos.getSectionCoord(x);
        int j = ChunkSectionPos.getSectionCoord(z);
        long l = ChunkPos.toLong(i, j);
        if (this.chunk != null && this.chunkPos == l) {
            return this.chunk;
        } else {
            BlockView blockView = this.world.getChunkAsView(i, j);
            this.chunk = blockView;
            this.chunkPos = l;
            return blockView;
        }
    }

    protected VoxelShape computeNext() {
        while(true) {
            if (this.blockIterator.step()) {
                int i = this.blockIterator.getX();
                int j = this.blockIterator.getY();
                int k = this.blockIterator.getZ();
                int l = this.blockIterator.getEdgeCoordinatesCount();
                if (l == 3) {
                    continue;
                }

                BlockView blockView = this.getChunk(i, k);
                if (blockView == null) {
                    continue;
                }

                this.pos.set(i, j, k);
                BlockState blockState = blockView.getBlockState(this.pos);
                if (blockState.isOf(Blocks.LILY_PAD)) continue;
                if (!blockState.shouldSuffocate(blockView, this.pos) || l == 1 && !blockState.exceedsCube() || l == 2 && !blockState.isOf(Blocks.MOVING_PISTON)) {
                    continue;
                }

                VoxelShape voxelShape = blockState.getCollisionShape(this.world, this.pos, this.context);
                if (voxelShape == VoxelShapes.fullCube()) {
                    if (!this.box.intersects(i, j, k, (double)i + 1.0, (double)j + 1.0, (double)k + 1.0)) {
                        continue;
                    }

                    return voxelShape.offset(i, j, k);
                }

                VoxelShape voxelShape2 = voxelShape.offset(i, j, k);
                if (!VoxelShapes.matchesAnywhere(voxelShape2, this.boxShape, BooleanBiFunction.AND)) {
                    continue;
                }

                return voxelShape2;
            }

            return this.endOfData();
        }
    }

    public List<VoxelShape> collectAll() {
        ArrayList<VoxelShape> collisions = new ArrayList<>();

        while(this.hasNext()) {
            collisions.add(this.next());
        }

        return collisions;
    }
}
