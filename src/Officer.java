public class Officer extends Personnel
{

    public Officer(String name, String surname, String registrationNumber, String position, String yearOfStart) {
        super(name, surname, registrationNumber, position, yearOfStart);
        setBaseSalary(2600d);
        // Calculation of ssBenefits.
        setSsBenefits((getBaseSalary()/100)*65);
    }

    @Override
    double calculateTotalSalary(int workingHours[]) {
        // Total Salary = BaseSalary + ssBenefits + severancePay + overWorkSalary.
        double extra = 0d;
        for(int hours : workingHours){
            if(hours > 40 && hours % 40 <= 10){
                // They are paid 20$ for each extra working hour.
                extra+= (hours%40)*20;
            }
            else if(hours > 40 && hours % 40 > 10){
                // They can not work more than 50 hours.
                extra+= 10*20;
            }
        }
        // Setting overWorkSalary.
        setOverWorkSalary(extra);
        setTotalSalary(getBaseSalary()+getSsBenefits()+getSeverancePay()+getOverWorkSalary());
        // Total salary is calculated.
        return getTotalSalary();
    }
}
