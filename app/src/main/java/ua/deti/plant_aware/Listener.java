package ua.deti.plant_aware;

import java.util.ArrayList;

import ua.deti.plant_aware.model.Plant;

public class Listener {

    private ArrayList<String> warnings;
    private Plant p;
    private double temp, soil, wind;

    public Listener()
    {
        this.warnings = new ArrayList<>();
    }

    public void process(Plant p, double temp, double soil, double wind)
    {
        if(temp <= p.getIdealTemp() / 2 || temp >= p.getIdealTemp() * 2)
        {
            this.warnings.add("Warning, the temperature around your " + p.getPlantname() + " is reaching critical levels");
        }

        if(soil <= p.getIdealSoil() / 2 || soil >= p.getIdealSoil() * 2)
        {
            this.warnings.add("Warning, the soil humidity around your " + p.getPlantname() + " is reaching critical levels");
        }

        if(wind <= p.getIdealWind() / 2 || wind >= p.getIdealWind() * 2)
        {
            this.warnings.add("Warning, the wind around your " + p.getPlantname() + " is reaching critical levels");
        }

    }

    public ArrayList<String> getWarnings(){
        ArrayList<String> ret = this.warnings;
        this.warnings.clear();
        return ret;
    }

}
