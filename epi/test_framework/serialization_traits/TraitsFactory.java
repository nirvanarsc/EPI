
package epi.test_framework.serialization_traits;

import epi.BinaryTree;
import epi.BinaryTreeNode;
import epi.BstNode;
import epi.ListNode;
import epi.test_framework.EpiUserType;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TraitsFactory {
  private static final Map<Type, SerializationTraits> PRIMITIVE_TYPES_MAPPING = new HashMap<>();

  static {
        PRIMITIVE_TYPES_MAPPING.put(String.class, new StringTraits());
        PRIMITIVE_TYPES_MAPPING.put(Integer.class, new IntegerTraits());
        PRIMITIVE_TYPES_MAPPING.put(int.class, new IntegerTraits());
        PRIMITIVE_TYPES_MAPPING.put(Short.class, new ShortTraits());
        PRIMITIVE_TYPES_MAPPING.put(short.class, new ShortTraits());
        PRIMITIVE_TYPES_MAPPING.put(Long.class, new LongTraits());
        PRIMITIVE_TYPES_MAPPING.put(long.class, new LongTraits());
        PRIMITIVE_TYPES_MAPPING.put(Character.class, new CharacterTraits());
        PRIMITIVE_TYPES_MAPPING.put(char.class, new CharacterTraits());
        PRIMITIVE_TYPES_MAPPING.put(Boolean.class, new BooleanTraits());
        PRIMITIVE_TYPES_MAPPING.put(boolean.class, new BooleanTraits());
        PRIMITIVE_TYPES_MAPPING.put(Float.class, new FloatTraits());
        PRIMITIVE_TYPES_MAPPING.put(float.class, new FloatTraits());
        PRIMITIVE_TYPES_MAPPING.put(Double.class, new DoubleTraits());
        PRIMITIVE_TYPES_MAPPING.put(double.class, new DoubleTraits());
  }

  public static SerializationTraits getTraits(Type type) {
    if (type.equals(Void.TYPE)) {
      return new VoidTraits();
    }
    if (type instanceof ParameterizedType) {
      ParameterizedType pt = (ParameterizedType)type;
      Type ty = pt.getRawType();
      if (ty.equals(List.class)) {
        return new ListTraits(getTraits(getInnerGenericType(pt, 0)));
      }
      if (ty.equals(Iterable.class)) {
        return new ListTraits(getTraits(getInnerGenericType(pt, 0)));
      }
      if (ty.equals(Set.class)) {
        return new SetTraits(getTraits(getInnerGenericType(pt, 0)));
      }
      if (ty.equals(BinaryTreeNode.class) || ty.equals(BinaryTree.class) ||
          ty.equals(BstNode.class)) {
        return new BinaryTreeTraits((Class<?>)ty,
                                    getTraits(getInnerGenericType(pt, 0)));
      }
      if (ty.equals(ListNode.class)) {
        return new LinkedListTraits(getTraits(getInnerGenericType(pt, 0)));
      }
    }

    SerializationTraits mapped = PRIMITIVE_TYPES_MAPPING.get(type);
    if (mapped != null) {
      return mapped;
    }

    if (type instanceof Class) {
      EpiUserType ann =
          (EpiUserType)((Class)type).getAnnotation(EpiUserType.class);

      return new UserTypeTraits((Class)type, ann);
    }

    throw new RuntimeException("Unsupported argument type: " +
                               type.getTypeName());
  }

  private static Type getInnerGenericType(Type type, int idx) {
    if (type instanceof ParameterizedType) {
      return ((ParameterizedType)type).getActualTypeArguments()[idx];
    } else {
      throw new RuntimeException(type.getTypeName() +
                                 " has no generic type arguments");
    }
  }
}
