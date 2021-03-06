/*
 * Copyright 2014 Higher Frequency Trading
 *
 * http://www.higherfrequencytrading.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.chronicle.hash.serialization.internal;

import net.openhft.chronicle.hash.hashing.LongHashFunction;
import net.openhft.chronicle.hash.serialization.BytesInterop;
import net.openhft.chronicle.hash.serialization.BytesReader;
import net.openhft.chronicle.hash.serialization.SizeMarshaller;
import net.openhft.lang.io.Bytes;
import org.jetbrains.annotations.NotNull;

public enum IntegerMarshaller
        implements BytesInterop<Integer>, BytesReader<Integer>, SizeMarshaller {
    INSTANCE;

    @Override
    public long size(@NotNull Integer e) {
        return 4L;
    }

    @Override
    public int sizeEncodingSize(long size) {
        return 0;
    }

    @Override
    public long minEncodableSize() {
        return 4L;
    }

    @Override
    public int minSizeEncodingSize() {
        return 0;
    }

    @Override
    public int maxSizeEncodingSize() {
        return 0;
    }

    @Override
    public void writeSize(Bytes bytes, long size) {
        assert size == 4L;
        // do nothing
    }

    @Override
    public boolean startsWith(@NotNull Bytes bytes, @NotNull Integer e) {
        return e == bytes.readInt(bytes.position());
    }

    @Override
    public boolean equivalent(@NotNull Integer a, @NotNull Integer b) {
        return a.intValue() == b.intValue();
    }

    @Override
    public long hash(@NotNull LongHashFunction hashFunction, @NotNull Integer e) {
        return hashFunction.hashInt(e);
    }

    @Override
    public void write(@NotNull Bytes bytes, @NotNull Integer e) {
        bytes.writeInt(e);
    }

    @Override
    public long readSize(Bytes bytes) {
        return 4L;
    }

    @NotNull
    @Override
    public Integer read(@NotNull Bytes bytes, long size) {
        return bytes.readInt();
    }

    @NotNull
    @Override
    public Integer read(@NotNull Bytes bytes, long size, Integer toReuse) {
        return bytes.readInt();
    }
}
