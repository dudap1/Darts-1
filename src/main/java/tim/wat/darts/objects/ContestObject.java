package tim.wat.darts.objects;

public class ContestObject {
    private Long id;
    private String contestName;
    private String playerLogin;

    public String getPlayerLogin() {
        return playerLogin;
    }

    public void setPlayerLogin(String playerLogin) {
        this.playerLogin = playerLogin;
    }

    public ContestObject(Long id, String contestName) {
        this.id=id;
        this.contestName = contestName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }




}
