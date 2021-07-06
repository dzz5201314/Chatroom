package com.dz.protocol;

import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;

/*
* 用于扩展序列化、反序列化算法
* */
public interface Serializer {

    //反序列化方法
    <T> T deserialize(Class<T> clazz,byte[] bytes);

    //序列化方法
    <T> byte[] serialize(T object);

    enum Algorithm implements Serializer{

        Java {
            @Override
            public <T> T deserialize(Class<T> clazz, byte[] bytes) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                    return (T) ois.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException("反序列化失败",e);
                }
            }

            @Override
            public <T> byte[] serialize(T object) {
                try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(bos);
                    oos.writeObject(object);
                    return bos.toByteArray();
                }catch (IOException e){
                    throw new RuntimeException("序列化失败",e);
                }
            }
        },
        Json{
            @Override
            public <T> T deserialize(Class<T> clazz, byte[] bytes) {
                String json = new String(bytes,StandardCharsets.UTF_8);
                return new Gson().fromJson(json,clazz);
            }

            @Override
            public <T> byte[] serialize(T object) {
                String json = new Gson().toJson(object);
                return json.getBytes(StandardCharsets.UTF_8);

            }
        }
    }
}
