package serverdateditor.ServerDatEditor.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.primitives.UnsignedInteger;
import lombok.SneakyThrows;
import serverdateditor.ServerDatEditor.ScriptData;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Stream;

public class BinaryUtils {

    public static <T extends ScriptData> void serialize(DataOutputStream writer, List list, T targetClass) {
        var objectMapper = new ObjectMapper();
        for (Object obj : list) {
            T converted = (T) objectMapper.convertValue(obj, targetClass.getClass());
            Stream.of(converted.getClass().getDeclaredFields()).forEach(field -> {
                try {
                    if (!field.getName().equals("serverDataHeader")) {
                        Object fieldValue = getFieldValue(converted, field);
                        if (field.getType().isAssignableFrom(int.class)) {
                            byte[] array = ByteBuffer.allocate(4).putInt((int) fieldValue).array();
                            writer.write(revertBytes(array));
                        } else if (field.getType().isAssignableFrom(long.class)) {
                            byte[] array = ByteBuffer.allocate(4).putInt(Integer.parseUnsignedInt(fieldValue + "")).array();
                            writer.write(revertBytes(array));
                        } else if (field.getType().isAssignableFrom(String.class)) {
                            Base64.getDecoder().decode((String) fieldValue);
                        } else if (field.getType().isAssignableFrom(byte[].class)) {
                            writer.write((byte[]) fieldValue);
                        } else if (field.getType().isAssignableFrom(byte.class)) {
                            writer.write((byte) fieldValue);
                        } else if (field.getType().isAssignableFrom(float.class)) {
                            byte[] array = ByteBuffer.allocate(4).putFloat((float) fieldValue).array();
                            writer.write(revertBytes(array));
                        } else if (field.getType().getSimpleName().equals("ArrayList")) {
                            ScriptData classTypeField = (ScriptData) ((List) fieldValue).get(0);
                            serialize(writer, (List) Objects.requireNonNull(fieldValue), classTypeField);
                        } else if (!field.getType().isPrimitive() && field.getType().isAssignableFrom(Object.class)) {
                            serialize(writer, Collections.singletonList(fieldValue), (ScriptData) fieldValue);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @SneakyThrows
    public static <T> List unSerialize(DataInputStream reader, T obj) {
        List<T> list = new ArrayList<>();
        unSerializeChildren(reader, obj, list);
        return list;
    }

    @SneakyThrows
    private static byte[] ReadAscii(DataInputStream input) {
        var strBytes = new ArrayList<>();
        int b;
        while ((b = input.readByte()) != 0x00) {
            strBytes.add((byte) b);
        }
        var resultBytes = new byte[strBytes.size()];
        for (int i = 0; i < strBytes.size(); i++) {
            resultBytes[i] = (byte) strBytes.get(i);
        }
        return resultBytes;
    }

    public static byte[] revertBytes(byte[] readNBytes) {
        var reversed = new byte[readNBytes.length];
        for (int i = readNBytes.length - 1, j = 0; i >= 0; i--, j++) {
            reversed[j] = readNBytes[i];
        }
        return reversed;
    }

    @SneakyThrows
    private static void unSerializeChildren(DataInputStream reader, Object obj, List list) {
        while (reader.available() > 0) {
            for (Field field : obj.getClass().getDeclaredFields()) {
                try {
                    if (field.getType().isAssignableFrom(int.class)) {
                        setFieldValue(obj, field, ByteBuffer.wrap(revertBytes(reader.readNBytes(4))).getInt());
                    } else if (field.getType().isAssignableFrom(long.class)) {
                        setFieldValue(obj, field, Long.parseLong(ByteBuffer.wrap(revertBytes(reader.readNBytes(4))).getInt() + ""));
                    } else if (field.getType().isAssignableFrom(byte[].class)) {
                        var val = (byte[]) getFieldValue(obj, field);
                        if (val.length > 0) {
                            setFieldValue(obj, field, reader.readNBytes(val.length));
                        } else {
                            var x = ReadAscii(reader);
                            setFieldValue(obj, field, x);
                        }
                    } else if (field.getType().isAssignableFrom(byte.class)) {
                        setFieldValue(obj, field, reader.readByte());
                    } else if (field.getType().isAssignableFrom(float.class)) {
                        setFieldValue(obj, field, ByteBuffer.wrap(revertBytes(reader.readNBytes(4))).getFloat());
                    } else if (field.getType().getSimpleName().equals("ArrayList")) {
                        for (Object child : (List) getFieldValue(obj, field)) {
                            unSerialize(reader, child);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            obj = Stream.of(Class.forName(obj.getClass().getName())
                    .getDeclaredConstructors()).filter(c -> c.getParameterCount() == 0)
                    .findFirst().orElseThrow()
                    .newInstance();
        }
    }

    private static Object getFieldValue(Object obj, Field field) {
        try {
            String methodName = "get" + ("" + field.getName().charAt(0)).toUpperCase()
                    + field.getName().substring(1);
            return Stream.of(obj.getClass().getDeclaredMethods()).filter(
                    f -> f.getName().equals(methodName)
            ).findFirst().orElseThrow().invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            if (field.getType().isAssignableFrom(byte[].class)) {
                return null;
            } else {
                return 0;
            }
        }
    }

    private static void setFieldValue(Object obj, Field field, Object value) {
        try {
            var methodName = "set" + ("" + field.getName().charAt(0)).toUpperCase()
                    + field.getName().substring(1).replace(" ", "");
            Stream.of(obj.getClass().getDeclaredMethods())
                    .filter(f -> f.getName().equals(methodName))
                    .findFirst().orElseThrow().invoke(obj, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
