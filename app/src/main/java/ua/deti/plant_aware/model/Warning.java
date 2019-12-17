package ua.deti.plant_aware.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

@Getter
@Document(collection = "main")
public class Warning {

    private String w;
    private String ts;

    public Warning(String w, String ts)
    {
        this.w = w;
        this.ts = ts;
    }

    public String getW()
    {
        return this.w;
    }

    public void setW(String w)
    {
        this.w = w;
    }


    public String getTs()
    {
        return this.ts;
    }

    public void setTs(String ts)
    {
        this.ts = ts;
    }

}