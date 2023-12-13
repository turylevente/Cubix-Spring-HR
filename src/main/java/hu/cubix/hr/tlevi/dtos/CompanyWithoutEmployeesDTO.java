package hu.cubix.hr.tlevi.dtos;

public class CompanyWithoutEmployeesDTO {
    private long id;
    private long registationNumber;
    private String name;
    private String adress;

    public CompanyWithoutEmployeesDTO() {
    }

    public CompanyWithoutEmployeesDTO(long id, int registationNumber, String name, String adress) {
        this.id = id;
        this.registationNumber = registationNumber;
        this.name = name;
        this.adress = adress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRegistationNumber() {
        return registationNumber;
    }

    public void setRegistationNumber(int registationNumber) {
        this.registationNumber = registationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}

