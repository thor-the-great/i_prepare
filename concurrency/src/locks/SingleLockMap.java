package locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SingleLockMap<K, V>{
    Lock lock = new ReentrantLock();
    Map<K, V> m;

    SingleLockMap() {
        m = new HashMap();
    }

    void put(K key, V value) {
        lock.lock();
        m.put(key, value);
        lock.unlock();
    }

    V get(K key) {
        V v;
        lock.lock();
        v = m.get(key);
        lock.unlock();
        return v;
    }
}
