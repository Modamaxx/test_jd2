package service.api;

import java.io.IOException;

public interface ICalculationService {
    public int[] Department() throws IOException;

    public int[] Position() throws IOException;

    public String pageCount(double limit);
}
