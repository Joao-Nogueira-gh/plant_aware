package ua.deti.plant_aware;

import java.util.ArrayList;

import ua.deti.plant_aware.model.*;

public class Listener {

    private ArrayList<Warning> warnings;

    public Listener()
    {
        this.warnings = new ArrayList<>();
    }

    public void process(Plant p, double temp, double soil, double wind, String ts)
    {
        if(temp <= p.getIdealTemp() / 2 || temp >= p.getIdealTemp() * 2)
        {
            this.warnings.add(new Warning("Warning, the temperature around your " + p.getPlantname() + " is reaching critical levels", ts));
        }

        if(soil <= p.getIdealSoil() / 2 || soil >= p.getIdealSoil() * 2)
        {
            this.warnings.add(new Warning("Warning, the soil humidity around your " + p.getPlantname() + " is reaching critical levels", ts));
        }

        if(wind <= p.getIdealWind() / 2 || wind >= p.getIdealWind() * 2)
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
