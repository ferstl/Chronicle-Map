/*
 * Copyright 2015 Higher Frequency Trading
 *
 *  http://www.higherfrequencytrading.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.openhft.chronicle.map;

final class MapMethodsSupport {

    static <K, V> void returnCurrentValueIfPresent(
            MapQueryContext<K, V> q, ReturnValue<? super V> returnValue) {
        MapEntry<K, V> entry = q.entry();
        if (entry != null)
            returnValue.returnValue(entry.value());
    }

    static <K, V> boolean tryReturnCurrentValueIfPresent(
            MapQueryContext<K, V> q, ReturnValue<? super V> returnValue) {
        if (q.readLock().tryLock()) {
            MapEntry<K, V> entry = q.entry();
            if (entry != null) {
                returnValue.returnValue(entry.value());
                return true;
            }
            // Key is absent
            q.readLock().unlock();
        }
        q.updateLock().lock();
        MapEntry<K, V> entry = q.entry();
        if (entry != null) {
            returnValue.returnValue(entry.value());
            return true;
        }
        return false;
    }

    private MapMethodsSupport() {}
}
