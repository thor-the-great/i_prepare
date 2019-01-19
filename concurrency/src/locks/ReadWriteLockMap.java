package locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMap<K, V>{
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();
    Map<K, V> m;

    ReadWriteLockMap() {
        m = new HashMap();
    }

    void put(K key, V value) {
        writeLock.lock();
        m.put(key, value);
        writeLock.unlock();
    }

    V get(K key) {
        V v;
        readLock.lock();
        v = m.get(key);
        readLock.unlock();
        return v;
    }
}
