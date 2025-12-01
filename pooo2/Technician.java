
import java.util.ArrayList;

public class Technician{
    private static int nextId = 1;
    private final int id;
    private String name;
    private ArrayList<Incident> incidentsAssigned;

    public Technician(String name){
        this.id = nextId++;
        this.name = name;
        this.incidentsAssigned = new ArrayList<>();
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getIncidents(){
        String outIncidents = "";
        for (Incident incident : incidentsAssigned) {
            outIncidents += "\n" + incident.toString();
        }
        return outIncidents;
    }

    public void setName(String name){
        this.name = name;
    }

    public void assignToIncident(Incident incident){
        incidentsAssigned.add(incident);
    }

    @Override
    public String toString(){
        return this.id + " " + this.name + " " + incidentsAssigned.size();
    }
}