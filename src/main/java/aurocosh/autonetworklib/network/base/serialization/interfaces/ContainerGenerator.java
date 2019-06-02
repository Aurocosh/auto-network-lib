package aurocosh.autonetworklib.network.base.serialization.interfaces;

@FunctionalInterface
public interface ContainerGenerator<T> {
    T get(int capacity);
}
