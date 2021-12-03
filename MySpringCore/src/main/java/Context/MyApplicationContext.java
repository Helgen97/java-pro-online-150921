package Context;

import Anotations.MyBean;
import Anotations.MyConfiguration;
import Exceptions.NotConfigurationClassException;
import Exceptions.NotFoundBeanException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MyApplicationContext {
    private final HashMap<Class<?>, Object> beanMap = new HashMap<>();

    public MyApplicationContext(Class<?> configClass) throws NotConfigurationClassException {
        if (!configClass.isAnnotationPresent(MyConfiguration.class)) {
            throw new NotConfigurationClassException("Input class are not configuration class!");
        } else {
            try {
                makingBeans(configClass);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public Object getBean(Class type) throws NotFoundBeanException {
        if (beanMap.containsKey(type)) {
            return beanMap.get(type);
        } else {
            throw new NotFoundBeanException("Not such bean from input class!");
        }
    }

    private void makingBeans(Class<?> configClass) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        Object o = configClass.getConstructor().newInstance();

        Method[] methods = configClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBean.class)) {
                Object clazz = method.invoke(o);
                beanMap.put(method.getReturnType(), clazz);
            }
        }
    }


}
