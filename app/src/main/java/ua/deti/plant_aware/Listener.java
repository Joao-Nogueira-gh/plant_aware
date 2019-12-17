package ua.deti.plant_aware;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ua.deti.plant_aware.model.*;

public class Listener {

    private ArrayList<Warning> warnings;

    public Listener()
    {
        this.warnings = new ArrayList<>();
    }

    public void process(Plant p, double temp, double soil, double wind, String timestamp)
    {

        long yourSeconds = Long.valueOf(timestamp);
        Date d = new Date(yourSeconds * 1000);
        DateFormat df = new SimpleDateFormat("dd MMM yyyy hh:mm:ss zzz");
        System.out.println(df.format(d));
        String ts = df.format(d).toString();
        
        if(temp <= p.getIdealTemp() / 2 || temp >= p.getIdealTemp() * 2)
        {
            this.warnings.add(new Warning("Warning, the temperature around your " + p.getPlantname() + " is reaching critical levels", ts));
        }

        if(soil <= p.getIdealSoil() / 2 || soil >= p.getIdealSoil() * 2)
        {
            this.warnings.add(new Warning("Warning, the soil humidity around your " + p.getPlantname() + " is reaching critical levels", ts));
        }

        if(wind <= p.getIdealWind() / 4 || wind >= p.getIdealWind() * 4)
        {
            this.warnings.add(new Warning("Warning, the wind around your " + p.getPlantname() + " is reaching critical levels", ts));
        }

        System.out.println(this.warnings.size());

    }

    public ArrayList<Warning> getWarnings(){
        return this.warnings;
    }

    public void flush()
    {
        this.warnings.clear();
    }

}
