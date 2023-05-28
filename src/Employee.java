public class Employee extends Personnel
{
    public Employee(String name, String surname, String registrationNumber, String position, String yearOfStart) {
        super(name, surname, registrationNumber, position, yearOfStart);
    }

    @Override
    double calculateTotalSalary(int[] workingPlan) {
        // They get severancePay as default.
        setTotalSalary(getSeverancePay());
        return getTotalSalary();
    }
}



