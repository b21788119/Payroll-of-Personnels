public abstract class Personnel {
    // Common attributes
    private String name;
    private String surname;
    private String registrationNumber;  //Unique for all personnel
    private String position;
    private String yearOfStart;
    // Salaries will be calculated by working plans.
    private int workingPlan[];
    private double baseSalary;
    private double ssBenefits;
    private double severancePay;
    private double overWorkSalary;
    private double transMoney;
    private double foodMoney;
    private double totalSalary = 0d;

    public Personnel(String name,String surname,String registrationNumber,String position,
                    String yearOfStart)
    {
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registrationNumber;
        this.position = position;
        this.yearOfStart = yearOfStart;
        this.workingPlan = new int[4];
        // Formula is same for all personnel.
        this.severancePay = (2020 - Integer.parseInt(yearOfStart)) * 20 * 0.8;
    }
    // An abstract method to calculate salary.
    abstract double calculateTotalSalary(int workingPlan[]);

    public String display()
    {
        // This function will be used while printing output for each active personnel in the university.
        String info = "";
        info+=  "Name : "+ this.name+"\n"+"Surname : "+ this.surname +"\n" +
                "Registration Number : " + this.registrationNumber +"\n" +
                "Position : "+ this.position + "\n"+ "Year of Start : " + this.yearOfStart +
                "\n" + "Total Salary : "+ this.totalSalary + "$\n";
        return info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getYearOfStart() {
        return yearOfStart;
    }

    public void setYearOfStart(String yearOfStart) {
        this.yearOfStart = yearOfStart;
    }

    public int[] getWorkingPlan() {
        return workingPlan;
    }

    public void setWorkingPlan(int[] workingPlan) {
        this.workingPlan = workingPlan;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getSsBenefits() {
        return ssBenefits;
    }

    public void setSsBenefits(double ssBenefits) {
        this.ssBenefits = ssBenefits;
    }

    public double getSeverancePay() {
        return severancePay;
    }

    public void setSeverancePay(double severancePay) {
        this.severancePay = severancePay;
    }

    public double getOverWorkSalary() {
        return overWorkSalary;
    }

    public void setOverWorkSalary(double overWorkSalary) {
        this.overWorkSalary = overWorkSalary;
    }

    public double getTransMoney() {
        return transMoney;
    }

    public void setTransMoney(double transMoney) {
        this.transMoney = transMoney;
    }

    public double getFoodMoney() {
        return foodMoney;
    }

    public void setFoodMoney(double foodMoney) {
        this.foodMoney = foodMoney;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }
}

