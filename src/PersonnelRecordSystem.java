import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class PersonnelRecordSystem
{
    // HashMap to access all personnel by their registration number.
    private HashMap<String,Personnel> allPersonnel;
    private FileReader databaseReader;

    public PersonnelRecordSystem()
    {
        // Initializing variables here.
        this.allPersonnel = new HashMap<>();
        this.databaseReader = new FileReader();
    }
    public void run(String personnelList,String workingPlans)
    {
        // Arguments will be personnel.txt,monitoring.txt
        // Reading both files.
        String allPersonnel[] = this.databaseReader.ReadFile(personnelList);
        String allWorkingPlans[] = this.databaseReader.ReadFile(workingPlans);
        for(String line : allPersonnel)
        {
            // For this assignment,index of specific information will always be the same.
            String name = line.split("\\s+")[0];
            String surname = line.split("\\s+")[1];
            String registrationNumber = line.split("\\s+")[2];
            String position = line.split("\\s+")[3];
            String yearOfStart = line.split("\\s+")[4];
            Personnel newPersonnel;
            switch (position)
            {
                // Creating newPersonnel according to its position info.
                case "FACULTY_MEMBER":
                    newPersonnel = new FacultyMember(name,surname,registrationNumber,position,yearOfStart);
                    this.allPersonnel.put(registrationNumber,newPersonnel);
                    break;
                case "RESEARCH_ASSISTANT":
                    newPersonnel = new ResearchAssistant(name,surname,registrationNumber,position,yearOfStart);
                    this.allPersonnel.put(registrationNumber,newPersonnel);
                    break;
                case "OFFICER":
                    newPersonnel = new Officer(name,surname,registrationNumber,position,yearOfStart);
                    this.allPersonnel.put(registrationNumber,newPersonnel);
                    break;
                case "SECURITY":
                    newPersonnel = new Security(name,surname,registrationNumber,position,yearOfStart);
                    this.allPersonnel.put(registrationNumber,newPersonnel);
                    break;
                case "WORKER":
                    newPersonnel = new Worker(name,surname,registrationNumber,position,yearOfStart);
                    this.allPersonnel.put(registrationNumber,newPersonnel);
                    break;
                case "CHIEF":
                    newPersonnel = new Chief(name,surname,registrationNumber,position,yearOfStart);
                    this.allPersonnel.put(registrationNumber,newPersonnel);
                    break;
                case "PARTTIME_EMPLOYEE":
                    newPersonnel = new PartTimeEmployee(name,surname,registrationNumber,position,yearOfStart);
                    this.allPersonnel.put(registrationNumber,newPersonnel);
                    break;
            }
        }
        for(String plan : allWorkingPlans)
        {
            int workingPlan[] = new int[4];
            String registrationNumber = plan.split("\\s+")[0];
            // Creation of workingPlan.
            workingPlan[0] = Integer.parseInt(plan.split("\\s+")[1]);
            workingPlan[1] = Integer.parseInt(plan.split("\\s+")[2]);
            workingPlan[2] = Integer.parseInt(plan.split("\\s+")[3]);
            workingPlan[3] = Integer.parseInt(plan.split("\\s+")[4]);
            // Retrieve appropriate personnel from our map and update it.
            Personnel personnel = this.allPersonnel.get(registrationNumber);
            personnel.calculateTotalSalary(workingPlan);
            this.allPersonnel.replace(registrationNumber,personnel);
        }
    }

    public void displayAllPersonnel()
    {
        // Creating an output file for each active personnel in the system.
        for(Personnel p : this.allPersonnel.values()){
            String fileName = p.getRegistrationNumber()+".txt";
            File file = new File(fileName);
            try {
                FileWriter writer = new FileWriter(fileName);
                writer.write(p.display());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
class ResearchAssistant extends Academician
{
    public ResearchAssistant(String name,String surname,String registrationNumber,String position,
                             String yearOfStart)
    {
        super(name,surname,registrationNumber,position,yearOfStart);
        // Calculation formula of ssBenefits for Research Assistants.
        setSsBenefits((getBaseSalary() / 100)*105);
        // Setting totalSalary by using override method.
    }

    @Override
    public double calculateTotalSalary(int workingPlan[])
    {
        // Research Assistants do not have overWorkSalary.
        setTotalSalary(super.calculateTotalSalary(workingPlan) + getSsBenefits());
        return getTotalSalary();
    }
}

class FacultyMember extends Academician
{
    // Extra attribute.Same as overWorkSalary.
    private double addCourseFee;
    public FacultyMember(String name,String surname,String registrationNumber,String position,
                         String yearOfStart)
    {
        super(name,surname,registrationNumber,position,yearOfStart);
        // Calculation formula is different from the formula of research assistants.
        setSsBenefits((getBaseSalary() / 100)*135);
        this.addCourseFee = 0d;
    }
    @Override
    public double calculateTotalSalary(int workingPlan[]) {
        // BaseSalary + severancePay
        double currentTotal = super.calculateTotalSalary(workingPlan);
        for(int hours : workingPlan)
        {
            if(hours > 40 && hours % 40 <= 8){
                // For each extra working hour,adding 20$
                this.addCourseFee += ((hours%40) *20d);
            }
            else if(hours > 40 && hours % 40 > 8)
            {
                // If a faculty member works more than 48 hours
                this.addCourseFee += 160d;
            }

        }
        setOverWorkSalary(this.addCourseFee);
        currentTotal+= (getOverWorkSalary() + getSsBenefits());
        // Total salary is calculated.
        setTotalSalary(currentTotal);
        return  getTotalSalary();
    }
}
class FullTimeEmployee extends Employee
{
    // Salary will be calculated based on the dayOfWork.
    private int dayOfWork;
    public FullTimeEmployee(String name, String surname, String registrationNumber, String position, String yearOfStart) {
        super(name, surname, registrationNumber, position, yearOfStart);
        this.dayOfWork = 0;
    }

    public int getDayOfWork() {
        return dayOfWork;
    }

    public void setDayOfWork(int dayOfWork) {
        this.dayOfWork = dayOfWork;
    }
}
class PartTimeEmployee extends Employee
{
    public PartTimeEmployee(String name, String surname, String registrationNumber, String position, String yearOfStart) {
        super(name, surname, registrationNumber, position, yearOfStart);
    }

    @Override
    double calculateTotalSalary(int[] workingPlan) {
        double total = 0d;
        // A Part-time Employee can work minimum 10 hours and maximum 20 hours in a week.
        for(int hours : workingPlan){
            if(hours >= 10 && hours <= 20){
                total+= hours*18d;
            }
            else if(hours > 20){
                total+= 360d;
            }
            // If they work less than 10 hours in a week,they do not get any salary for that week.
        }
        // severancePay + total
        setTotalSalary(super.calculateTotalSalary(workingPlan) + total);
        return getTotalSalary();
    }
}
class Worker extends FullTimeEmployee
{
    public Worker(String name, String surname, String registrationNumber, String position, String yearOfStart) {
        super(name, surname, registrationNumber, position, yearOfStart);
        // Workers do not work at weekends.
        setDayOfWork(20);
        setOverWorkSalary(0d);
    }

    @Override
    double calculateTotalSalary(int[] workingPlan) {
        // Workers work 40 hours (5 days) in a week.We are ignoring the condition of working less than 40 hours here.
        double extra = 0d;
        for(int hours : workingPlan){
            // If they work more than 40 hours and less than 51 hours they are paid overWorkSalary.
            if(hours > 40 && hours % 40 <= 10){
                extra+= hours%40 * 11d;
            }
            // If a worker works more than 50 hours in a week,he is not paid extra money..
            else if(hours > 40 && hours % 40 > 10){
                extra+= 110d;
            }
        }
        setOverWorkSalary(extra);
        // They are paid 105$ for each day of work.
        setTotalSalary(super.calculateTotalSalary(workingPlan) + getDayOfWork()*105 + getOverWorkSalary());
        return getTotalSalary();
    }
}
class Chief extends FullTimeEmployee
{
    public Chief(String name, String surname, String registrationNumber, String position, String yearOfStart) {
        super(name, surname, registrationNumber, position, yearOfStart);
        setDayOfWork(20);
        setOverWorkSalary(0d);
    }
    @Override
    double calculateTotalSalary(int[] workingPlan) {
        // Same calculations as workers.The only difference is payment amount.
        double extra = 0d;
        for(int hours : workingPlan){
            if(hours > 40 && hours % 40 <= 8){
                extra+= hours%40 * 15d;
            }
            else if(hours > 40 && hours % 40 > 8){
                extra+= 8*15d;
            }
        }
        setOverWorkSalary(extra);
        setTotalSalary(super.calculateTotalSalary(workingPlan) + getDayOfWork()*125 + getOverWorkSalary());
        return getTotalSalary();
    }
}

class FileReader
{
    public String[] ReadFile(String path){
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];
            for (String line:Files.readAllLines(Paths.get(path))){
                results[i++]=line;
            }
            return results;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}