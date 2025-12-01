import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public class App{
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Incident> incidents = new ArrayList<>();
    public static ArrayList<Technician> technicians = new ArrayList<>();
    public static int amountComputers = 61;
    public static String incidentAttributes = "Id \t Type \t Status \t Criticality \t Place \t Date Reported \t Technician \t Date Solved";
    public static String techniciansAttributes = "Id \t Name \t Incidents Solved";

    // checa si existe un incidente mediante su id y lo devuelve si es el caso
    public static Incident checkIncident(int incidentId){
        Incident incidentSelected = null;

        for(Incident incident : incidents){
            if(incident.getId() == incidentId){
                incidentSelected = incident;
            }
        }
        
        if(incidentSelected != null){
            return incidentSelected;
        } else{
            return null;
        }
    }

    // checa si existe un tecnico mediante su id y lo devuelve si es el caso
    public static Technician checkTechnician(int technicianId){
        Technician technicianSelected = null;

        for(Technician technician : technicians){
            if(technician.getId() == technicianId){
                technicianSelected = technician;
            }
        }
        
        if(technicianSelected != null){
            return technicianSelected;
        } else{
            return null;
        }
    }
    
    // imprime los incidentes y sus atributos
    public static void listIncidents(){
        String out = "";
        System.out.println(incidentAttributes);
        for (Incident incident : incidents) {
            out += "\n" + incident.toString();
        }
        System.out.println(out);
    }

    public static void filterMenu(){
        int option;
        boolean stop1 = false;
        do{
            System.out.println("1. Filter through Status");
            System.out.println("2. Filter through Types");
            System.out.println("3. Sort through Criticality");
            System.out.println("4. Filter through Computer Number");
            System.out.println("5. Filter through Assgined Technician");
            System.out.println("0. Go back");
            option = scanner.nextInt();
            switch(option){
                case 1:
                    System.out.println("Filter through status:");
                    System.out.println("1. ACTIVE");
                    System.out.println("2. PENDING");
                    System.out.println("3. RESOLVED");
                    int optionStatus = scanner.nextInt();
                    switch(optionStatus){
                        case 1:
                            statusFilter(Status.ACTIVE);
                            break;
                        case 2:
                            statusFilter(Status.PENDING);
                            break;
                        case 3:
                            statusFilter(Status.RESOLVED);
                            break;
                        default:
                            System.out.println("Incorrect input");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Filter through types:");
                    System.out.println("1. HARDWARE");
                    System.out.println("2. SOFTWARE");
                    System.out.println("3. NETOWRK");
                    int optionType = scanner.nextInt();
                    switch(optionType){
                        case 1:
                            typeFilter(Type.HARDWARE);
                            break;
                        case 2:
                            typeFilter(Type.SOFTWARE);
                            break;
                        case 3:
                            typeFilter(Type.NETWORK);
                            break;
                        default:
                            System.out.println("Incorrect input");
                            break;
                    }
                    break;

                case 3:
                    sortCriticality(incidents);
                    break;

                case 4:
                    System.err.println("Input the Computer Number: ");
                    int place = scanner.nextInt();
                    if(place < 0 || place > amountComputers){
                            System.out.println("Incorrect input");
                    } else{
                        placeFilter(place);
                    }
                    break;

                case 5:
                    listTechnicians();
                    System.err.println(" \n Input the Technician's Id: ");
                    Technician technician = checkTechnician(scanner.nextInt());
                    if(technician!= null){
                        System.out.println(incidentAttributes);
                        System.out.println(technician.getIncidents());
                    } else{
                        System.out.println("No Technician with that Id exists");
                    }
                    break;

                case 0:
                    System.out.print("Going back...");
                    stop1 = true;
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }while(stop1 == false);
    }

    public static void statusFilter(Status status){
        int amountResults = 0;
        System.out.println(incidentAttributes);

        for (Incident incident : incidents) {
            if(incident.getStatus() == status){
                ++amountResults;
                System.out.println(incident.toString());
            }
        }

        if (amountResults > 0) {
            System.out.println("The amount of " + status + " incidents is " + amountResults);
        } else{
            System.out.println("No " + status + " incidents in the classroom");
        }
    }

    public static void typeFilter(Type type){
        int amountResults = 0;
        System.out.println(incidentAttributes);

        for (Incident incident : incidents) {
            if(incident.getType() == type){
                ++amountResults;
                System.out.println(incident.toString());
            }
        }

        if (amountResults > 0) {
            System.out.println("The amount of " + type + " incidents is " + amountResults);
        } else{
            System.out.println("No " + type + " incidents in the classroom");
        }
    }

    public static void sortCriticality(ArrayList<Incident> incidents){
        ArrayList<Incident> criticalList = incidents;
        // compara los enteros de dos objetos del arreglo.
        Collections.sort(criticalList, (i1, i2) -> Integer.compare(i1.getCriticality(), i2.getCriticality())); 
        String out = "";
        System.out.println(incidentAttributes);
        for (Incident incident : criticalList) {
            out += "\n" + incident.toString();
        }
        System.out.println(out);
    }

    public static void placeFilter(int place){
        int amountResults = 0;
        System.out.println(incidentAttributes);

        for(Incident incident : incidents){
            if(incident.getPlace() == place){
                System.out.println(incident.toString());
            }
        }
        System.out.println("The amount of incidents in Computer No." + place + " is " + amountResults);
    }
    
    //imprime la descripcion de un incidente segun su id
    public static void readDescription(){
        Incident selectedIncident = null;
        do{
            System.out.println("Input the Id of the Incident Report You Wish To Read");
            int idIncident = scanner.nextInt();
            if(checkIncident(idIncident) != null){
                selectedIncident = checkIncident(idIncident);
                System.out.println(selectedIncident.getDescription());
            } else{
                System.out.println("Invalid Id, Try Again");
            }
        } while (selectedIncident == null);
    }

    //menu para reportar incidentes
    public static void reportIncident(){
        boolean exit = false;
        Type type = Type.HARDWARE;
        int place;
        String description;
        
        do { 
            System.out.println("What type of incident is it?");
            System.out.println("1. HARDWARE");
            System.out.println("2. SOFTWARE");
            System.out.println("3. NETWORK");
            System.out.println("4. Go back");

            int option = scanner.nextInt();

            switch(option){
                case 1:
                    type = Type.HARDWARE;
                    break;
                case 2:
                    type = Type.SOFTWARE;
                    break;
                case 3:
                    type = Type.NETWORK;
                    break;
                case 4:
                    System.out.println("Going back...");
                    exit = true;
                    break;
                default:
                    System.out.println("Incorrect Input");
                    break;
            }

            if(exit == false){
                boolean stop = false;
                do {
                    System.out.println("Input your Computer's Number: ");
                    place = scanner.nextInt();
                    if (place > amountComputers || place < 1){
                        System.out.println("No computer with that number");
                    } else{
                        stop = true;
                    }
                } while (stop == false);

                System.out.println("Describe the issue: ");
                scanner.nextLine(); // Limpia el scanner
                description = scanner.nextLine(); // Recibe la descripcion

                Incident incident = new Incident(description, type, place);
                incidents.add(incident);

                System.out.println("Incident no. " + incident.getId() + " succesfully reported");
            }
        } while (exit == false);
    }

    // asigna una criticidad a un incidente segun su Id
    public static void assignCriticality(){
        Incident selectedIncident = null;
        int criticality = 0;

        do { 
            System.out.println("Input the Id of the Incident: ");
            int incidentId = scanner.nextInt();
            
            if(checkIncident(incidentId) == null){
                System.out.println("Incident not found");
            } else{
                selectedIncident = checkIncident(incidentId);
            }
        } while (selectedIncident == null);

        do {
            System.out.println("Set the criticality of the Incident on a Scale from 1 to 5:");
            criticality = scanner.nextInt();

            if(criticality < 1 || criticality > 5){
                System.out.println("The criticality must only be set on a Scale from 1 to 5:");
            }
            
        } while (criticality < 1 || criticality > 5); // la criticidad va del 1 al 5

        selectedIncident.setCriticality(criticality);
    }

    // crea un tecnico nuevo
    public static void addTechnician(){
        String name;
        System.out.println("Input the Name of the Technician: ");
        scanner.nextLine(); // limpiar scanner
        name = scanner.nextLine();

        Technician technician1 = new Technician(name);
        technicians.add(technician1);

        System.out.println("Technician Successfully Registered");
    }
    // lista todos los tecnicos en el array
    public static void listTechnicians(){
        String out = "";
        System.err.println(techniciansAttributes);
        for (Technician technician : technicians) {
            out += "\n" + technician.toString();
        }
        System.out.print(out);
    }

    //asigna un tecnico a un incidente segun su Id
    public static void assignTechnician(){
        Incident selectedIncident = null;
        Technician assignedTechnician = null;
        do{
            System.out.println("\n Input the Id of the Incident you wish to Assign a Technician to: ");
            int idIncident = scanner.nextInt();

            if(checkIncident(idIncident) == null){
                System.out.println("Incident not Found");
            } else{
                selectedIncident = checkIncident(idIncident);
            }
        } while(selectedIncident == null);

        do{
            System.out.println("Input the Id of the Technician: ");
            int idTechnician = scanner.nextInt();

            if(checkTechnician(idTechnician) == null){
                System.out.println("Technician not Found");
            } else{
                assignedTechnician = checkTechnician(idTechnician);
            }
        } while(assignedTechnician == null);
        
        assignedTechnician.assignToIncident(selectedIncident);
        selectedIncident.setTechnician(assignedTechnician);
        selectedIncident.setStatus(Status.PENDING);
        System.out.println("Technician " + assignedTechnician.getName() + " Successfully Assgined to Incident with Id " + selectedIncident.getId());
    }

    // devuele los incidentes activos en el salon
    public static void classroomReport(){
        int amountNotSolved = 0;
        System.out.println(incidentAttributes);

        for (Incident incident : incidents) {
            if(incident.getStatus() == Status.ACTIVE){
                ++amountNotSolved;
                System.out.println(incident.toString());
            }
        }

        if (amountNotSolved > 0) {
                System.out.println("The amount of ACTIVE incidents is " + amountNotSolved);
        } else{
            System.out.println("No ACTIVE incidents in the classroom");
        }
    }

    public static void main(String[] args) {
        boolean stop = false;

        do { 
            System.out.println("\n");
            System.out.println("<-<-<- Ticketing System ->->->");
            System.out.println("1. Report an Incident");
            System.out.println("2. Read a Report");
            System.out.println("3. See report history");
            System.out.println("4. Filter through reports");
            System.out.println("5. Classroom Report");
            System.out.println("6. Mark an Incident as Solved");
            System.out.println("7. Assign Criticality to an Incident");
            System.out.println("8. Register a Technician");
            System.out.println("9. List Technicians");
            System.out.println("10. Assign Technician to Incident");

            System.out.println("0. Exit");


            int option = scanner.nextInt();
            System.out.println("\n");
            switch(option){
                case 1:
                    reportIncident();
                    break;

                case 2:
                    listIncidents();
                    readDescription();
                    break;
                    
                case 3:
                    listIncidents();
                    break;

                case 4:
                    filterMenu();
                    break;

                case 5:
                    classroomReport();
                    break;

                case 6:
                    System.out.println("\n Select Id of Incident to Mark as Solved:");
                    int idSelected = scanner.nextInt();
                    for (Incident incident : incidents) {
                      if(incident.getId() == idSelected){
                        incident.setStatus(Status.RESOLVED);
                        System.out.println("Incident Marked As Solved.");
                        }
                    }
                    break;
                case 7:
                    assignCriticality();
                    break;

                case 8:
                    addTechnician();
                    break;

                case 9:
                    listTechnicians();
                    break;

                case 10:
                    listTechnicians();
                    assignTechnician();
                    break;

                case 0:
                    stop = true;
                    break;
                
                default:
                    System.out.println("Incorrect Input, Try Again");
            }
        } while (stop == false);
    }
}