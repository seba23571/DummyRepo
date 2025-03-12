package org.example.git;

import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;

public class DynamicMethodInvocation {
    public static void main(String[] args) {
        try {
            // URL del archivo .jar en GitHub
            String jarUrl = "https://github.com/seba23571/DummyRepo/raw/refs/heads/main/TestSaludo.jar";
            
            // Crear un ClassLoader con el JAR remoto
            URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL(jarUrl)});
            
            // Cargar la clase desde el JAR
            Class<?> clazz = classLoader.loadClass("org.example.Saludador");
            
            // Crear una instancia y ejecutar el método
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getMethod("saludar");
            method.invoke(instance);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}