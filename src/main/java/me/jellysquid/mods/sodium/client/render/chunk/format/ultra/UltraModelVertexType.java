package me.jellysquid.mods.sodium.client.render.chunk.format.ultra;

import me.jellysquid.mods.sodium.client.gl.attribute.GlVertexAttribute;
import me.jellysquid.mods.sodium.client.gl.attribute.GlVertexAttributeFormat;
import me.jellysquid.mods.sodium.client.gl.attribute.GlVertexFormat;
import me.jellysquid.mods.sodium.client.model.vertex.buffer.VertexBufferView;
import me.jellysquid.mods.sodium.client.model.vertex.type.BlittableVertexType;
import me.jellysquid.mods.sodium.client.model.vertex.type.ChunkVertexType;
import me.jellysquid.mods.sodium.client.render.chunk.format.ChunkMeshAttribute;
import me.jellysquid.mods.sodium.client.render.chunk.format.ModelVertexSink;
import net.minecraft.client.render.VertexConsumer;

/**
 * Uses half-precision floating point numbers to represent position coordinates and normalized unsigned shorts for
 * texture coordinates. All texel positions in the block diffuse texture atlas can be exactly mapped (including
 * their centering offset), as the
 */
public class UltraModelVertexType implements ChunkVertexType {
    public static final GlVertexFormat<ChunkMeshAttribute> VERTEX_FORMAT = GlVertexAttribute.builder(ChunkMeshAttribute.class, 16)
            .addElement(ChunkMeshAttribute.POSITION, 0, GlVertexAttributeFormat.UNSIGNED_INT_2_10_10_10_REV, 4, false)
            .addElement(ChunkMeshAttribute.COLOR, 4, GlVertexAttributeFormat.UNSIGNED_BYTE, 4, true)
            .addElement(ChunkMeshAttribute.TEXTURE, 8, GlVertexAttributeFormat.UNSIGNED_SHORT, 2, false)
            .addElement(ChunkMeshAttribute.LIGHT, 12, GlVertexAttributeFormat.UNSIGNED_SHORT, 2, true)
            .build();

    public static final float MODEL_SCALE = (1.0f / 32.0f);
    public static final float TEXTURE_SCALE = (1.0f / 32768.0f);

    @Override
    public ModelVertexSink createFallbackWriter(VertexConsumer consumer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ModelVertexSink createBufferWriter(VertexBufferView buffer, boolean direct) {
        return direct ? new UltraModelVertexBufferWriterUnsafe(buffer) : new UltraModelVertexBufferWriterNio(buffer);
    }

    @Override
    public BlittableVertexType<ModelVertexSink> asBlittable() {
        return this;
    }

    @Override
    public GlVertexFormat<ChunkMeshAttribute> getCustomVertexFormat() {
        return VERTEX_FORMAT;
    }

    @Override
    public float getModelScale() {
        return MODEL_SCALE;
    }

    @Override
    public float getTextureScale() {
        return TEXTURE_SCALE;
    }
}
