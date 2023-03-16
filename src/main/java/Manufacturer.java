public class Manufacturer {
    private String groupName;
    private Country country;

    public Manufacturer(String groupName, Country country) {
        this.groupName = groupName;
        this.country = country;
    }

    public Country getCountry() {
        return null;
    }

    public String getGroupName() {
        return null;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return groupName + "\\" + country;
    }
}

