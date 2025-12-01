import java.time.LocalDateTime;

public class Incident{
    private static int nextId = 1;
    private final int id;
    private String description;
    private Type type;
    private Status status;
    private int criticality;
    private final LocalDateTime dateReported;
    private int place;
    private LocalDateTime dateSolved;
    private Technician technician;

    public Incident(String description, Type type, int place){
        this.id = nextId++;
        this.description = description;
        this.type = type;
        this.status = Status.ACTIVE; 
        this.dateReported = LocalDateTime.now();
        this.place = place;
    }

    public int getId(){
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }

    public Type getType(){
        return this.type;
    }

    public Status getStatus(){
        return this.status;
    }

    public int getCriticality(){
        return this.criticality;
    }

    public LocalDateTime getDateReported(){
        return this.dateReported;
    }

    public int getPlace(){
        return this.place;
    }

    public Technician getTechnician(){
        return this.technician;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setType(Type type){
        this.type = type;
    }

    public void setStatus(Status status){
        this.status = status;
        if(this.status == Status.RESOLVED){
            this.dateSolved = LocalDateTime.now();
        }
    }

    public void setCriticality(int criticality){
        this.criticality = criticality;
    }

    public void setPlace(int place){
        this.place = place;
    }

    public void setTechnician(Technician technician){
        this.technician = technician;
    }

    @Override
    public String toString(){
        String string = this.id + "\t" + this.type + "\t" + this.status + "\t" + this.criticality + "\t" +  this.place + "\t" + this.dateReported.toString();
        if(this.technician == null){
            string += "\t" + "No technician assigned";
        } else{
            string += "\t" + this.technician.getName();
        }
        if(this.dateSolved == null){
            string += "\t" + "Not Solved Yet";
        } else{
            string += "\t" + this.dateSolved.toString();
        }
        return string;
    }
}