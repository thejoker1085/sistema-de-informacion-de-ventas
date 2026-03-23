package co.edu.uptc.pojo;

public enum Gender {
    MASCULINE, FEMININE;
    
    public String getDisplayValue() {
        return this == MASCULINE ? "M" : "F";
    }
}
