package aurocosh.automessagelib.network.base.serialization.interfaces;

@FunctionalInterface
public interface ContainerGenerator<T> {
    T get(int capacity);
}
