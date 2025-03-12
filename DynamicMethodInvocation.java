
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.nio.file.*;
import java.lang.reflect.Method;

public class DynamicMethodInvocation {
    public static void main(String[] args) {
        try {
            // URL del archivo .jar en GitHub
            String jarUrl = "https://github.com/seba23571/DummyRepo/raw/refs/heads/main/TestSaludo.jar";
            String cachePath = "TestSaludo.jar";

            // Descargar el JAR si no está en caché
            if (!Files.exists(Paths.get(cachePath))) {
                URLConnection connection = new URL(jarUrl).openConnection();
                connection.setConnectTimeout(5000); // 5 segundos
                connection.setReadTimeout(10000); // 10 segundos
                Files.copy(connection.getInputStream(), Paths.get(cachePath), StandardCopyOption.REPLACE_EXISTING);
            }

            // Cargar el JAR desde caché
            URLClassLoader classLoader = new URLClassLoader(new URL[]{Paths.get(cachePath).toUri().toURL()});
            
            // Cargar la clase y ejecutar el método
            Class<?> clazz = classLoader.loadClass("org.example.Saludador");
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getMethod("saludar");
            method.invoke(instance);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
