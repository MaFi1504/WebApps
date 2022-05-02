package Database;

public class User {


    private long id;
    private String name;
    private String profilPath;


    public String getName(){return name;}

    public void setName(String  name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfilPath(){return profilPath;}

    public void setProfilPath(String  profilPath) {
        this.profilPath = profilPath;
    }



}
