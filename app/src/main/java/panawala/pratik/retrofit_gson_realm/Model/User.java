package panawala.pratik.retrofit_gson_realm.Model;

import io.realm.RealmObject;

public class User extends RealmObject {
    private Integer id;
    private String email;
    private Integer status;
    private String displayName;
    //    private RealmList<Domain> domains;
    private Integer superUser;
    private Integer registrationType;
    public User() {
    }

    public User(Integer id, String email, Integer status, String displayName, Integer superUser, Integer registrationType) {
        this.id = id;
        this.email = email;
        this.status = status;
        this.displayName = displayName;
        this.superUser = superUser;
        this.registrationType = registrationType;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @return The domains
     */
//    public RealmList<Domain> getDomains() {
//        return domains;
//    }

    /**
     * @param domains The domains
     */
//    public void setDomains(RealmList<Domain> domains) {
//        this.domains = domains;
//    }

    /**
     * @param displayName The displayName
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return The superUser
     */
    public Integer getSuperUser() {
        return superUser;
    }

    /**
     * @param superUser The superUser
     */
    public void setSuperUser(Integer superUser) {
        this.superUser = superUser;
    }

    /**
     * @return The registrationType
     */
    public Integer getRegistrationType() {
        return registrationType;
    }

    /**
     * @param registrationType The registrationType
     */
    public void setRegistrationType(Integer registrationType) {
        this.registrationType = registrationType;
    }
}
