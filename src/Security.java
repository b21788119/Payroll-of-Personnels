public class Security extends Personnel
{
    public Security(String name, String surname, String registrationNumber, String position, String yearOfStart) {
        super(name, surname, registrationNumber, position, yearOfStart);
        // For transportation,they are paid 5$ everyday.
        setTransMoney(5d);
        // For food,they are paid 10$ everyday.
        setFoodMoney(10d);
    }

    @Override
    double calculateTotalSalary(int[] workingPlan) {
        // Security personnel do not work one day of the week.
        double total = 0d;
        for(int hours : workingPlan){
            if(hours >= 30 && hours <= 54){
                // Securities can work minimum 30 hours and maximum 54 hours in a week.
                total+= (6 * (getFoodMoney()+getTransMoney())) + (hours*10d);
            }
            else if(hours > 54)
            {   // If they work more than 54 hours,they can not get extra money.
                total+= (6 * (getFoodMoney()+getTransMoney())) + (540d);
            }
        }
        setTotalSalary(getSeverancePay() + total);
        return getTotalSalary();
    }
}
