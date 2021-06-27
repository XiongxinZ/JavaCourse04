import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XlassLoader extends ClassLoader {

    public static void main(String[] args) throws Exception {
        // 相关参数
        final String className = "Hello";
        // 创建类加载器
        ClassLoader classLoader = new XlassLoader();
        // 加载相应的类
        Class<?> clazz = ((XlassLoader) classLoader).findClass(className);
        // 创建对象
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 遍历对象的所有实例方法并调用
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println(clazz.getSimpleName() + "." + m.getName());
            // 调用实例方法
            Method method = clazz.getMethod(m.getName());
            method.invoke(instance);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 将路径转化为包名
        String resourcePath = name.replace("/", ".");
        // 文件后缀
        final String suffix = ".xlass";
        // 获取输入流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);
        try {
            // 读取数据
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            // 存储在数组里
            inputStream.read(byteArray);
            // 所有字节作(x=255-x)处理
            byte[] classBytes = decode(byteArray);
            // 将字节码转化为对应类的实例
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
    }

    // 破译字节码的编码
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

    // 关闭资源
    private static void close(Closeable res) {
        if (res != null) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}