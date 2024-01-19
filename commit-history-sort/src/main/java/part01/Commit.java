package part01;

public class Commit {
	
	private String commitBody;
	private String contributor;
	private String commitId;
	private String parentId;
	

	
	public Commit(String commitText, String contributor, String commitId, String parentId) {
		this.commitBody = commitText;
		this.contributor = contributor;
		this.commitId = commitId;
		this.parentId = parentId;
	}
	
	public Commit(String commitText) {
		this.commitBody = commitText;
		this.contributor = "";
		this.commitId = "";
		this.parentId = "";
	}

	public String getCommitBody() {
		return commitBody;
	}
	
	public void setCommitBody(String commitText) {
		this.commitBody = commitText;
	}
	
	public String getContributor() {
		String[] words = commitBody.split(" ");
		return words[3];
	}

	public void setContributor(String contributor) {
		this.contributor = contributor;
	}

	public String getCommitId() {
		commitId = commitBody.substring(7, 13);		
		return commitId;
	}
	
	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}
	
	public String getParentId() {
		int index1 = commitBody.indexOf("(Parent:");
		int index2 = commitBody.indexOf(")");
		String parentIdString = commitBody.substring(index1, index2);
		String[] words = parentIdString.split(" ");
		String parentId = words[1];
		return parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	
	
	
	
	
	
    /* <pre>
    * Commit d4e5f6 by ohjelmointi2 (Parent: a1b2c3)
    * This is my comment message!
    * This is the second line of the message.
    </pre>
    */
	
	
}
