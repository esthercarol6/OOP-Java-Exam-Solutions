public class Patient {
    private String patientId;
    private String allergy;

    public Patient(String patientId, String allergy) {
        this.patientId = patientId; // ‘this’ resolves conflict
        this.allergy = allergy;
    }
}