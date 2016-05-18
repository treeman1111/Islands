package com.duncangb.islands.terrain;

import java.io.IOException;

public interface IChunk extends ITileContainer{
    void unload() throws IOException;
}
