package bankomate.services;

import java.io.IOException;

public interface IOService {
    String read() throws IOException;
    void write(String message);
    void writeUnknownError();
    public int readInt();
    public long readLong();

}
