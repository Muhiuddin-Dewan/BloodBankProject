package BloodBankProject;

public class User {
    private int id;
    private String name;
    private String bloodGroup;
    private String address;
    private String mobileNumber;

    public User(int id, String name, String bloodGroup, String address, String mobileNumber) {
        this.setId(id);
        this.setName(name);
        this.setBloodGroup(bloodGroup);
        this.setAddress(address);
        this.setMobileNumber(mobileNumber);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

    // Add getters and setters as needed
}
