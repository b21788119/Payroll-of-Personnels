public class Academician extends Personnel
{
    public Academician(String name,String surname,String registrationNumber,String position,
                       String yearOfStart)
    {
        // Using the constructor of the super class.
        super(name,surname,registrationNumber,position,yearOfStart);
        // Base Salary is constant and 2600$.
        setBaseSalary(2600d);
        // Calculation will be different for Research Assistants and Faculty Members.
        setSsBenefits(0d);
        setOverWorkSalary(0d);
    }
    @Override
    public double calculateTotalSalary(int workingPlan[])
    {
        // BaseSalary + severancePay will be used to calculate salaries of all Academicians.
        setTotalSalary(getBaseSalary()+getSeverancePay());
        return getTotalSalary();
    }
}


