package models;



public class ModelUsers {
    String name;

    public ModelUsers() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return location;
    }

    public void setUid(String location) {
        this.location = location;
    }

    public ModelUsers(String name, String email, String image, String location) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.location = location;
    }

    String email;

    String image;

    String location;
}

