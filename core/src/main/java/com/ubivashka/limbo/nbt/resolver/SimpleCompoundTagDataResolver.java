package com.ubivashka.limbo.nbt.resolver;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ubivashka.limbo.nbt.resolver.annotation.ListTagAnnotation;
import com.ubivashka.limbo.nbt.resolver.annotation.TagData;
import com.ubivashka.limbo.nbt.type.ByteArrayTag;
import com.ubivashka.limbo.nbt.type.ByteTag;
import com.ubivashka.limbo.nbt.type.CompoundTag;
import com.ubivashka.limbo.nbt.type.DoubleTag;
import com.ubivashka.limbo.nbt.type.FloatTag;
import com.ubivashka.limbo.nbt.type.IntArrayTag;
import com.ubivashka.limbo.nbt.type.IntTag;
import com.ubivashka.limbo.nbt.type.ListTag;
import com.ubivashka.limbo.nbt.type.LongArrayTag;
import com.ubivashka.limbo.nbt.type.LongTag;
import com.ubivashka.limbo.nbt.type.ShortTag;
import com.ubivashka.limbo.nbt.type.StringTag;
import com.ubivashka.limbo.nbt.type.Tag;
import com.ubivashka.limbo.nbt.type.TagDataType;


public class SimpleCompoundTagDataResolver extends AbstractCompoundTagDataResolver {
    @Override
    protected void processField(Object fieldObject, CompoundTagDataResolverContext context) {
        CompoundTag tag = context.getCompoundTag();
        String key = context.getKey();
        TagData tagData = context.getTagData();
        tag.add(key, SimpleTagFactory.getFactory(tagData.type().getId()).createTag(fieldObject, context));
    }

    private static class SimpleTagFactory<T> {
        private static final SimpleTagFactory<Object> BYTE_FACTORY = new SimpleTagFactory<>(object -> {
            if (object instanceof Byte)
                return new ByteTag((Byte) object);
            if (object instanceof Boolean)
                return new ByteTag((Boolean) object);
            throw new IllegalStateException("Cannot resolve object " + object.getClass().getSimpleName() + " with value " + object);
        });
        private static final SimpleTagFactory<Short> SHORT_FACTORY = new SimpleTagFactory<>(ShortTag::new);
        private static final SimpleTagFactory<Integer> INTEGER_FACTORY = new SimpleTagFactory<>(IntTag::new);
        private static final SimpleTagFactory<Long> LONG_FACTORY = new SimpleTagFactory<>(LongTag::new);
        private static final SimpleTagFactory<Float> FLOAT_FACTORY = new SimpleTagFactory<>(FloatTag::new);
        private static final SimpleTagFactory<Double> DOUBLE_FACTORY = new SimpleTagFactory<>(DoubleTag::new);
        private static final SimpleTagFactory<byte[]> BYTE_ARRAY_FACTORY = new SimpleTagFactory<>(ByteArrayTag::new);
        private static final SimpleTagFactory<Object> STRING_FACTORY = new SimpleTagFactory<>(object -> new StringTag(object.toString()));
        private static final SimpleTagFactory<Object> LIST_FACTORY = new SimpleTagFactory<>((object, context) -> {
            if (object instanceof ListTag)
                return (ListTag<?>) object;
            if (object instanceof Collection && context.getField().isAnnotationPresent(ListTagAnnotation.class)) {
                ListTagAnnotation listTag = context.getField().getAnnotation(ListTagAnnotation.class);
                Collection<?> collection = (Collection<?>) object;
                TagDataType dataType = listTag.value();
                if (listTag.shouldResolve())
                    collection = collection.stream().map(collectionObject -> SimpleTagFactory.getFactory(dataType.getId()).createTag(collectionObject, context)).collect(
                            Collectors.toList());

                return new ListTag(dataType.getTagClass(), collection);
            }
            throw new IllegalStateException("Cannot resolve object " + object.getClass().getSimpleName() + ",field name " + context.getField().getName() + ", value " + object);
        });
        private static final SimpleTagFactory<Object> COMPOUND_FACTORY = new SimpleTagFactory<>((object, context) -> {
            if (object instanceof CompoundTag)
                return (CompoundTag) object;
            return (Tag) context.getResolver().resolveTag(object, context.getProtocolId());
        });
        private static final SimpleTagFactory<int[]> INT_ARRAY_FACTORY = new SimpleTagFactory<>(IntArrayTag::new);
        private static final SimpleTagFactory<long[]> LONG_ARRAY_FACTORY = new SimpleTagFactory<>(LongArrayTag::new);
        private static final SimpleTagFactory<?>[] TAG_FACTORIES = new SimpleTagFactory<?>[]{BYTE_FACTORY, SHORT_FACTORY, INTEGER_FACTORY, LONG_FACTORY, FLOAT_FACTORY, DOUBLE_FACTORY,
                BYTE_ARRAY_FACTORY, STRING_FACTORY, LIST_FACTORY, COMPOUND_FACTORY, INT_ARRAY_FACTORY, LONG_ARRAY_FACTORY};
        private BiFunction<T, CompoundTagDataResolverContext, Tag> tagCreateFunction;

        public SimpleTagFactory(Function<T, Tag> tagCreateFunction) {
            this.tagCreateFunction = (object, resolver) -> tagCreateFunction.apply(object);
        }

        public SimpleTagFactory(BiFunction<T, CompoundTagDataResolverContext, Tag> tagCreateFunction) {
            this.tagCreateFunction = tagCreateFunction;
        }

        public Tag createTag(Object object, CompoundTagDataResolverContext context) {
            return tagCreateFunction.apply((T) object, context);
        }

        public static SimpleTagFactory<?> getFactory(int typeId) {
            SimpleTagFactory<?> tagFactory = TAG_FACTORIES[9];
            return TAG_FACTORIES[typeId];
        }
    }
}
