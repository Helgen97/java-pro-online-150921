import Anotations.MyBean;
import Anotations.MyConfiguration;
import Context.MyApplicationContext;

public class Main {
    public static void main(String[] args) {
        try {
            MyApplicationContext context = new MyApplicationContext(ConfigurationClass.class);
            World world = (World) context.getBean(World.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


@MyConfiguration
class ConfigurationClass {

    public ConfigurationClass() {

    }

    @MyBean
    public World world() {
        return new World(hello());
    }

    @MyBean
    public Hello hello() {
        return new Hello();
    }
}

class Hello {

    public Hello() {
        System.out.println("Hello");
    }
}

class World {

    public World(Hello hello) {
        System.out.println("Hello world!");
    }
}
