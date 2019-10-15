package cs.anu.edu.au.comp2100.weiming.object;

abstract class Subject {
    private String code;
    private String name;
    private Career career;
    private int unit;
    private int year;

    public Subject(String code, String name, Career career, int unit, int year) {
        this.code = code;
        this.name = name;
        this.career = career;
        this.unit = unit;
        this.year = year;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
