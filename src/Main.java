public class Main {
    public static void main(String[] args) {
        String personnelFile = args[0];
        String monitoringFile = args[1];
        // Creating the system
        PersonnelRecordSystem system = new PersonnelRecordSystem();
        // Running the system and filling the database.
        system.run(personnelFile,monitoringFile);
        // Displaying all personnel and their data by using database.
        system.displayAllPersonnel();
    }
}
