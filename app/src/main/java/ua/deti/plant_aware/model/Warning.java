package ua.deti.plant_aware.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "main")
public class Warning {

    @Autowired
    private String warn;
    @Autowired
    private String ts;

    public Warning(String w, String ts)
    {
        this.warn = w;
        this.ts = ts;
    }

}