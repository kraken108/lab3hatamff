package p_jakdan_hjalmo.aktakurvan.Tokens;

/**
 * Created by Jakob on 2017-12-19.
 */

public class TokenItem {
    private String authorizedEntity;
    private String scope;
    private String token;

    public TokenItem(String authorizedEntity, String scope,String token) {
        this.authorizedEntity = authorizedEntity;
        this.scope = scope;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthorizedEntity() {
        return authorizedEntity;
    }

    public void setAuthorizedEntity(String authorizedEntity) {
        this.authorizedEntity = authorizedEntity;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
