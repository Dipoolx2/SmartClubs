package smartclubs.issues;

public abstract class Issue {
    public String code;
    public String description;
    public IssueCategory issueCategory;
    public IssueFix issueFix;

    public Issue(String code, String description, IssueCategory issueCategory) {
        this.code = code;
        this.description = description;
        this.issueCategory = issueCategory;
    }

}
